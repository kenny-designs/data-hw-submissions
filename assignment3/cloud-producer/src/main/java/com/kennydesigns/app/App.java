package com.kennydesigns.app;

// Reading in files with lyrics
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import com.kennydesigns.app.WordCounter;

// Data structures
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

// import needed classes for kumo word cloud
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Entry point into our application
 */
public class App {
  private static final String LYRICS = "./input/lyrics.txt";
  private static final String OUTPUT = "./output/wordcloud.png";
  private static final String FILTER = "[(),]";

  public static void main( String[] args ) throws IOException {
    // read in the lyrics and count the frequency with which they appear
    WordCounter wc = readLyrics();

    // create a WordFrequency List for generating the WordCloud
    List<WordFrequency> wordFrequencies = new ArrayList<WordFrequency>();

    // populate the WordFrequency List based on our WordCounter
    for (Entry<String, Integer> entry : wc.getEntrySet()) {
      wordFrequencies.add(new WordFrequency(entry.getKey(), entry.getValue()));
    }

    // create the WordCloud
    final Dimension dimension = new Dimension(1000, 1000);
    final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
    wordCloud.setPadding(2);
    wordCloud.setBackground(new PixelBoundryBackground("./input/windmill.png"));
    wordCloud.setColorPalette(new ColorPalette(new Color(0x934334), new Color(0x2F472D), new Color(0x75B84F), new Color(0xFFFFFF)));
    wordCloud.setFontScalar(new LinearFontScalar(20, 60));
    wordCloud.build(wordFrequencies);
    wordCloud.writeToFile(OUTPUT);
  }

  /**
   * Read the lyrics from LYRICS and count their frequency with WordCounter
   */
  public static WordCounter readLyrics() {
    // utilized to count lyrics via a HashMap
    WordCounter wc = new WordCounter();

    try {
      // read in lyrics
      Scanner sc = new Scanner(new File(LYRICS));

      // log each word in lyric and increment its count in WordCounter
      while (sc.hasNext()) {
        // remove any non-word elements
        wc.incrementCounter(sc.next().toLowerCase().replaceAll(FILTER, ""));
      }

      sc.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // return the final WordCounter
    return wc;
  }
}
