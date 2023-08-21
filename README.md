# Assignment 2 - Reversi
B-OOP 2022

Your task is to create a window Java application running the game Reversi using the AWT and Swing libraries. The user interface of the application must consist of:

* Canvas (or JPanel) - that will draw the game board
* side menu (you can choose the placement of this menu, it can also be on the top, or at the bottom of the window)

## Game rules
### Starting position and the win condition

The game is also known as Othello. The game starts with a board of 6x6 tiles, the size of the board can be changed by the user (the picture shows a board od size 8x8).

![image](images/reversi.png)

The goal of the game is to have more stones on the board than your opponent. The game ends if all tiles are occupied by stones, or if neither player can play a valid move.

### Placing the stones
The player places a stone of their color onto the board on their turn. The stone must be placed in such a way, that it captures at least one stone of their opponent. If a player cannot capture a stone with any available move, they pass their turn.
The game begins with 4 stones placed on the board, two stones belong to the white and two stones belong to the black player (see picture above).

### Capturing stones
The players must place stones in such a way, that they trap opposing stones in a continuous line between two stones of their own color in any direction (vertically, horizontally, or diagonally).
All the trapped stones are captured by the player and change color.

If neither player can make a capturing move the game ends.

Source:

[BrainKing](https://brainking.com/en/GameRules?tp=9)

[Online version of the game](https://cardgames.io/reversi/)
## Requirements
The game is played against a computer opponent. You can decide which color is played by the player and which by the computer. The computer can play in two ways:

* By playing a random valid move (Penalisation of 1 point)
* By playing the move that captures the most stones (If there are more such moves you can choose one in any way you want)

The game is played with the mouse. When a mouse hovers above a tile that is a valid move, this tile is highlighted. All the tiles that are valid moves are highlighted.

The menu must contain:
* information about the current player, or when the game ends, information about the winner.
* a button that resets the game
* information about the current dimensions of the board
* a component that is used to set the size of the game board (out of the valid values 6,8,10,12). You can choose what component to use for this, possible examples include: Slider, JTextField, JComboBox

By pressing the R key on the keyboard the game resets. By pressing the ESC key the game closes.

## Grading
You can get 15 points for this assignment. 5 points are given for fulfilling the outlined rules of the game, 5 points are given for complying with OOP principles, 5 points are given for correct usage of the AWT and Swing libararies.
**The program must be able to compile, otherwise 0 points are given for the assigment.**
The github pipeline checks whether the program can be compiled. The main focus during grading is put on object-oriented approach and OOP principles used by the solution.
Including, but not limited to:
* appropriate naming of classes, methods and variables in a single language (class names starting with a capital letter, method names starting with a lowercase letter),
* appropriate use of access modifiers (public, private, or protected) when restricting access to class methods and attributes,
* the use of inheritance and polymorphism,
* usage of exceptions when handling undesired behavior (do not catch or throw the instances of the generic Exception class),
* don't use nested classes,
* don't use static methods, or non-constant static variables (you don't need them to complete the assignment),
* don't put any logic into the main method and its class. The main method should only be used to create a new object,
* you can use the lombok library and its annotations in your solution. The neccessary dependency is already present in the pom.xml file.

Issues that resulted in a 0.5 - 1 point reduction last year:

* The application shows an empty window when it starts and the content is displayed only after the window is resized
* The initial dimensions of the window are not set
* Some of the required listeners are not implemented
* The game cannot be reset
* Drawing glitches
* Uncaught exceptions
* The program does not exit, when the window closes
* The win condition is not checked correctly
* Code in the main method
* Lack of OOP principles
* Some of the listeners are broken after the game resets
* A new window is opened when the game resets
* Listeners are implemented with nested classes
* The game cannot be finished
* Frame.setVisible is called too early

If the assignment lacks a substantial part of the specified functionality additional points are substracted.

**The originality of the code is checks, all assignments with a more than 80% match are awarded with 0 points.**

## Handing in the assigment

Clone the assignment from the repository created from this template by the provided link trough GitHub Classroom (if you create your own repository with the "use this template" button, we won't be able to see your repository, and we won't be able to grade it!). Upload your solutions to your repository using the Git version control system (git commit + git push).

Make sure, that your repository was created under the **Interes-Group** group, otherwise we won't be able to access your repository, and the assignment will not be graded.

You can push commits to the repository while you work - you don't have to push everything at once. Only the code in the _master_ branch will be graded. You have until **15.4.2022 23:00** to complete the assignment.

Only edit files in the _src/main_ folder or its sub-folders. You mustn't change any other files in the repository (especially the _pom.xml_ file, and the github pipeline files).

You have to have your name set in your github account (settings > profile > name), so that we can match students with their assignments. **If we are unable to match a student with their assignment, the student will receive 0 points for the assignment!**
