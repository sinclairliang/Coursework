//-----------------------------------------------------------------------------
// Sinclair Liang
// wliang13@ucsc.edu
// List.java
// Illustrates Java Generics
//-----------------------------------------------------------------------------
@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T> {

	private class Node {
		T item;
		Node next;

		Node(T x) {
			item = x;
			next = null;
		}
	}
	private Node head;
	private int numItems;

	public List() {
		head = null;
		numItems = 0;
	}

	//----- private functions ----
	private Node find(int index) {
		Node N = head;

		for (int i = 1; i < index ; i++ ) {
			N = N.next;
		}

		return N;
	}
	//----------------------------
	public boolean isEmpty() {
		return (numItems == 0);
	}

	public int size() {
		return numItems;
	}

	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index < 1 || index > this.size()) {
			throw new ListIndexOutOfBoundsException("get(): invalid index: "+ index);
		}

		else {
			Node N = find(index);
			return N.item;
		}
	}

	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
		if (index < 1 || index > numItems + 1) {
			throw new ListIndexOutOfBoundsException("List error, index already existed or smaller than 0");

		} else {
			if (index == 1) {
				Node N = new Node(newItem);
				head = N;

			} else {
				Node P = find(index - 1);
				Node C = P.next;
				Node N = new Node(newItem);
				P.next = N;
				N.next = C;
			}

			numItems++;
		}

	}


	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index > numItems) {
			throw new ListIndexOutOfBoundsException("List error, exceeds boundry.");

		} else {
			if (numItems == 1) {
				this.removeAll();

			} else {
				if (index == 1) {
					Node N = find(index-1);
					head = N.next;
				}
				else
				{
					Node P = find(index-2);
					Node C = find(index+1);
					P.next = C;
				}
			}
			numItems--;
		}
	}

	public void removeAll() {
		head = null;
		numItems = 0;
	}

	public String toString() {
		String s = "";

		for (Node N = head; N != null; N = N.next) {
			s += N.item.toString() + " ";
		}

		return s;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object rhs) {
		boolean eq = false;
		List<T> R = null;
		Node N = null;
		Node M = null;

		if (this.getClass() == rhs.getClass()) {
			R = (List<T>)rhs;
			eq = (this.numItems == R.numItems);

			N = this.head;
			M = R.head;

			while (eq && N != null) {
				eq = (N.item == M.item);
				N = N.next;
				M = M.next;
			}
		}

		return eq;
	}
}

























