//-----------------------------------------------------------------------------
// IntegerStack.h
// Header file for the IntegerStack ADT
//-----------------------------------------------------------------------------

#ifndef _INTEGER_STACK_H_INCLUDE_
#define _INTEGER_STACK_H_INCLUDE_


// Stack
// Exported reference type
typedef struct StackObj* Stack;

// newStack()
// constructor for the Stack type
Stack newStack(void);

// freeStack()
// destructor for the Stack type
void freeStack(Stack* pS);

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Stack S);

// push()
// pushes x onto top of S
// pre: none
void push(Stack S, int x);

// pop()
// deletes and returns item at top of S
// pre: !isEmpty(S)
int pop(Stack S);

// peek()
// returns item on top of S
// pre: !isEmpty(S)
int peek(Stack S);

// popAll()
// resets S to the empty state
// pre: !isEmpty(S)
void popAll(Stack S);

// printStack()
// prints a text representation of S to the file pointed to by out
// pre: none
void printStack(FILE* out, Stack S);


#endif
