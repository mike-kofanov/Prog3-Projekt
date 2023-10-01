//
// Created by mike_k on 18.10.22.
//
#include <stdio.h>


int ue4aufgabe7(void){
    int a[5] = {0, 1,2, 3, 4};
    int b[5];

    int* ptrB = b;
    for(int* ptrA = a + 4; ptrA != (a - 1); ptrA--, ptrB++){
        *ptrB = *ptrA;
    }

    ptrB = b;
    printf("[");
    for(int i = 0; i < 5; i++){
        if((i + 1) == 5){
            printf(" %d ]", *ptrB);
        } else {
            printf(" %d, ", *ptrB);
        }
        ptrB++;
    }

    return 0;
}