/* ******************************************************/
// CREATED: Sinclair Liang on May 30th, 2018;
// CHANGED: Sinclair Liang on June 1st, 2018: Adding Comments;
/* ******************************************************/ 
public class Vector_test extends Vector
{
	public static void main(String[] args)
	{
		// System.out.println("\t--------------Testing Arithmetic Methods-----------------\t");
		Vector a = new Vector(6,-8);
		Vector b = new Vector(7,5);
		Vector c = new Vector(13,9);
		Vector d = new Vector(4,6);


		addion_test();
		sub_test();
		constructor_test();
		normalization_test();


		System.out.println("\t--------------Testing Other Methods-----------------\t");

		float a_mag = a.getMagnitude();
		float b_mag = b.getMagnitude();
		System.out.println("Magnitude of a = "+a_mag);
		System.out.println("Magnitude of b = "+b_mag);

		Vector h = a.scalarMultiply(6);
		System.out.println("h is a.scalarMultiply(6) = ");
		print(h);
		
		System.out.println();
		float i = a.dotProduct(b);
		System.out.println("i is a.dotProduct(b) = " + i);
		
		float a_angle = a.getAngle();
		float b_angle = b.getAngle();
		System.out.println("a angle = " + a_angle);
		System.out.println("b angle = " + b_angle);



	}

	public static void constructor_test()
	{	
		System.out.println("\t--------------Testing polar constructor -----------------\t");

		Vector polar_b = polarVector(0.75f,15.3f);
		print(polar_b);
		System.out.println();
	}


	public static void normalization_test()
	{
		System.out.println("\t--------------Testing Normalize Methods-----------------\t");
		Vector a = new Vector(6,-8);
		Vector b = new Vector(7,5);
		Vector c = new Vector(13,9);
		Vector d = new Vector(4,6);	
		Vector a_normalised = a.normalize();
		Vector b_normalised = b.normalize();
		Vector c_normalised = c.normalize();
		System.out.println();
		print(a_normalised);
		System.out.println();
		print(b_normalised);
		System.out.println();
		print(c_normalised);
		System.out.println();
		System.out.println("\t--------------Ending Normalize Methods-----------------\t");

	}

	public static void addion_test()
	{
		System.out.println("\t--------------Testing Adding Methods-----------------\t");
		Vector a = new Vector(6,-8);
		Vector b = new Vector(7,5);
		Vector c = new Vector(13,9);
		Vector d = new Vector(4,6);
		Vector e = a.add(b);
		Vector f = c.add(d);

		System.out.println("a + b = ");
		print(e);
		System.out.println();

		System.out.println("c + d = ");
		print(f);
		System.out.println();
		System.out.println("\t--------------Ending of Adding Methods-----------------\t");
	}

	public static void sub_test()
	{
		System.out.println("\t--------------Testing Subtraction Methods-----------------\t");
		Vector a = new Vector(6,-8);
		Vector b = new Vector(7,5);
		Vector c = new Vector(13,9);
		Vector d = new Vector(4,6);
		Vector e = a.subtract(b);
		Vector f = c.subtract(d);

		System.out.println("a - b = ");
		print(e);
		System.out.println();
		
		System.out.println("c - d = ");
		print(f);
		System.out.println();

		System.out.println("\t--------------Ending of Subtraction Methods-----------------\t");
	}
}