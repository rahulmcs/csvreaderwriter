csvreaderwriter
===============

A utility library providing functionality to read/write CSV (tab separated tokens in this case) to and from text files.

Historically, the CSVReaderWriter class provided this functionality via a bloated, inconistent and semantically inaccurate interface and has now been deprecated in favour of the new and improved com.CSV.io.Reader and com.CSV.io.Writer interfaces 
alongwith com.CSV.io.ReaderFactory and com.CSV.io.WriterFactory classes to instantiate robust implementations providing
Tab separated values read and write functionality.
com.CSV.TabSeparatedReader and com.CSV.io.TabSeparatedWriter provide concrete implementations of the com.CSV.io.Reader and com.CSV.io.Writer interfaces using the popular OpenCSV open source library available under Apache 2.0 license.

An interface oriented design allows for providing more concrete implementations of the Reader and Writer interfaces (e.g. an implementation for storing meta data alogwith CSV values) and changing underlying implementation without breaking the api.

Please browse com.CSV.AddressProcessing package to undestand the old CSVReaderWriter api and the javadocs to understand the shortfalls and the motivation behind deprecating the entire class.

Packagae com.CSV.io contains the newly refactored code alongwith javadoc to understand the method usage.
