package obj4;

/**
 * Creates a doubly-linked list version of a linked list
 */
public class DoublyLinkedList {
  private Link first;   // reference to very first link in list
  private Link last;    // reference to very last link in list

  public DoublyLinkedList() {}

  /**
   * Creates a new DoublyLinkedList based on the given charr array
   *
   * @param arr The char array we wish to create a DoublyLinkedList out of
   */
  public DoublyLinkedList(char[] arr) {
    // insert each char into the DoublyLinkedList while maintaining the original order
    for (char c : arr) {
      insertLast(c);
    }
  }

  /**
   * Returns true if there are no items in the linked list, false otherwise
   *
   * @return boolean True is items in linked list, false if not
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Returns the very first link in the DoublyLinkedList
   *
   * @return Link First link in the list
   */
   public Link getFirst() {
     return first;
   }

   /**
    * Returns the very last link in the DoublyLinkedList
    *
    * @return Link Last link in the list
    */
   public Link getLast() {
     return last;
   }

  /**
   * Inserts a new link to the very front of the list
   *
   * @param d The char value to be inserted at the front of the list
   */
  public void insertFirst(char d) {
    // begin by creating a new Link
    Link newLink = new Link(d);

    // if the list is empty, the new link needs to be both our first and last link
    if (isEmpty()) {
      last = newLink;
    }
    // otherwise, the original first link now points back towards the new link
    else {
      first.previous = newLink;
    }

    // the new link now points forward to the old first
    newLink.next = first;
    // and then the new link takes over as first in the list
    first = newLink;
  }

  /**
   * Inserts a new link to the very back of the list
   *
   * @param d The char value to be inserted at the back of the list
   */
  public void insertLast(char d) {
    // create a new link
    Link newLink = new Link(d);

    // if the list is empty, the new link needs to be both our first and last link
    if (isEmpty()) {
      first = newLink;
    }
    // otherwise, the old last link must reference the new link
    // and the new link must reference the old
    else {
      last.next = newLink;
      newLink.previous = last;
    }

    // the new link now takes over as the new last link in list
    last = newLink;
  }

  /**
   * Deletes the very first link from the list and returns it or null if list was empty
   *
   * @return Link The link that was deleted from the front of the list or null if there was none
   */
  public Link deleteFirst() {
    // nothing to delete, return null
    if (isEmpty()) {
      return null;
    }

    // create a reference to the first link in the list
    Link temp = first;

    // if there is only one link in the list, set last to null
    if (first.next == null) {
      last = null;
    }
    // otherwise, have the next link from the first have its previous variable
    // set to null, effectively separating the links from each other
    else {
      first.next.previous = null;
    }

    // set the second element in the list to be the new first
    // defaults to just null if there was only one link in the list
    first = first.next;

    // return the link that was deleted
    return temp;
  }

  /**
   * Deletes the very last link from the list and returns it or null if list was empty
   *
   * @return Link The link that was deleted from the back of the list or null if there was none
   */
  public Link deleteLast() {
    // nothing to delete, return null
    if (isEmpty()) {
      return null;
    }

    // create a reference to the last link in the list
    Link temp = last;

    // if there is only one link in the list, set first to null
    if (first.next == null) {
      first = null;
    }

    // otherwise, have the previous link from the last have its next variable
    // set to null, effectively separating the links from each other
    else {
      last.previous.next = null;
    }

    // set the second to last element in the list to be the new last
    // defaults to just null if there was only one link in the list
    last = last.previous;

    // return the link that was deleted
    return temp;
  }

  /**
   * Inserts a new link immediately after the key specified. Returns true
   * if successful and false if the key does not exist
   *
   * @param key The link with the value we wish to insert a new link after
   * @param d The value of the new link we wish to insert after the given key
   * @return boolean True if the key was found and the new link successfully
   * inserted. False if the key could not be found
   */
  public boolean insertAfter(char key, char d) {
    // can't insert after key for there are no keys to be found, return false
    if (isEmpty()) {
      return false;
    }

    // create a reference to the first link in the list
    Link current = first;

    // loop through the list searching for a link that matches the given key
    while (current.data != key) {
      // key was not found, advance to next link
      current = current.next;

      // if there is no next link, return false
      if (current == null) {
        return false;
      }
    }

    // create a new link to insert after the link with the given key
    Link newLink = new Link(d);

    // if the key is for a link at the very back of the list,
    // set the new link to be the new last link in the array
    if (current == last) {
      newLink.next = null;
      last = newLink;
    }
    // otherwise, have the new link reference the link after the one matching
    // the key then have that same link that comes after the one matching the
    // key point back towards the new link
    else {
      newLink.next = current.next;
      current.next.previous = newLink;
    }

    // have the new link reference back towards the link that matches the key
    // then have that same link reference forwards to the new link
    newLink.previous = current;
    current.next = newLink;

    // return true for the new link was successfully inserted after the link
    // matching the given key
    return true;
  }

  /**
   * Looks for a Link with given key value in the list then deletes it. If done
   * successfully, it returns a reference to the deleted Link. Otherwise, returns
   * null
   *
   * @param key The value of the Link we wish to delete from the list
   * @return Link The link that was deleted from the list, null if nothing was found
   */
  public Link deleteKey(char key) {
    // can't delete a Link by its key if there is nothing in the list, return null
    if (isEmpty()) {
      return null;
    }

    // obtain a reference the to first link in the list
    Link current = first;

    // loop through the list checking to see if the link with given key exists
    while (current.data != key) {
      // key was not found, advance to next link
      current = current.next;

      // there are no more links to loop through, return null
      if (current == null) {
        return null;
      }
    }

    // if the key points to a link at the beginning of the list,
    // set the new first to the one after the found link
    if (current == first) {
      first = current.next;
    }
    // otherwise, have the link before the link that matches the given key
    // reference the link after said matching key link
    else {
      current.previous.next = current.next;
    }

    // if the key points to a link at the end of the list,
    // set the new last link to be the one before the matching key link
    if (current == last) {
      last = current.previous;
    }
    // otherwise, have the link after the link that matches the given key
    // reference the link before said matching key link
    else {
      current.next.previous = current.previous;
    }

    // link that matches the key was successfully deleted, return the link
    return current;
  }

  /**
   * Print the value of each link from first to last
   */
  public void displayForward() {
    System.out.print("List (first-->last): ");

    // reference the first link
    Link current = first;

    // loop forwards from the very beginning of the list displaying each link's
    // value until no more links exist
    while (current != null) {
      current.displayLink();
      current = current.next;
    }

    System.out.println();
  }

  /**
   * Print the value of each link from last to first
   */
  public void displayBackward() {
    System.out.print("List (last-->first): ");

    // reference the very last link
    Link current = last;

    // loop backwards from the very end of the list displaying each link's
    // value until no more links exist
    while (current != null) {
      current.displayLink();
      current = current.previous;
    }

    System.out.println();
  }
}
