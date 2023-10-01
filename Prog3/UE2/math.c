#include <stdio.h>
#include "summe.h"
#include "differenz.h"

int berechne(void){
    printf("Ihre Wahl:\n<S>umme oder <D>ifferenz? ");
    char userInput;
    scanf("%c", &userInput);
    if(userInput == 'S'){
        printf("Ergebnis: %d\n", summe());
        return 0;
    } else if(userInput == 'D'){
        printf("Ergebnis: %d\n", differenz());
        return 0;
    } else {
        printf("User Eingabe ist ungueltig!\n");
        return -1;
    }
}

int main(void){
    return berechne();
}