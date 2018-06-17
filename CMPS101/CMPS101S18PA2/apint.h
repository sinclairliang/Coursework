//-----------------------------------------------------------------------------
// apint.h
// Sinclair Liang
// wliang13@ucsc.edu
// pa2: Building arbitrary precision numeric types by using ADT
//-----------------------------------------------------------------------------

typedef struct apint apint;

void print(apint *Z); // the method to properly print out apint object;
apint* apint_new(); // default constructor, to allocate memory;
apint* ArrayConstruct(char *a); // Turning a string into an apint object;
apint* intConstruct(int a); // Turning integer into apint object;
apint* normalisation(apint *original); 
// making sure it returns the normalised form of apint object by eliminating leading zeros;
apint* half(apint* A); // Reture half of the value of input;
apint* add(apint* one, apint* two); // Method foe addtion, return the sum of two apint objects;
apint* doubling(apint* A); // Reture twice of the value of input;
apint* subtract(apint* one, apint* two); // Method foe subtraction, return the difference of two apint objects;
int is_zero(apint* A); // to judge whether an apint is zero or not, return 1 if it is 0, 0 otherwise;
int is_one (apint* A); // to judge whether an apint is one or not, return 1 if it is 0, 0 otherwise;
apint* mul(apint* one, apint* two); // Multiplication method for apint objects, retunn the product of two apint objects;
apint* factorial(apint* a); // a function to calculate factorial of an apint object;