package com.CSV.io;

import java.io.*;

/**
 * Factory class for the different CSV reader implementations
 *
 * @author rahulchaudhari
 */
public class ReaderFactory {

    /**
     * Gets an instance of a CSV reader (@link #com.CSV.io.TabSeparatedReader} capable of
     * reading tab separated tokens from the specified file.
     *
     * Example usage:
     * <pre>
     * {@code
     * com.CSV.io.Reader tabSeparatedReader = null;
     * try {
     *      tabSeparatedReader = ReaderFactory.getTabSeparatedFileReader("data.txt");
     *      String data[] = tabSeparatedReader.readNextLine();
     *
     *   } catch(IOException e) {
     *      //log
     *  } finally {
     *      try {
     *          tabSeparatedReader.close();
     *      } catch(Exception e) {}
     *  }
     * }
     * </pre>
     * @param fileName file containing tab-separated csv values
     * @return instance of csv Reader capable of reading tab separated values.
     *
     * @throws IOException error during file operations
     */
    public static Reader getTabSeparatedFileReader(final String fileName) throws IOException {
        return new TabSeparatedReader( new BufferedReader( new FileReader(fileName)) );
    }
}
