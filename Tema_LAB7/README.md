
# Lab 7: Collections and Generics in Kotlin

## Introduction
This lab focuses on the use of collections and generics in Kotlin. Kotlin distinguishes between mutable and immutable collections. Mutable collections can be updated in place by adding, removing, or replacing elements, whereas immutable collections will create a new collection with the updates, leaving the original collection unchanged. All collections are found in the `kotlin.collections` namespace.

## Collections
### Iterable vs Sequence vs Java Stream
- **Iterable**: Represents a collection of elements that can be iterated over.
- **Sequence**: Similar to `Iterable` but supports lazy evaluation, meaning intermediate operations do not create new collections but return a new sequence with the applied operations.
- **Java Stream**: Java streams are also lazy and more efficient for heavy computational processes.

### Key Differences
- Sequences in Kotlin are more flexible with many processing functions as extension functions.
- Java Streams can be processed in parallel using the `parallel` function.

### Useful Links
- [Kotlin Collections](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/)
- [Kotlin Sequences Illustrated Guide](https://typealias.com/guides/kotlin-sequences-illustrated-guide/)
- [Effective Kotlin: Use Sequence for Bigger Collections](https://blog.kotlin-academy.com/effective-kotlin-use-sequence-for-bigger-collections-with-more-than-one-processing-step-649a15bb4bf)

## Generics
Generic programming allows functions and types to operate with any data type. In Kotlin, generics are declared using angle brackets.

### Bounded Polymorphism
#### Upper Bounds
Restrict the types a generic function can operate on to those that are subtypes of a specified type.

```kotlin
fun <T : Comparable<T>> min(first: T, second: T): T {
    val k = first.compareTo(second)
    return if (k <= 0) first else second
}
```

#### Multiple Bounds
A generic type can be restricted to multiple upper bounds.

```kotlin
fun <T> minSerializable(first: T, second: T): T where T : Comparable<T>, T : Serializable {
    val k = first.compareTo(second)
    return if (k <= 0) first else second
}
```

### Variance
Variance annotations (`out` and `in`) allow for flexible use of generic types.

- **Covariance (`out`)**: Allows a type to be a subtype of another type if the generic type parameter is only produced.
- **Contravariance (`in`)**: Allows a type to be a subtype of another type if the generic type parameter is only consumed.

### Type Projections
Used to handle cases where you want to restrict how a generic type can be used.

### Star Projections
Allows using a generic type without specifying the exact type.

```kotlin
fun copy(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices) {
        to[i] = from[i]
    }
}
```

## Examples
### Collections
#### Creating Collections
```kotlin
val intArray: Array<Int> = arrayOf(1, 2, 3)
val intList: List<Int> = listOf(1, 2, 3)
val aMap: Map<String, Int> = mapOf("hi" to 1, "hello" to 2)
val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)
```

#### Operations on Collections
```kotlin
fun operate(): Unit {
    val intList = listOf(1, 2, 3)
    val aMap = mapOf("hi" to 1, "hello" to 2)

    assert(listOf(1, 2, 3, 1) == intList + 1)
    assert(mapOf("hi" to 1, "hello" to 2, "Goodbye" to 3) == aMap + Pair("Goodbye", 3))

    val mutableList = mutableListOf(1, 2, 3)
    mutableList -= 2
    println(mutableList) // [1, 3]
}
```

### Generics
```kotlin
fun <T> random(one: T, two: T, three: T): T = TODO()
fun <K, V> put(key: K, value: V): Unit = TODO()

class Box<T>(t: T) {
    var value = t
}
```

### Algebraic Data Types
Kotlin supports algebraic data types through sealed classes.

```kotlin
sealed class LinkedList<out T> {
    object Empty : LinkedList<Nothing>()
    data class Node<out T>(val value: T, val next: LinkedList<T>) : LinkedList<T>()

    fun isEmpty(): Boolean = when (this) {
        is Empty -> true
        is Node -> false
    }
}
```

## Lab Exercises and Homework
### Completed Tasks
- **Processed the last 50 entries from `/var/log/apt/history.log`**:
  - Extracted metadata `start-date` and `Commandline` and transformed `start-date` into a timestamp.
  - Stored the timestamp and command in a class `HistoryLogRecord` implementing the `Comparable` interface.
  - Created a `MutableHashMap` with keys as timestamps and values as `HistoryLogRecord` objects.
  - Implemented a polymorphic function to find the maximum of two `HistoryLogRecord` objects using timestamps.
  - Implemented a polymorphic search and replace function using covariance.

This README provides an overview of the collections and generics concepts in Kotlin, illustrated with practical examples and tasks completed during the lab.
