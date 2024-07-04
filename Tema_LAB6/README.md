
# Lab 6: Object-Oriented Programming in Python

## Introduction
This lab focuses on the application of object-oriented programming (OOP) principles using Python. It includes an exploration of Python's capabilities to support various programming paradigms, particularly OOP.

## Development Environment
- **IDE:** PyCharm Community Edition is used, providing integrated analysis and syntax highlighting features that enhance Python coding.
- **Recommendations:** Avoid using copy/paste from external sources as it may confuse the integrated syntax analyzer and make debugging more complex for beginners.

## Python OOP Basics
- **Classes and Inheritance:** All Python classes derive from the base class `object`. If no other class is specified, a new class will inherit from `object`.
- **Command Line Arguments:** Parameters are handled using the `sys` module, with the first parameter accessible via `sys.argv[1]`.

## Data Types and Operator Overloading
- **Type Annotations:** Introduced in Python 3.5, type hints help clarify the expected data types within the code but do not influence dynamic type assignment.
- **Magic Methods:** Python uses special methods to define behavior for built-in operations. These can be overridden to customize how objects handle operations like addition, subtraction, etc.

## Access Specifiers
- Python does not enforce access controls strictly but uses naming conventions to indicate the intended level of access:
  - **Public Variables:** No underscore prefix.
  - **Protected Variables:** Single underscore prefix.
  - **Private Variables:** Double underscore prefix.

## Naming Conventions and Coding Standards
- **Classes:** Use PascalCase.
- **Variables and Methods:** Use snake_case.
- **Constants:** Upper case with underscores.
- **PEP 8:** Follow the PEP 8 style guide for Python code, which helps maintain readability and consistency across Python codebases.

## Key Examples from the Lab
- **Inheritance and Polymorphism:** Demonstrated through simple class examples that override methods and utilize Python's duck typing to achieve polymorphism.
- **Operator Overloading:** Customizing behavior for standard operators to handle user-defined objects in intuitive ways.

## Lab Exercises
1. **Implement an Application:** Start from the super class example to identify file categories based on character frequency in a given directory.
   - **Categories:**
     - ASCII/UTF8 Text: High frequencies for characters {9,10,13,32...127} and very low for {0...8,11,12,14,15...31, 128...255}.
     - UNICODE/UTF16 Text: The null character (0) appears in at least 30% of the text.
     - Binary Files: Frequencies are somewhat uniformly distributed across {0...255}.
2. **Class Diagram Implementation:** Use the class diagram to recursively traverse a directory and identify specific file types regardless of extension, displaying absolute paths and properties like dimensions for BMP files.

## Accomplishments
- **Completed Exercises 1 and 2:** Successfully implemented the application to analyze file types in a directory using character frequencies and developed a script to recursively search and classify files.

This README provides an overview of object-oriented programming in Python as applied in Lab 6, guiding through the practical implementation of class structures, inheritance, and polymorphism to solve real-world problems.
