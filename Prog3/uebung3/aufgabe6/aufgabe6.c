#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#define LAENGE 20

typedef struct {
    char value[LAENGE + 1]; // 21, weil letzter char '\0' ist !
    int lengthOfNumberWithoutLeadingZeros;
    int indexOfFirstCharWithoutLeadingZeros;
} int20;

int20 create20(char inputArray[]);
int20 getNewLengthOfNumWithoutLeadingZerosAndFirstIndexAfterLeadingZeros(int20 number);
int20 addition(int20 greaterNum, int20 shorterNum);
int20 add20(int20 a, int20 b);
void print20(int20 a);
int aufgabe6(void);

int20 create20(char inputArray[]) {
    int20 number;
    int i = 0;
    for(; i < LAENGE; i++){
        number.value[i] = '0';
    }
    number.value[i] = '\0'; // Letzte Stelle wird auf '\0' gesetzt!
    number.lengthOfNumberWithoutLeadingZeros =  strlen(inputArray);
    int indexOfFirstCharWithoutLeadingZeros = -1;
    int inputIterator = number.lengthOfNumberWithoutLeadingZeros - 1;
    for(int index = LAENGE - 1; index >= 0; index--){
        if(inputIterator >= 0){
            number.value[index] = inputArray[inputIterator];
            inputIterator--;
        } else{
            indexOfFirstCharWithoutLeadingZeros = index + 1;
            break;
        }
    }
    number.indexOfFirstCharWithoutLeadingZeros = indexOfFirstCharWithoutLeadingZeros;
    return number;
}

int20 getNewLengthOfNumWithoutLeadingZerosAndFirstIndexAfterLeadingZeros(int20 number){
    int length = 0;
    bool isFirstCharAfterLeadingZeros = false;
    for(int i = 0; number.value[i] != '\0'; i++){
        if(!isFirstCharAfterLeadingZeros && number.value[i] != '0'){
            isFirstCharAfterLeadingZeros = true;
            number.indexOfFirstCharWithoutLeadingZeros = i;
        }
        if(isFirstCharAfterLeadingZeros){
            length++;
        }
    }
    return number;
}

int20 addition(int20 greaterNum, int20 shorterNum){
    int20 sumNum;
    strcpy(sumNum.value, greaterNum.value);
    sumNum.lengthOfNumberWithoutLeadingZeros = greaterNum.lengthOfNumberWithoutLeadingZeros;
    sumNum.indexOfFirstCharWithoutLeadingZeros = greaterNum.indexOfFirstCharWithoutLeadingZeros;

    int uebertrag = 0;
    bool endAddition = false;
    for(int index = LAENGE - 1; index >= 0; index--){
        if(!endAddition) {
            int newDigit = (greaterNum.value[index] - '0') + (shorterNum.value[index] - '0') + uebertrag;
            if (newDigit >= 10) {
                if (index == 0) {
                    printf("ZAHLEN SIND ZU GROß UND KOENNEN NICHT ADDIERT WERDEN!");
                }
                uebertrag = 1;
                newDigit -= 10;
            } else {
                uebertrag = 0;
            }
            sumNum.value[index] = newDigit + '0';
        } else {
            break;
        }

        if(index == shorterNum.indexOfFirstCharWithoutLeadingZeros && uebertrag == 0){ // break if shorter Num has been added
            endAddition = true;
        }

        else if(index == shorterNum.indexOfFirstCharWithoutLeadingZeros - 2){ // break after uebertrag was added from shorter num
            endAddition = true;
        }
    }
    sumNum = getNewLengthOfNumWithoutLeadingZerosAndFirstIndexAfterLeadingZeros(sumNum);
    return sumNum;
}


int20 add20(int20 a, int20 b) {
    int lengthOfA = a.lengthOfNumberWithoutLeadingZeros;
    int lengthOfB = b.lengthOfNumberWithoutLeadingZeros;
    bool isABiggerThanB = lengthOfA >= lengthOfB;
    return isABiggerThanB ? addition(a, b) : addition (b, a);
}

void print20(int20 a) {
    bool isFirstCharAfterLeadingZeros = false;
    for(int i = 0; a.value[i] != '\0'; i++){
        if(!isFirstCharAfterLeadingZeros && a.value[i] != '0'){
            isFirstCharAfterLeadingZeros = true;
        }
        if(isFirstCharAfterLeadingZeros){
            printf("%c", a.value[i]);
        }
    }
    printf("\n");
}

int aufgabe6(void) {
    int20 a1= create20("12345678901234567890");
    int20 b1= create20("100");

    int20 sum1= add20(a1, b1);
    print20(a1);
    print20(b1);
    printf("a1 + b1 =  ");
    print20(sum1);

    int20 a2= create20("9700");
    int20 b2= create20("422");
    int20 sum2= add20(a2, b2);
    print20(a2);
    print20(b2);
    printf("a2 + b2 =  ");
    print20(sum2);

    return 0;
}