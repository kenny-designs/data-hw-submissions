package linkedlist;

/**
 * Used to iterate through the LinkList class.
 * This class is based on the same ListIterator class from the textbook under
 * listing 5.9
 */
public class ListIterator {
  private Link current;     // link we are currently pointing to
  private Link previous;    // the previous link from our current one
  private LinkList ourList; // the LinkList we are iterating through

  /**
   * Creates a new ListIterator object based on the given LinkList then sets
   * current to the first Link in given list
   *
   * @param list The LinkList to iterate through
   */
  public ListIterator(LinkList list) {
    ourList = list;
    reset();
  }

  /**
   * Returns a copy of the ListIterator that references the same LinkList and points
   * to the same current and previous Links.
   *
   * @return ListIterator The new ListIterator object based on the ListIterator copy
   * was invoked on
   */
  public ListIterator copy() {
    ListIterator li = new ListIterator(ourList);
    li.current = current;
    li.previous = previous;

    return li;
  }

  /**
   * Sets the iterator back to the beginning of the list
   */
  public void reset() {
    current = ourList.getFirst();
    previous = null;
  }

  /**
   * Returns true if the iterator is at the end of the list, otherwise false
   *
   * @param boolean True if at end of list, false if not
   */
  public boolean atEnd() {
    return current.next == null;
  }

  /**
   * Makes the ListIterator advance forward by a single link via setting previous
   * to the current Link and changing current to the next Link in the list
   */
  public void nextLink() {
    previous = current;
    current = current.next;
  }

  /**
   * Returns the Link the iterator is currently on
   *
   * @return Link The current Link
   */
  public Link getCurrent() {
    return current;
  }

  /**
   * Returns the Link that came prior to the current one
   *
   * @return Link The previous link
   */
  public Link getPrevious() {
    return previous;
  }

  /**
   * Sets the first Link in the list then resets the iterator
   *
   * @param f The new first Link in the list
   */
  public void setFirst(Link f) {
    ourList.setFirst(f);
    reset();
  }


  /**
   * Inserts a new Link based on the given value after the current link
   *
   * @param d Value of the new link to insert after the current Link
   */
  public void insertAfter(long d) {
    Link newLink = new Link(d);

    if (ourList.isEmpty()) {
      ourList.setFirst(newLink);
      current = newLink;
    }
    else {
      newLink.next = current.next;
      current.next = newLink;
      nextLink(); // point to the new link
    }
  }

  /**
   * Inserts a new Link based on the given value before the current link
   *
   * @param d Value of the new link to insert before the current Link
   */
  public void insertBefore(long d) {
    Link newLink = new Link(d);

    if (previous == null) {
      newLink.next = ourList.getFirst();
      ourList.setFirst(newLink);
      reset();
    }
    else {
      newLink.next = previous.next;
      previous.next = newLink;
      current = newLink;
    }
  }

  /**
   * Deletes the current link then moves on to the next. If the deleted Link was
   * at the end of the list, reset the iterator back to the beginning of the list
   *
   * @return long The value of the Link that was deleted from the list
   */
  public long deleteCurrent() {
    long value = current.data;

    // at beginning of list
    if (previous == null) {
      ourList.setFirst(current.next);
      reset();
    }
    else {
      previous.next = current.next;

      if (atEnd()) {
        reset();
      }
      else {
        current = current.next;
      }
    }

    return value;
  }
}
