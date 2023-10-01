//
// Created by mike_k on 12.10.22.
//
#include <stdio.h>
int aufgabe7(void){
    int i;
    int* ip;
    printf("Eingabe: ");
    ip = &i;
    scanf("%d", ip);
    printf("Eingabe war %d\n", *(ip));
    return 0;
}