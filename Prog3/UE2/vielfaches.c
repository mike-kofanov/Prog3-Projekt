#include <stdio.h>
int main(void){
    int a;
    do {
        printf("Bitte positive ganze Zahl eingeben: ");
        scanf("%d", &a);
    } while (a<=0);
    for (int i=0;i<10;i++) {
        for (int j=0;j<10;j++) {
            if (i==0) {
                printf(" ");
            }
            printf("%d", i*10+j);
            if ((i*10+j)%a==0) {
                printf("# ");
            } else {
                printf(" ");
            }
        }
        printf("\n");
    }
    return 0;
}