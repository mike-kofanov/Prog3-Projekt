//
// Created by mike_k on 26.10.22.
//
#include <stdio.h>
#include <string.h>

int ue5aufgabe1(){
    char passwort[32] = "GeHeIm";  /* Dem Programmbenutzer unbekannt */
    char eingabe[32] = "";

    /* Ggf. muessen Sie die Reihenfolge der beiden vorstehenden
       Array-Definitionen umkehren, um den unten beschriebenen Effekt
       zu beobachten (plattformabhaengig). Auf den Pool-PCs ist die obige
       Reihenfolge die richtig, um den gewuenschten Effekt zu beobachten.
    */

    printf("eingabe[] = %s\n", eingabe);
    printf("passwort[] = %s\n", passwort);

    printf("Bitte Passwort fuer den Hochsicherheitsbereich eingeben:\n");

    /* read all including spaces and tabs to the end of the line */
    scanf("%31[^\n]", eingabe); // Liest nur die ersten 31 Zeichen ein + '\0'
    for (int i=0; i<64; i++) {
        printf("%3d (%p): %02X %c\n",
               i, eingabe+i, (unsigned char)eingabe[i], eingabe[i]);
    }

    if (!strncmp(eingabe, passwort, strlen(passwort))) {
        printf("Passwort korrekt - Willkommen im Hochsicherheitsbereich!\n");
        return 0;
    } else {
        printf("Passwort falsch - Zugang verweigert!\n");
        return -1;
    }
}