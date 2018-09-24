//-----------------------------------------------------------------------------
// HashDemo.c
// Demonstrate functions rotate_left(), pre_hash(), hash() and showBits()
//
// compile: gcc -std=c99 -o HashDemo HashDemo.c
//
//-----------------------------------------------------------------------------

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

const int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);  // remainder on division by sizeInBits
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
   unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100
   while (*input) {                   // while *input is not '\0' (not end of string)
      result ^= *input++;                // result = result ^ *input (current char alters result) 
                                         // input++  (go to next char)
      result = rotate_left(result, 5);   // rotate result by fixed amount
   }
   return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// showBits()
// print out the bits in an unsigned int
void showBits(unsigned int x) {
   int i;
   int sizeInBits = 8*sizeof(unsigned int);
   char symbol[2] = {'0','1'};
   char* binary = malloc(sizeInBits + 1);

   memset(binary, 0, sizeInBits + 1);

   for (i=0; i<sizeInBits; i++) {
       binary[sizeInBits-i-1] = symbol[(x>>i) & 0x01];
   }
   
   printf("%s\n", binary);
   free(binary);
}

int main(void){
   char str1[] = "iron-heartedly";
   char str2[] = "ironheartedly";
   char str3[] = "Ironheartedly";

   printf("When tableSize=%d:\n", tableSize);
   printf("%s hashes to index: %d\n", str1, hash(str1));
   printf("%s hashes to index: %d\n", str2, hash(str2));
   printf("%s hashes to index: %d\n", str3, hash(str3));

   printf("\n\n");

   printf("Number of bytes in unsigned int = %d\n", sizeof(unsigned int));
   printf("Number of bits in unsigned int = %d\n", 8*sizeof(unsigned int));
   showBits(0xBAE86554);
   showBits(rotate_left(0xBAE86554, 5));
   showBits(0xBAE86554 << 5);
   showBits(0xBAE86554 >> (8*sizeof(unsigned int) - 5) );
   showBits( (0xBAE86554 << 5) | (0xBAE86554 >> (8*sizeof(unsigned int) - 5) ) );

   printf("\n\n");
   printf("\'a\' = "); showBits('a'); 
   printf("\'b\' = "); showBits('b'); 
   printf("\'c\' = "); showBits('c'); 

   return EXIT_SUCCESS;
}