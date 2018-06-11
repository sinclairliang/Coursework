//-----------------------------------------------------------
// FindRoot.java
// Sinclair Liang
// wliang13
// pa4
// A programme that determines the real roots of a polynomial that lie within a
// specified range
//-----------------------------------------------------------
import java.util.Scanner;
class Roots
{
    static double poly(double[] C, double x)
    {
	double result = 0;
	for (int i = 0; i<C.length; i++)
	    {
		result += C[i] * Math.pow(x,i);
	    }
	return result;
    }

    static double[] diff(double[] C)
    {
	int rounds = (C.length) - 1;
	// make an array with the length of the length of original - 1;
	double[] coefficient = new double [rounds];
	for(int i = 1; i < (C.length); i++)
	    {
		coefficient[i-1] = C[i] *i;
	    }
	return coefficient;
    }

    static void printArray(double[] A)
    {
        System.out.print("( ");
        for(int i=0; i<A.length; i++)
	    System.out.print(A[i]+" ");
	System.out.println(")");
    }

    static double findRoot(double[] C, double a, double b, double tolerance)
    {
    	// using bisection method to find the root
	double mid = a, width;
	width = b-a;
	while(width > tolerance)
	    {
		mid = (a+b)/2;
		if ((poly(C, a)*poly(C, mid)) <= 0) 
		    {
			b = mid;
		    }
		else 
		    {
			a = mid;
		    }
		width = b-a;
	    }
	return mid;
    }



    public static void main( String[] args )
    {
	Scanner sc = new Scanner(System.in);
	double resulotion = 0.01;
	// the width of each subinterval;
	double tolerance = 0.0000001;
	// how small do we want to stop fingding roots;
	double threshold = 0.001;
	// how close is our root to 0 to be considered as a root;

	System.out.print("Enter the degree: ");
	int number = sc.nextInt();
	// number = the number of highest degree;
	double [] array = new double [number+1];
	// number+1 =  of nomials;
	System.out.print("Enter " + (number+1) +" coefficients: ");

	for(int i=0; i<array.length; i++)
	    { 
		array[i] = sc.nextInt();;
	    }


	System.out.print("Enter the left and right endpoints: ");
	double L = sc.nextInt();
	// L = left end point;
	double R = sc.nextInt();
	// R = Right end point;
	double l = L;
	double r = l + resulotion;
	double result;
	double [] degree = diff(array);
	int k = 0;
	// k indicats how many roots we have got;
	while (r <= R)
	    {
		if (((poly(array,l))*(poly(array,r))) < 0)
		    {
			result = findRoot(array,l,r,tolerance);
			System.out.printf("\nRoot found at "+"%.5f%n", result);
			k += 1;
		    }
		else if (((poly(degree,l))*(poly(degree,r))) < 0)
		    {
			result = findRoot(degree ,l,r,tolerance);
			if ((Math.abs(poly(array,result))) < threshold)
			    {
				System.out.printf("\nRoot found at "+"%.5f%n", result);
				k += 1;
			    }
		    }
		l = r;
		r = r + resulotion;
	    }
	if (k == 0)
	    {
		System.out.print("\nNo roots were found in the specified range.");
	    }
    }
}
