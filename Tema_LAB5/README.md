
# P2P Game Development Using Tkinter/PyQt5/PyGame

## Introduction
This lab focuses on designing and implementing a peer-to-peer (P2P) game for two players, utilizing popular Python libraries like Tkinter, PyQt5, and PyGame. The game integrates real-time interaction between players through a networked environment, ensuring dynamic gameplay and persistent data management.

## Game Design
- **Technology Stack:** Depending on the chosen library (Tkinter, PyQt5, or PyGame), the game's user interface and control mechanisms are designed to provide an interactive and engaging player experience.
- **Network Communication:** Utilizes a System-V message queue to handle communication between two instances of the game, ensuring that player actions are synchronized across the network.
- **Database Integration:** Uses a SQLite database to store player names and scores. This allows for persistent storage and retrieval of player data across sessions, enhancing the continuity of the gaming experience.

## Detailed Features
- **Player Identification:** Upon starting the game, players enter their names, which are used to track scores and manage sessions.
- **Wait Mechanism:** The game remains in wait mode until another player connects, ensuring that both players are ready before the game begins.
- **Score Tracking:** Scores are updated at the end of each round and stored in the database. When the same players reconnect, their previous scores are displayed, adding a competitive edge.

## Bonus: Multi-process Chat System
- **Implementation:** Alongside the game, a multi-process chat system is implemented using similar technologies, which allows real-time communication between players.
- **Database Use:** The chat system uses a SQLite database to store user details and message logs securely. Optional security features include password hashing and encryption to protect user data.

## System Diagrams
- **Entity-Relationship Diagram:** Details the database schema, illustrating the relationships between user data and messages.
- **UML Class Diagram:** Outlines the structure of the game and chat systems, showing class hierarchies and interactions.
- **Use-Case Diagram:** Depicts the user interactions possible with the system, from logging in to playing the game or using the chat.

## GUI Design
- **Interface Elements:** The game interface includes essential elements such as game status displays, player controls, and navigation options. The chat interface includes user registration, login forms, and message views.
- **Aesthetics and Usability:** The design focuses on user-friendly interfaces that are intuitive and aesthetically pleasing, promoting a seamless user experience.

## Tasks Completed
- **Homework Reference:** The current homework in the repository pertains to enhancing and perfecting the game based on this exercise, focusing on extending functionality and improving user interaction.

This README provides a comprehensive overview of the development and capabilities of the P2P game and associated systems, guiding users and developers through the functionalities and technical specifications of the project.
