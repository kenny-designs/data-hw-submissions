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

public class Assignment1 {
  /**
   * Checks to see if given string has duplicate chars. Capitalization matters so
   * 'a' and 'A' are counted as two separate chars!
   *
   * For my implementation, I make use of double for loops. The outer loop works
   * by extracting one char at a time from the given String to check for duplicates.
   * The inner loop iterates through every char after the extracted one to see if
   * any duplicates of it exist. If there happens to be one, we immediately return
   * false thus dropping out of the function. If no duplicates were found, we fall back
   * to the outer loop and try the next char in the String. If absolutely no duplicates
   * exist, we return true.
   *
   * @param s The string to check for duplicates
   * @return boolean True if given string has no duplicate chars, otherwise false
   */
  private static boolean isUniqueChar(String s) {
  	// outer loop extracts the char we wish to check for duplicates
  	for (int j = 0; j < s.length(); j++) {
  		char c = s.charAt(j);
  		// inner loop checks if a duplicate of the extracted char exists
  		for (int k = j+1; k < s.length(); k++) {
  			// upon finding a duplicate, return false
  			if (c == s.charAt(k)) {
  				return false;
  			}
  		}
  	}

  	// no duplicates found, return true
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
