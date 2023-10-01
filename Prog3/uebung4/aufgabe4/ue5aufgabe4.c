//
// Created by mike_k on 18.10.22.
//
#include <stdio.h>

int aufgabe4(void){
    int a[]= { 1, 5, 19, -4, 3 };
    int* p;
    int i;
    /* Lasse p auf das 0-te Array-Element verweisen. */
    p = a;
    /* Ihr Code hier */
    for (i=1; i<5; i++) {
        /* Prüfe, ob das Array-Element i größer als das von
        p referenzierte Element ist */
        if (*p < *(a + 1) ) {
            /* Lasse p auf das Array-Element i verweisen */
            p = (a + i);
            /* Ihr Code hier */
        }
    }
    /* Gib das von p referenzierte Element als das größte aus: */
    printf("Maximum: %d\n", *p );


    return 0;
}