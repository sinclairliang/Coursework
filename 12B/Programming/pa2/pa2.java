//-----------------------------------------------------------------------------
// Search.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa2: Using Merge Sort to sort a String Array, then use binary search to 
// find the target string.
//-----------------------------------------------------------------------------
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
class Search 
{
   static void mergeSort(String[] word, int[] lineNumber, int p, int r)
   {
      int q;
      if(p < r) 
      {
         q = (p+r)/2;
         mergeSort(word, lineNumber, p, q);
         mergeSort(word, lineNumber, q+1, r);
         merge(word, lineNumber, p, q, r);
      }
   }

   
   static void merge(String[] word, int[] lineNumber, int p, int q, int r)
   {
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] Left = new int[n1];
      int[] Right = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++)
      {
         L[i] = word[p+i];
         Left[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++)
      { 
         R[j] = word[q+j+1];
         Right[j] = lineNumber[q+j+1];
      }
      i = 0; j = 0;
      for(k=p; k<=r; k++)
      {
         if( i<n1 && j<n2 )
         {
            if( L[i].compareTo(R[j]) < 0 )
            {
               word[k] = L[i];
               lineNumber[k] = Left[i];
               i++;
            }
            else
            { 
               word[k] = R[j];
               lineNumber[k] = Right[j];
               j++;
            }
         }
         else if( i<n1 )
         {
            word[k] = L[i];
            lineNumber[k] = Left[i];
            i++;
         }
         else
         { // j<n2
            word[k] = R[j];
            lineNumber[k] = Right[j];
            j++;
         }
      }
   }


   static int binarySearch(String[] A, int p, int r,  String target)
   {
      int q;
      if(p > r) 
      {
         return -1;
      }
      else
      {
         q = (p+r)/2;
         if(target.equals(A[q]))
         {
            return q;
         }
         else if(target.compareTo(A[q]) < 0)
         {
            return binarySearch(A, p, q-1, target);
         }
         else
         { // target > A[q]
            return binarySearch(A, q+1, r, target);
         }
      }
   }


   static void usage()
   {
      System.out.println("% Search\nUsage: Search file target1 [target2 ..]");
      System.exit(1);
   }


   static int GetLineNumber(String address) throws IOException
   {
      Scanner in = new Scanner(new File(address));
      int lineCount = 0;
      while( in.hasNextLine() )
      {
         in.nextLine();
         lineCount++;
      }
      in.close();
      return lineCount;
   }


   public static void main(String[] args) throws IOException
   {
      if(args.length < 1)
      {
         usage();
      }
      // int lineCount = GetLineNumber(args[0]);
      Scanner in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z");
      String big = in.next();
      String[] Lines = big.split("\n|\\s+");
      String[] Sorted = new String[Lines.length];
      int[] LineNumber = new int[Lines.length];

      for (int i=0; i<Lines.length; i++) 
      {
         // put the line number into LineNumber[]
         LineNumber[i] = i+1;
      }

      for (int i=0; i<Lines.length; i++) 
      {
         //copy the whole array
         Sorted[i] = Lines[i];
      }
      mergeSort(Sorted,LineNumber,0,Sorted.length-1);
      for (int i=1; i<args.length; i++) 
      {
         int FoundLineNumber = binarySearch(Sorted,0,Sorted.length-1,args[i]);
         if (FoundLineNumber >= 0)
            {
               System.out.println(args[i]+ " found on line "+ LineNumber[FoundLineNumber]);
            }
            else
            {
               System.out.println(args[i]+" Not found");
            }
      }
   }
}