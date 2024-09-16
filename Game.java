/** Game.java
*   Author: benjamin sidley. bms2227
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    //need to initialize varibale player, 
    //computers hand, face card ands that cards suit
    public Game(){
        input = new Scanner(System.in);
        //makes new deck for this instance of the game
        cards = new Deck();
        // shuffles said deck
        cards.shuffle();
        p1 = new Player();
        compHand = new ArrayList<Card>();
        //deals 7 cards to both hands
        for (int i=0; i<7; i++){
            p1.addCard(cards.deal());
            compHand.add(cards.deal());
        }
        faceup = cards.deal();
        currentSuit = faceup.getSuit();

    }

   

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){    
            System.out.println(this.intro());
            boolean notEnd = this.end();
            //while statment to stop game once ends
            while (notEnd == true){
                //print statements for human guide
                System.out.println("The up card is the " + faceup);
                System.out.println("The current suit is " + faceup.charToString());
                //determines what card is facing up from
                //calling the player turn which makes the player play a card
                faceup = p1.playsTurn(cards,faceup);               
                char x = faceup.getSuit();
                int y = faceup.getRank();
                //if player plays 8, they get to choose what suit is shows
                //when calls the method to get their choice
                if (y == 8){
                    //places choice in suit varibale 
                    x = this.eightSwap();
                } 
                notEnd = this.end();
                //checking if game has ended after human turn to break while loop
                if (notEnd == false){
                    break;
                }
                faceup = this.computerTurn(x,y);
                //checking if game has ended after computer turn 
                notEnd = this.end();
                if (notEnd == false){
                    break;
                }





            }
            //calls method to ask user if they want to play 
            //again after game ends
            boolean again = this.wantAgain();
            //returns that decision to main method
            return again;

        
    }
    //method to get players choice on suit if played an 8
    public char eightSwap(){
        //prints options to choose from
        System.out.println("Choose a suit to set! (c,h,d,s)");
        //takes users input for what suit they want
        char ch = input.next().charAt(0);
        String pr = "";
        //converting the choice to readable string 
        if (ch== 'c'){
            pr = "Clubs";
        }
        else if (ch== 'h'){
            pr = "hearts";
        }
        else if (ch== 'd'){
            pr = "Diamonds";
        }
        else if (ch== 's'){
            pr = "Spades";
        }
        else{
            System.out.println("thats not a suit!");
        }
        //prints what suit is shown now that they made theur choice
        System.out.println("The current suit is " + pr);
        //returns their choice to be placed in the varibale
        //that holds the suit that determines what the computer
        //can play
        return ch;

    }
    //simple method that asks user if they want to play again
    public boolean wantAgain(){
        System.out.print("Would you like to play again? (y/n)");
            char ch = input.next().charAt(0);
            //takes choice and assigns/returns boolean determining on choice
            if (ch == 'y'){
                return true;
            } else {
                return false;
            }

    }

    
    
    //simple string method that holds the opening intoduction string
    public String intro(){
        String statement = "\n\nWelcome to Crazy Eights! You'll start with 7 cards." +
        "\nYour job is to match a card in your hand with the up card." +
        "\nYou can match it by suit or rank." +
        "\nIf you play an 8, you can switch the active suit." +
        "\nIf you run out of cards, you win!" +
        "\nIf you make it through the whole deck then whoever has" +
        "\nthe fewest cards left wins!" +
        "\nGood luck!\n\n"; 

        return statement;        
    }

    
    //check for end of game based on the 3 factors
    public boolean end(){
        //3 factors, both hands lengths and if the deck has any more cards
        boolean notCheck = cards.canDeal();
        int userLen = p1.playerLen();
        int compLen = compHand.size();
        String endStatement = "";
        //determining if one of these factors are true
        //if one is assigns and returs false to end game and break out
        //of while loop in the play method
        if (notCheck == false || userLen == 0 || compLen ==0){
            if (userLen<compLen){
                endStatement = "You Win!! :)";
                notCheck = false;
            } else if (userLen>compLen){
                endStatement = "You Lost :(";
                notCheck = false;                
            } else {
                endStatement = "You Tied :| ";
                notCheck = false;
                
            }
            //prints who won the game based on who has fewer cards
            System.out.println("You have "+userLen+ " cards left" +
            "\nComputer has "+compLen+" cards left\n" +
            endStatement );
        }
        //returns the boolean true if game is not over or false
        //if game is over
        return notCheck;

    }

    
    
    
                                                                                    
    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
     private Card computerTurn(char x, int y){
        boolean played = false;
        boolean check = cards.canDeal();
        Card temp = compHand.get(0);
        while (played == false && check == true){
            //checks if there is a playable card in computers hand
            //iterates through hand
            for (int i =0; i<compHand.size() && played == false; i++){
                Card now = compHand.get(i);
                char nowSuit = now.getSuit();
                int nowRank = now.getRank();
                //if there is playable card, it will play it and 
                //break out of for loop because plyed will equal true
                if (nowSuit == x || nowRank == y || nowRank == 8){
                    temp = compHand.get(i);
                    compHand.remove(i);
                    System.out.println("The computer played " + temp+"\n");
                    played = true;                   
                }
                //if not cards can be played in the computers hand,
                //the computer will draw a card from the deck until they
                //get a card they can play
                if (played != true && i==compHand.size()-1){
                    check = cards.canDeal();
                    if (check == true){
                        System.out.println("Computer drew card\n");
                        compHand.add(cards.deal());
                    }
                    
                    
                }
            
            }



        }
        //retruns what card the computer plays to determine suit and
        //rank the human needs to follow
        return temp;
            
    }
    
// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.


}