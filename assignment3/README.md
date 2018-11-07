# Assignment 3
[Assignment 3 Website](https://kenny-designs.github.io/data-hw-submissions/assignment3/word-cloud-site/)

[output.txt](./cloud-producer/output/output.txt)

To compile the program, open the [cloud-producer](./cloud-producer/) directory on the command line and enter the following:

```
mvn package
```

After successfully running the previous command, enter the following to run the program:

```
mvn exec:java
```

If you want to supply your own files, enter the following:

```
mvn exec:java -Dexec.args="./input/lyrics.txt ./input/windmill.png"
```

If you want to filter certain characters from each word, enter a regular expression as the third parameter like so:
```
mvn exec:java -Dexec.args="./input/lyrics.txt ./input/windmill.png [(),.!?]"
```

The regular expression [(),.!?] removes any of the characters within the [] from all words we encounter.

It may take a moment for the word cloud to finish but when it does the final rendered image will appear in the output folder
named wordcloud.png. You'll also find another file called output.txt that has all the 'frequency: word' pairs that were found
sorted from greatest to smallest.

![wordcloud](./cloud-producer/output/wordcloud.png)
