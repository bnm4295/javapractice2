package Hash;

public class LinkedList<AnyType> {


  protected Node<AnyType> head;

  public LinkedList(){//Constructor
    head = null;
  }

  // Get the size of List
  public int size(){
    return size(head);
  }
  
  // Add item to the end of the list
  public void add(AnyType item){
    head = addLast(head, item);
  }
  

  // Remove item from the list
  public void remove(AnyType item) {
	    head = remove(head, item);
  }
  //Prints entire list
  void printList( Node p ) {                   
       if (p != null) {
           System.out.println(p.item); 
           printList( p.next ); 
    }
  }
  //gets position of the list
  public AnyType get(int pos)
  {
      if (head == null) throw new IndexOutOfBoundsException();

      Node<AnyType> tmp = head;
      for (int k = 0; k < pos; k++) tmp = tmp.next;

      if( tmp == null) throw new IndexOutOfBoundsException();
      
      return tmp.item;
  }
  

  private int size(Node<AnyType> initialHead){
    if (initialHead == null) {
      return 0; 
    }  
    return 1 + size (initialHead.next);
  }
    
  private Node<AnyType> addLast(Node<AnyType> node, AnyType item) {
    if (node == null) {
      return new Node <AnyType>(item);
    }
    node.next = addLast(node.next, item);
    return node;
  }
 
   
  private Node<AnyType> remove(Node<AnyType> node, AnyType item) {
    if (node == null) {  
      return node;
    }
    if (node.item.equals(item)) {
      return node.next; 
    }
    node.next = remove(node.next, item);
    return node;
  }

  //Node Class
  private static class Node<AnyType> {
  private AnyType item;
  private Node<AnyType> next;

      private Node(AnyType value) {
          item = value;
          next = null;
      }

      private Node(AnyType value, Node<AnyType> reference) {
          item = value;
          next = reference;
      }
  }

}
//The linked list was previously not mine, but turned it recursively
//Removed some parts that are unnecessary for the assignment
//http://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/code/LinkedList.java
