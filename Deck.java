/** Deck.java
*   Author: Ben Sidley . bms2227
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/

class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    // constructs a default Deck
    //deck consists of 52 card objects that are 
    //added into the deck in this constructor using that nested for loop   
    public Deck(){
        top = 0;
        deck = new Card[52];
        int t = 0;
        char s = 'g';
        for (int i=0; i<4; i++){
            if (i==0){
                 s= 'c'; 
            } else if (i==1){
                 s = 'd';
            } else if (i==2){
                 s = 's';
            } else if (i==3){
                 s = 'h';
            }
            for (int k=1; k<14; k++){
                Card c = new Card(s,k);
                deck[t] = c;
                t += 1;
            }

        }

        
    }

    // Deals the top card off the deck
    //when dealt, the placeholder will rise by one
    //to then deal the next card when called
    public Card deal(){
           int whatCard = top;
           top += 1;
           return deck[whatCard];
    }


    // returns true provided there is a card left in the deck to deal
    //this is to determine one of the ways the game can end
    public boolean canDeal(){
        boolean out = true;
        if (top==52){
            out = false;
        }
        return out;
    }

    // Shuffles the deck
    public void shuffle(){
        //swaps one element with another 2000 times
        //to make a well randomized deck
        for (int i=0;i<2000;i++){
            int index1=(int)(Math.random()*52);
            int index2=(int)(Math.random()*52);
            //this will swap one element with another
            if (index1 != index2){
                Card temp1 = deck[index1];
                Card temp2 = deck[index2];
                deck[index1]=temp2;
                deck[index2]=temp1;
            }

        }

    }

    // Returns a string representation of the whole deck
    public String toString(){
       String total = "";
       //adds descpription of everty card in deck to have a string
       //describing whole deck line by line using + \n
       for (int i=0; i<52; i++){
           total += deck[i] + "\n";
       }
       return total;
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.
}