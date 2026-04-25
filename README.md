# Tic Tac Toe

## Team Members
Beniam Sisay  
David Thumma  

## Development Language
Java  

## Platform
Java Swing  

## Project Description
We are developing a Tic Tac Toe game using Java with a Swing-based graphical user interface. The application supports both player vs. player and player vs. computer gameplay. The system manages turn order, validates moves, detects wins and ties, and allows the user to restart the game.

The game also includes a persistence feature that saves statistics such as total wins, losses, and ties to a file so that results are preserved across sessions.

---

## Design Patterns

### 1. Strategy Pattern
The Strategy pattern is used to define different behaviors for the computer player.

A `MoveStrategy` interface allows multiple implementations of move selection logic, such as:
- `RandomMoveStrategy`
- `SmartMoveStrategy`

This allows the `ComputerPlayer` to use different algorithms without modifying its code.

---

### 2. Observer Pattern
The Observer pattern is used to keep the UI synchronized with the game state.

The game model acts as the subject and notifies observers (the Swing UI) whenever:
- the board changes
- the turn changes
- the game ends

This keeps the UI decoupled from the core game logic.

---

### 3. Factory Pattern
The Factory pattern is used to create player objects.

A `PlayerFactory` centralizes the logic for creating:
- `HumanPlayer`
- `ComputerPlayer`

This avoids spreading object creation logic throughout the system and improves maintainability.

---

### 4. Command Pattern
The Command pattern is used to represent user actions in the UI.

Each action is encapsulated as a command object, such as:
- `MakeMoveCommand`
- `RestartGameCommand`

Swing buttons create command objects and call `execute()` instead of directly modifying the game. This decouples the UI from the game logic and makes actions easier to extend.

---

## Core Classes and Interfaces

- `GameController` – coordinates game flow  
- `GameModel` – manages game state and rules  
- `Board` – represents the Tic Tac Toe grid  
- `GameView` – Swing GUI for user interaction  

- `Player` (interface)  
- `HumanPlayer`  
- `ComputerPlayer`  

- `MoveStrategy` (interface)  
- `RandomMoveStrategy`  
- `SmartMoveStrategy`  

- `PlayerFactory`  

- `GameObserver` (interface)  

- `GameCommand` (interface)  
- `MakeMoveCommand`  
- `RestartGameCommand`  

- `StatisticsManager` – handles saving/loading stats  

---

## Object-Oriented Principles

This project demonstrates key OO design principles:

- **Coding to abstractions**  
  Interfaces such as `Player`, `MoveStrategy`, and `GameCommand` are used instead of concrete classes.

- **Polymorphism**  
  `HumanPlayer` and `ComputerPlayer` are treated uniformly as `Player` objects.

- **Dependency Injection**  
  `ComputerPlayer` receives a `MoveStrategy` through its constructor, allowing flexible behavior.

- **Separation of Concerns**  
  The UI, game logic, and object creation are kept separate.

---

## Persistence

The application saves game statistics (wins, losses, ties) to a file and reloads them when the program starts. This ensures that user progress is preserved across sessions.

---

## Planned Test Cases

1. A move is correctly placed on an empty square  
2. A move is rejected if the square is already occupied  
3. A win is detected correctly (rows, columns, diagonals)  
4. A tie is detected when the board is full with no winner  
5. Game statistics are correctly saved and loaded  
6. The computer player selects a valid move  
7. Restarting the game resets the board but preserves statistics  
