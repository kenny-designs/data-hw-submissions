package linkedlist;

/**
 * A single Link object holds a long value and points to the next Link in a list.
 * This class is based on the same Link class from the textbook under Listing 5.9
 */
public class Link {
  public long data; // the value of each Link
  public Link next; // points to the next Link in the list

  /**
   * Constructs a new Link object based on the given long value
   *
   * @param d Value of the new Link object
   */
  public Link(long d) {
    data = d;
  }

  /**
   * Prints the value of the Link
   */
  public void displayLink() {
    System.out.print(data + " ");
  }
}
