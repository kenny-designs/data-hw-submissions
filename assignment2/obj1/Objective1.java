package obj1;

import java.util.Scanner;       // reading in input from supplied file
import utilities.Utils;         // converting String to long array
import linkedlist.LinkList;     // Singly linked list
import linkedlist.ListIterator; // iterator to go over singly linked list

/**
 * 1) Write code to remove duplicates from an unsorted linked list using iterators.
 *
 *    a) Use two pointers: current which iterates through the linked list and runner
 *       which checks all subsequent nodes for duplicates.
 *
 *    b) It should run in O(n^2) time - that means you shouldn’t have to use more
 *       than 2 nested loops.
 *
 *    c) It is okay to sort input before checking for duplicate values.
 *
 *    d) Sample Inputs (output does not have to be sorted)
 *        i) 4,2,1,2,8 —> 4,2,1,8
 *        ii) 1,6,3,3,1,9,2 —> 1,6,3,9,2
 */
public class Objective1 {
  public Objective1() {
    // create Scanner to read in input from given file
    Scanner sc = new Scanner(System.in);

    // loop through each line in the given file for long values
    // while reporting which line we were just on
    int line = 1;
    while (sc.hasNextLine()) {
      // indicate which line we are reading from
      System.out.printf("Line %d\n" +
                        "-------\n", line);

      // read the line and convert it to a long array
      long[] arr = Utils.stringToLongArray(sc.nextLine());

      // if a long array was successfully formed, perform the objective
      if (arr != null) {
        LinkList list = new LinkList(arr);  // create list from long array

        // display list before removing duplicates
        System.out.print("Before removing duplicates: ");
        list.displayList();

        // remove any duplicates
        removeDuplicates(list);

        // display list without duplicates
        System.out.print("After removing duplicates:  ");
        list.displayList();
      }

      // shift down a line for readability
      System.out.println();

      // go on to next line
      line++;
    }

    // file has been fully read and processed, end program
    System.out.println("Objective1 complete!");
    sc.close();
  }

  /**
   * Takes a given LinkList then removes duplicates of any Links that share the
   * same value as one another.
   *
   * @param list The LinkList to remove all duplicates from
   */
  public static void removeDuplicates(LinkList list) {
    // list is empty. As such, there are no duplicates to delete so return
    if (list.isEmpty()) {
      return;
    }

    // declare two iterators. current is used for the link we are checking for
    // duplicates in the LinkList and runner checks all subsequent links after
    // current and deletes any duplicates
    ListIterator current, runner;

    // start current at the beginning of the LinkList
    current = list.getIterator();

    // loop through each Link in the list to check for duplicate values
    while (current.getCurrent() != null) {
      // set runner to point to the Link directly after the current Link in current
      runner = current.copy();
      runner.nextLink();

      // loop through all subsequent Links that come after current's current Link
      // we check if runner's current is null if it's the same as the head of our list
      // for deleteCurrent() will reset runner to the beginning of the list if it deletes
      // the final link and nextLink will change current to null if we were just on the
      // last link of the list
      while (runner.getCurrent() != null && runner.getCurrent() != list.getFirst()) {
        // check if two Links share the same value
        if (current.getCurrent().data == runner.getCurrent().data) {
          // Links are duplicates of each other, delete then continue checking
          runner.deleteCurrent();
        }
        else {
          // no duplicate found, advance to next Link in runner
          runner.nextLink();
        }
      }
      // finished iterating through runner, continue on to next Link in current
      current.nextLink();
    }
  }
}
