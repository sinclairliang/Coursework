//-----------------------------------------------------------------------------
// Dictionary.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa3: Building Dictionary data stucture by using BST(Binary Search Tree)
//-----------------------------------------------------------------------------
public class Dictionary implements DictionaryInterface {
	// ------ private inner Node class -----
	private class Node {
		String key;
		String value;
		Node left;
		Node right;

		Node(String key, String value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	// ------ inner variables -----

	private Node head;
	private int numItems;

	// ------ private methods -----
	private Node find(Node R, String key) {
		if (R == null || key == R.key) {
			return R;
		}

		if (key.compareTo(R.key) < 0 ) {
			return find(R.left, key);

		} else {
			return find(R.right, key);
		}
	}

	Node findParent(Node N, Node R) {
		Node P = null;

		if (N != R) {
			P = R;

			while (P.left != N && P.right != N) {
				if (N.key.compareTo(P.key) < 0) {
					P = P.left;

				} else {
					P = P.right;
				}
			}
		}

		return P;
	}

	private Node findLeftmost(Node R) {
		Node L = R;

		if (L != null) {
			for (; L.left != null; L = L.left) {
				return L;
			}
		}

		return L;
	}

	// ------ dictionary constructor which initializes the private feild -----
	public Dictionary() {
		head = null;
		numItems = 0;
	}

	// ----- interfaces ------
	public boolean isEmpty() {
		if (this == null) {
			System.out.println("ERROR");
			System.exit(0);
		}

		return (numItems == 0);
	}

	public int size() {
		if (this == null) {
			System.out.println("ERROR");
			System.exit(0);
		}

		return numItems;
	}

	public String lookup(String key) {
		if (this == null) {
			System.out.println("ERROR");
			System.exit(0);
		}

		Node N = find(this.head, key);
		return (N == null ? null : N.value);
	}

	public void insert(String key, String value) throws DuplicateKeyException {
		Node N, A, B;

		if (this == null) {
			System.out.println("ERROR");
			System.exit(0);
		}

		if (find(head, key) != null) {
			throw new DuplicateKeyException("cannot insert duplicate keys");
		}

		N = new Node(key, value);
		B = null;
		A = this.head;

		while (A != null) {
			B = A;

			if ((key).compareTo(A.key) < 0) {
				A = A.left;

			} else {
				A = A.right;
			}
		}

		if (B == null) {
			this.head = N;

		} else if ((key).compareTo(B.key) < 0) {
			B.left = N;
		}

		else {
			B.right = N;
		}

		numItems++;
	}

	public void delete(String key) throws KeyNotFoundException {
		Node N, P, S;
		N = find(head, key);

		if (N == null) {
			throw new DuplicateKeyException("cannot delete non-exisiting keys");
		}

		if (N.left == null && N.right == null) {
			if (N == head) {
				head = null;

			} else {
				P = findParent(N, head);

				if (P.right == N) {
					P.right = null;

				} else {
					P.left = null;
				}
			}
		}

		else if (N.right == null) {
			if (N == head) {
				head = N.left;

			} else {
				P = findParent(N, head);

				if (P.right == N) {
					P.right = N.left;

				} else {
					P.left = N.left;
				}
			}
		}

		else if (N.left == null) {
			if (N == head) {
				head = N.right;

			} else {
				P = findParent(N, this.head);

				if (P.right == N) {
					P.right = N.right;
				}

				else {
					P.left = N.right;
				}
			}
		}

		else {
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);

			if (P.right == S) {
				P.right = S.right;

			} else {
				P.left = S.right;
			}
		}

		numItems--;
	}

	public void makeEmpty() {
		head = null;
		numItems = 0;
	}

	public String toString() {
		Node N = head;
		String result = "";

		while (N != null) {
			result += N.key + " " + N.value + "\n";
			N = N.right;
		}

		return result;
	}
}
