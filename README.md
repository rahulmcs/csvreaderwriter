csvreaderwriter
===============

A utility library to allow reading/writing of Tab separated tokens (CSV) to and from text files.

Historically, [CSVReaderWriter](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/AddressProcessing/CSVReaderWriter.java)  provided this functionality via a bloated, inconsistent and semantically inaccurate interface which has now been deprecated in favour of the refactored [Reader](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Reader.java) and [Writer](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Writer.java) interfaces. Please browse [CSVReaderWriter](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/AddressProcessing/CSVReaderWriter.java) to understand the now deprecated api and the motivation behind deprecating the entire class.

Package [io](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/) contains the newly refactored code alongwith javadocs to understand the method usage.

[ReaderFactory](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/ReaderFactory.java) and [WriterFactory](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/WriterFactory.java) provide factory methods to instantiate concrete implementations of the new interfaces. 

[TabSeparatedReader](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/TabSeparatedReader.java) and [TabSeparatedWriter](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/TabSeparatedWriter.java)  are concrete implementations of the [Reader](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Reader.java) and [Writer](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Writer.java) interfaces. They use the popular [OpenCSV](http://opencsv.sourceforge.net) library available under Apache 2.0 license for implementing the core CSV parsing and serialisation/de-serialisation logic thereby benefiting from a robust and standard implementation that has been heavily tested in production code. It should be noted that RFC 4180 provides a de-facto standard for the CSV format and it is in our interest to look for existing solutions that implement this standard rather than having to implement and maintain it in-house.

An interface oriented design allows for providing various concrete implementations of the [Reader](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Reader.java) and [Writer](https://github.com/rahulmcs/csvreaderwriter/blob/master/src/main/java/com/CSV/io/Writer.java) interfaces (e.g. an implementation for storing meta data alogwith CSV values) and also allows for changing the underlying implementation without breaking the public api.


New Usage
===============

To read a file `data.txt` containing just some tab separated values, the following code should suffice:

        com.CSV.io.Reader tabSeparatedReader = null;
        try {
            tabSeparatedReader = com.CSV.io.ReaderFactory.getTabSeparatedFileReader("data.txt");
            List<String[]> tokens = tabSeparatedReader.readAll();
            //consume tokens here ...
        } catch (IOException e) {
            // log exception
        } finally {
            try {
                tabSeparatedReader.close();
            } catch (Exception e) {
                //log exception
            }
        }

To write a few lines worth of tab separated values to a file `data.txt`, one would implement the following:

        com.CSV.io.Writer tabSeparatedWriter = null;
        try {
            tabSeparatedWriter = com.CSV.io.WriterFactory.getTabSeparatedFileWriter("data.txt");
            
            //create a two lines worth of tokens that need to be tab separated and added to data.txt
            ArrayList<String[]> lines = new ArrayList<String[]>();
            lines.add(new String[] {
                    "abc", "xyz", "pqr"
            });
            lines.add(new String[] {
                    "123", "456", "789"
            });
            tabSeparatedWriter.append(lines);
            
            //can append directly to the file as well
            tabSeparatedWriter.append(new String[] {
                    "reader", "writer"
            });
        } catch (IOException e) {
            // log exception
        } finally {
            try {
                tabSeparatedWriter.close();
            } catch (Exception e) {
            }
        }


Build Instructions
===============
The project uses gradle to build the java source files into a jar library and maven to push the generated jar artifacts to the maven local repository. Having maven and gradle already configured on your system is therefore a pre-requisite. It would also be simplest to install IntelliJ which has in-built support for gradle and maven.

Steps to follow on the Mac OSX command line (not tested on windows):

1. Clone the github repository as `git clone git@github.com:rahulmcs/csvreaderwriter.git`. You may need to install git command line tools if not already installed.

2. `cd cvsreaderwriter`

3. `./gradlew uploadArchives`. This will build the library and publish the artifact to the local maven repository with following details:

`Group Id: 'com.CSV'`

`artifact id: 'csvreaderwriter'`

`version: '1.1'`

Get it
===============

Once deployed, the locally published jar can then be used in your gradle or Maven based application project by adding the following dependency information to your `build.gradle`:

repositories {

    mavenLocal()
    
}

dependencies {

     compile 'com.CSV:csvreaderwriter:1.1'
     
}

TODO
===============
Create a Sonatype Nexus repository so that the artifact can be published on to Maven central and can then be accessed by applications without needing to first publish the library to a local maven repository.
