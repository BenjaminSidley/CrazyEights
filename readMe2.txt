Benjamin Sidley
bms2227

no outside sources used

Card.java:
this class creates a card object. each card has a suit and rank to describe it.
for example king of spades or 3 of diamonds.
we have methods to return a specific cards suit and rank as shown
theres also a method to turn the descriptions of a card into a readable string
so we can actually read it as "king of spades" because when we return a suit it is only
one character (s,c,d,h) and a rank 1-13.
ive added another method to change just the suit into a string so i can print that into the 
interface to specify the suit that is turned up or chosen.

Deck.java:
Creates a deck object which is an Array
adds every card in a standard deck to the array using a nested for loop
then has a method to deal a card from that deck into a computer or player hand
or play the first card of game
another method to return if we can deal anymore. we cant deal anymore if there are
no more cards to deal! returns a boolean statement 
another method to shuffle the deck that is called in game class so we have a randomized
deck every game
another two string method to return a human readable string of a whole deck of cards

Player.java:
this class creates a player class that instantiated a hand which is an array list
to hold what cards the human player has
this class has a method to deal a card into that hand calling the deal method in deck class
this class also contains the humans player turn code
a lot goes into this. we first ask what card the user wants to play after displaying
what cards the user has. they could either play a card or draw a card to add a card to their hand
then after playing an allowed card. the method will return that card to the game class
where this method was called. if the player plays an 8, the program calls another method which allows
the player to choose what suit is going to be shown to the computer(changing the suit).
the class also has a to string method to display a human readable version of the players hand
with the numbers to the left so the player knows what card they are choosing to play
Ive also added a length method that will get the length of the players hand that will be called
by the game class to determine the winner of the game

game.java:
this class is where we instantiate a new game that will make a new deck and shuffle this deck.
it also instantiates the player object and computer player hand along with adding 7 cards to begin both
hands with 7 cards. we also need to instantiate a variable for what card is up and the suit of that card.
the first method is really important
the play method controls the play of the game. this consists of determining if the game has ended based on if
one of the hands has no more cards or the deck has run out of cards. it also calls the methods for the human
player move and the computer player move. 
one method in this class is the swap suit method. if the player plays an 8, they get to choose the suit that is shown
next. then it will put that suit into a print statement using a string conversion of that character representation of the suit
another method is the method that is called to return the boolean true if the player wants to play again once the 
game is over or false, doesnt want to play again. 
another method just returns the introduction string statement because it is pretty long and would make the play
method much longer if included in there. instead it is called by the play method. 
the second to last method returns weather the game is over or not. i need to call this method muliple times in the play method
as the game can end at many points throughout the game. the method returns false(meaning game has ended) if 
either the deck has run out of cards or one of the players hands have run out of cards. then the method prints
who wins
finally, the last method is the computer players turn. this method will have the computer player play the first 
allowed card to be played in their hand or draw if their are no allowed cards. the method then returns the card that is played
to determine the suit and rank that the human must follow for their next turn.

