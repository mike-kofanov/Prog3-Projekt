#include <stdio.h>
int get_input(char prompt[]){
    printf("%s",prompt);
    int value;
    scanf("%d", &value);
    return value;
}
