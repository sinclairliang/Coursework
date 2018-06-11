//-----------------------------------------------------------------------------
// DictionaryTest.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa3: This file is to test different functionalities in Dictionary ADT
//-----------------------------------------------------------------------------
import java.util.Scanner;
import java.io.*;

class DictionaryTest
{
   public static void main(String[] args)
   {
   	String v;
   	Dictionary A = new Dictionary();
   	A.insert("3","e");
   	A.insert("8","f");
   	A.insert("56","54");
   	// System.out.println(A.isEmpty());
   	// System.out.println(A.size());
   	// v = A.lookup("3");
      System.out.println(A);
      // A.delete("3");
      // A.delete("8");
      A.delete("56");
      // System.out.println(A.size());

   	// System.out.println(v);
   	System.out.println(A);
   	// A.makeEmpty();
   	// System.out.println(A);
   }
}
