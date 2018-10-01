package linkedlist;

/**
 * A singly linked list object based on the LinkList class from the textbook under
 * listing 5.9
 */
public class LinkList {
  private Link first; // the very first Link in the list

  public LinkList() {}

  /**
   * Creates a new LinkList object where each Link is created based on each element
   * in the given long array
   *
   * @param nums The long array that will be converted into a LinkList
   */
  public LinkList(long[] nums) {
    // create a ListIterator that references our LinkList
    ListIterator li = getIterator();

    // insert each element from the given long array into the LinkList one at a time
    for (long n : nums) {
      li.insertAfter(n);
    }
  }

  /**
   * Returns a reference to the very first Link in the list
   *
   * @return Link The first Link in the list
   */
  public Link getFirst() {
    return first;
  }

  /**
   * Sets a new Link to be the first in the list
   *
   * @param Link The Link to be the new first in the list
   */
  public void setFirst(Link f) {
    first = f;
  }

  /**
   * Returns true if the list is empty, false if not
   *
   * @return boolean True if the list is empty, false if it is not
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Returns a new ListIterator based on the LinkList object this method was invoked on
   *
   * @return ListIterator The ListIterator based on the LinkList this method was invoked on
   */
  public ListIterator getIterator() {
    return new ListIterator(this);
  }

  /**
   * Returns the size of the LinkList depending on the number of links it has
   *
   * @return long The size of the list
   */
  public long getSize() {
    // nothing in the list, return 0
    if (isEmpty()) {
      return 0;
    }

    // create an iterator to go through the list counting each node
    ListIterator li = getIterator();

    // increment size for each node in the list
    long size = 0;
    while (li.getCurrent() != null) {
      size++;
      li.nextLink();
    }

    // return the final size of the list
    return size;
  }

  /**
   * Returns the final link in the list. Null if list is empty.
   *
   * @return Link The final Link in the list
   */
  public Link getTail() {
    // list is empty therefore no tail. Return null
    if (isEmpty()) {
      return null;
    }

    // iterator for going to end of the list to retrieve tail
    ListIterator li = getIterator();

    // iterate to end of the list
    while (!li.atEnd()) {
      li.nextLink();
    }

    // return the final link
    return li.getCurrent();
  }

  /**
   * Prints the entire list to the screen based on each individual Link looping from
   * start to finish
   */
  public void displayList() {
    Link current = first;

    // loop through each Link in the list until no more remain
    while (current != null) {
      // display the Link
      current.displayLink();
      // advance to the next Link in the list
      current = current.next;
    }
    System.out.println();
  }
}
