//-----------------------------------------------------------------------------
// Name: Sinclair Liang
// wliang13@ucsc.edu
// lab4: using  memory allocation to sort out different kinds of chars.
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

int n_alpha = 0;
int n_digit = 0;
int n_punctuation = 0;
int n_whitespace = 0;

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha;     // string holding all alphabetic chars
   char* digit;     // string holding all numeric chars
   char* punctuation;   // string holding all punctuation chars
   char* whitespace;    // string holding all whitespace chars

   // check command line for correct number of arguments 
   if(argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if((in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if((out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char));

   assert(line!=NULL && alpha!=NULL);
   assert(line!=NULL && digit!=NULL);
   assert(line!=NULL && punctuation!=NULL);
   assert(line!=NULL && whitespace!=NULL);

   int j = 0;

   while(fgets(line, MAX_STRING_LENGTH, in) != NULL){
      j++;
      extract_chars(line, alpha, digit, punctuation, whitespace);
      fprintf(out,"line %d contains:\n", j);

      fprintf(out, "%i", n_alpha);
      fprintf(out, "%s", n_alpha==1 ?" alphabetic character: ":" alphabetic characters: ");
      fprintf(out, "%s\n", alpha);

      fprintf(out, "%i", n_digit);
      fprintf(out, "%s", n_digit==1 ?" numeric character: ":" numeric characters: ");
      fprintf(out, "%s\n", digit);

      fprintf(out, "%i", n_punctuation);
      fprintf(out, "%s", n_punctuation==1 ?" punctuation character: ":" punctuation characters: ");
      fprintf(out, "%s\n", punctuation);

      fprintf(out, "%i", n_whitespace);
      fprintf(out, "%s", n_whitespace==1 ?" whitespace character: ":" whitespace characters: ");
      fprintf(out, "%s\n", whitespace);

      n_alpha = 0;
      n_digit = 0;
      n_punctuation = 0;
      n_whitespace = 0;
   }

   free(line);
   free(alpha);
   free(digit);
   free(punctuation);
   free(whitespace);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}
 
// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( (int) s[i]) ) 
      {
         a[j++] = s[i];
         n_alpha++;
      }
      if( isdigit( (int) s[i]) )
      {
         d[k++] = s[i];
         n_digit++;
      } 
      if( ispunct( (int) s[i]) ) 
      {
         p[l++] = s[i];
         n_punctuation++;
      }
      if( isspace( (int) s[i]) )
      {
         w[m++] = s[i];
         n_whitespace++;
      } 
      i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[m] = '\0';
}
