/*
    Due thread A e B dispongono di una variabile condivisa 'int x' ed uno stato interno 'int hit', ambo operano nel seguente modo:

        Ciclicamente:
            Aspetta 300ms. Se la variabile condivisa x>500, stampa 'hit' e termina.
            Altrimenti incrementa 'x' e la stampa.

*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

pthread_mutex_t mutex;

int x = 0;

void* func(void* arg){
    int t = (int) arg;
    char thread[128];

    if(t == 1)
        strcpy(thread, "[Thread A] ");
    else
        strcpy(thread, "\t\t\t[Thread B] ");


    for(int hit = 0; ; hit++){
        usleep(rand()%30000);
        pthread_mutex_lock(&mutex);
            if(x > 500){
                printf("%s hit : %d\n", thread, hit);
                pthread_mutex_unlock(&mutex);
                pthread_exit(NULL);
            }
            else{
                x++;
                printf("%s x : %d\n", thread, x);
            }
        pthread_mutex_unlock(&mutex);
    }
}

int main(int argc, char **argv){
    pthread_t tA, tB;

    srand(time(NULL));
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&tA, NULL, func, (void*)1);
    pthread_create(&tB, NULL, func, (void*)2);

    pthread_exit(NULL);
}