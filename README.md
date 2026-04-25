Project Title

Tic Tac Toe

Team Members

Beniam Sisay, David Thumma

Development Language

Java

Platform

Java Swing

Project Description

We are developing a Tic Tac Toe game using Java and a Swing graphical user interface. The game will support local player vs. player gameplay and player vs. computer gameplay. The program will manage turn order, validate moves, detect wins and ties, restart the board, and clearly display game results. The project will also persist game statistics, including wins, losses, and ties, to a file so results are saved across sessions.

Design Patterns

The project will use four design patterns:

Strategy Pattern
Used for computer move behavior. ComputerPlayer depends on a MoveStrategy interface, allowing different AI behaviors such as RandomMoveStrategy and SmartMoveStrategy.
Observer Pattern
Used to notify the Swing UI when the game state changes. The game model acts as the subject, and the GUI acts as an observer.
Factory Pattern
Used to create player objects depending on the selected game mode. This keeps player creation logic out of the controller and UI.
Command Pattern
Used to represent user actions such as making a move, restarting the game, and saving statistics. Swing buttons create command objects and call execute() instead of directly changing game state.
Core Classes and Interfaces
Board
GameModel
GameController
GameView
Player
HumanPlayer
ComputerPlayer
MoveStrategy
RandomMoveStrategy
SmartMoveStrategy
PlayerFactory
GameObserver
GameCommand
MakeMoveCommand
RestartGameCommand
StatisticsManager
