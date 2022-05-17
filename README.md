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


