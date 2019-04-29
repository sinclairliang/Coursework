//-----------------------------------------------------------------------------
// Sinclair Liang
// wliang13@ucsc.edu
// ListTest.java
//-----------------------------------------------------------------------------

public class ListTest
{
	public static void main(String[] args)
	{
		List<String> A = new List<String>();
		A.add(1, "2");
		A.add(2, "ten");
		// System.out.println("A.size() is "+A.size());
		// A.remove(1);
		System.out.println("A: "+A);
		System.out.println(A.isEmpty());
		System.out.println("A.size() is "+A.size());
		System.out.println("A.get(2) is "+A.get(2));
		System.out.println("A.equals(A) is "+A.equals(A));
	}
}