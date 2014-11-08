
package com.CSV.io;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of the CSV {@link Writer} interface for tab separated values.
 *
 * @note Delegates the actual implementation to the popular OpenCSV open source library
 * @see {http://opencsv.sourceforge.net/}
 *
 * @author rahulchaudhari
 *
 */
public class TabSeparatedWriter implements Writer {

    private final CSVWriter mCSVWriterDelegate;

    public TabSeparatedWriter(BufferedWriter bufferedWriter) throws IOException {
       mCSVWriterDelegate = new CSVWriter(bufferedWriter, '\t');
    }

    @Override
    public void append(List<String[]> lines) {
        mCSVWriterDelegate.writeAll(lines);
    }

    @Override
    public void append(String[] line) {
        mCSVWriterDelegate.writeNext(line);
    }

    @Override
    public void close() throws IOException {
        mCSVWriterDelegate.close();
    }
}
