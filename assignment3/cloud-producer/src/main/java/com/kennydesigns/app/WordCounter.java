package com.kennydesigns.app;

// Used for tracking word frequency
import java.util.HashMap;

// Used for return the entry set
import java.util.Map.Entry;
import java.util.Set;

/**
 * Makes use of HashMap to track the frequency certain lyrics appear
 */
public class WordCounter {
  private HashMap<String, Integer> frequency = new HashMap<>();

  /**
   * Increment the value associated with the given key
   */
  public void incrementCounter(String key) {
    // if the key exists, store its value. If not, set to 0
    Integer val = frequency.containsKey(key) ? frequency.get(key) : 0;

    // update the key/value pair in the HashMap
    frequency.put(key, val + 1);
  }

  /**
   * Used for returning the entry set
   */
  public Set<Entry<String, Integer>> getEntrySet() {
    return frequency.entrySet();
  }
}
