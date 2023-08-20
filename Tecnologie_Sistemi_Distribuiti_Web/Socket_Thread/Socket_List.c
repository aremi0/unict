/*
    Il server mantiene uno stato interno tra tutti gli utenti, fino alla sua terminazione, tramite un array di stringhe 
    di 10 caratteri V.

    Se il server riceve la stringa "LIST" allora risponderà al client mandando V, formattando le stringhe nel seguente modo:
    "str1\nstr2\n..."

    Se il server riceve una qualsiasi altra stringa allora, se non è già presente in V, la inserisce in V.

    P.s. mi secca togliere tutti printf di debug, comunque il codice funziona.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

#define PORT 7777
#define MAXQUEUE 1
#define DIM 128
#define S 10

int handleMsg(char* msg){
    if(strncmp(msg, "LIST", strlen("LIST")) == 0){ //Se è arrivato "LIST"...
        printf("1___debug___handlemsg___strncmp___true___\n");
        return 1;
    }

    printf("2___debug___handlemsg___strncmp___false___\n");
    return 0;
}

char V[S][S] = {0};

int main(int argc, char** argv){
    struct sockaddr_in server, client;
    socklen_t clientLen = sizeof(client);
    int sockfd, newSockfd, retcode, v_cont = 0;
    char msg[DIM] = {0};
    char rsp[DIM] = {0};

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
        memset(&rsp, 0, DIM);

        //Andrebbe in un while...
        retcode = read(newSockfd, &msg, DIM-1);
        if(retcode < 0){
            perror("read");
            exit(-1);
        }

        msg[retcode] = '\0';
        printf("%d byte ricevuti: %s", retcode, msg);

        if(handleMsg(msg) == 1){ //E' arrivato "LIST"
            for(int i = 0; i < v_cont; i++){
                printf("3___debug___handlemsg___main-for___V[i]___%s\n", V[i]);
                strcat(rsp, V[i]);
                strcat(rsp, "\n");
            }

            //Resetto V[i]...
            memset(&V, 0, S);
            v_cont = 0;
            printf("4___debug___handlemsg___main-for-finish___rsp___\n%s", rsp);
        }
        else{ //Devo memorizzare/controllare la stringa arrivata...
            for(int i = 0; i < v_cont; i++){
                printf("5___debug___handlemsg___main-else-for-finish___V[i]___%s\n", V[i]);
                if(strncmp(V[i], msg, strlen(V[i])) == 0){ //Se la stringa è già presente...
                    printf("6___debug___handlemsg___main-else-for-finish___V[i]_-_msg__%s-%s\n", V[i], msg);
                    strcpy(rsp, "Presente\n");
                }
            }

            //Altrimenti inserisco la stringa in lista, se c'è spazio
            if(v_cont < S){
                strcpy(V[v_cont++], msg);
                printf("7___debug___handlemsg___main-if___V[v_cont-1]___%s\n", V[v_cont-1]);
                strcpy(rsp, "Inserito\n");
            }
            else{
                printf("8___debug___handlemsg___main-else____NON C'è SPAZIO\n");
                strcpy(rsp, "Non c'è spazio\n");
            }
        }

        //Preparo la risposta...
        retcode = write(newSockfd, &rsp, strlen(rsp));
        if(retcode < 0){
            perror("write");
            exit(-1);
        }

        close(newSockfd);
    }

    close(sockfd);
}