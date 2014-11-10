
package com.CSV.AddressProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @deprecated As of version 1.1, replaced by the new {@link com.CSV.io.Reader} and {@link com.CSV.io.Writer}
 * interfaces. For the new usage, please refer to {@link com.CSV.io.ReaderFactory} and {@link com.CSV.io.WriterFactory}
 *
 * This class has been deprecated since the class and each of its methods have serious design flaws
 * thereby rendering the api to be confusing, insecure and highly inefficient:
 * <ul>
 * <li> 1. Provides a fat and confusing interface with read and write methods in the same class.</li>
 * <li> 2. The {@link #open} method provides a confusing {@link Mode} parameter which then places
 * an additional burden on {@link #write} and {@link #read} methods to perform sanity checks. We would be better
 * off by having two classes - one that reads from and the other that writes to CSV tab separated files</li>
 * <li> 3. The {@link #write method uses var args whereas it is semantically more accurate in this context to
 * simply pass in an array of strings representing a tab separated line}</li>
 * <li> 4. The {@link #read(String[]) read} method requires a pre-allocated string array as input however it is
 * unreasonable for the caller to know the size in advance</li>
 * <li> 5. The {@link #read(String, String) read} method is redundant in favour of {@link #read(String[]) read}</li>
 * </ul>
 *
 * Please also see inline javadoc comments pointing out deficiencies that further motivate deprecation of the class.
 */
@Deprecated public class CSVReaderWriter {
    private BufferedReader _bufferedReader = null;

    private BufferedWriter _bufferedWriter = null;

    public enum Mode {
        Read(1), Write(2);

        private int _mode;

        Mode(int mode) {
            this._mode = mode;
        }

        public int getMode() {
            return _mode;
        }
    }

    /** RC: Missing api documentation */
    /** RC: Should throw a IOException rather than a general Exception */
    /** RC: A mode in this context is very confusing since this class allows both reading and writing.
     *  RC: What happens when you open the file in read mode but then invoke write? It might be worth
     * splitting read and write functionality into separate classes
     * */
    public void open(String fileName, Mode mode) throws Exception {
        if (mode == Mode.Read) {
            _bufferedReader = new BufferedReader(new FileReader(fileName));
        } else if (mode == Mode.Write) {
            FileWriter fileWriter = new FileWriter(fileName);
            _bufferedWriter = new BufferedWriter(fileWriter);
        } else {
            /** RC: Is this even possible? */
            throw new Exception("Unknown file mode for " + fileName);
        }
    }

    /** RC: Missing api documentation */
    /** RC: Would be worth passing in a string array representing a line of tab separated tokens rather than
     * variable arguments as the former is more semantically accurate and consistent.
     */
    public void write(String... columns) throws IOException {
        String outPut = "";

        /** RC: As indicated above, what happens if the file was opened in read mode - should this function
         * not check the mode and only operate in write mode?
         */

        /** RC: More efficient to use a StringBuilder to perform the join operation rather than
         * use the inefficient + operator in a loop.
         *
         * However, one should note that RFC 4180 provides a de facto standard CSV format and
         * it is preferable to use a well tested open source library (released under Apache 2.0 preferably)
         * to achieve the tab limited CSV file writing/Reading.
         * {@see <a href="http://opencsv.sourceforge.net">http://opencsv.sourceforge.net</a>}
         * is one such popular library which is heavily tested, robust and reliable.
         */
        for (int i = 0; i < columns.length; i++) {
            outPut += columns[i];
            if ((columns.length - 1) != i) {
                outPut += "\t";
            }
        }

        writeLine(outPut);
    }

    /** RC: Missing api documentation */
    /** RC: Would be worth returning a string array representing a line of tab separated tokens.
     * RC: Redundant boolean return value since the api throws IOException anyways.
     */
    public boolean read(String[] columns) throws IOException {
        final int FIRST_COLUMN = 0;
        final int SECOND_COLUMN = 1;

        /** RC: what is the purpose of the above two? Does the api only read first two tab separated tokens?
         * If so that is not obvious anywhere from the method signature?
         */

        String line;
        String[] splitLine;

        String separator = "\t";

        /** RC: As indicated above, what happens if the file was opened in write mode - should this function
         * not check the mode and only operate in read mode?
         */

        line = readLine();

        /** RC: readLine could return empty or null - need check */
        splitLine = line.split(separator);

        /** RC:  columns empty check needed before populating */
        if (splitLine.length == 0) {
            columns[0] = null;
            columns[1] = null;

            return false;
        } else {
            columns[0] = splitLine[FIRST_COLUMN];
            columns[1] = splitLine[SECOND_COLUMN];

            return true;
        }

        /** However, one should note that RFC 4180 provides a de facto standard CSV format and
         * it is preferable to use a well tested open source library (released under Apache 2.0 preferably)
         * to achieve the tab limited CSV file writing/Reading.
         * {@see <a href="http://opencsv.sourceforge.net">http://opencsv.sourceforge.net</a>}
         * is one such popular library which is heavily tested, robust and reliable.
         */
    }

    /** RC: Missing api documentation */
    /** RC: This method is really redundant as there needs to be only a method that reads an array of string tokens?*/
    public boolean read(String column1, String column2) throws IOException {
        final int FIRST_COLUMN = 0;
        final int SECOND_COLUMN = 1;

        String line;
        String[] splitLine;

        String separator = "\t";

        /** RC: As indicated above, what happens if the file was opened in write mode - should this function
         * not check the mode and only operate in read mode?
         */

        line = readLine();

        if (line == null) {
            column1 = null;
            column2 = null;

            return false;
        }

        splitLine = line.split(separator);

        if (splitLine.length == 0) {
            column1 = null;
            column2 = null;

            return false;
        } else {
            column1 = splitLine[FIRST_COLUMN];
            column2 = splitLine[SECOND_COLUMN];

            return true;
        }
    }

    private void writeLine(String line) throws IOException {
        /** RC: missing empty or null check for line */
        _bufferedWriter.write(line);
    }

    private String readLine() throws IOException {
        return _bufferedReader.readLine();
    }

    /** RC: missing API documentation */
    public void close() throws IOException {

        if (_bufferedWriter != null) {
            _bufferedWriter.close();
        }

        if (_bufferedReader != null) {
            _bufferedReader.close();
        }
    }

}
