//-----------------------------------------------------------------------------
// apint.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa1: Building arbitrary precision numeric types by using ADT
//-----------------------------------------------------------------------------
import java.lang.*;
import java.util.Arrays;
import java.lang.Math;

class apint
{
	public String sign="+";
	public int length;
	public int[] digits;

//--------------------------------------------------------------------------
// Constructors
//--------------------------------------------------------------------------

	public apint()
	{
	    // default constructor;
		sign = "";
		length = 0;
		digits = null;
	}
	public apint(String s)
	{
		// a constructor for a string
		char s0 = s.charAt(0);
		if (!Character.isDigit(s0)) 
		{
			sign = String.valueOf(s0);
			length = s.length()-1;
			digits = new int[length];
			for (int i=0; i<length; i++) 
			{
				digits[i] = (int)(s.charAt(i+1) - '0');
			}
		}
		else
		{
			length = s.length();
			digits = new int[length];
			for (int i=0; i<length; i++) 
			{
				digits[i] = (int)(s.charAt(i) - '0');
			}
		}
	}

	apint(int a)
	{
		// conversion of ints
		if (a == 0) 
		{
      		digits = new int[1];
      		length = 1;
      		digits[0] = 0;
		}
		
		else
		{
			int back_up = a;
			while (a > 0) 
			{
				length++;
				a /= 10;
			}

			digits = new int[length];
			for (int i=0; i<length; i++) 
			{
				digits[length-i-1] = back_up % 10;
				back_up /= 10;
			}	
		}

	}

	
	apint(int[] a)
	{
		// a constructor for an array of integers
		int ori_length = a.length;
		int zero_length = 0;
		int j = 0;
      	while (j < ori_length)
      	{
      		if (a[j] != 0) 
      		{
      			break;
      		}
      		else
      		{
      			zero_length++;
      		}
      		j++;
      	}
      	int should_be = a.length-zero_length;
      	
      	if (should_be == 0) 
      	{
      		digits = new int[1];
      		length = 1;
      		digits[0] = 0;
      	}
      	else
      	{
      		digits = new int[should_be];
      		for (int i=0;i<should_be ;i++ ) 
      		{
      			digits[i] = a[zero_length+i];
      		}
      		length = should_be;
      	}
        

	}

	apint(double a)
	{
		// conversion of reals
		String a_String = String.valueOf(Math.floor(a));
		String[] b_String = a_String.split(".0");
		length = b_String[0].length();
		digits = new int[length];
		for (int i=0; i<length; i++) 
		{
			digits[i] = (int)(b_String[0].charAt(i) - '0');
		}
	}


//--------------------------------------------------------------------------
// methods
//--------------------------------------------------------------------------

	public static void print(apint z)
	{
	    System.out.print(z.sign);
	    int leading_zero=0;
	    Boolean flag = false;
	    int i=0;
	    for (int k=0; k<z.length; k++)
	    {
	    	System.out.print(z.digits[k]);
	    }	
	    // System.out.println();
	}


	apint add(apint z)
	{
		int carry_over = 0;
		int [] longer_array;
    	int [] shorter_array;
		int [] result = new int [Math.max(this.length, z.length)+2];
    	int need_add = (Math.min(this.length, z.length)+1);
	     if (this.length >= z.length) 
	     {
	        longer_array = this.digits;
	        shorter_array = z.digits;
	     }
	     else
	     {
	        longer_array = z.digits;
	        shorter_array = this.digits;
	     }

	   	// System.out.print("longer_array = ");
	    // printArray(longer_array);
	    // System.out.print("shorter_array = ");
	    // printArray(shorter_array);


	    int [] longer_array_use = new int [longer_array.length+1];
	    longer_array_use [0] = 0;
	    for (int j = 1;j<longer_array_use.length ;j++) 
	    {
	    	longer_array_use [j] = longer_array[j-1];
	    }

	    int [] shorter_array_use = new int [shorter_array.length+1];
	    shorter_array_use [0] = 0;
	    for (int k = 1;k<shorter_array_use.length ;k++) 
	    {
	    	shorter_array_use [k] = shorter_array[k-1];
	    }



	    for (int i = 0;i<need_add ;i++)
	    {
	        int temp_digit = shorter_array_use[shorter_array_use.length-i-1] + longer_array_use[longer_array_use.length-i-1]+carry_over;
	        // System.out.println(longer_array_use[longer_array_use.length-i-1] + " is adding "+ shorter_array_use[shorter_array_use.length-i-1]+ " carry_over = "+carry_over + " temp_digit = " + temp_digit);
	        if (temp_digit < 10)
	        {
	            result [result.length-i-1] =  temp_digit;
	            carry_over = 0;
	        }
	        else
	        {
	            result [result.length-i-1] =  temp_digit - 10;
	            carry_over = 1;
	        }
	    }
	    // System.out.println("result [] = ");
	    // printArray(result);
	    for (int k = need_add+1;k<result.length ;k++) 
	    {
	        int temp_digit = longer_array_use[result.length-k-1] + carry_over;
	        if (temp_digit >= 10) 
	        {
	        	result [result.length-k] = temp_digit-10;
	        	carry_over = 1;
	        }
	        else
	        {
	        	result [result.length-k] = temp_digit;
	        	carry_over = temp_digit/10;
	        }
	        // System.out.println("carry_over = "+carry_over+"temp_digit = "+temp_digit);
	        // System.out.println(longer_array_use[result.length-k-1] + " is adding "+" carry_over = "+carry_over + " temp_digit = " + temp_digit);
	        // result [result.length-k] = temp_digit;
	        // carry_over = temp_digit/10;
	        // System.out.println("result [] = ");
	    	// printArray(result);
	    	// System.out.println("carry_over = "+carry_over);
	    }
	    return new apint(result);
	}

