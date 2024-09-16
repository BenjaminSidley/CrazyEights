/** Player.java
*   Author: Benjamin Sidley. bms2227
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;


class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

   //this class will initialize a new player and its varibales
   //which are basically only its hand
    public Player(){
        input = new Scanner(System.in);
        hand = new ArrayList<Card>();
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        //pretty simple, adds card to deck when called from game class using 
        //deal method in deck class
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck, Card c){        
        //print statements to guide the human player
        System.out.println("\nType 'draw' to draw a card, or type the number "+
        "next to the card in your hand that you wish to play\n");
        System.out.println("Your cards are:\n");
        System.out.println(this.handToString() + "\n ");
        boolean valid = false;
        Card temp = hand.get(0);
        boolean check = deck.canDeal();
        //while loop to make sure the game isnt over and that the 
        //player can only play one card at a time
        while (valid == false && check == true){
            check = deck.canDeal();
            //checks if the deck has any more cards
            //if doesnt, breaks out of while loop to end game
            if (check == false){
                break;
            }
            //takes user input 
            String index = input.nextLine();
            //if input is draw, program deals a card into the humans hand
            //and then asks it to play a card again
            if (index.equals("draw")){
                hand.add(deck.deal());
                System.out.println("Your cards are now:\n");
                System.out.println(this.handToString() + "\n " );
                System.out.println("\nType 'draw' to draw a card, or type the number "+
                "next to the card in your hand that you wish to play\n");
            } else {
                //when the player actually plays a card
                try {
                    //takes number of card on the left to indicate what
                    //card they want to play
                    int num = Integer.parseInt(index);                                       
                    temp = hand.get(num-1);                   
                    boolean canYou = this.isValid(temp,c);
                    //if statment making sure the card is a valid card so
                    //the human cannot cheat. calls isValid method to determine this
                    //if valid input, will remove that card from hand and place it down
                    if (canYou == true){           
                        System.out.println("You played the " + temp);
                        hand.remove(num-1);
                        valid = true;
                    }
                } 
                //making sure the player types the correct format allowing them
                //to make another choice if they dont 
                //tell them what mistake they made 
                catch (NumberFormatException e) {
                    System.out.println("\nInvalid input, try again");
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("\nThat card index is not in your hand\n" +
                    "please pick another card...one thats in your hand");


                }
             }


        }
        //returns what card is played
        return temp; 

    }

    
    //class to determine if the card played is allowed and is valid 
    //will not allow card to be played if not in the play method
    public boolean isValid(Card a,Card c){
        boolean temp = true;
        char ch = c.getSuit();
        int rn = c.getRank();
        char playedch = a.getSuit();
        int playedrn = a.getRank();
        //if statemnt determines if the played rank or suit matches
        //the shown rank or suit. or if played 8 is allowed
        if (ch != playedch && rn != playedrn && playedrn !=8){
            temp = false;
        }        
        if (temp == false){
            System.out.println("\nThat card is not allowed. "+
            "Please play a card with the same rank, suit, or an 8!\n ");
        }
        //returns boolean determining if it is a valid play
        return temp ;
    }   
    
    // Accessor for the players hand   
    public ArrayList<Card> getHand(){
        return hand;
    }

    //length method so we can determine winner of game
    //by determining length of both hands
    public int playerLen(){
        int length = hand.size();
        return length;
    }

    // Returns a printable string representing the player's hand
    //very similar to deck class but will have indicator so 
    //it is easy for player to play desired card
    public String handToString(){
        String total = "";
        int i = 1;
        String IStr = "";     
        for(Card index : hand){
            IStr = Integer.toString(i);
            //adds indicator in front so player can deterine what card
            total += IStr+ "    " + index + "\n";
            i += 1;
        }
        return total;
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

} // end
