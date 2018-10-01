package obj4;

import java.util.Scanner; // reading in input from supplied file

/**
 * 4) Implement a function to check if a linked list is a palindrome.
 *
 *    a) A palindrome is same forwards as it is backwards (i.e. 0 -> 1 -> 2 -> 1 -> 0)
 *    b) Use a doubly linked list and stack
 *    c) Sample input: 01210
 *    d) Extra credit: inputs take any data type (not just numbers)
 */
public class Objective4 {
  public Objective4() {
    // used for reading in the given input
    Scanner sc = new Scanner(System.in);

    // loop through each line in the given file checking for palindromes
    int line = 1;
    while (sc.hasNextLine()) {
      // inform the user what line was just read
      System.out.printf("Line %d\n" +
                        "-------\n", line);

      // convert the line in to a DoublyLinkedList
      DoublyLinkedList dll = new DoublyLinkedList(sc.nextLine().toCharArray());

      // display the list forwards and back
      dll.displayForward();
      dll.displayBackward();

      // confirm whether or not the list is a palindrome
      System.out.println("As you can see, the previous list is " +
        (isPalindrome(dll) ? "" : "NOT ") + "a palindrome.\n");

      // go on to next line
      line++;
    }

    // file has been fully read and processed, end program
    System.out.println("Objective4 complete!");
    sc.close();
  }

  /**
   * As a foreword about this function, I made it specifically to make use of both
   * the DoublyLinkedList and Stack classes as requested in part b in the objective
   * description. This solution works as it should but I much prefer the solution I
   * left commented out below the following two isPalindrome functions. I write the
   * reason why in a comment above it.
   *
   * The following two isPalindrome() methods work by supplying the first one with
   * a DoublyLinkedList list. We then generate a stack based on it which gets passed
   * in to the second isPalindrome() method along with the very first link in the given
   * DoublyLinkedList. From there, it's a simple matter of recursion to determine if
   * the values of each link in the given list is indeed a palindrome. The details of
   * said recursion is explained in the description of the second isPalindrome method
   *
   * @param list The DoublyLinkedList we wish to check if the values are a palindrome
   * @boolean True if the values of the given list are indeed a palindrome, false if not
   */
  public boolean isPalindrome(DoublyLinkedList list) {
    // create a stack to feed the value of link of the list in to
    Stack s = new Stack();

    // set current to the first Link in the list then loop through until the end
    Link current = list.getFirst();
    while (current != null) {
      // push each link's value in to the stack
      s.push(current.data);
      // move on to the next Link
      current = current.next;
    }

    // via recursion, return true if the list is a palindrome or not
    return isPalindrome(list.getFirst(), s);
  }

  /**
   * When first invoked, Link l is the very first Link in the original DoublyLinkedList
   * and Stack s is the result of taking every link in the original DoublyLinkedList
   * and pushing them in to Stack s from beginning to end. From there, we compare the
   * data from Link l and the result from popping Stack s. If they are the same, then
   * there may be a palindrome on our hands so we recursively invoke isPalindrome()
   * again but with the Link that comes after Link l and the new Stack s since being
   * popped. If Stack s is ever empty, we know we have a palindrome and return true.
   * However, if the data reaches a point where it doesn't match up we know that the
   * original DoublyLinkedList is NOT a palindrome and so we return false.
   *
   * @param l The Link to compare with the popped Link in Stack s
   * @param s The Stack we are popping from to compare to Link l
   * @return boolean True if the original DoublyLinkedList is a palindrome, false if not
   */
  private boolean isPalindrome(Link l, Stack s) {
    // if the Stack is empty, then we can infer that it has passed all previous
    // tests to see if there is a palindrome and there is nothing left to test.
    // As such, return true
    if (s.isEmpty()) {
      return true;
    }

    // if the data from Link s and the popped Link from Stack s match up,
    // we know there is a chance for the original DoublyLinkedList to be a
    // palindrome and need to keep testing
    else if (l.data == s.pop().data) {
      // continue testing for a palindrome on the Link next to Link l
      return isPalindrome(l.next, s);
    }

    // Considering there are more Links to check on Stack s but we've already
    // encountered a mismatch, we know that the original DoublyLinkedList is
    // NOT a palindrome and can return false
    return false;
  }

  /*
   * The following was my original solution to the isPalindrome() problem that does
   * NOT incorporate Stacks. I personally prefer it for it doesn't require me to
   * take the time to generate a Stack and there is only a single version of the
   * isPalindrome() method needed instead of two. Plus, it runs on O(n) time
   * instead of O(n^2) as per the solution I used above. The only downside is that
   * it will essentially destroy the original DoublyLinkedList unless I create a
   * copy of it but that in itself will raise the time to O(n^2). Even so, it's
   * much easier to read than the version that makes use of the Stack and DoublyLinkedList.
   * I only did that to demonstrate that I know how a stack works.
   */
  /*
  public boolean isPalindrome(DoublyLinkedList list) {
    if (list.isEmpty()) {
      return true;
    }
    else if (list.getFirst().data == list.getLast().data) {
      list.deleteFirst();
      list.deleteLast();
      return isPalindrome(list);
    }

    return false;
  }
  */
}