	apint sub(apint z)
	{
		int carry_over=0;
		int [] longer_array;
	    int [] shorter_array;
	    // System.out.println();
	   	// System.out.println("this.digits [] = ");
	    // printArray(this.digits);
	   	// System.out.println("z.digits [] = ");
	    // printArray(z.digits);	
	    
	    int [] this_use = new int [this.length+1];
	    this_use [0] = 0;
	    for (int j = 1;j<this_use.length ;j++) 
	    {
	    	this_use [j] = this.digits[j-1];
	    }

	    int [] that_use = new int [z.length+1];
	    that_use [0] = 0;

	    for (int k = 1;k<that_use.length ;k++) 
	    {
	    	// System.out.println("z.length = "+z.length);
	    	// printArray(z.digits);
	    	that_use [k] = z.digits[k-1];
	    }
	    // System.out.println("this_use = ");
	    // printArray(this_use);
	    // System.out.println("that_use = ");
	    // printArray(that_use);
	    longer_array = return_larger(this_use, that_use);
	    shorter_array = return_shorter(this_use, that_use);
	    // System.out.println("longer_array = ");
	    // printArray(longer_array);
	    // System.out.println("shorter_array = ");
	    // printArray(shorter_array);

	    int [] result = new int [Math.max(this.length, z.length)+1];
	    int need_sub = Math.min(this.length, z.length);
	    for (int i = 0;i<need_sub ;i++)
	    {   
	        int temp_digit = longer_array[longer_array.length-i-1] - shorter_array[shorter_array.length-i-1]+carry_over;
	        // System.out.println(longer_array[longer_array.length-i-1] + " is subtracting "+ shorter_array[shorter_array.length-i-1]+ " carry_over = "+carry_over + " temp_digit = " + temp_digit);
	        
	        if (temp_digit < 0)  
	        {
	            result [result.length-i-1] =  (10-shorter_array[shorter_array.length-i-1]+longer_array[longer_array.length-i-1]+carry_over);
	            carry_over = -1;
	        }
	        else
	        {
	            result [result.length-i-1] =  temp_digit;
	            carry_over = 0;
	        }
	        // System.out.println("result [] = ");
	        // printArray(result);
	        
	    }
	    for (int k = need_sub+1;k<result.length ;k++) 
	    {

			
	        int temp_digit = longer_array[result.length-k]+carry_over;

	        // System.out.println(longer_array[result.length-k] + " is being put into result[] "+ " carry_over = "+carry_over + " temp_digit = " + temp_digit);
	        if (temp_digit == -1) 
	        {
	        	result [result.length-k] = 9;
	        	carry_over = -1;
	        	// k++;
	        }
	        else
	        {
	        	result [result.length-k] = temp_digit;
	        	carry_over = 0;
	        }
	        
	        // System.out.println(longer_array[result.length-k-1] + " is being put into result[] "+ " carry_over = "+carry_over + " temp_digit = " + temp_digit);
	        
	    }
	    return new apint(result);




	}

