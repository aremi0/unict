/*
    Classico esempio di produttore-consumatore con una vabiabile condivisa che deve restare strettamente positiva.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>

int counter = 0;
pthread_cond_t for_resource;
pthread_mutex_t mutex;

void* prod(void* arg);
void* cons(void* arg);

#define D_CONS 4
#define D_PROD 2

int main(int argc, char** argv){
    pthread_t t_cons[D_CONS];
    pthread_t t_prod[D_PROD];

    srand(time(NULL));

    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&for_resource, NULL);

    for(int i = 0; i < D_PROD; i++)
        pthread_create(&t_prod[i], NULL, prod, NULL);

    for(int i = 0; i < D_CONS; i++)
        pthread_create(&t_cons[i], NULL, cons, NULL);
    

    pthread_exit(NULL);
}

void* prod(void* arg){
    for(int i = 0; ;i++){
        pthread_mutex_lock(&mutex);
        counter++;
        printf("PROD: %d\n", counter);
        pthread_cond_broadcast(&for_resource);
        pthread_mutex_unlock(&mutex);

        int delay = rand()%5001+13000;
        usleep(delay);
    }
}

void* cons(void* arg){
    for(int i = 0; ;i++){
        pthread_mutex_lock(&mutex);
        while(counter <= 0)
            pthread_cond_wait(&for_resource, &mutex);

        if(counter > 10){
            counter-=9;
            printf("\t\tCONS___BOOST-9: %d\n", counter);
        }
        else{
            counter--;
            printf("\t\tCONS___%d\n", counter);
        }
        pthread_mutex_unlock(&mutex);

        int delay = rand()%45001+10000;
        usleep(delay);
    }
}