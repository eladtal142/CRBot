# CRBot - A Clash Royale Automated Player

### This Bot Can Play Clash Royale For You Using The Information Gathered From The Pixels On Your Screen

### The Bot Is Made Using 4 Java Classes

`The Bot Class`

The first thing the Bot class does is handle the card types and stores the 
emulator frame position. To handle the card types, the bot class takes the average
color of each pixel of a cropped image that contains each card displayed on the Clash Royale game screen.

The Bot class also holds the main method, so you run the Bot class when
you want to start the program. The Bot class being the main method also means 
it handles most of the data types and contains the current state
of the game.

`The Window Class`

The Window class, although pretty complex on the inside, is fairly simple to explain.
Essentially, what it does is display the state of the game that the Bot decides is correct 
onto a java window. It does this by displaying a live video of the game, as well
as displaying text to the screen and labeling certain parts.

It gets this information from the bot class and decision class who make the calculations,
as well as act upon them.

`The Decider Class`

The Decider class makes the final decisions that the bot performs onto the player screen.
It needs to calculate which card would be the smartest to use every time the player has enough elixir
to purchase a troop, and once it decides which troop is the best to play, it moves on to the next step. 

After the bot decides, it uses an algorithm to calculate which position would be the best
position to play the troop on, based on several different positions that that troop
would normally be played.

`The IdentifyCard class`

The IdentifyCard class holds the basic methods to scan for troops on the board, and
gives the Bot class access to the algorithms needed to perform searches and compare colors
of everything on the board.

It also stores the data for each type of card so that the bot is able to diffrentiate between
different types of cards that the opponent places.


