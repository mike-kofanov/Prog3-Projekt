//
// Created by mike_k on 26.10.22.
//
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define NAME_LEN 40

typedef struct {
    char name[NAME_LEN + 1];
    int personalnummer;
    float gehalt;
} angestellter;


void printAngestellter(angestellter** array, int index){
    if(array[index] != NULL){
        printf("Angestellter '%s' hat die Personalnummer '%d' und folgendes Gehalt '%.2f'\n",
               array[index]->name, array[index]->personalnummer, array[index]->gehalt);
    } else {
        printf("Angestellter existiert nicht.\n");
    }
}

void printAllAngestelle(angestellter** array, int length){
    for(int i = 0; i < length; i++){
        if(array[i] != NULL){
            printAngestellter(array, i);
        }
    }
}

void createAngestellter(angestellter** array, int index) {
    if(array[index] == NULL){
        array[index] = (angestellter*)malloc(sizeof(angestellter));
        printf("Bitte den Namen des Angestellten angeben: ");
        scanf(" %40[^\n]",   array[index]->name);
        printf("Bitte die Personalnummer des Angestellten angeben: ");
        scanf(" %d", &array[index]->personalnummer);
        printf("Bitte das Gehalt des Angestellten angeben: ");
        scanf(" %f", &array[index]->gehalt);
        printAngestellter(array, index);
    } else {
        free(array[index]);
        array[index] = NULL;
        createAngestellter(array, index);
    }
}

void deleteAngestellter(angestellter** array, int index) {
    if(array[index] != NULL){
        free(array[index]);
        array[index] = NULL;
    } else {
        printf("Es existiert keine Angestellter an diesem Index\n");
    }
}

int welcherAngesteller(){
    printf("Um welchen Angestellten handelt es sich?\nBitte Index zwischen '0' und '9' angeben: ");
    int index;
    scanf(" %d", &index);
    return index;
}


int ue5aufgabe2(void){
    angestellter *array[10] = { NULL };
    while(true){
        printf("Falls Sie einen neuen Angestellten anlegen moechten druecken Sie <n>\n"
               "Falls Sie einen Angestellten ausgeben moechten, druecken Sie <p>\n"
               "Falls Sie einen Angestellten loeschen moechten, druecken Sie <d>\n"
               "Falls Sie alle vorhandenen Angestellten ausgeben moechten, druecken Sie <a>\n"
               "Falls Sie das Programm beenden moechten, druecken Sie <q>\n"
               "Ihre Eingabe bitte: ");
        char command;
        scanf(" %c", &command);
        if(command == 'n'){
            int index = welcherAngesteller();
            createAngestellter(array, index);
        } else if(command == 'd'){
            int index = welcherAngesteller();
            deleteAngestellter(array, index);
        } else if(command == 'p') {
            int index = welcherAngesteller();
            printAngestellter(array, index);
        } else if(command == 'a'){
            printAllAngestelle(array, 10);
        } else if (command == 'q'){
            printf("Programm wird beendet...");
            break;
        }
    }
    return 0;
}