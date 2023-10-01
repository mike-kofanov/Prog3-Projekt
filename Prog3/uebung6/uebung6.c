//
// Created by mike_k on 03.11.22.
//

#include <stdio.h>
#include "aufgabe1/ue6aufgabe1.h"
#include "aufgabe2/ue6aufgabe2.h"
#include "aufgabe6/ue6aufgabe6.h"


int uebung6(int argc, char* argv[]){
    int returnCode;
    //returnCode = ue6aufgabe1();
    //printf("\n");
    returnCode = ue6aufgabe2(argc, argv);
    //printf("\n");
    //returnCode = ue6aufgabe6();
    printf("\n");
    return returnCode;
}