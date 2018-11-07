/******************************************************************************
 * Author: Alexander M. Aguilar
 * Class: CISC 3130
 * Section: MY9
 ******************************************************************************/

package com.kennydesigns.app;

// Used for finding the frequency of words from a given file
import com.kennydesigns.app.WordCounter;

// Import needed classes for kumo to create the wordcloud
import java.io.IOException;
import java.io.File;
import java.util.List;
import com.kennycason.kumo.nlp.FrequencyFileLoader;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Entry point to program that reads in the lyrics.txt file and creates a word
 * word cloud based on it.
 */
public class App {
  private static final String OUTPUT_IMG = "./output/wordcloud.png",  // location to save wordcloud
                              OUTPUT_TXT = "./output/output.txt";     // location to save output.txt

  /**
   * Entry point to program
   */
  public static void main(String[] args) {
    // exit program if user gave too many or too few args
    if (args.length < 2) {
      System.out.println("Too few arguments. Please supply path to lyrics and path to image");
      System.exit(0);
    }
    else if (args.length > 3) {
      System.out.println("Too many arguments. At most, supply a path to lyrics, path to an image, and a regular expression");
      System.exit(0);
    }

    // retrieve location of the lyrics file, the image file, and optional regular expression from the user
    // filter defaults to an empty String if no regular expression was supplied
    String lyrics =   args[0],
           inputImg = args[1],
           filter =   args.length == 3 ? args[2] : "";

    // read in the lyrics and count the frequency with which they appear
    WordCounter wc = new WordCounter(lyrics, filter);

    // export the lyrics to a .txt file in the form of 'frequency: word'
    wc.exportWords(OUTPUT_TXT);

    // create the WordCloud
    System.out.println("Now creating wordcloud...");
    try {
      // load in the 'frequency: word' word pair based on the OUTPUT_TXT file
      final FrequencyFileLoader frequencyFileLoader = new FrequencyFileLoader();
      final List<WordFrequency> wordFrequencies = frequencyFileLoader.load(new File(OUTPUT_TXT));

      // create the dimensions of the new wordcloud and set its CollisionMode
      final Dimension dimension = new Dimension(1000, 1000);
      final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);

      // with the wordcloud created, set some basic properties
      wordCloud.setPadding(2);
      wordCloud.setBackground(new PixelBoundryBackground(inputImg));
      wordCloud.setColorPalette(new ColorPalette(new Color(0x934334), new Color(0x2F472D), new Color(0x75B84F), new Color(0xFFFFFF)));
      wordCloud.setFontScalar(new LinearFontScalar(20, 60));

      // build our wordcloud based on the frequency our words appeared
      wordCloud.build(wordFrequencies);

      // write the final cloud to the OUTPUT_IMG file
      wordCloud.writeToFile(OUTPUT_IMG);
      System.out.println("Success!");
    }
    catch (IOException e) {
      // could not build the wordcloud, report to user
      e.printStackTrace();
      System.out.println("Failed to create wordcloud.");
    }
  }
}
