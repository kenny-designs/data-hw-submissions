package obj4;

/**
 * Creates a stack that operates via the DoublyLinkedList class located in package obj4
 */
public class Stack {
  DoublyLinkedList list;

  public Stack() {
    list = new DoublyLinkedList();
  }

  /**
   * Pushes given char on to the stack
   *
   * @param c The char to push on to the stack
   */
  public void push(char c) {
    list.insertLast(c);
  }

  /**
   * Pops the last element off of the stack and returns it
   *
   * @return Link The element that was popped off the stack. Null if none
   */
  public Link pop() {
    return list.deleteLast();
  }

  /**
   * Returns the last element of the stack without deleting it
   *
   * @return Link The last element on the stack. Null if none
   */
  public Link peek() {
    return list.getLast();
  }

  /**
   * Returns true if the stack is empty. Else if not
   *
   * @return boolean True is the stack if empty, otherwise false
   */
  public boolean isEmpty() {
    return list.isEmpty();
  }
}
