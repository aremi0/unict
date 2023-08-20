/*
    Se la stringa inviata dal client è composta da un numero arbitrario di 'V', il server risponde con "Vero",
    altrimenti risponde con "Falso";
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 3333
#define MAXQUEUE 1
#define DIM 128

char* AND(char* check);

int main(int argc, char** argv){
    struct sockaddr_in server, client;
    socklen_t clientLen = sizeof(client);
    int sockfd, newSockfd, retcode = 0;
    char msg[DIM] = {0};

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

    printf("Server pronto\n");

    while(1){
        newSockfd = accept(sockfd, (struct sockaddr *) &client, &clientLen);
        if(newSockfd < 0){
            perror("accept");
            exit(-1);
        }

        printf("Client connesso [%s, %d]\n", inet_ntoa(client.sin_addr), ntohs(client.sin_port));

        memset(&msg, 0, DIM);

        //Andrebbe in un while...
        retcode = read(newSockfd, &msg, DIM-1);
        if(retcode < 0){
            perror("read");
            exit(-1);
        }
        else if(retcode == 0){
            printf("Connessione chiusa dal client\n");
            close(newSockfd);
            continue;
        }

        msg[retcode] = '\0';

        printf("%d byte ricevuti: %s\n", retcode, msg);


        //Preparo la risposta...
        strcpy(msg, AND(msg));
        retcode = write(newSockfd, &msg, strlen(msg));
        if(retcode < 0){
            perror("write");
            exit(-1);
        }

        close(newSockfd);
    }

    close(sockfd);
}

char* AND(char* check){
    for(int i = 0; i < strlen(msg)-2; i++){ //-2 perchè telnet manda due caratteri in più, ovvero \r\n;
        if(check[i] != 'V')
            return "Falso\n";
    }

    return "Vero\n";
}