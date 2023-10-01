//
// Created by mike_k on 18.10.22.
//

#include <stdio.h>
#include <time.h>

int ue3Aufgabe8(void){
    time_t timeArray[2] = { 645703200, 49888800 };
    printf("Datum 1: %s", ctime(&timeArray[0]));
    printf("Datum 2: %s", ctime(&timeArray[1]));
    return 0;
}