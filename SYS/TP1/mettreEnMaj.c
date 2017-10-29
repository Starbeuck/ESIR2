#include <stdio.h>
#include <ctype.h>

char * mettreEnMajuscule (char *arr){
    int i = 0;
    for(i = 0; arr[i] != '\0'; i++){
        arr[i]=toupper(arr[i]);
    };
    return arr;
}
