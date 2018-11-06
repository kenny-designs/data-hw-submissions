package com.kennydesigns.app;

// Used for tracking word frequency
import java.util.HashMap;

// Used for reading and writing text files containing the words to count
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

// Sorting the HashMap from the most frequent word to the least
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Tracks the frequency at which words appear in a given file
 */
public class WordCounter {
  // HashMap to check word frequency
  private HashMap<String, Integer> frequency = new HashMap<>();

  /**
   * Constructs a new WordCounter based on given file
   *
   * @param file The file we wish to find the frequency of
   */
  public WordCounter (String file) {
    // populate HashMap frequency based on given file and no filter
    this(file, "");
  }

  /**
   * Constructs a new WordCounter based on given file and filter
   *
   * @param file The file we wish to find the frequency of
   * @param filter Regular expression used to filter each word of unwanted characters
   */
  public WordCounter (String file, String filter) {
    // populate HashMap frequency based on given file and filter
    readFileFrequency(file, filter);
  }

  /**
   * Increment the value associated with the given key
   *
   * @param key The key we wish to increment the value of in the HashMap frequency
   */
  private void incrementCounter(String key) {
    // if the key exists, store its value. If not, set to 0
    Integer val = frequency.containsKey(key) ? frequency.get(key) : 0;

    // increment the value associated with the key by 1
    frequency.put(key, val + 1);
  }

  /**
   * Reads in the text from the given file and produces a HashMap based on how
   * frequent each word is
   *
   * @param file The file we wish to find the frequency of
   * @param filter Regular expression with characters we want removed from all words
   */
  private void readFileFrequency(String file, String filter) {
    try {
      // read in the given file
      Scanner sc = new Scanner(new File(file));

      // log each word from the file and find its frequency
      while (sc.hasNext()) {
        // grab the next word, make it lowercase, filter it, then increment its
        // value in the HashMap frequency
        incrementCounter(sc.next().toLowerCase().replaceAll(filter, ""));
      }

      // close the Scanner
      sc.close();
    }
    // the supplied file was not found
    catch (FileNotFoundException e) {
      // report to user what went wrong
      e.printStackTrace();
    }
  }

  /**
   * Sorts the entries in the HashMap from greatest to smallest and writes them
   * all to a .txt file
   *
   * @param outputFile The filer we wish to write our result out to
   */
  public void exportWords(String outputFile) {
    // create a list of keys
    List<String> keyList = new ArrayList<>();

    // populate keyList with all keys from HashMap frequency
    for (String key : frequency.keySet()) {
      keyList.add(key);
    }

    // sorts the keyList from highest to lowest based on the value associated with each key
    Collections.sort(keyList, new Comparator<String>() {
      // Sort from hightest to lowest
      public int compare(String s1, String s2) {
        return frequency.get(s2) - frequency.get(s1);
      }
    });

    try {
      // create FileWriter to create a txt file filled with word/frequency pairs
      FileWriter fw = new FileWriter(new File(outputFile));

      // iterate over the sorted list writing each one out to out outputFile
      for (String key : keyList) {
        // write to outputFile in the style of 'frequency: word'
        fw.write(frequency.get(key) + ": " + key + "\n");
      }

      // close FileWriter
      fw.close();
    }
    catch (IOException e) {
      // if anything goes wrong, report the exception to the user
      e.printStackTrace();
    }
  }
}
