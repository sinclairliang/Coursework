//-----------------------------------------------------------------------------
// Sinclair Liang
// wliang13@ucsc.edu
// Dictionary.c
// Dictionary with Hash Table
//-----------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

#define MAX_LEN 180
const int tableSize = 101;

// ----- functions provided on class webpage------
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift)
{
   int sizeInBits = 8 * sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);

   if (shift == 0)
      return value;

   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char *input)
{
   unsigned int result = 0xBAE86554;

   while (*input)
   {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char *key)
{
   return pre_hash(key) % tableSize;
}

// private types --------------------------------------------------------------
// NodeObj
typedef struct NodeObj
{
   char *key;
   char *value;
   struct NodeObj *next;
} NodeObj;

// Node
typedef NodeObj *Node;

// newNode()
// constructor of the Node type
Node neuNode(char *k, char *v)
{
   Node N = malloc(sizeof(NodeObj));
   assert(N != NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return (N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node *pN)
{
   if (pN != NULL && *pN != NULL)
   {
      free(*pN);
      *pN = NULL;
   }
}

// ListObj
typedef struct ListObj
{
   Node head;
} ListObj;

// List
typedef ListObj *List;

//constructor for the List type
List neuList(void)
{
   List L = malloc(sizeof(ListObj));
   assert(L != NULL);
   L->head = NULL;
   return L;
}

typedef struct DictionaryObj
{
   List table;
   int numItems;
} DictionaryObj;

Node find(Node N, char *key)
{
   while (N != NULL)
   {
      if (strcmp(N->key, key) == 0)
      {
         break;
      }

      N = N->next;
   }

   return N;
}

void deleteAll(Node N)
{
   if (N != NULL)
   {
      deleteAll(N->next);
      freeNode(&N);
   }
}

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary()
{

   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D != NULL);
   D->table = calloc(tableSize, sizeof(ListObj));
   D->numItems = 0;
   return D;
}

void freeDictionary(Dictionary *pD)
{
   if (pD != NULL && *pD != NULL)
   {
      if (!isEmpty(*pD))
      {
         makeEmpty(*pD);
      }

      free(*pD);
      *pD = NULL;
   }
}

int isEmpty(Dictionary D)
{
   if (D == NULL)
   {
      fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   return (D->numItems == 0);
}

int size(Dictionary D)
{
   return D->numItems;
}

char *lookup(Dictionary D, char *k)
{
   Node N;
   List L;
   int tableIndice = hash(k);
   L = &D->table[tableIndice];
   N = find(L->head, k);

   if (N == NULL)
   {
      return NULL;
   }

   else
   {
      return N->value;
   }
}

void insert(Dictionary D, char *k, char *v)
{

   Node N;
   List L;
   int tableIndice = hash(k);
   N = neuNode(k, v);
   L = &D->table[tableIndice];

   if (D == NULL)
   {
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   if (lookup(D, k) != NULL)
   {
      fprintf(stderr, "Alraedy existed\n");
      exit(EXIT_FAILURE);
   }

   else
   {
      N->next = L->head;
      L->head = N;
      N = NULL;
      D->numItems++;
   }
}

void delete (Dictionary D, char *k)
{

   Node N;
   List L;
   int tableIndice = hash(k);
   L = &D->table[tableIndice];

   if (D == NULL)
   {
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   if (lookup(D, k) == NULL)
   {
      fprintf(stderr, "Dictionary error: key not found\n");
      exit(EXIT_FAILURE);
   }

   else
   {
      if (find(L->head, k) == L->head)
      {
         N = L->head;
         L->head = L->head->next;
         N->next = NULL;
      }

      else
      {
         N = find(L->head, k);
         Node P = L->head;
         Node C = L->head->next;

         while (C != N)
         {
            C = C->next;
            P = P->next;
         }

         P->next = N->next;
         N->next = NULL;
      }

      D->numItems--;
      freeNode(&N);
   }
}

void makeEmpty(Dictionary D)
{
   List L;

   if (D == NULL)
   {
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on an invalid Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   if (D->numItems == 0)
   {
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on an empty Dictionary\n");
      exit(EXIT_FAILURE);
   }

   for (int i = 0; i < tableSize; i++)
   {
      L = &D->table[i];
      deleteAll(L->head);
   }

   D->table = NULL;
   D->numItems = 0;
}

void printDictionary(FILE *out, Dictionary D)
{
   Node N;
   List L;

   if (D == NULL)
   {
      fprintf(stderr, "Dictionary Error: calling printDictionary on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   else
   {
      for (int i = 0; i < tableSize; ++i)
      {
         L = &D->table[i];
         N = L->head;

         while (N != NULL)
         {
            fprintf(out, "%s %s\n", N->key, N->value);
            N = N->next;
         }
      }
   }
}
