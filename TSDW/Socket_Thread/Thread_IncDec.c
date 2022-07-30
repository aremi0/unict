/*
    Due thread tI e tD condividono una variabile 'int n' inizialmente uguale a 100, ed operano nel seguente modo:

        tI;
            Ciclicamente aspetta 100ms, se n > 150 termina, comunicandolo sullo standart output.
            Altrimenti la incrementa di un valore random compreso tra 0-9, comunicandolo sullo standart output.
            Se esegue un numero di cicli >1000 termina, comunicandolo sullo standart output.

        tD;
            Ciclicamente aspetta 300ms, se n < 80 termina, comunicandolo sullo standart output.
            Altrimenti la decrementa di un valore random compreso tra 0-9, comunicandolo sullo standart output.
            Se esegue un numero di cicli >1000 termina, comunicandolo sullo standart output.
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

int n = 100;
pthread_mutex_t mutex;


void* f1(void* arg){
    for(int i = 0; i < 1000; i++){
        usleep(100000);

        pthread_mutex_lock(&mutex);
            if(n <= 150){
                n += rand()%10;
                printf("[thread tI] : %d\n", n);
            }
            else{
                pthread_mutex_unlock(&mutex);
                printf("[thread tI] : FINISHED\n");
                pthread_exit(NULL);
            }
        pthread_mutex_unlock(&mutex);
    }

    printf("[thread tI] : EXCEEDED\n");
    pthread_exit(NULL);
}

void* f2(void* arg){
    for(int i = 0; i < 1000; i++){
        usleep(300000);

        pthread_mutex_lock(&mutex);
            if(n >= 80){
                n -= rand()%10;
                printf("\t\t\t[thread tD] : %d\n", n);
            }
            else{
                pthread_mutex_unlock(&mutex);
                printf("\t\t\t[thread tD] : FINISHED\n");
                pthread_exit(NULL);
            }
        pthread_mutex_unlock(&mutex);
    }

    printf("\t\t\t[thread tD] : EXCEEDED\n");
    pthread_exit(NULL);
}

int main(int argc, char** argv){
    pthread_t tI, tD;

    srand(time(NULL));
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&tI, NULL, f1, NULL);
    pthread_create(&tD, NULL, f2, NULL);

    pthread_exit(NULL);
}