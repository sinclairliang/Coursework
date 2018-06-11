//-----------------------------------------------------------------------------
// QueueTest.java
// Test Client for the Queue class
//-----------------------------------------------------------------------------

public class QueueTest {
	public static void main(String[] args) {
		Queue A = new Queue();
		// System.out.println(A.isEmpty());
		A.enqueue(5); A.enqueue(3); A.enqueue(9); A.enqueue(7); A.enqueue(8);
		System.out.println(A);
		// A.dequeue();
		// System.out.println(A);
		// System.out.println(A.peek());
		// System.out.println(A.isEmpty());
		// System.out.println(A.length());
		// System.out.println(A.peek());
		A.dequeueAll();
		System.out.println(A);
	}
}