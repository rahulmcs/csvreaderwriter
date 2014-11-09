csvreaderwriter
===============

A utility library providing functionality to read/write CSV (tab separated tokens in this case) to and from text files.

Historically, the CSVReaderWriter class provided this functionality via a bloated, inconistent and semantically inaccurate interface and has now been deprecated in favour of the new and improved com.CSV.io.Reader and com.CSV.io.Writer interfaces 
alongwith com.CSV.io.ReaderFactory and com.CSV.io.WriterFactory classes to instantiate robust implementations providing
Tab separated values read and write functionality.
com.CSV.TabSeparatedReader and com.CSV.io.TabSeparatedWriter provide concrete implementations of the com.CSV.io.Reader and com.CSV.io.Writer interfaces using the popular OpenCSV open source library available under Apache 2.0 license.

An interface oriented design allows for providing more concrete implementations of the Reader and Writer interfaces (e.g. an implementation for storing meta data alogwith CSV values) and changing underlying implementation without breaking the api.

Please browse com.CSV.AddressProcessing package to undestand the old CSVReaderWriter api and the javadocs to understand the shortfalls and the motivation behind deprecating the entire class.

Package com.CSV.io contains the newly refactored code alongwith javadoc to understand the method usage.

Build Instructions
===============
The project uses gradle to build the java source files into a jar library and maven to push the generated jar artifacts to the maven local repository.Therefore having maven and gradle already configured on your system is a pre-requisite. It would be simplest to pull the code locally for github and open it using IntelliJ which has in-built support for gradle and mavem.

On the command line, simply issue a gradle uploadArchives (or ./gradlew uploadArchives on a mac) command which will build the library and publish the artifact to the local maven repository with followng details:
Group Id: 'com.CSV'
artifact id: 'csvreaderwriter'
version: '1.1'

Dependency Instructions
===============

Once deployed, the locally published jar canthen be used in your gradle or maven based application project by adding the following dependency information to your build.gradle file:

repositories {
    mavenLocal()
}

dependencies {
     compile 'com.CSV:csvreaderwriter:1.1'
}

TODO
===============
Need to create Sonatype repository so that the artifact can be published on Maven central and can then be accessed by applications without needing to first publish the library to a local maven repository.
