#include <stdio.h>

int main(){
    printf("Hello world\n");
    int a=0,b=1,c=2,d=3,e=4;
    a=b-c+d*e;
    printf("%d\n",a);
    int numbers[10];
    numbers[0]=10;
    numbers[1]=20;
    numbers[2]=30;
    numbers[3]=40;
    numbers[4]=50;
    numbers[5]=60;
    numbers[6]=70;
    numbers[7]=80;
    numbers[8]=90;
    numbers[9]=100;
    printf("The 7th number in the array is %d\n",numbers[6]);
    return 0;
}