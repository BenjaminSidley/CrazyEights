/** Card.java
*   Author: Benjamin Sidley
*   
*   
*   Models a typical playing card
*
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        this.suit=suit;
        this.rank=rank;
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    //needed to add this so i can change a character to a string
    //when specifying what suit is faced up or chosen and put 
    //that string into a print statement instead of c,d,s, or h
    public String charToString(){
        String charStr = "";
        //if statements to determine what suit is played 
        //to change it to its string form
        if (this.getSuit()=='c'){
            charStr = "Clubs";
        }else if (this.getSuit()=='s'){
            charStr = "Spades";
        }else if(this.getSuit()=='h'){
            charStr = "Hearts";
        }else if(this.getSuit()=='d'){
            charStr = "Diamonds";
        }
        //returns the made string
        return charStr;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        String suitString;
        String rankString;
        // takes every case of card to change to a string form
        //goes through every case of rank (1-13) to switch if needed
        //to string form with adding "of" to go with suit
        switch(rank){
        case 1:
            rankString="Ace of ";
            break;
        case 11:
            rankString="Jack of ";
            break;
        case 12:
            rankString="Queen of ";
            break;
        case 13:
            rankString="King of ";
            break;
        default:
            rankString= rank+" of ";
        }
        //now switching to string for the each suit
        switch(suit){
        case 'c':
            suitString = "Clubs";
            break;
        case 'd':
            suitString = "Diamonds";
            break;
        case 's':
            suitString = "Spades";
            break;
        case 'h':
            suitString = "Hearts";
            break;
        default:
            suitString = "Invalid suit";
        }

        //putting two strings together to creat the desciption
        //ex. King of hears or ace of spades 
        return rankString + suitString;
    }
}