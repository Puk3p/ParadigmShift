
# Lab 10: Coroutines and Double Chain of Responsibility in Kotlin

## Introduction
This lab explores the use of coroutines and the implementation of a double chain of responsibility pattern in Kotlin. The goal is to create an application that processes requests using coroutines and sends responses back through a hierarchical chain of handlers. The lab also covers the creation of abstract factories for handler instantiation.

### Recommended Resources
- **Hands-on Design Patterns with Kotlin** by Alexey Soshin
- **Functional Kotlin** by Mario Arias, Rivu Chakraborty
- Course materials from Programming Paradigms, Lecture 10
- [Kotlin Coroutines Guide](https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/topics/coroutines-guide.md)

## Setting Up the Development Environment
1. **Update the development environment** to ensure all packages are up-to-date.
2. **Create a new project** using Gradle or Maven with Kotlin as the target JVM.

### Gradle Project Setup
Add the following dependency in `build.gradle`:
```gradle
dependencies {
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0'
}
```

### Maven Project Setup
Add the following dependency in `pom.xml`:
```xml
<dependency>
    <groupId>org.jetbrains.kotlinx</groupId>
    <artifactId>kotlinx-coroutines-core</artifactId>
    <version>1.6.0</version>
    <type>pom</type>
</dependency>
```

## Coroutines Overview
Coroutines provide a way to write asynchronous, non-blocking code. They can be suspended and resumed at a later time, making them ideal for handling tasks such as I/O operations without blocking the main thread.

### Recursive Functions and Tail Recursion
Kotlin supports tail recursion optimization, allowing certain types of recursive functions to be executed efficiently without risk of stack overflow.
```kotlin
val eps = 1E-10 // precision threshold

tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))
```

### Recursive Coroutines
To use a tail-recursive function in a coroutine, the `suspend` keyword is required:
```kotlin
tailrec suspend fun fibonacci(n: Int, a: Long, b: Long): Long {
    return if (n == 0) a else fibonacci(n-1, b, a+b)
}
```

### Debugging Coroutines
Add the following dependency for debugging coroutines:
```gradle
dependencies {
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.6.0'
}
```
or
```xml
<dependency>
    <groupId>org.jetbrains.kotlinx</groupId>
    <artifactId>kotlinx-coroutines-debug</artifactId>
    <version>1.6.0</version>
</dependency>
```

Example of coroutine debugging:
```kotlin
package com.pp.laborator

import kotlinx.coroutines.*
import kotlinx.coroutines.debug.*

suspend fun computeValue(): String = coroutineScope {
    val one = async { computeOne() }
    val two = async { computeTwo() }
    combineResults(one, two)
}

suspend fun combineResults(one: Deferred<String>, two: Deferred<String>): String =
    one.await() + two.await()

suspend fun computeOne(): String {
    delay(5000)
    return "4"
}

suspend fun computeTwo(): String {
    delay(5000)
    return "2"
}

fun main() = runBlocking {
    DebugProbes.install()
    val deferred = async { computeValue() }
    delay(1000)
    DebugProbes.dumpCoroutines()
    println("
Dumping only deferred")
    DebugProbes.printJob(deferred)
}
```

## Double Chain of Responsibility
The goal is to design and implement a double chain of responsibility (similar to a doubly linked list). The chain will send a response to the superior handler at the end of request processing to notify whether the task was completed successfully.

### Abstract Factory for Handler Instantiation
Two factories will be created:
- **EliteFactory**: Creates `CEOHandler`, `ExecutiveHandler`, and `ManagerHandler`.
- **HappyWorkerFactory**: Creates `HappyWorkerHandler`.

### Class Diagram and Object Diagram
Class and object diagrams will illustrate the structure and relationships between handlers and factories.

### Implementation Details
Handlers will use `suspend` functions for processing requests. Each handler creates a coroutine for handling incoming requests:
- If the current handler can process the request, it will handle it in a coroutine and send a response up the chain.
- If the current handler cannot process the request, it will create a coroutine to call the `handleRequest` function of the next handler.

The `delay` function will be used with variable periods to simulate processing time.

### Example Execution Flow
A request intended for `ExecutiveHandler` will first reach `CEOHandler` on the upper chain, which forwards it to `ExecutiveHandler`. `ExecutiveHandler` processes the request and sends a response back through the lower chain, reaching `CEOHandler` again and finally returning to the initial chain.

### Message Structure
- **Request**: `Request - <message>`
- **Response**: `Response - <message>`

### Example Code
```kotlin
package com.pp.laborator

import kotlinx.coroutines.*

sealed class RequestMsg
data class Request(val message: String) : RequestMsg()
data class Response(val message: String) : RequestMsg()

interface Handler {
    suspend fun handleRequest(request: RequestMsg): RequestMsg
}

abstract class AbstractHandler : Handler {
    var nextHandler: Handler? = null
    var previousHandler: Handler? = null

    override suspend fun handleRequest(request: RequestMsg): RequestMsg {
        return nextHandler?.handleRequest(request) ?: Response("No handler found")
    }
}

class CEOHandler : AbstractHandler() {
    override suspend fun handleRequest(request: RequestMsg): RequestMsg {
        return if (request is Request && request.message.contains("CEO")) {
            delay(1000) // Simulate processing time
            Response("Handled by CEO")
        } else {
            super.handleRequest(request)
        }
    }
}

class ExecutiveHandler : AbstractHandler() {
    override suspend fun handleRequest(request: RequestMsg): RequestMsg {
        return if (request is Request && request.message.contains("Executive")) {
            delay(1000) // Simulate processing time
            Response("Handled by Executive")
        } else {
            super.handleRequest(request)
        }
    }
}

class ManagerHandler : AbstractHandler() {
    override suspend fun handleRequest(request: RequestMsg): RequestMsg {
        return if (request is Request && request.message.contains("Manager")) {
            delay(1000) // Simulate processing time
            Response("Handled by Manager")
        } else {
            super.handleRequest(request)
        }
    }
}

class HappyWorkerHandler : AbstractHandler() {
    override suspend fun handleRequest(request: RequestMsg): RequestMsg {
        return if (request is Request && request.message.contains("Happy Worker")) {
            delay(1000) // Simulate processing time
            Response("Handled by Happy Worker")
        } else {
            super.handleRequest(request)
        }
    }
}

fun main() = runBlocking {
    val ceoHandler = CEOHandler()
    val executiveHandler = ExecutiveHandler()
    val managerHandler = ManagerHandler()
    val happyWorkerHandler = HappyWorkerHandler()

    ceoHandler.nextHandler = executiveHandler
    executiveHandler.nextHandler = managerHandler
    managerHandler.nextHandler = happyWorkerHandler

    val request = Request("Request - Executive")
    val response = ceoHandler.handleRequest(request)
    println(response)
}
```

## Completed Tasks
1. **Implemented a double chain of responsibility**: Created an application that processes requests using a double chain of responsibility pattern. Handlers were instantiated using abstract factories, and each handler utilized coroutines for request processing and response handling.

This README provides a comprehensive overview of coroutines and the double chain of responsibility pattern in Kotlin, illustrated with practical examples and detailed explanations of the completed tasks.
