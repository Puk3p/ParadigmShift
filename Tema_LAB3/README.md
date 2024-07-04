
# Lab 3: Simple ADT Applications in Kotlin

## Introduction
This lab focuses on practical applications of Abstract Data Types (ADT) using Kotlin. Students will learn to set up a Maven project, manage dependencies, and utilize Kotlin for various data operations including regular expressions, HTTP requests, and data manipulation through ADTs.

## Setting Up a Maven Project
- Start by creating a new Maven project in your IDE (e.g., IntelliJ).
- Navigate to `File → New → Project → Maven`.
- Check `Create from archetype` and select the `kotlin-archetype-jvm` which is suitable for Kotlin JVM projects.

## Adding Maven Dependencies
Manage your project's dependencies via the `pom.xml` file. Key dependencies for this lab include:
- **khttp** for HTTP operations.
- **JSoup** for HTML and XML processing.

```xml
<dependencies>
    <dependency>
        <groupId>com.github.jkcclemens</groupId>
        <artifactId>khttp</artifactId>
        <version>0.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.13.1</version>
    </dependency>
</dependencies>
```

## Regular Expressions in Kotlin
Regular expressions are utilized for searching, replacing, and splitting strings. Kotlin provides comprehensive support for regex operations.

### Example:
Using regex to extract different patterns from a given string.

## Lab Examples
1. **Regex Usage:** Demonstrates basic and advanced regex operations.
2. **HTTP GET and HTML Parsing:** Using `khttp` and `JSoup` to perform HTTP GET requests and parse HTML content.
3. **Phonebook with CRUD Operations:** Implement a simple phonebook that allows adding, searching, updating, and deleting contacts.
4. **Simple Translator Using Collections:** A basic translator using `Map` and `List` for storing and retrieving translations.
5. **Histogram of Word Distribution:** Utilizing Kotlin and possibly other languages via GraalVM to create a histogram of word frequencies.

## Lab Tasks
- Implement additional search and update functions in the phonebook example.
- Enhance the translator with functionalities like adding new words and saving the translated story.

## 
## Home Assignments Completed

1. **Processing an RSS Feed Using JSoup:**
   - Successfully processed an RSS feed using JSoup to build an Abstract Data Type (ADT) for storing data. The ADT includes a list of items, each containing attributes such as title, link, description, and publication date. Displayed the title and link of each item at the end.
   - Example RSS feed processed:
   ```
   <?xml version="1.0" encoding="UTF-8" ?>
   <rss version="2.0">
     <channel>
     <title>PP - Laboratory 3</title>
     <link>http://mike.tuiasi.ro/LabPP3.pdf</link>
     <description>Simple ADT applications in Kotlin</description>
     <item>
       <title>Creating a Maven Project</title>
       <link>http://mike.tuiasi.ro/LabPP3.pdf</link>
       <description>Adding dependencies in pom.xml</description>
     </item>
     <item>
       <title>Regular Expressions</title>
       <link>http://mike.tuiasi.ro/LabPP3.pdf</link>
       <description>Examples of regular expressions in Kotlin</description>
   </item>
  </channel>
</rss>
```

2. **Console Program for Processing Text-Based Ebooks:**
   - Developed a console program using collections and generics to process e-books in text format. The program performs tasks such as removing multiple spaces, eliminating multiple newline jumps, and detecting and removing page numbers.
   - Optional enhancements included detecting and removing author names and chapter titles, identifying and updating old Romanian character sets to current usage.

These tasks utilized the JSoup library for XML parsing and Kotlin's powerful collection and generic features to efficiently handle text manipulations.

- Successfully processed an RSS feed using JSoup, building an ADT for data storage and displaying the title and link of each item.
- Developed a console program using collections and generics to process e-books in text format, which included removing multiple spaces and newline jumps, detecting and removing page numbers, and optionally detecting and removing author names and chapter titles.

## Appendices
Helpful resources and documentation links are provided to assist students in understanding and applying the concepts learned in the lab.

This README provides an overview and guide to successfully completing Lab 3's tasks and understanding the application of Kotlin in various programming paradigms.
