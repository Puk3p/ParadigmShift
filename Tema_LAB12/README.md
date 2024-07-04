
# Lab 12: Functional Programming in Kotlin

## Introduction
This lab focuses on functional programming in Kotlin, highlighting key concepts such as lambda expressions, higher-order functions, pure functions, and data processing operations using Kotlin's rich standard library.

### Recommended Resources
- **Functional Kotlin** by Mario Arias and Rivu Chakraborty
- Lecture 12 from the Programming Paradigms course

## Functional Programming Overview
Functional programming is a paradigm where the focus is on transforming data with expressions. Ideally, these expressions should not have side effects.

### Lambda Expressions and Anonymous Functions
Lambda expressions and anonymous functions are used to define functions on the fly. They are not declared but used immediately as an expression.

#### Example
```kotlin
val capitalize = { str: String -> str.capitalize() }

fun main() {
    println(capitalize("hello world!"))
}
```

### Higher-Order Functions
A higher-order function is a function that takes functions as parameters or returns a function.

#### Example
```kotlin
fun <T> transform(t: T, fn: (T) -> T): T {
    return fn(t)
}

fun main() {
    println(transform("kotlin", { str: String -> str.capitalize() }))
    println(transform("kotlin", { it.capitalize() }))
}
```

### Pure Functions and Side Effects
#### Side Effects
In programming, a side effect occurs when a function modifies any state outside its scope, such as changing a global variable, modifying an argument, generating exceptions, writing output, or calling other functions.

#### Pure Functions
A pure function's result is completely dependent on its parameters. For each call with the same parameter, it always produces the same result without causing side effects.

#### Example
```kotlin
fun addNumbers(a: Int = 0, b: Int = 0): Int {
    return a + b
}

fun main() {
    println("Result is ${addNumbers(10, 10)}")
}
```

### Extension Functions
Extension functions allow you to add new functions to existing types.

#### Example
```kotlin
fun String.toPascalCase(): String {
    return this.split(" ").joinToString("") { it.capitalize() }
}

fun main() {
    println("whatever name you want".toPascalCase())
}
```

### Data Operations on Collections
Kotlin provides a rich set of functions for processing collections.

#### Example
```kotlin
fun main() {
    val list = 1.until(5).toList() // [1, 2, 3, 4]

    // filter
    val evenNumbers = list.filter { it % 2 == 0 } // [2, 4]

    // map
    val doubled = list.map { it * 2 } // [2, 4, 6, 8]

    // fold
    val sum = list.fold(0) { acc, i -> acc + i } // 10

    println(evenNumbers)
    println(doubled)
    println(sum)
}
```

## Functors and Delegates
### Functors
A functor defines a way to transform its contents.

#### Example
```kotlin
fun inc(value: Int): Int = value + 1

class ValueFunctor<T>(val value: T) {
    fun map(function: (T) -> T): ValueFunctor<T> {
        return ValueFunctor(function(value))
    }
}

fun main() {
    println(ValueFunctor(1).map(::inc).map(::inc).map(::inc).value)
}
```

### Delegates
Kotlin provides several built-in delegate functions, such as `lazy`, `observable`, and `vetoable`.

#### Example
```kotlin
import kotlin.properties.Delegates

var myIntEven: Int by Delegates.vetoable(0) { _, _, newValue ->
    newValue % 2 == 0
}

fun main() {
    myIntEven = 6
    myIntEven = 3
    println("myIntEven: $myIntEven") // 6
}
```

## Lab Exercises and Homework
### Completed Tasks
1. **Functional Operations on a List**: 
   - Removed numbers less than 5 from the list [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8] resulting in [21, 75, 39, 7, 35, 31, 7, 8].
   - Grouped the numbers into pairs: [(21, 75), (39, 7), (35, 31), (7, 8)].
   - Multiplied the numbers in the pairs: [1575, 273, 1085, 56].
   - Summed the results: 2989.

2. **Caesar Cipher on File Content**: 
   - Read content from a text file and encrypted all words with lengths between 4 and 7 characters using a Caesar cipher with a given offset.

This README provides a comprehensive overview of functional programming concepts in Kotlin, illustrated with practical examples and detailed explanations of the completed tasks.
