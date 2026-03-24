Project Title

Tic Tac Toe

Team Members

Beniam Sisay, David Thumma

Development Language

Java

Platform

Java Swing

Project Description

We will develop a Tic Tac Toe game in Java with a Swing-based graphical user interface. The application will support both player vs. player local gameplay and player vs. computer gameplay. It will manage turn order, validate moves, detect wins and ties, and allow the user to restart the game. The program will also display game results clearly to the user. In addition, the application will include a persistence feature that saves game statistics such as total wins, losses, and ties to a file so that results are preserved across sessions.

Design Patterns

We plan to use at least three design patterns in the architecture:

1. Strategy Pattern

The computer opponent will use the Strategy pattern so that different move-selection behaviors can be swapped without changing the game engine. For example, we can implement:
    •    a RandomMoveStrategy
    •    a SmartMoveStrategy

This allows the computer player to depend on an abstraction rather than a specific algorithm.

2. Observer Pattern

The game model will use the Observer pattern to notify the GUI whenever the board state changes, the turn changes, or the game ends. This keeps the user interface decoupled from the core game logic and allows updates to happen automatically when the model changes.

3. Factory Pattern

We will use a Factory pattern to create player objects based on the selected game mode. For example, a PlayerFactory can create either:
    •    a human player
    •    a computer player

This centralizes object creation and avoids scattering construction logic throughout the program.

Foundational Classes and Interfaces

The project will include core classes and interfaces with real logic, not just stubs. Planned components include:
    •    GameController: coordinates the flow of the game
    •    Board: stores the grid state and validates moves
    •    Player interface: abstraction for all player types
    •    HumanPlayer and ComputerPlayer: concrete implementations of Player
    •    MoveStrategy interface: abstraction for AI move logic
    •    RandomMoveStrategy / SmartMoveStrategy: concrete AI behaviors
    •    PlayerFactory: creates the correct player type
    •    StatisticsManager: handles saving and loading statistics from a file
    •    GameView: Swing GUI for displaying and interacting with the game

OO Principles

The design will demonstrate core object-oriented principles:
    •    Coding to abstractions: the system will use interfaces such as Player and MoveStrategy
    •    Polymorphism: HumanPlayer and ComputerPlayer will both be treated as Player objects
    •    Dependency injection: a ComputerPlayer will receive a MoveStrategy through its constructor, allowing different strategies to be injected without changing the class itself

Planned Test Cases

We will design at least five meaningful test cases, including:
    1.    A move is correctly placed on an empty square
    2.    A move is rejected if the square is already occupied
    3.    A win is detected correctly for rows, columns, and diagonals
    4.    A tie is detected correctly when the board is full and no player has won
    5.    Game statistics are correctly saved and loaded from a file
    6.    The computer player selects a valid move
    7.    Restarting the game resets the board but preserves saved statistics
