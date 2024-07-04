
# Lab: Chain of Responsibility and Command Patterns

## Introduction
This lab focuses on the implementation of Chain of Responsibility and Command design patterns in Kotlin. The main objective is to create an application that identifies the type of a file based on its content and executes it using the appropriate command.

### Design Patterns Overview
#### Chain of Responsibility
- **Purpose:** Allows an object to send a command without knowing which object will handle the request. The request is passed along a chain of handlers.
- **Applicability:** Used when more than one object may handle a request, and the handler isn't known a priori. The handler is determined at runtime.
- **Consequences:** Reduces the coupling between sender and receiver, allowing multiple handlers to process requests dynamically.

#### Command Pattern
- **Purpose:** Encapsulates a request as an object, thereby allowing for parameterization of clients with different requests, queueing of requests, and logging of requests.
- **Applicability:** Used when you need to issue requests to objects without knowing anything about the operation being requested or the receiver.
- **Consequences:** Decouples the object that invokes the operation from the one that knows how to perform it. Allows for easy addition of new commands without changing existing code.

## Implementation Details
### Chain of Responsibility
The application uses a Chain of Responsibility to determine the type of file based on its content. Each handler in the chain is responsible for identifying a specific type of file (Kotlin, Python, Bash, Java). If a handler cannot identify the file type, it passes the request to the next handler in the chain.

### Command Pattern
Once the file type is determined, the Command pattern is used to execute the file's content. The application uses the `subprocess` module to run the file and capture its output.

### Application Workflow
1. **Input:** The application receives a file without an extension from the keyboard.
2. **Chain of Responsibility:** The file content is passed through a chain of handlers. Each handler checks for specific keywords, import statements, or the presence of a main function to identify the file type.
3. **Command Execution:** After identifying the file type, the appropriate command is executed using the `subprocess` module, and the output is captured and displayed.

### Example Handlers
#### KotlinHandler
```kotlin
class KotlinHandler(val next: Handler?) : Handler {
    override fun handleRequest(fileContent: String): String? {
        if (fileContent.contains("fun") || fileContent.contains("when")) {
            return "Kotlin"
        } else {
            return next?.handleRequest(fileContent)
        }
    }
}
```

#### PythonHandler
```kotlin
class PythonHandler(val next: Handler?) : Handler {
    override fun handleRequest(fileContent: String): String? {
        if (fileContent.contains("def") || fileContent.contains("import")) {
            return "Python"
        } else {
            return next?.handleRequest(fileContent)
        }
    }
}
```

#### BashHandler
```kotlin
class BashHandler(val next: Handler?) : Handler {
    override fun handleRequest(fileContent: String): String? {
        if (fileContent.contains("#!/bin/bash") || fileContent.contains("echo")) {
            return "Bash"
        } else {
            return next?.handleRequest(fileContent)
        }
    }
}
```

#### JavaHandler
```kotlin
class JavaHandler(val next: Handler?) : Handler {
    override fun handleRequest(fileContent: String): String? {
        if (fileContent.contains("public class") || fileContent.contains("import")) {
            return "Java"
        } else {
            return next?.handleRequest(fileContent)
        }
    }
}
```

### Command Execution
#### CommandExecutor
```kotlin
class CommandExecutor {
    fun execute(command: String, filePath: String): String {
        val process = Runtime.getRuntime().exec("$command $filePath")
        return process.inputStream.bufferedReader().readText()
    }
}
```

## Example Usage
```kotlin
fun main(args: Array<String>) {
    val fileContent = readFile("path/to/file")

    val handlerChain = KotlinHandler(PythonHandler(BashHandler(JavaHandler(null))))
    val fileType = handlerChain.handleRequest(fileContent)

    if (fileType != null) {
        val executor = CommandExecutor()
        val command = when (fileType) {
            "Kotlin" -> "kotlinc"
            "Python" -> "python"
            "Bash" -> "bash"
            "Java" -> "java"
            else -> throw IllegalArgumentException("Unknown file type")
        }
        val output = executor.execute(command, "path/to/file")
        println("Output: $output")
    } else {
        println("Could not determine the file type.")
    }
}

fun readFile(filePath: String): String {
    return File(filePath).readText()
}
```

## Completed Tasks
1. **Implemented an application to identify and execute file content**: The application receives a file without an extension, determines its type using the Chain of Responsibility pattern, and executes it using the Command pattern.

This README provides a comprehensive overview of the Chain of Responsibility and Command design patterns, illustrated with practical examples and a detailed explanation of the completed tasks.