	apint multiplication(apint z)
	{
		// used Russian-peasant Algorithm
		// tribute to: https://www.cut-the-knot.org/Curriculum/Algebra/PeasantMultiplication.shtml
		apint a = this;
		apint result = new apint(0);
		if (is_zero(this) == true || is_zero(z) == true) 
		{
			return new apint(0);
		}
		if (a.length == 1 && z.length == 1) 
		{
			
			return new apint(a.digits[a.length-1]*z.digits[z.length-1]);
		}
		

		if (a.digits[a.length-1]%2 != 0) 
		{
			result = result.add(z);
			// System.out.print("pre_result = ");
	  		// print(result);
	  		// System.out.println();
		}

	    while(is_one(a) == false)
	    {
	        a = half(a);
	        // System.out.print("a = ");
	        // print(a);
	        // printArray(a.digits);
	        z = doubling(z);
	        // System.out.print("b = ");
	        // printArray(z.digits);
	        if (a.digits[a.length-1]%2 != 0) 
	        {
	            result = result.add(z);
	            // System.out.print("result = ");
	            // print(result);
	        }
	        // System.out.println();
	    }
	    // System.out.println();
	    return result;
	}

	apint division(apint z)
	{
		// primative way to divide: add one to result whenever the divisor is subtracted from the divident.
		// might be quite slow;
		apint divident = this;
		apint divisor = z;
		apint one = new apint(1);
		apint result1 = new apint(0);
		while(divident.compare(divisor)>=0)
		{
			divident=divident.sub(divisor);
			result1 = result1.add(one);
			// System.out.println("result = ");
			// print(result1);
		}
		return result1;
	}


  	public int compare(apint other) 
  	{
	    if (length < other.length)
	    {
	      return -1;
	    }
	    else if (length > other.length)
	    {
	      return 1;
	    }
	    
	    for (int i=0; i<length; i++) 
	    {
	      if (digits[i] < other.digits[i])
	      {
	      	return -1;
	      }
	        
	      else if (digits[i] > other.digits[i])
	      {
	      	return 1;
	      }   
	    }
	    return 0;
  	}


    public static apint half(apint A)
    {
    	// return half of the input
        int [] result = new int [A.length];
        int carry_over = 0;
        for (int i = 0;i<A.length ;i++)
        {
            result[i] = (A.digits[i]+carry_over*10)/2;
            carry_over = A.digits[i]%2;
        }
        return new apint(result);
    }
    
    public static apint doubling (apint A)
    {
    	// return double of the input
        apint result = A.add(A);
        return result;
    }

    public static Boolean is_one (apint A)
    {
    
		int i = 0;
		for( ; i < A.length; i++)
		{
		   if(A.digits[i] != 0)
		   {
		    break;
		   }
		}
		int [] no_zeros = new int[A.length - i];
		for (int k = 0;k<no_zeros.length ;k++ ) 
		{
			no_zeros[k] = A.digits[k+i];
		}
    	// printArray(no_zeros);
    	if (no_zeros.length == 1) 
    	{
    		if (no_zeros[0] == 1) 
    		{
    		 	return true;
    		} 
    	}
	    return false;
    }

	public static boolean is_zero(apint A) 
	{
	    for (int i=0; i<A.length; i++)
	    {
	    	if (A.digits[i] != 0)
	    	{
	    		return false;
	    	}
	    }  
	    return true;
	}

	public static void printArray(int[] A)
	{
		for (int i=0;i< A.length;i++) 
		{
			System.out.print(A[i]);
		}
		// System.out.println();
	}


	public static int[] return_larger(int[] A, int[] B)
	{
		int [] larger = new int [Math.max(A.length, B.length)];
		int i = 0;
		if (A.length != B.length) 
		{
			if (A.length > B.length) 
			{
				larger = A;
			}
			else
			{
				larger =  B;
			}
		}
		else
		{
			while(i<A.length)
			{
				if (A[i] != B[i]) 
				{
					if (A[i] > B[i])
					{
						larger = A;
					}
					else
					{
						larger = B;
					}
					break;
				}
				i++;
			}
		}
		return larger;
	}

	public static int[] return_shorter(int[] A, int[] B)
	{
	  	int [] shorter = new int [Math.min(A.length, B.length)]; 
	  	int i = 0;
	  	if (A.length != B.length) 
	  	{
	  		if (A.length < B.length) 
	  		{
	  			shorter = A;
	  		}
	  		else
	  		{
	  			shorter =  B;
	  		}
	  	}
		else
		{
			while(i<A.length)
			{
				if (A[i] != B[i]) 
				{
					if (A[i] > B[i])
					{
						shorter = B;
					}
					else
					{
						shorter = A;
					}
					break;
				}
				i++;
			}
		}
	  	return shorter;
	}
	public static apint factorial(apint n)
	{
	  	apint one = new apint(1);
	  	apint zero = new apint(0);
	  	if (is_zero(n)) 
	  	{
	  		return one;	
	  	}
	  	if (is_one(n)) 
	  	{
	  		return one;
	  	}
	  	else
	  	{
	  		return n.multiplication(factorial(n.sub(one)));
	  	}
	}
}








