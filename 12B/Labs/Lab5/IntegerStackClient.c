//-----------------------------------------------------------------------------
// IntegerStackClient.c
// Test client for IntegerStack ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include"IntegerStack.h"

int main(int argc, char* argv[]){
   Stack A = newStack();
   Stack B = newStack();

   push(A,5); push(A,3); push(A,9); push(A,7); push(A,8);
   printStack(stdout, A);
   printf("%d\n", peek(A));
   pop(A); pop(A); pop(A);
   printf("%d\n", peek(A));
   printStack(stdout, A);
   printf("%s\n", isEmpty(A)==0?"false":"true");
   printf("%s\n", isEmpty(B)==0?"false":"true");
   push(B, 5); push(B, 3);
   push(A, 12); push(B, 13);
   printStack(stdout, A);
   printStack(stdout, B);
   popAll(A);
   printStack(stdout, B);

   freeStack(&A);
   freeStack(&B);
   return(EXIT_SUCCESS);
}



