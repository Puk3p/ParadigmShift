
# Lab 4: Utilizing SOLID Principles in Kotlin

## Introduction
This lab focuses on the application of SOLID design principles in Kotlin. Students will explore these principles through practical examples and implement solutions that adhere to these standards.

## SOLID Principles Overview
- **Single Responsibility Principle:** A class should have one reason to change, suggesting that complicated classes should be split into smaller classes with clear responsibilities.
- **Open/Closed Principle:** Software entities should be open for extension but closed for modification.
- **Liskov Substitution Principle:** Derived classes should be substitutable for their base classes.
- **Interface Segregation Principle:** Many client-specific interfaces are better than one general-purpose interface.
- **Dependency Inversion Principle:** High-level modules should not depend on low-level modules. Both should depend on abstractions.

## UML (Unified Modeling Language)
- For an overview of UML diagrams, see the following resources:
  - [UML 2.5 Diagrams](https://www.uml-diagrams.org/uml-25-diagrams.html)
  - [UML Cheatsheet](https://loufranco.com/wp-content/uploads/2012/11/cheatsheet.pdf)

## Examples in Lab
1. **Single Responsibility Principle:** The class 'Library' should not handle serialization directly; this responsibility should be delegated to a separate serializer object.
2. **Open/Closed Principle:** Implement functionality in a way that new shapes can be added without altering existing code by having all derived classes implement a specific area calculation method.
3. **Liskov Substitution Principle:** Derived classes should enhance, not replace, the functionality of base classes.
4. **Interface Segregation Principle:** Use multiple specific interfaces rather than a general one to avoid forcing clients to depend on methods they do not use.
5. **Dependency Inversion Principle:** The web crawler does not instantiate a parser directly within its `processContent` function but uses an abstraction (interface) that each parser implements.

## Lab Tasks and Home Assignments
- **Home Assignment 1:** Implemented a web crawler using khttp and/or Jsoup. Each parser implements a method specific to the response type (JSON/XML/YAML).
- **Home Assignment 2:** Designed and implemented a cinema ticket purchasing application following SOLID principles. Utilized https://app.diagrams.net/ for drawing UML diagrams.

This README provides an extensive overview and serves as a guide to successfully understanding and applying SOLID principles in software development using Kotlin.
