extern int get_input(char prompt[]);
/*
1b)
Fehlermeldung:
    differenz.o: In function `get_input':
    differenz.c:(.text+0x0): multiple definition of `get_input'
    summe.o:summe.c:(.text+0x0): first defined here
    collect2: error: ld returned 1 exit status
    makefile:4: recipe for target 'math' failed

Übersetzungsphase der Fehlermeldung:
Linker

Ursache der Fehlermeldung:
Eine Funktion darf in C nur einmal definiert werden.
Wird die Funktion jedoch in der Header Datei definiert, so wird bei jeder Instanz wo die Header Datei 
von einer anderen C Datei eingebunden wird bei der Übersetzung jener C Datei die Funktion aus der Header Datei neu definiert.


1c)

superHeader.h
    #define PROJECT_NAME "Tolles Projekt"

subHeader.h
#include "superHeader.h"


Macros PROJECT_NAME wird an alle Dateien "vererbt", die superHeader.h includen!

*/