#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

#define ADDRESS "192.168.43.171"
#define PORT 7777
#define DIM 1024

int main(int argc, char** argv){
    struct sockaddr_in remote;
    int sockfd, retcode = 0;
    char buffer[DIM] = {0};

    if(argc != 2){
        printf("Usage: ./cl <msg>\n");
        return 0;
    }

    sockfd = socket(PF_INET, SOCK_STREAM, 0);
    if(sockfd < 0){
        perror("socket");
        exit(-1);
    }

    memset(&remote, 0, sizeof(remote));
    remote.sin_family = AF_INET;
    remote.sin_port = htons(PORT);
    inet_pton(AF_INET, ADDRESS, &(remote.sin_addr));

    if(connect(sockfd, (struct sockaddr *) &remote, sizeof(remote)) < 0){
        perror("connect");
        exit(-1);
    }

    printf("Connessione stabilita con %s\n", ADDRESS);

    retcode = write(sockfd, argv[1], sizeof(argv[1]));
    if(retcode < 0){
        perror("write");
        exit(-1);
    }

    printf("%d byte spediti\n", retcode);

    retcode = read(sockfd, &buffer, sizeof(buffer));
    if(retcode < 0){
        perror("read");
        exit(-1);
    }

    buffer[retcode] = '\0';

    printf("%d byte ricevuti: %s\n", retcode, buffer);

    close(sockfd);
}