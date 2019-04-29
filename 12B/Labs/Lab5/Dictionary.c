//-----------------------------------------------------------------------------
//  Dictionary.c
//  Sinclair Liang
//  wliang13@ucsc.edu
//  Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj
{
  char* key;
  char* value;
  struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v)
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
void freeNode(Node* pN)
{
  if ( pN != NULL && *pN != NULL )
  {
    free(*pN);
    *pN = NULL;
  }
}

// Node head;

Node find(char* key)
{
  Node N = NULL;

  for (; N != NULL; N = N->next)
  {
    if (N->key == key)
    {
      return N;
    }
  }

  free(N);
  return NULL;
}


// DictionaryObj
typedef struct DictionaryObj
{
  Node head;
  int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary()
{

  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D != NULL);
  D->head = NULL;
  D->numItems = 0;
  return D;
}


void freeDictionary(Dictionary* pD)
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
  if ( D == NULL )
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


char* lookup(Dictionary D, char* k)
{
  if (D == NULL)
  {
    fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  Node N = D->head;

  while (N != NULL)
  {
    if (strcmp(N->key, k) == 0)
    {
      return N->value;
      break;
    }

    N = N->next;
  }

  return NULL;
}

void insert(Dictionary D, char* k, char* v)
{
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
    if (isEmpty(D))
    {
      Node N;
      N = newNode(k, v);
      D->head = N;
    }

    else
    {
      Node H = D->head;

      while (H != NULL)
      {
        if (H->next == NULL)
        {
          break;
        }

        H = H->next;
      }

      H->next = newNode(k, v);
    }

    D->numItems++;
  }
}

void delete(Dictionary D, char* k)
{
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
    Node N = D->head;

    if (strcmp(N->key, k) == 0)
    {
      D->head = N->next;
    }

    else
    {
      while (N != NULL && N->next != NULL)
      {
        if (strcmp(N->next->key, k) == 0)
        {
          Node P = N;
          Node C = N->next;
          P->next = C->next;
          N = P;
          freeNode(&C);
        }

        N = N->next;
      }
    }

    D->numItems--;
  }
}


void makeEmpty(Dictionary D)
{
  D->numItems = 0;
  freeNode(&D->head);
}



void printDictionary(FILE* out, Dictionary D)
{
  Node N = D->head;

  if (D == NULL)
  {
    fprintf(stderr, "Dictionary Error: calling printDictionary on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  else
  {
    while (N != NULL)
    {
      fprintf(out, "%s %s\n", N->key, N->value);
      N = N->next;
    }
  }
}

