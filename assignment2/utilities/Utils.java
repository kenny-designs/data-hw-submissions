package utilities;

import java.util.Arrays;  // splitting input into long arrays

/**
 * A collection of utility methods used throughout the program
 */
public final class Utils {
  private Utils() {}

  /**
   * Takes a String of space separated numbers then returns them as an array of longs.
   * If the given String has illegal characters, such as letters or decimals, then
   * the returned long array is null.
   *
   * This method is forgiving with its input for it trims off leading and trailing
   * whitespace and separates each value regardless of the number of whitespace
   * characters between them so long as there is at least one whitespace character.
   *
   * Examples:
   *  Legal and ideal input:  "1 12 54 100 -1"
   *
   *  Legal but messy input:  " 123 12 -5 13  "
   *                          "1 43    4 1"
   *                          "1  2 10"
   *                          "1 2 09 0012 00006"
   *
   *  Illegal inputs:         "1 3 54 F"
   *                          "54 4 6 1.2"
   *                          ""
   *                          "  "
   *
   * @param str String of space separated numbers to convert to a long array
   * @param long[] Reference to the long array generated from the given String or
   * just null if the given String could not be converted to a long array.
   */
  public static long[] stringToLongArray(String str) {
    // long array we'll be returning, is null by default
    long[] arr = null;

    try { // attempt to turn given String into a long array
      // in order to generate the long array, I decided to make use of a Java 8
      // addition to the Arrays class known as the stream() method.
      // To begin, I will use the stream() method to create a Stream<String> object
      // by passing in the resulting String array of trimming our given str and splitting
      // each entry by whitespace.
      // From there, I invoke mapToLong() from our resulting Stream<String> object
      // and pass it the method parseLong from the Long class. This will result in
      // a LongStream object in which each element in our Stream<String> object will
      // be converted to a long. If there are any illegal characters, such as letters
      // or floating point values, mapToLong will throw NumberFormatException. If
      // all is well, we now have a LongStream object that we convert to an array with
      // its toArray() method and store the resulting reference in arr
      arr = Arrays.stream(str.trim().split("\\s+"))
        .mapToLong(Long::parseLong)
        .toArray();
    }
    // handle exception when unable to convert to long
    catch (NumberFormatException e) {
      // report error to user
      System.out.println("Could not convert to long array due to: " + e);
    }

    // return resulting long array
    return arr;
  }
}
