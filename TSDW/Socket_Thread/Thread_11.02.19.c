/*
    Due thread operano nel seguente modo:

        thread1;
            Ciclicamente genera un numero randomico, se quanto è dispari si mette in attesa.
            Se invece è pari aspetta 300ms e stampa il numero generato.

        thread2;
            Ciclicamente risveglia il thread1 ogni 8 secondi. 
*/

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <pthread.h>

pthread_cond_t in_wait;
pthread_mutex_t mutex;

void* t1(void* arg);
void* t2(void* arg);

int main(int argc, char **argv){
    pthread_t thread1, thread2;

    srand(time(NULL));

    pthread_cond_init(&in_wait, NULL);
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&thread1, NULL, t1, NULL);
    pthread_create(&thread2, NULL, t2, NULL);

    pthread_exit(NULL);
}

void* t1(void* arg){
    int m = 0;

    for(int i = 0; ; i++){
        m = rand();

        if(m%2 == 1){ //Se è dispari...
            printf("[thread1] in attesa...\n");
            pthread_mutex_lock(&mutex); //La wait si può chiamare soltanto quando la mutex del thread è in lock, altrimenti
            pthread_cond_wait(&in_wait, &mutex); //segmentation fault...
            pthread_mutex_unlock(&mutex);
        }

        usleep(300000);
        printf("[thread1] %d\n", m);
    }
}

void* t2(void* arg){
    for(int i = 0; ; i++){
        pthread_cond_signal(&in_wait);
        sleep(2);
    }
}