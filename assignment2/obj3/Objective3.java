package obj3;

import java.util.Scanner;       // reading in input from supplied file
import utilities.Utils;         // converting String to long array
import linkedlist.LinkList;     // Singly linked list
import linkedlist.ListIterator; // iterator to go over singly linked list
import linkedlist.Link;         // needed for findIntersectingLink() method

/**
 * 3) Given two singly linked lists, determine if the two lists intersect. Return
 * the intersecting node. Note that the intersection is defined based on reference,
 * not value. That is, if the nth node of the first linked list is the exact same
 * node (by reference) as the jth node of the second linked list, then they are intersecting.
 *
 *    a) Find the lengths of each list and tail.
 *    b) Compare the tails. If they are different there is no intersection.
 *    c) Set pointers to the start of each linked list.
 *    d) Traverse each linked list until the pointers are the same.
 *    e) Sample input
 *      i. [1,3,5,7] and [2,4,5,6] —> the lists intersect at 5
 */
public class Objective3 {
  public Objective3() {
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

      // if there is no other line, break for there is nothing to check for intersections with arr1
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
        LinkList list1 = new LinkList(arr1);
        LinkList list2 = new LinkList(arr2);

        list1.displayList();
        list2.displayList();
        createIntersection(list1, list2);
        Link intersect = findIntersectingLink(list1, list2);
        if (intersect != null) {
          System.out.println("Intersection found at node with value: " + intersect.data);
        }
        else {
          System.out.println("No intersection found.");
        }
      }

      // shift down line for readability
      System.out.println();

      // finished processing pair of lines, go to next pair
      line += 2;
    }

    // file has been fully read and processed, end program
    System.out.println("Objective3 complete!");
    sc.close();
  }

  /**
   * Due to the way I will be reading in data, this method is imperative for it
   * accepts two LinkLists then checks if they overlap from their tails forward.
   * If there is any overlap, then the method will have both lists point to the
   * beginning of the intersection where they naturally should. For example,
   * lets say we read in the following two lines:
   *
   *     1, 2, 5, 10, 15, 9, 0  // shifted to bring attention to where they overlap
   *  1, 3, 10, 1, 8, 15, 9, 0
   *
   * When first converted into linked lists, previous two lines will have each
   * node point to eachother like so:
   *
   *       [1]->[2]->[5]->[10]->[15]->[9]->[0]  // shifted again to aid visualizing the overlap
   *  [1]->[3]->[10]->[1]->[8]->[15]->[9]->[0]
   *
   * Despite them both lists ending in 15, 9, and 0, there are two completely separate nodes
   * holding the value 15 that point to two completely separate nodes holding the value
   * 9, and then the same with the nodes holding value 0.
   *
   * At this point, the two lists don't actually intersect via reference for there
   * are two completely different nodes holding the value of 15 in each list.
   *
   * What the createIntersection() method will do from here is have both lists
   * reference the same node with value 15 where they naturally should. As such,
   * both lists now intersect with eachother due to reference. The final result
   * will look like this:
   *
   *       [1]->[2]->[5]->[10]--+
   *                            |
   *                            +-->[15]->[9]->[0]
   *                            |
   *  [1]->[3]->[10]->[1]->[8]--+
   *
   * @param list1 The first list we wish to have intersect with the second
   * @param list2 The second list we wish to have intersect with the first
   */
  public void createIntersection(LinkList list1, LinkList list2) {
    // if tails don't share the same data then it's safe to say there is no intersection
    if (list1.getTail().data != list2.getTail().data) {
      return;
    }

    // compare list sizes to even up their iterators
    long size1 = list1.getSize();
    long size2 = list2.getSize();
    long difference = Math.abs(size1 - size2);

    // create two iterators. one to represent the smaller list and one for the bigger
    ListIterator liLarger = size1 >= size2 ? list1.getIterator() : list2.getIterator();
    ListIterator liSmaller = size2 <= size1 ? list2.getIterator() : list1.getIterator();

    // adjust the larger iterator to match up with the smaller in remaining Links to iterate through
    for (long pos = 0; pos < difference; pos++) {
      liLarger.nextLink();
    }

    // begin checking for an intersection
    Link intersectingLargeLink = null;
    Link intersectingSmallLink = null;
    Link prevSmallLink = null;
    Link prevLargeLink = null;
    while (liLarger.getCurrent() != null) {
      // if there is overlap, take note of when it starts
      if (liLarger.getCurrent().data == liSmaller.getCurrent().data && intersectingLargeLink == null) {
        intersectingLargeLink = liLarger.getCurrent();
        intersectingSmallLink = liSmaller.getCurrent();
        prevSmallLink = liSmaller.getPrevious();
        prevLargeLink = liLarger.getPrevious();
      }
      // any overlap that was found has been lost, set intersectingLink back to null
       else if (liLarger.getCurrent().data != liSmaller.getCurrent().data) {
        intersectingLargeLink = null;
        intersectingSmallLink = null;
        prevSmallLink = null;
        prevLargeLink = null;
      }

      // advance both iterators forward
      liLarger.nextLink();
      liSmaller.nextLink();
    }

    // if intersectingLargeLink isn't null, then we know the two lists overlap and can
    // have them point to the same intersecting node
    if (intersectingLargeLink != null) {
      // if prevLink is null then we know both lists overlap completely
      // as such, we can just set the intersecting Link to be the new first
      if (prevSmallLink == null) {
        liSmaller.setFirst(intersectingLargeLink);
      }
      else {
        prevLargeLink.next = intersectingSmallLink;
      }
    }
  }

  /**
   * Used to check for intersecting Links between two LinkLists. If one is found,
   * we return the Link where they intersect. If they do not intersect, return null
   *
   * @param list1 The first list to check for an intersection
   * @param list2 The second list to check for an intersection with list 1
   * @param Link The Link both lists intersect eachother at. Null if they don't intersect
   */
  public Link findIntersectingLink(LinkList list1, LinkList list2) {
    // if tails don't share the same data then it's safe to say there is no intersection
    if (list1.getTail().data != list2.getTail().data) {
      return null;
    }

    // compare list sizes to even up their iterators
    long size1 = list1.getSize();
    long size2 = list2.getSize();
    long difference = Math.abs(size1 - size2);

    // create two iterators. one to represent the smaller list and one for the bigger
    ListIterator liLarger = size1 >= size2 ? list1.getIterator() : list2.getIterator();
    ListIterator liSmaller = size2 <= size1 ? list2.getIterator() : list1.getIterator();

    // adjust the larger iterator to match up with the smaller in remaining Links to iterate through
    for (long pos = 0; pos < difference; pos++) {
      liLarger.nextLink();
    }

    // loop through until we find a match
    while (liLarger.getCurrent() != null) {
      // check for a match
      if (liLarger.getCurrent() == liSmaller.getCurrent()) {
        // it's been found! Return the link
        return liLarger.getCurrent();
      }

      // advance forward
      liLarger.nextLink();
      liSmaller.nextLink();
    }

    // no match found, return null
    return null;
  }
}
