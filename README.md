# OOP_EX2-Ex4
This Github project is an educational example for using git & github as part of the Object Oriented Programming course in Ariel University. The main focus of this project is to allow an online suppoer of Ex2, Ex3, Ex4 which are all related to a GIS like system.

Pacman game

Goal of the game: Eat all the fruits in the board.
Game usage:

Creating your own game:
The user can make his own game by creating the pacmans and fruits on the board by selecting the appropriate menu item.


![1](https://user-images.githubusercontent.com/44944939/50638350-25fe1480-0f66-11e9-8b8d-b738a737a14e.png)

 
by selecting “pacman” – the user can click anywhere on the board and select the location of the pacman.
by selecting “fruit” – the user can click anywhere on the board and select the location of the pacman.
by selecting “clear” – The game board will be completely deleted.


Saving your own game: 
In order to save your own game, you should choose the “game” option in the menu bar and then click on “save game”, you won’t be able to save an empty game (empty board). Your game will be saved as a new csv file, named “new game” with all the data of your pacmans and fruits.


 ![2](https://user-images.githubusercontent.com/44944939/50638425-5ba2fd80-0f66-11e9-94f0-f167d37057a6.png)


Uploading a game: 


![3](https://user-images.githubusercontent.com/44944939/50638447-76757200-0f66-11e9-86ce-6eb9789803b2.png)

By selecting “load file” - the user can choose a csv file with the data of the game.

Play: 
When choosing the “play” option the pacmans will eat the fruits on the board. Considering the speed for each pacman.  The user will see the path for each pacman and the fruits It “ate”.

End game:
by selecting “end game” – the game window will close.


Game algorithm:

1.	Run over a loop until all the fruit was eaten
2.	Go over all the Packmans and check which fruit is closest to him in terms of time
3.	Of all the nearby fruits, check the shortest time
4.	The Pacman, whose distance to his fruit is the shortest, is the first one to play
5.	In each step the packman makes to a fruit, the fruit is eaten (removed from the list)
6.	each fruit we eat, its location is added to the Pacman path
7.	Keep running on the loop until all the fruits are eaten
8.	At the end of the loop, go over all the paths that were created and add them to the shortest path solution.
