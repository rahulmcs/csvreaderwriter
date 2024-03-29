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
     * writing tab separated tokens to the specified file.
     *
     * Example usage:
     * <pre>
     *  com.CSV.io.Writer tabSeparatedWriter = null;
     * {@code
     * try {
     *      tabSeparatedWriter = com.CSV.io.WriterFactory.getTabSeparatedFileWriter("data.txt");
     *      tabSeparatedWriter.append(new String[]{"abc", "xyz", "pqr"});
     *
     *   } catch(IOException e) {
     *      //log exception
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
     * @throws IOException error during file operations
     */
    public static Writer getTabSeparatedFileWriter(final String fileName) throws IOException{
        return new TabSeparatedWriter( new FileWriter(fileName) );
    }

}
