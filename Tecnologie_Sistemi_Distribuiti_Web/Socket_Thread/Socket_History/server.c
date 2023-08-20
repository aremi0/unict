#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

#define PORT 7777
#define MAXQUEUE 1
#define DIM 1024
char history[DIM] = {0};
const char* show = "SHOW";

char* list(char* s);

int main(int argc, char** argv){
    struct sockaddr_in server, client;
    socklen_t clientLen = sizeof(client);
    int retcode, sockfd, newSockfd = 0;
    char buffer[DIM] = {0};

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

        printf("Connessione stabilita [%s, %d]\n", inet_ntoa(client.sin_addr), ntohs(client.sin_port));

        //Andrebbe in un while...
        memset(&buffer, 0, DIM);
        retcode = read(newSockfd, &buffer, DIM-1);
        if(retcode < 0){
            perror("read");
            exit(-1);
        }
        else if(retcode == 0){
            printf("Connessione chiusa dal client\n");
            close(newSockfd);
            continue;
        }

        buffer[retcode] = '\0';
        printf("%d byte ricevuti: %s\n", retcode, buffer);

        //Preparo risposta
        strcpy(buffer, list(buffer));
        retcode = write(newSockfd, &buffer, strlen(buffer));
        if(retcode < 0){
            perror("write");
            exit(-1);
        }

        close(newSockfd);
    }
    close(sockfd);
}

char* list(char* s){
    if(strncmp(show, s, strlen(show))){
        strcat(history, ";");
        strcat(history, s);
        return "OK\n";
    }

    strcat(history, "\n");
    return history;
}