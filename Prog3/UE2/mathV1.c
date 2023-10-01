#include <stdio.h>
#include <string.h>

long calculate(long x, long y, char operation){
    long result;
    if(operation == 'S'){
        result = x + y;
    } else { // Is always 'D'
        result = x - y;
    }
    return result;
}

void userInputOutput(String firstOutput, String secondOutput, char operation){
        printf(firstOutput);
        long firstValue;
        scanf("%d", &firstValue);
        printf(secondOutput);
        long secondValue;
        scanf("%d", &secondValusecondSummande);
        printf("\nErgebnis: %d\n", calculate(x,y, operation));
}


void main(void){     
    printf("Ihre Wahl:\n<S>umme oder <D>ifferenz? ");
    char userInput;
    scanf("%c", &userInput);
    if(userInput == 'S'){
        userInputOutput("\nErster Summand: ", "\nErster Summand: ", 'S');
    } else if(userInput == 'D'){
        userInputOutput("\nMinuend: ", "\nSubtrahend: ", 'D');
    } else {
        printf("User Eingabe ist ungueltig!\n");
        return -1;
    }
    return 0;
}


int main(void) {
    berechne();
    return 0;
}