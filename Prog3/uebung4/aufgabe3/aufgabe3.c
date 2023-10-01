//
// Created by mike_k on 18.10.22.
//
#include <stdio.h>

void tausche_intPtr(int** i, int** j){ //Pointer Adressen werden getauscht. Geht, weil selber Datentyp
    int* temp = *i;
    *i = *j;
    *j = temp;
}

void tausche_int(int* i, int* j){ // Wert der Pointer werden getauscht
    int temp = *i;
    *i = *j;
    *j = temp;
}

int aufgabe3(void){
    int i = 1; int j = 2;
    printf("i = %d, j = %d\n", i, j); /* Gibt 1 und 2 aus */
    tausche_int(&i, &j); // Adresse des Pointers wird übergeben.
    printf("i = %d, j = %d\n", i, j); /* Gibt 2 und 1 aus */

    int* iPtr = &i;
    int* jPtr = &j;
    tausche_intPtr(&iPtr, &jPtr);
    printf("Adressentausch: i = %d, j = %d\n", *iPtr, *jPtr); /* Gibt 2 und 1 aus */
    return 0;
}