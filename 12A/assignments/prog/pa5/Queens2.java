//-----------------------------------------------------------
// n-Quess.java
// Sinclair Liang
// wliang13
// pa5
// 
//-----------------------------------------------------------
import java.util.Arrays;
class Queens2

{
	private static int result_found = 0;
	public static void main(String[] args)
	{
		int user_input = 4;		
		int [] Array = new int[user_input+1];
		int [] solu ={0,1, 3, 5, 2, 4};
		Array[0] = 0;
		for (int i =1;i<Array.length ;i++ ) 
		{
			Array[i] = i;
		}
		//printArray(Array);
		System.out.println(isSolution(solu));
	}

	static boolean isSolution(int[] A)
	{
		int n = A.length;
		int vertical, horizontal;
		int k = 0;
		for (int i = 1; i < n ; i++ ) 
		{
			for (int j = i+1; j<n ;j++ ) 
			{
				vertical = Math.abs(A[i]-A[j]);
				horizontal = Math.abs(i-j);
				result_found ++;
				k ++;
				if ( vertical == horizontal) 
				{
					return false;
				}
				if (k==(n-1)*(n-2)/2)
				{
					return true;
				}
				else
				{
					continue;
				}
			}
		}
		return false;
	}
	static void nextPermutation(int[] A)
	{
		int flag=0;
		int pivot=0;
		int successor = 0;
		for (int i = (A.length -2); i >= 1 ; i--) 
		{
			if (A[i] < A[i+1]) 
			{
				pivot = i;
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			reverse(A,1,A.length-1);
		}
		for (int j = (A.length -1); j >=1; j--) 
		{
			if (A[j] > A[pivot]) 
			{
				successor = j;
				break;
			}	
		}
		if(flag==1)
		{
			swap(A,pivot,successor);
			reverse(A,pivot+1,A.length-1);
		}	
	}

	static void swap(int[] Q, int i, int j)
	{
		int temp = Q[i];
		Q[i] = Q[j];
		Q[j] = temp;
	}

	static void reverse(int[] T, int start, int end)
	{
		int i = start, j = end;
		while (i<j)
		{
			swap(T,i,j);
			i++;
			j--;
		}
	}

    static void printArray(int[] A)
    {
        System.out.print("(");
        for(int i=0; i<A.length-1; i++)
        {
	    	System.out.print(A[i]+", ");
        }
        System.out.print(A[A.length-1]);
		System.out.println(")");
    }

    static int factorial(int n)
    {
    	if ( n == 1) 
    	{
    		return 1;
    	}
    	else
    	{
    		return (factorial(n-1)*n);
    	}
    }

    static void usage()
    {
        System.out.println("Usage: Queens [-v] number");
        System.out.println("Option: -v verbose output, print all solutions");
    }
}