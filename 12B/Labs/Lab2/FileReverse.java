import java.io.*;
import java.util.Scanner;

class FileReverse 
{
  public static String stringReverse(String s, int n)
  {
    String result = "";
    // starts with an empty string
    if (n>0) 
    {
      result += s.charAt(s.length()-1)+stringReverse(s.substring(0,s.length()-1),n-1);
      return result;
    }
    return s;  
  }

   public static void main(String[] args) throws IOException
  {
    Scanner in = null;
    PrintWriter out = null;
    String line = null;
    String[] token = null;
    int i, n, lineNumber = 0;
    if(args.length < 2)
    {
      System.out.println("Usage: FileReverse <input file> <output file>");
      System.exit(1);
    }
    // open files
    in = new Scanner(new File(args[0]));
    out = new PrintWriter(new FileWriter(args[1]));
    while( in.hasNextLine() )
    {
      lineNumber++;
      line = in.nextLine().trim() + " "; 
      token = line.split("\\s+");      
      n = token.length;
      for(i=0; i<n; i++)
      {
      	String ready_convert = token[i];
      	int length_of_string = ready_convert.length();
      	out.println(stringReverse(ready_convert,length_of_string));
      }
    }
    in.close();
    out.close();
  }
      
}