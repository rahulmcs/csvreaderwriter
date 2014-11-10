
package com.CSV.io;

import java.io.IOException;
import java.util.List;

/**
 * Interface for reading CSV (comma-separated values) files.
 *
 * The following default behaviour applies:
 * <ul>
 *     <li>The default separator value is ','. </li>
 *     <li>The default quote character is '"'</li>
 *     <li>The default escape character is '\\'</li>
 * </ul>
 * Implementations are free to override the above default values.
 *
 * @author rahulchaudhari
 *
 */
public interface Reader {

    /**
     * Reads the entire file into a List with each element being a String[] containing a line of csv tokens.
     *
     * @return a List of String[], with each String[] representing a line of the file containing csv tokens.
     * @throws IOException on error during reading
     */
    List<String[]> readAll() throws IOException;

    /**
     * Reads the next line of csv tokens from the file and converts to a string array.
     *
     * @return a string array with each csv as a separate entry.
     * @throws IOException on error during reading
     */
    String[] readNextLine() throws IOException;

    /**
     * Closes the underlying reader stream.
     * @throws IOException on error during close
     */
    void close() throws IOException;
}
