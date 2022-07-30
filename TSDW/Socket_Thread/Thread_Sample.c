/*
    Tre thread condividono una variabile 'int sample' inizialmente uguale a 50, ed operano tutti nel seguente modo:

        Ciclicamente generano un numero random compreso tra 10 e 90, se questo numero è uguale a sample
        termina, comunicandolo sullo standard output.
        Altrimenti sovrascriverà sample con tale valore randomico,
        comunicando sullo standard output <nome_thread, vecchio_sample, nuovo_sample>.

*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>

pthread_mutex_t mutex;

int sample = 50;

void* func(void* arg){
    int t = (int) arg;
    char thread[128];

    if(t == 1)
        strcpy(thread, "[Thread X]");
    else if(t == 2)
        strcpy(thread, "\t\t\t[Thread Y]");
    else
        strcpy(thread, "\t\t\t\t\t\t[Thread Z]");

    for(int i = 0; ; i++){
        int ran = rand()%81+10;

        pthread_mutex_lock(&mutex);
            if(ran == sample){
                printf("%s : TERMINATO\n", thread);
                pthread_mutex_unlock(&mutex);
                pthread_exit(NULL);
            }
            else{
                printf("%s : old sample<%d>, new sample<%d>\n", thread, sample, ran);
                sample = ran;
            }
        pthread_mutex_unlock(&mutex);
    }
}

int main(int argc, char **argv){
    pthread_t tX, tY, tZ;

    srand(time(NULL));

    pthread_mutex_init(&mutex, NULL);

    pthread_create(&tX, NULL, func, (void*)1);
    pthread_create(&tY, NULL, func, (void*)2);
    pthread_create(&tZ, NULL, func, (void*)3);

    pthread_exit(NULL);
}