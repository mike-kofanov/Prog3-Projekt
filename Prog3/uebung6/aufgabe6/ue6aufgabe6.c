//
// Created by mike_k on 03.11.22.
//
#include <stdio.h>
#include <stdbool.h>

long double calcMwStForNetto(long double d) {
    printf("Mwst. vom Netto: ");
    return d * (long double)0.19;
}

long double calcBruttoForNetto(long double d){
    printf("Brutto vom Netto: ");
    return d + d * (long double)0.19;
}

long double calcNettoForBrutto(long double d){
    printf("Netto vom Bruto: ");
    return d - d * (long double)0.19;
}

long double (*funcPtr[3])() = {calcMwStForNetto, calcBruttoForNetto, calcNettoForBrutto};


int ue6aufgabe6(){
    int index;
    long double value;
    while (true){
        printf("Ihre Eingabe\n"
               "\t<funktion> [<betrag>]\n"
               "Bedeutung von <funktion>: 0=Mwst. vom Netto, 1=Brutto vom Netto, 2=Netto vom Brutto, 3=Ende\n"
               "z. B. 0 99.95 (für die Berechnung der Mehrwertsteuer von 99.95 netto)\n"
               "Ihre Eingabe bitte: ");
        scanf(" %d %Lf", &index, &value);
        if(index == 3) {
            break;
        } else {
            printf("%.2Lf\n", (*funcPtr[index])(value));
        }
    }
    return 0;
}