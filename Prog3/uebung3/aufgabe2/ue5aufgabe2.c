//
// Created by mike_k on 12.10.22.
//

#include "stdio.h"

double matrix_elem_sum(double matrix[], int n, int lengths[]);

double matrix_elem_sum(double matrix[], int n, int lengths[]){
    int endIterator = 1;
    for(int i = 0; i < n; i++){ // Get End (double*) matrix
        endIterator *= lengths[i];
    }
    double sum = 0.0;
    for(int i = 0; i < endIterator; i++){ // Get Sum of all Matrix elements
        sum += matrix[i];
    }
    return sum;
}

int aufgabe2(void){
    double matrix2d[3][2] =
            { {111, 112}, {121, 122}, {131, 132} };
    int lengths2d[2] = {3, 2};

    double matrix3d[2][3][4] = {
            { {111, 112, 113, 114}, {121, 122, 123, 124}, {131, 132, 133, 134} },
            { {211, 212, 213, 214}, {221, 222, 223, 224}, {231, 232, 233, 234} }
    };
    int lengths3d[3] = {2, 3, 4};
    printf("Sum of matrix2d = %.f\n",
    matrix_elem_sum((double*)matrix2d, 2, lengths2d)); // gibt 729 aus
    printf("Sum of matrix3d = %.f\n",
    matrix_elem_sum((double*)matrix3d, 3, lengths3d)); // gibt 4140
    return 0;
}