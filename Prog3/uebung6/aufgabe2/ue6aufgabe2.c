//
// Created by mike_k on 03.11.22.
//
#include <stdio.h>
#include <errno.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool scanNum(char* argument, long* num){
    char* endptr = NULL;
    errno = 0;
    *num = strtol(argument, &endptr, 10); /* 10 = Dezimalsystem */
    if (strlen(endptr) == true) {
        printf("Kann '%s' nicht in Zahl umwandeln: Falsches Format\n", argument);
        return false;
    } else if (errno != 0) {
        printf("Kann '%s' nicht in Zahl umwandeln: %s\n", argument, strerror(errno));
        return false;
    }
    return true;
}

int ue6aufgabe2(int argc, char* argv[]){
    int returnCode = 0;
    if(argc != 3){
        printf("Benutzung: ./plus <zahl> <zahl>");
        return -1;
    }
    long a, b = 0;
    if(scanNum(argv[1], &a) && scanNum(argv[2], &b)){
        printf("%ld + %ld = %ld", a, b, a+b);
        return 0;
    } else {
        return -1;
    }
}