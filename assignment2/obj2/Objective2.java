package obj2;

import java.util.Scanner;       // reading in input from supplied file
import utilities.Utils;         // converting String to long array
import linkedlist.LinkList;     // Singly linked list
import linkedlist.ListIterator; // iterator to go over singly linked list

/**
 * 1) Merge two sorted linked lists into one sorted singly linked list.
 *
 *    a) Inputs are sorted, you can assume smallest to largest values.
 *
 *    b) Sample input:
 *        i) [1,3,5] and [2,4,5,6] —> [1,2,3,4,5,5,6]
 */
public class Objective2 {
  public Objective2() {
    // create Scanner to read in input from given file
    Scanner sc = new Scanner(System.in);

    // extract two lines at a time from the given file converting them into
    // sorted arrays to be merged and presented to the user
    int line = 1;
    while (sc.hasNextLine()) {
      // will be used to create out two linked lists that we wish to merge
      long[] arr1, arr2;

      // report which lines we are currently on
      System.out.printf("Lines %d and %d\n" +
                        "---------------\n", line, line+1);

      // convert the first line to a long array
      arr1 = Utils.stringToLongArray(sc.nextLine());

      // if there is no other line, break for there is nothing to merge arr1 with
      if (!sc.hasNextLine()) {
        System.out.println("No legal pairs of lines left to read, exiting program.\n");
        break;
      }

      // convert next line to a long array
      arr2 = Utils.stringToLongArray(sc.nextLine());

      // if either line is invalid, report which one and move on
      if (arr1 == null || arr2 == null) {
        if (arr1 == null && arr2 == null) {
          System.out.printf("Both lines %d and %d contain illegal long values!\n", line, line+1);
        }
        else {
          System.out.printf("Line %d contains illegal long values!\n",
            line + (arr1 == null ? 0 : 1));
        }
      }
      else {
        // if both long arrays were successfully formed, perform the objective
        LinkList sortedList1 = createSortedList(arr1);
        LinkList sortedList2 = createSortedList(arr2);

        // display first linked list
        System.out.print("First linked list:     ");
        sortedList1.displayList();

        // display the second
        System.out.print("Second linked list:    ");
        sortedList2.displayList();

        // display them merged together
        System.out.print("Lists merged together: ");
        mergeSortedLists(sortedList1, sortedList2).displayList();
      }

      // shift down line for readability
      System.out.println();

      // finished processing pair of lines, go to next pair
      line += 2;
    }

    // file has been fully read and processed, end program
    System.out.println("Objective2 complete!");
    sc.close();
  }

  /**
   * returns a sorted LinkList based on given array of nums from low to high
   *
   * @param nums Array of long values to turn into a sorted linked list
   * @return LinkList The resulting sorted linked list
   */
  public LinkList createSortedList(long[] nums) {
    // create our soon to be sorted list to return
    LinkList list = new LinkList();

    // create a ListIterator that references our LinkList
    ListIterator li = list.getIterator();

    // insert each element from the given long array into the LinkList one at a time
    for (long n : nums) {
      // set iterator back to beginning
      li.reset();

      // iterate through list until we find the correct position for n to be inserted
      while (li.getCurrent() != null && n > li.getCurrent().data) {
        li.nextLink();
      }

      // insert the new link then continue to populate the rest of the sorted array
      li.insertBefore(n);
    }

    return list;
  }

  /**
   * Merges two sorted linked lists into one sorted list.
   *
   * @param list1 The first sorted list to merge
   * @param list2 The second sorted list to merge
   */
  public LinkList mergeSortedLists(LinkList list1, LinkList list2) {
    // create a LinkList to hold the final merged sorted list
    LinkList mergedList = new LinkList();

    // to begin, let's populate mergedList with the same values as list1
    // to do that, we create two iterators. One for merged list and one for list1
    ListIterator li = list1.getIterator();
    ListIterator mergedLi = mergedList.getIterator();

    // loop through all links in list1 inserting the data of each one into mergedList
    while (li.getCurrent() != null) {
      mergedLi.insertAfter(li.getCurrent().data);
      li.nextLink();
    }

    // now that mergedList is an exact copy of list1, we can insert list2 two
    // in to it now. We do that by first getting the ListIterator for list2
    li = list2.getIterator();

    // now we'll loop through every value in list2 inserting them into mergedList
    while (li.getCurrent() != null) {
      // reset mergedList so we can seek out where to insert from the beginning
      mergedLi.reset();

      // continue looping through mergedList until we find a suitable spot to insert our list2 value
      while (mergedLi.getCurrent() != null && li.getCurrent().data > mergedLi.getCurrent().data) {
        mergedLi.nextLink();
      }

      // insert the list2 value where it belongs
      mergedLi.insertBefore(li.getCurrent().data);
      // carry on to the next link in list2
      li.nextLink();
    }

    // we now have a fully merged list from lists 1 and 2! Return the new merged sorted list
    return mergedList;
  }
}
