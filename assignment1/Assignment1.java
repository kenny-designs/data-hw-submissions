/******************************************************************************
*  Compilation:  javac Assignment1.java
*  Execution:    java Assignment1 < input.txt
*
*  Reads in a text file and for each line verifies whether the word has
*  unique characters.
*
*  % cat input.txt
*  Hello
*  World
*
*  % java Assignment1 < input.txt
*  False
*  True
*
******************************************************************************/
import java.util.Scanner;

/**
 * Author: Alexander M. Aguilar
 * Class: CISC 3130
 * Section: MY9
 */
public class Assignment1 {
  /**
   * Checks to see if given string has duplicate chars. Capitalization matters so
   * 'a' and 'A' are counted as two separate chars!
   *
   * For my implementation, I decided to first sort the given String then check
   * if any chars are repeated. I do this by first breaking the given String s into
   * a char array with the String class method toCharArray(). I store a reference to the
   * resulting char array in the variable sorted. I then pass sorted into the method insertionSort()
   * that, as per the name, sorts our char array. I can now assume that any repeating characters
   * will be immediately next to each other so I simply loop through the char array checking
   * if the previous value is the same. If so, then the given String does not have all unique chars
   * so we return false. If absolutely so duplicates were found though, we return true for this
   * given String does indeed have all unique characters.
   *
   * @param s The string to check for duplicates
   * @return boolean True if given string has no duplicate chars, otherwise false
   */
  private static boolean isUniqueChar(String s) {
    // Convert the given String into a char array
    char[] sorted = s.toCharArray();

    // sort the resulting char array with the insertionSort() method
    insertionSort(sorted);

    // loop through the sorted array checking to see if any characters are repeated
    for (int j = 1; j < sorted.length; j++) {
      // if the current char is the same as the previous, we know the given string
      // is not unique so immediately return false
      if (sorted[j-1] == sorted[j]) {
        return false;
      }
    }
    // no duplicate chars were found! Return true
    return true;
  }

  /**
   * Takes a string then converts it to all lower case before being fed into
   * method insertionSort() to be sorted based on the ASCII values of each char
   * @param s The word we wish to sort
   * @return String The sorted word
   */
  private static String sortWord(String s) {
    // Convert the string to lowercase then break it into a char array
  	char[] c = s.toLowerCase().toCharArray();

    // sort c
  	insertionSort(c);

    // return the char array as a new String
  	return new String(c);
  }

  /**
   * Sorts the given char array based on each chars ASCII value
   * @param word The char array to sort
   */
  public static void insertionSort(char[] word) {
  	if (word == null || word.length == 0) {
  		return;
  	}

  	for (int i=0; i<word.length; i++) {
  		char temp = word[i];
  		int j = i;
  		while (j>0 && word[j-1]>temp) {
  			word[j] = word[j-1];
  			j--;
  		}
  		word[j] = temp;
  	}
  }

  public static void main(String[] args) {
  	Scanner scanner = new Scanner(System.in);

  	// read in words and determine whether it is composed of unique characters
  	while (scanner.hasNextLine()) {
  		String s = scanner.nextLine();
      // report whether or not s has all unique chars then show the sorted version of s
  		System.out.println("\n" + isUniqueChar(s) + "\n" + sortWord(s));
  	}

  	scanner.close();
  }
}
