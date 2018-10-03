class recursive_subarrays
{
	public static void main(String[] args)
	{
		int[] A = {1,2,3,4,5};
		int[] B = {};
		printAllsub_back(A,A.length-1,B);
		printAllsub_front(A,A.length-1,B);

	}
	
	public static void printAllsub_front(int[] A, int i, int[] B)
	{
		if (i < 0) 
		{
			printArray(B);
			return;
		}

		int[] C = B.clone();
		printAllsub_front(A,i-1,C);

		int[] D = new int [B.length+1];
		D[0] = A[i];
		for (int j=1;j<D.length ;j++ ) 
		{
			D[j] = B[j-1];
		}
		printAllsub_front(A,i-1,D);
	}


	public static void printAllsub_back(int[] A, int i, int[] B)
	{
		if (i < 0) 
		{
			printArray(B);
			return;
		}

		int[] C = new int [B.length+1];
		C[0] = A[i];
		for (int j=1;j<C.length ;j++ ) 
		{
			C[j] = B[j-1];
		}
		printAllsub_back(A,i-1,C);

		C = B.clone();
		printAllsub_back(A,i-1,C);
	}



	static void printArray(int[] A)
	{
		for (int i=0;i<A.length;i++) 
		{
			System.out.print(A[i]);
		}
		System.out.println();
	}
}