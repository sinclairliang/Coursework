//-----------------------------------------------------------------------------
// Dictionary.c
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types and functions ------------------------------------------------

// NodeObj
typedef struct NodeObj
{
   char* key;
   char* value;
   struct NodeObj* left;
   struct NodeObj* right;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v)
{
   Node N = malloc(sizeof(NodeObj));
   assert(N != NULL);
   N->key = k;
   N->value = v;
   N->left = N->right = NULL;
   return (N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN)
{
   if ( pN != NULL && *pN != NULL )
   {
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj
{
   Node root;
   int numPairs;
} DictionaryObj;


// findKey()
// returns the Node containing key k in the subtree rooted at R, or returns
// NULL if no such Node exists
Node findKey(Node R, char* k)
{
   if (R == NULL || strcmp(k, R->key) == 0)
      return R;

   if ( strcmp(k, R->key) < 0 )
      return findKey(R->left, k);
   else // strcmp(k, R->key)>0
      return findKey(R->right, k);
}


// findParent()
// returns the parent of N in the subtree rooted at R, or returns NULL
// if N is equal to R. (pre: R != NULL)
Node findParent(Node N, Node R)
{
   Node P = NULL;

   if ( N != R )
   {
      P = R;

      while ( P->left != N && P->right != N )
      {
         if (strcmp(N->key, P->key) < 0)
            P = P->left;
         else
            P = P->right;
      }
   }

   return P;
}

// findLeftmost()
// returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
Node findLeftmost(Node R)
{
   Node L = R;

   if ( L != NULL ) for ( ; L->left != NULL; L = L->left) ;

   return L;
}

// printInOrder()
// prints the (key, value) pairs belonging to the subtree rooted at R in order
// of increasing keys to file pointed to by out.
void printInOrder(FILE* out, Node R)
{
   if ( R != NULL )
   {
      printInOrder(out, R->left);
      fprintf(out, "%s %s\n", R->key, R->value);
      printInOrder(out, R->right);
   }
}

// deleteAll()
// deletes all Nodes in the subtree rooted at N.
void deleteAll(Node N)
{
   if ( N != NULL )
   {
      deleteAll(N->left);
      deleteAll(N->right);
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
   D->root = NULL;
   D->numPairs = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD)
{
   if ( pD != NULL && *pD != NULL )
   {
      makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   return (D->numPairs == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   return (D->numPairs);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k)
{
   Node N;

   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   N = findKey(D->root, k);
   return ( N == NULL ? NULL : N->value );
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v)
{
   Node N, A, B;

   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   if ( findKey(D->root, k) != NULL )
   {
      fprintf(stderr,
              "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }

   N = newNode(k, v);
   B = NULL;
   A = D->root;

   while ( A != NULL )
   {
      B = A;

      if ( strcmp(k, A->key) < 0 ) A = A->left;
      else A = A->right;
   }

   if ( B == NULL ) D->root = N;
   else if ( strcmp(k, B->key) < 0 ) B->left = N;
   else B->right = N;

   D->numPairs++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k)
{
   Node N, P, S;

   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   N = findKey(D->root, k);

   if ( N == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }

   if ( N->left == NULL && N->right == NULL )
   {
      // case 1 (no children)
      if ( N == D->root )
      {
         D->root = NULL;
         freeNode(&N);
      }

      else
      {
         P = findParent(N, D->root);

         if ( P->right == N ) P->right = NULL;
         else P->left = NULL;

         freeNode(&N);
      }
   }

   else if ( N->right == NULL ) // case 2 (left but no right child)
   {
      if ( N == D->root )
      {
         D->root = N->left;
         freeNode(&N);
      }

      else
      {
         P = findParent(N, D->root);

         if ( P->right == N ) P->right = N->left;
         else P->left = N->left;

         freeNode(&N);
      }
   }

   else if ( N->left == NULL ) // case 2 (right but no left child)
   {
      if ( N == D->root )
      {
         D->root = N->right;
         freeNode(&N);
      }

      else
      {
         P = findParent(N, D->root);

         if ( P->right == N ) P->right = N->right;
         else P->left = N->right;

         freeNode(&N);
      }
   }

   else                       // case3: (two children: N->left!=NULL && N->right!=NULL)
   {
      S = findLeftmost(N->right);
      N->key = S->key;
      N->value = S->value;
      P = findParent(S, N);

      if ( P->right == S ) P->right = S->right;
      else P->left = S->right;

      freeNode(&S);
   }

   D->numPairs--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
   deleteAll(D->root);
   D->root = NULL;
   D->numPairs = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{
   if ( D == NULL )
   {
      fprintf(stderr,
              "Dictionary Error: calling printDictionary() on NULL"
              " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   printInOrder(out, D->root);
}

