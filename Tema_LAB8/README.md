
# Lab 8: Design Patterns in Kotlin

## Introduction
This lab explores the application of design patterns in Kotlin, providing insights into creating reusable and maintainable code. The key design patterns covered include the State, Composite, and Command patterns.

### Recommended Resources
- **Design Patterns: Elements of Reusable Object-Oriented Software** by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides.
- **Hands-on Design Patterns with Kotlin** by Alexey Soshin.
- **Kotlin Programming: The Big Nerd Ranch Guide** by Josh Skeen and David Greenhalgh.
- Examples from the 8th lecture of the Programming Paradigms course.

## State Pattern
### Purpose
Allows an object to alter its behavior when its internal state changes, making it appear as if the object changes its class.

### Applicability
- When the behavior of an object depends on its state and must change its behavior at runtime according to that state.
- When operations have large, multipart conditional statements that depend on the object's state.

### Consequences
- Localizes state-specific behavior and partitions behavior for different states.
- Makes state transitions explicit.
- State objects can be shared.

### Class Diagram
Refer to the example with a finite state machine from the course.

### Implementation
#### Classic Approach
Each state is managed within a switch statement in each method of the class.

#### Smart Approach
Allows adding new states without modifying existing code. Each state defines its behavior.

```kotlin
package com.pp.laborator

fun main(args: Array<String>) {
    val snail = Snail()
    val still = Still(snail)
    
    val a = still.seeHero()
    println(a is Still)
    println(a is Aggressive)
    
    val b = still.timePassed()
    println(b is Still)
}

class Snail {
    internal var mood: Mood = Still(this)
    private var healthPoints = 10
}

interface WhatCanHappen {
    fun seeHero(): Mood
    fun getHit(pointsOfDamage: Int): Mood
    fun timePassed(): Mood
}

sealed class Mood : WhatCanHappen {
    override fun seeHero() = this
    override fun getHit(pointsOfDamage: Int) = this
    override fun timePassed() = this
}

class Still(private val snail: Snail) : Mood() {
    override fun seeHero(): Mood {
        return snail.mood.run {
            Aggressive(snail)
        }
    }
}

class Aggressive(snail: Snail) : Mood() {
    override fun seeHero(): Mood {
        TODO()
    }
    override fun getHit(pointsOfDamage: Int): Mood {
        TODO()
    }
    override fun timePassed(): Mood {
        TODO()
    }
}

class Retreating : Mood()
class Dead : Mood()
```

## Examples
### Combining Composite and Command Patterns
Design a minimalist edit menu using the Composite pattern for a tree structure and the Command pattern to display the class/function of each menu item.

### Source Code
```kotlin
// Command.kt
class Command(private val command: () -> Unit) {
    fun execute() {
        command()
    }
}

// MenuItem.kt
open class MenuItem(val command: Command) {
    open fun clicked() {
        command.execute()
    }
    open fun add(component: MenuItem) {}
    open fun remove(component: MenuItem) {}
    open fun getComponents(): List<MenuItem> = TODO()
}

// Copy.kt
class Copy : MenuItem(Command { println("Sunt in clasa Copy") }) {
    override fun clicked() {
        this.command.execute()
        println("Copy: clicked()")
    }
}

// Paste.kt
class Paste : MenuItem(Command { println("Sunt in clasa Paste") }) {
    override fun clicked() {
        this.command.execute()
        println("Paste: clicked()")
    }
}

// Indent.kt
class Indent : MenuItem(Command { println("Sunt in clasa Indent") }) {
    override fun clicked() {
        this.command.execute()
        println("Indent: clicked()")
    }
}

// Unindent.kt
class Unindent : MenuItem(Command { println("Sunt in clasa Unindent") }) {
    override fun clicked() {
        this.command.execute()
        println("Unindent: clicked()")
    }
}

// EditMenu.kt
class EditMenu : MenuItem(Command { println("Sunt in clasa EditMenu") }) {
    private var components: MutableList<MenuItem> = mutableListOf()

    override fun clicked() {
        this.command.execute()
        println("EditMenu: clicked()")
    }

    override fun add(component: MenuItem) {
        components.add(component)
    }

    override fun remove(component: MenuItem) {
        components.remove(component)
    }

    override fun getComponents(): MutableList<MenuItem> {
        return components
    }
}

// LineMenu.kt
class LineMenu : MenuItem(Command { println("Sunt in clasa LineMenu") }) {
    private var components: MutableList<MenuItem> = mutableListOf()

    override fun clicked() {
        this.command.execute()
        println("LineMenu: clicked()")
    }

    override fun add(component: MenuItem) {
        components.add(component)
    }

    override fun remove(component: MenuItem) {
        components.remove(component)
    }

    override fun getComponents(): MutableList<MenuItem> {
        return components
    }
}

// Main.kt
fun main(args: Array<String>) {
    var editMenu = EditMenu()
    var copy = Copy()
    var paste = Paste()
    var lineMenu = LineMenu()
    var indent = Indent()
    var unindent = Unindent()

    lineMenu.add(indent)
    lineMenu.add(unindent)

    editMenu.add(copy)
    editMenu.add(paste)
    editMenu.add(lineMenu)

    editMenu.clicked()
    editMenu.getComponents().forEach { it.clicked() }
    lineMenu.getComponents().forEach { it.clicked() }
}
```

## Applications and Homework
### Lab Applications
1. **Double Chain of Responsibility**: Design and implement a double chain of responsibility (similar to a doubly linked list) with an abstract factory pattern for handler instantiation.

### Homework
1. **Design and Implement an AND Gate Application**: Calculate the outputs of AND gates with 2, 3, 4, and 8 inputs. The application should decouple the abstraction of the logic gate from its implementation using the Bridge pattern. Each AND gate will be built using the Builder pattern, sending each input through the builder. Outputs will be calculated using a finite state machine.

This README provides an overview of design patterns in Kotlin, illustrating their application through practical examples and detailed explanations of lab tasks and homework assignments.
