//
// Created by mike_k on 26.10.22.
//
#include <stdio.h>
#include <stdlib.h>

void free0(void** zeiger){ // Uebergabe einer dynamischen Speicher Adresse eines Pointers.
    if (zeiger != NULL) { // Check ob die Speicher Adresse nicht Null ist.
        free(*zeiger); //Uebergabe des Pointer an free()
        *zeiger = NULL; // Pointer wird auf Null
    }
}

int ue5aufgabe4(void){
    char* p = malloc(10);
    printf("%p\n", p); /* Ausgabe z. B. 0x470228 */
    free0(&p); // Uebergabe der Speicheradresse des Pointers
    printf("%p\n", p); /* Ausgabe 0x0 oder 0 oder (nil) o. ä. */
    return 0;
}