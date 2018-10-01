/******************************************************************************
 * Author: Alexander M. Aguilar
 * Class: CISC 3130
 * Section: MY9
 ******************************************************************************/

import obj1.Objective1;
import obj2.Objective2;
import obj3.Objective3;
import obj4.Objective4;

public class Assignment2 {
  public static void main(String[] args) {

    // general help message if program is ran incorrectly
    String helpMsg = "To run this program, you must enter which objective you\n" +
                     "wish to run and give an input file like so:\n\n" +
                     "java Assignment2 1 < obj1/input.txt";

    // no objective supplied, exit program
    if (args.length == 0) {
      System.out.println(helpMsg);
      return;
    }

    // run chosen objective
    switch (args[0].charAt(0)) {
      case '1':
        new Objective1();
        break;

      case '2':
        new Objective2();
        break;

      case '3':
        new Objective3();
        break;

      case '4':
        new Objective4();
        break;

      default:
        // invalid input, exit program
        System.out.println(helpMsg);
    }
  }
}
