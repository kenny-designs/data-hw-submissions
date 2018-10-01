package obj4;

/**
 * Links designed for doubly-linked lists
 */
public class Link {
  public char data;           // character value being stored
  public Link next;           // references next link in the list
  public Link previous;       // references the previous link in the list

  /**
   * Creates a new Link with given char value
   * @param d Value to set data to
   */
  public Link(char d) {
	  data = d;
  }

  /**
   * Prints the value of data
   */
  public void displayLink() {
	  System.out.print(data + " ");
  }
}
