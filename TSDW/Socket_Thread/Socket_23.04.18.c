/*
    Il server esamina la stringa inviata dal client, se contiene singole cifre,
    es: "asd4asd5asd6"
            ^   ^   ^
            |   |   |
    il server somma tutte queste singole cifre in una variabile 'x'.
    Il server mantiene uno stato interno 'int n' che conta tutte le varie interazione con gli utenti.
    Lo stato si mantiene tra tutti gli utenti fino alla terminazione del server.
    Il server risponderà al client con un messaggio del tipo <n, x>;


    P.s. in questa soluzione c'è un errore nel caso di stringhe del tipo "asd45asd", ovvero che con due cifre consecutive,
    è risolvibile (penso) isolando il singolo char e passandolo poi ad atoi, anzichè fare direttamente atoi(msg[3])...
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <ctype.h>

#define PORT 3333
#define MAXQUEUE 1
#define DIM 128

int sommacifre(char* s);

int main(int argc, char** argv){
    struct sockaddr_in server, client;
    socklen_t clientLen = sizeof(client);
    int sockfd, newSockfd, retcode, n = 0;
    char buff[DIM] = {0};

    sockfd = socket(PF_INET, SOCK_STREAM, 0);
    if(sockfd < 0){
        perror("socket");
        exit(-1);
    }

    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);
    server.sin_addr.s_addr = INADDR_ANY;

    if(bind(sockfd, (struct sockaddr *) &server, sizeof(server)) < 0){
        perror("bind");
        exit(-1);
    }

    if(listen(sockfd, MAXQUEUE) < 0){
        perror("listen");
        exit(-1);
    }

    printf("Server Pronto\n");

    while(1){
        newSockfd = accept(sockfd, (struct sockaddr *) &client, &clientLen);
        if(newSockfd < 0){
            perror("accept");
            exit(-1);
        }

        printf("Client connesso [%s, %d]\n", inet_ntoa(client.sin_addr), ntohs(client.sin_port));

        memset(&buff, 0, DIM);

        //Andrebbe in un while...
        retcode = read(newSockfd, &buff, DIM-1);
        if(retcode < 0){
            perror("read");
            exit(-1);
        }
        else if(retcode == 0){
            printf("Connessione chiusa dal client\n");
            close(newSockfd);
            continue;
        }

        buff[retcode] = '\0';
        n++;

        printf("%d byte ricevuti: %s", retcode, buff);

        //Preparo la risposta...
        int x = sommacifre(buff);
        memset(&buff, 0, DIM);
        sprintf(buff, "%d,%d\n", n, x);
        retcode = write(newSockfd, &buff, strlen(buff));
        if(retcode < 0){
            perror("write");
            exit(-1);
        }

        close(newSockfd);
    }

    close(sockfd);
}

int sommacifre(char* s){
    int sum = 0;
    for(int i = 0; i < strlen(s); i++){
        if(isdigit((unsigned char)s[i]))
            sum += atoi(&s[i]);
    }

    return sum;
}