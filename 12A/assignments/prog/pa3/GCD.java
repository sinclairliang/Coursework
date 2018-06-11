//-----------------------------------------------------------
// GCD.java
// Sinclair Liang
// wliang13
// pa3
// A program that calculates the Greatest Common Divisor when two integers are 
// given.
//-----------------------------------------------------------


import java.util.Scanner;

class GCD
{
	public static void main( String[] args )
	{
		Scanner sc = new Scanner(System.in);
		int r;
		int bigger, smaller;
		int original_bigger, original_smaller;
        System.out.print("Enter a positive integer: ");
        while (true)
        {

        	while(!sc.hasNextInt())
        	{
        		sc.next();
        		System.out.print("Please enter a positive integer: ");
        	}
        	bigger = sc.nextInt();
        	if (bigger>0) 
        	{
        	    original_bigger = bigger;
                    break;
        	}
        	System.out.print("Please enter a positive integer: ");
        }
        System.out.print("Enter another positive integer: ");
        while (true)
        {
        	while(!sc.hasNextInt())
        	{
        		sc.next();
        		System.out.print("Please enter a positive integer: ");
        	}
        	smaller = sc.nextInt();
        	if (smaller>0) 
        	{
        		original_smaller = smaller;
        		break;
        	}
        	System.out.print("Please enter a positive integer: ");
        }
		r = (int)(bigger%smaller);
		while(r != 0 )
		{
			bigger = smaller;
			smaller = r; 
			r = (int)(bigger%smaller);
		}
		System.out.println("The GCD of "+original_bigger+" and "+
		original_smaller+" is "+smaller);
	}
}
