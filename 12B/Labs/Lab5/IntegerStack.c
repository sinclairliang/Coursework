//-----------------------------------------------------------------------------
//   IntegerStack.c
//   Implementation file for IntegerStack ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"IntegerStack.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   int item;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(int x) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->item = x;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// StackObj
typedef struct StackObj{
   Node top;
   int numItems;
} StackObj;


// public functions -----------------------------------------------------------

// newStack()
// constructor for the Stack type
Stack newStack(void){
   Stack S = malloc(sizeof(StackObj));
   assert(S!=NULL);
   S->top = NULL;
   S->numItems = 0;
   return S;
}

// freeStack()
// destructor for the Stack type
void freeStack(Stack* pS){
   if( pS!=NULL && *pS!=NULL ){
      if( !isEmpty(*pS) ) popAll(*pS);
      free(*pS);
      *pS = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Stack S){
   if( S==NULL ){
      fprintf(stderr, 
              "Stack Error: calling isEmpty() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   return(S->numItems==0);
}

// push()
// pushes x onto top of S
// pre: none
void push(Stack S, int x){
   Node N;
   if( S==NULL ){
      fprintf(stderr, "Stack Error: calling push() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   N = newNode(x);
   N->next = S->top;
   S->top = N;
   S->numItems++;
}

// pop()
// deletes and returns item at top of S
// pre: !isEmpty(S)
int pop(Stack S){
   int returnValue;
   Node N;
   if( S==NULL ){
      fprintf(stderr, "Stack Error: calling pop() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if( S->numItems==0 ){
      fprintf(stderr, "Stack Error: calling pop() on empty Stack\n");
      exit(EXIT_FAILURE);
   }
   N = S->top;
   returnValue = S->top->item;
   S->top = S->top->next;
   S->numItems--;
   freeNode(&N);
   return returnValue;
}

// peek()
// returns item on top of S
// pre: !isEmpty(S)
int peek(Stack S){
   if( S==NULL ){
      fprintf(stderr, "Stack Error: calling peek() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if( S->numItems==0 ){
      fprintf(stderr, "Stack Error: calling peek() on empty stack\n");
      exit(EXIT_FAILURE);
   }
   return S->top->item;
}

// popAll()
// resets S to the empty state
// pre: !isEmpty(S)
void popAll(Stack S){
   if( S==NULL ){
      fprintf(stderr, 
              "Stack Error: calling popAll() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if( S->numItems==0 ){
      fprintf(stderr, "Stack Error: calling popAll() on empty Stack\n");
      exit(EXIT_FAILURE);
   }
   while( S->numItems>0 ) pop(S);
}

// printStack()
// prints a text representation of S to the file pointed to by out
// pre: none
void printStack(FILE* out, Stack S){
   Node N;
   if( S==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=S->top; N!=NULL; N=N->next) fprintf(out, "%d ", N->item);
   fprintf(out, "\n");
}

