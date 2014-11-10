
package com.CSV.io;

import java.io.IOException;
import java.util.List;

/**
 * Interface for writing CSV (comma-separated values) files.
 *
 *  The following default behaviour applies:
 * <ul>
 *     <li>The default separator value is ','. </li>
 *     <li>The default quote character is '"'</li>
 *     <li>The default escape character is '\\'</li>
 *     <li>Default line terminator uses platform encoding.</li>
 * </ul>
 * Implementations are free to override the above default values.
 *
 * @author rahulchaudhari
 *
 */
public interface Writer {

    /**
     * Writes the entire list to a CSV file. Each element is assumed to be a line of tokens as a String[].
     * @param lines a List of String[], with each String[] representing a line of the file containing csv tokens.
     */
    void append(List<String[]> lines);

    /**
     * Writes the next line to the file
     * @param line a string array with each comma-separated element as a separate entry.
     */
    void append(String[] line);

    /**
     * Close the underlying stream flushing any buffered content
     * @throws IOException on error during close
     */
    void close() throws IOException;
}
