package blackjack;
/**
 * This class implements a Hand object. The hand holds the cards each player has been
 * dealt, and can be utilized to check to see if a player has an ace, has blackjack, keep a 
 * running total of the hand, etc.
 */

import blackjack.Card.Value;
import java.util.ArrayList;

public class Hand 
{
    private ArrayList<Card> cards;
    private int handTotal;
    private int aceCount = 0;
    
    //Constructor for a new Hand object.
    public Hand()
    {
        cards = new ArrayList<>();
    }
    public void addCard(Card c)
    {
        this.cards.add(c);
        
        /**Need to determine whether an ace will count for 1 or 11, based on
         * hand total.
         */
        
        if(c.cardValue == Value.ACE)
            aceCount++;
            /**If the card we draw puts us over 21, and there's an ace in our hand,
             * Then we want to make the ace equal 1 instead of 11. 
             */
        if(handTotal + c.points > 21 && aceCount != 0)
        {
         handTotal = handTotal + (c.points - (10*aceCount)); //Ace = 1
         System.out.println("Your aces are now low.");
         aceCount = 0;
        } 
         
        else
         handTotal = handTotal + c.points; //Ace = 11
        
    }
    
    /**
     * Method to dynamically get a running total of a given hand.
     * @return the running total of the hand
     */
    public int getHandTotal()
    {
        return this.handTotal;
    }
}