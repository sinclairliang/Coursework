//-----------------------------------------------------------------------------
// 	DictionaryTest.c
//  Sinclair Liang
//  wliang13@ucsc.edu
// 	Test file for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[])
{
	Dictionary A = newDictionary();
	// printf("%s\n", (isEmpty(A)?"true":"false"));
	// printf("%d\n", size(A));
	char* word1[] = {"one","two","three","four","five","six","seven"};
   	char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   	for(int i=0; i<7; i++)
   	{
      insert(A, word1[i], word2[i]);
   	}
	// insert(A, word1[0], word2[0]);
	// insert(A, word1[1], word2[1]);
	// insert(A, word1[2], word2[2]);
	// printf("%d\n", size(A));
	// insert(A, word1[0], word2[0]);
	// delete(A, "one");
	// printf("%d\n", size(A));

	// insert(A, word1[1], word2[0]);
	// printf("%d\n", size(A));
	// delete(A, "one");
	// printf("%s\n", word1[0]);
	// printf("%s\n", word1[0]);
	// char* t = lookup(A,word1[0]);
	// printf("%c\n", t);
	// printf("%d\n", size(A));
	printDictionary(stdout,A);
	free(A);
	A=NULL;
}	