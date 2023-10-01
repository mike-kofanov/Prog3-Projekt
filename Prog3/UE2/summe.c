#include "input.h"

int summe(void){
    int firstSummand = get_input("Erster Summand: ");
    int secondSummand = get_input("Zweiter Summand: ");
    return firstSummand + secondSummand;
}