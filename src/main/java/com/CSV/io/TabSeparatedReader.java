
package com.CSV.io;

import au.com.bytecode.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of the CSV {@link Reader} interface for tab separated values.
 *
 * Delegates the actual implementation to the popular OpenCSV open source library
 * @see <a href="http://opencsv.sourceforge.net">http://opencsv.sourceforge.net</a>

 * @author rahulchaudhari
 *
 */
public class TabSeparatedReader implements Reader {
    private CSVReader mCSVReaderDelegate;

    public TabSeparatedReader(java.io.Reader reader) throws IOException {
        mCSVReaderDelegate = new CSVReader(reader, '\t');
    }

    @Override
    public List<String[]> readAll() throws IOException {
        return mCSVReaderDelegate.readAll();
    }

    @Override
    public String[] readNextLine() throws IOException {
        return mCSVReaderDelegate.readNext();
    }

    @Override
    public void close() throws IOException {
        mCSVReaderDelegate.close();
    }
}
