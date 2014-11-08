package com.CSV.io;

import java.io.*;

/**
 * Factory class for the different CSV writer implementations
 *
 * @author rahulchaudhari
 */
public class WriterFactory {

    /**
     * Gets an instance of a CSV writer (@link #com.CSV.io.TabSeparatedWriter} capable of
     * writing tab separated tokens to specified file.
     *
     * Example usage:
     * <pre>
     * {@code
     * try {
     *      com.CSV.io.Writer tabSeparatedWriter = WriterFactory.getTabSeparatedFileWriter("data.txt");
     *      tabSeparatedWriter.append(new String[]{"abc", "xyz", "pqr"});
     *
     *   } catch(IOException e) {
     *      //log
     *  } finally {
     *      try {
     *          tabSeparatedWriter.close();
     *      } catch(Exception e) {}
     *  }
     * }
     * </pre>
     *
     * @param fileName file to which tab-separated csv values be written
     * @return instance of csv Writer capable of writing tab separated values to file.
     *
     * @throws IOException error during file IO
     */
    public static Writer getTabSeparatedFileWriter(final String fileName) throws IOException{
        return new TabSeparatedWriter( new BufferedWriter( new FileWriter(fileName)) );
    }

}
