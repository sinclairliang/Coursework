//-----------------------------------------------------------------------------
// Dictionary.java
// Sinclair Liang
// wliang13@ucsc.edu
// pa3: Building Dictionary data stucture by using ADT
//-----------------------------------------------------------------------------
public class Dictionary implements DictionaryInterface
{
	// ------ private inner Node class -----
	private class Node 
	{
	    String key;
	    String value;
	    Node next;
	    Node(String key, String value)
	    {
		    this.key = key;
	      	this.value = value;
		    next = null;
	    }
	}

	// ------ inner variables -----
  	private Node head;
  	private int numItems;

  	// ------ private methods -----
  	private Node find(String key)
  	{
  		Node N = head;
  		for(; N!=null; N=N.next)
  		{
  			if (N.key==key)
  			{
  				return N;
  			}
  		}
  		return null;
  	}

  	// ------ dictionary constructor which initializes the private feild -----
  	public Dictionary()
  	{
    	head = null;
    	numItems = 0;
  	}

  	// ----- interfaces ------
  	public boolean isEmpty()
  	{
  		return(numItems == 0);
  	}

	public int size()
	{
		return numItems;
	}

	public String lookup(String key)
	{
		Node N = find(key);
		if (N == null) 
		{
			return null;
		}
		else
		{
			return N.value;
		}
	}

	public void insert(String key, String value) throws DuplicateKeyException
	{
		if (lookup(key) != null)
		{
			throw new DuplicateKeyException("cannot insert duplicate keys");
		}
		else
		{
			if (head == null)
			// if the Dictionary is empty
			{
				Node N = new Node (key,value);
				head = N;
			}
			else
			{
				Node N = head;
				while(N.next != null)
				// while loop keeps running until 
				// reaches the end of Dictionary
				{
					if (N.next == null)
					{
						break;
					}
					N = N.next;
				}
				N.next = new Node(key, value);
			}
		}
		numItems++;
	}

	public void delete(String key) throws KeyNotFoundException
	{
		if (lookup(key) == null)
		{
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		else
		{
			if (this.size() == 1)
			// if there is one item in the Dictionary;
			{
				this.makeEmpty();
			}
			else
			{
				Node N = head;
				Node P = find(key);
				if (N == P)
				// remove the first element in the dictionary;
				{
					head = N.next;
				}
				else
				// others but not the first element;
				{
					while(!(N.next==P))
					{
						N = N.next;
					}
					N.next = P.next;
				}
				numItems--;
			}
		}
	}

	public void makeEmpty()
	{
		head = null;
		numItems = 0;
	}

	public String toString()
	{
		Node N = head;
		String result = "";
		while(N != null)
		{
			result += N.key + " " + N.value + "\n";
			N = N.next;
		}
		return result;
	}
}





























