
package com.CSV.io;

import java.io.IOException;
import java.util.List;

/**
 * Interface for writing CSV (comma-separated values) files
 *
 * @author rahulchaudhari
 *
 */
public interface Writer {

    /**
     * Writes the entire list to a CSV file. The list is assumed to be a String[].
     * @param lines a List of String[], with each String[] representing a line of the file containing csv tokens.
     */
    void append(List<String[]> lines);

    /**
     * Writes the next line to the file
     * @param line a string array with each comma-separated element as a separate entry.
     */
    void append(String[] line);

    /**
     * Close the underlying stream writer flushing any buffered content
     * @throws IOException on error during close
     */
    void close() throws IOException;
}
