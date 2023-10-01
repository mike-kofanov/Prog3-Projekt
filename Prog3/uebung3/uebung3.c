//
// Created by mike_k on 11.10.22.
//
#include "stdio.h"
#include "aufgabe6/aufgabe6.h"
#include "aufgabe7/aufgabe7.h"
#include "aufgabe2/aufgabe2.h"

int uebung3(){
    int returnCode;
    returnCode = aufgabe2();
    printf("\n");
    returnCode = aufgabe6();
    printf("\n");
    returnCode = aufgabe7();
    printf("\n");
    return returnCode;
}