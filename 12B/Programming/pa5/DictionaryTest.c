//-----------------------------------------------------------------------------
// 	DictionaryTest.c
//  Sinclair Liang
//  wliang13@ucsc.edu
// 	Test file for the Dictionary ADT with Hash Table
//-----------------------------------------------------------------------------


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[])
{
	Dictionary A = newDictionary();
	char* k;
	char* v;
	char* word1[] = {"one", "two", "three", "four", "five", "six", "seven"};
	char* word2[] = {"foo", "bar", "blah", "galumph", "happy", "sad", "blue"};
	int i;
	insert(A, word1[0], word1[1]);
	insert(A, word1[1], word1[2]);
	// delete(A, "one");
	// delete(A, "seven");
	printDictionary(stdout, A);


	k = word1[0];
	v = lookup(A, k);
	printf("key=\"%s\" %s\"%s\"\n", k, (v == NULL ? "not found " : "value="), v);
	printf("%s\n", (isEmpty(A) ? "true" : "false"));
	makeEmpty(A);
	printf("%s\n", (isEmpty(A) ? "true" : "false"));
	freeDictionary(&A);

}
