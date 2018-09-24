//-----------------------------------------------------------------------------
// HashTest.c
// Print statistics for different table sizes. Demonstrates that when using
// the division method h(k)=k%m, the table size m is critical. Values of m
// that are primes not close to a power of 2 tend to spread the probability
// of hashing to a given slot evenly across the whole table, as evidenced by
// the small standard deviations and small ranges. Values of m that are powers 
// of 2 tend to skew the probability distribution, making it non-uniform.
//
// compile:    gcc -std=c99 -o HashTest -lm HashTest.c
//
//-----------------------------------------------------------------------------

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

#define MAX_LINE_LEN 180
#define MIN_TABLE_SIZE 10
#define MAX_TABLE_SIZE 1025

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to m-1
int hash(char* key, int m){
   return pre_hash(key)%m;
}


int main(void){
   FILE* in = fopen("hashTestInput", "r");
   FILE* out = fopen("hashTestStats", "w");
   char* buffer = NULL;
   int bufferLength = 0, bufferOffset = 0;
   char line[MAX_LINE_LEN];
   int frequency[MAX_TABLE_SIZE];
   int i, m, lineLength, lineCount = 0;
   int x, min, max, range;
   double alpha, sum, stdev;

   // read lines into buffer, separated by '\0'
   while( fgets(line, MAX_LINE_LEN, in)!=NULL ){
      lineCount++;
      lineLength = strlen(line);
      line[--lineLength] = '\0';      // replace '\n' with '\0'
      bufferLength += (lineLength+1);
      buffer = realloc(buffer, bufferLength*sizeof(char) );
      strcpy(buffer+bufferOffset, line);
      bufferOffset += (lineLength+1);
   }
   fclose(in);

   // calculate and print statistics for different table sizes
   fprintf(out,
           "%4s\t%4s\t\t%4s\t\t%4s\t\t%4s\t\t%4s\n", 
           "table_size","min","max","range","average","  stdev");
   fprintf(out, 
           "-----------------------------------------------------------\n");
   for(m=MIN_TABLE_SIZE; m<=MAX_TABLE_SIZE; m++){

      // (re)initialize frequency array
      for(i=0; i<m; i++) frequency[i] = 0;

      // calculate frequencies
      bufferOffset = 0;
      while( bufferOffset<bufferLength ){
         frequency[hash(buffer+bufferOffset, m)]++;
         lineLength = strlen(buffer+bufferOffset);
         bufferOffset += (lineLength+1);
      }

      // calculate statistics for table size m
      alpha = lineCount/(double)m;  // load factor or average frequency
      x = max = min = frequency[0];
      sum = pow(x-alpha, 2);
      for(i=1; i<m; i++){
         x = frequency[i];
         min = (x<min)?x:min;
         max = (x>max)?x:max;
         sum += pow(x-alpha, 2);;
      }
      stdev = sqrt(sum/m);
      range = max-min;

      // print statistics for table size m
      fprintf(out, 
              "  %4d\t\t\t%4d\t\t%4d\t\t%4d\t\t%7.2f\t\t%7.2f\n", 
                 m,     min,   max,   range, alpha,   stdev);

   }
   fclose(out);
   free(buffer);

   return EXIT_SUCCESS;
}