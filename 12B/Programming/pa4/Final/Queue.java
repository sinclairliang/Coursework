//-----------------------------------------------------------------------------
//  Queue.java
//  Sinclair Liang
//  wliang13@ucsc.edu
//  Queue data type, using linked list.
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface {
  // ------ private inner Node class -----
  private class Node {
    Object value;
    Node next;
    Node(Object value) {
      this.value = value;
      next = null;
    }
  }

  // ------ inner variables -----
  private Node head;
  private Node rear;
  private int numItems;


  // ------ dictionary constructor which initializes the private feild -----
  public Queue() {
    head = null;
    rear = null;
    numItems = 0;
  }


  public boolean isEmpty() {
    return (numItems == 0);
  }

  public int length() {
    return numItems;
  }

  public void enqueue(Object newItem) {
    if (head == null) {
      Node N = new Node (newItem);
      head = N;

    } else {
      Node N = head;

      while (N.next != null) {
        if (N.next == null) {
          break;
        }

        N = N.next;
      }

      N.next = new Node(newItem);
    }

    numItems++;
  }

  public Object dequeue() throws QueueEmptyException {
    if ( this.isEmpty()) {
      throw new QueueEmptyException("cannot dequeue() empty queue");
    }

    Node N = head;
    Object returnValue = head.value;
    head = head.next;
    numItems--;
    return returnValue;
  }

  public Object peek() throws QueueEmptyException {
    // Object returnValue = head.value;
    if (this.isEmpty()) {
      throw new QueueEmptyException("cannot dequeue() empty queue");
    }

    return head.value;
  }

  public void dequeueAll() throws QueueEmptyException {
    if (this.isEmpty()) {
      throw new QueueEmptyException("cannot dequeue() empty queue");
    }

    head = null;
    rear = null;
    numItems = 0;
  }

  public String toString() {
    String result = "";
    Node N = head;

    while (N != null) {
      result += String.valueOf(N.value) + " ";
      N = N.next;
    }

    return result;
  }




}