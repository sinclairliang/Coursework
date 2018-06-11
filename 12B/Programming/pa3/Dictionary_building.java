//-----------------------------------------------------------------------------
// Dictionary.java
//-----------------------------------------------------------------------------
public class Dictionary_building implements DictionaryInterface 
{
  // private inner Node class
  private class Node {
    String key;
    String value;
    Node next;

    Node(String key, String value){
      this.key = key;
      this.value = value;
      next = null;
    }
  }

  private Node head;
  private int numItems;

  //dictionary constructor which initializes the private feild
  public Dictionary(){
    head = null;
    numItems = 0;
  }

  private Node find(String key){
    Node N = head;
    for(; N!=null; N=N.next){
      if (N.key==key) {
        return N;
      }
    }
    return null;
  }

  public boolean isEmpty()
  {
    return(numItems == 0);
  }

  public int size()
  {
    return numItems;
  }


  public void makeEmpty()
  {
    head = null;
    numItems = 0;
  }

  // public void delete(String key) throws KeyNotFoundException
  // {
  //   if (lookup(key) == null)
  //   {
  //     throw new KeyNotFoundException("cannot delete non-existent key");
  //   }
  //   else
  //   {
  //     Node P = find(key);
  //     Node N = head;
  //     if (N.next == null) 
  //     {
  //       head = null;
  //       P = null;
  //     }
  //     else
  //     {
  //       for(; N.next!=P; N=N.next)
  //       {
  //         // N.next = P.next;
  //         // P.next = null;
  //         // System.out.println(N);
  //       }
  //       // System.out.println(N);
  //     }
  //     numItems--;
  //   }
  // }

  // public void delete(String key) throws KeyNotFoundException
  // {
  //   if (lookup(key) == null)
  //   {
  //     throw new KeyNotFoundException("cannot delete non-existent key");
  //   }
  //   else
  //   {
      
      
  //     if (this.size() == 1) 
  //     {
  //       this.makeEmpty();
  //     }
  //     else
  //     {
  //       Node N = head;
  //       Node P = find(key);
  //       while (N!=P) 
  //       {
  //         N = N.next;
  //         if (N == P) 
  //         {
  //           break;
  //         }
  //       }
  //     System.out.println("----BEFORE----");
  //     // System.out.println("head   = " + head);
  //     // System.out.println("H.next = " + head.next);
  //     System.out.println("N      = " + N);
  //     System.out.println("N.next = " + N.next);
  //     System.out.println("P      = " + P);
  //     System.out.println("P.next = " + P.next);
  //     // N = N.next;
  //     N.next = P.next;
      
  //     System.out.println("----AFTER----");
  //     // System.out.println("head   = " + head);
  //     // System.out.println("H.next = " + head.next);
  //     System.out.println("N      = " + N);
  //     System.out.println("N.next = " + N.next);
  //     System.out.println("P      = " + P);
  //     System.out.println("P.next = " + P.next);
  //     }
  //     P.next = null;
  //     numItems--;
  //   }
  // }


  public void delete(String key) throws KeyNotFoundException
  {
    if (lookup(key) == null)
    {
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    else
    {
      if (this.size() == 1)
      {
        this.makeEmpty();
      }
      else
      {
        Node N = head;
        Node P = find(key);
        if (N == P)
        {
          head = N.next;
        }
        else
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



  public void insert(String key, String value) throws DuplicateKeyException
  {
    if (lookup(key) != null) 
    {
      // System.out.println("sdfdsfdf1");
      throw new DuplicateKeyException("cannot insert duplicate keys");
    }
    else
    {
      if (head == null) 
      {
        Node N = new Node (key,value);
        head = N;

      }
      else
      {
        Node N = head;
        while(N.next != null)
        {
          if (N.next == null) 
          {
            break;
          }
          N = N.next;
        }
        N.next = new Node(key,value);
      }
      numItems++;
    }
  }

  public String lookup(String key)
  {
    Node N = find(key);
    if (N==null) {
      // System.out.println("sdfush1");
      return null;
    }else{
      return N.value;
    }
  }

    public String toString()
    {
      Node N = head;
      String sb = "";
      while(N != null)
      {
        sb += N.key + " " + N.value + "\n"; 
        N = N.next;
      }
      return sb;
    }
}


























