//-----------------------------------------------------------------------------
// caps.c
// extracts the upper case letters in the input file and places them
// in the output file
// 
// 
// A new feature in this program is the placement of a function
// definition after function main, rather than before.  When this is
// done, a function prototype must precede function main.  A function
// prototype consists of the function heading followed by a semicolon.
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

// prototype of function extractCaps 
void extractCaps(char* s, char* caps);

// function main which reads command line arguments 
int main(int argc, char* argv[]){
   FILE* in;       // handle for input file                             
   FILE* out;      // handle for output file                            
   char* word;     // dynamically allocated string holding input word   
   char* capsWord; // dynamically allocated string holding caps in word 

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   in = fopen(argv[1], "r");
   if( in==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   out = fopen(argv[2], "w");
   if( out==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings word and capsWord from heap memory 
   word = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   capsWord = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( word!=NULL && capsWord!=NULL );

   // read each word in input file, extract caps 
   while( fscanf(in, " %s", word) != EOF ){
      extractCaps(word, capsWord);
      fprintf(out, "%s\n", capsWord);
   }

   // free heap memory associated with string variables word and capsWord 
   free(word);
   free(capsWord);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}


// definition of function extractCaps 
void extractCaps(char* s, char* caps){
   int i=0, j=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isupper(s[i]) ){
         caps[j] = s[i];
         j++;
      }
      i++;
   }
   caps[j] = '\0';
}

