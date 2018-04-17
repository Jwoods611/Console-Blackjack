package blackjack;
/**
 * This is the class that implements a Deck object. The deck is a standard deck of 52 cards.
 * After doing some research online, all other implementations I came across used an ArrayList to implement the deck.
 * But I've found that using a Stack is much more efficient and allows for ease of use when it comes to dealing and checking cards.
 */
import blackjack.Card.*;
import java.util.Collections;
import java.util.Stack;
public class Deck 
{
    public Stack<Card> deck = new Stack<>();

    public Deck()
    {
        //For each suit, fill the deck with one card of each rank
        for(Suit suit: Suit.values())
            for(Value value : Value.values())
                deck.push(new Card(suit, value));  
    }
    
    //Method to deal a card. Makes use of the Stack's predefined methods.
    public Card dealCard()
    {
        return deck.pop();
    }
    
    //Shuffles the given deck object. Also makes use of the Stack's interface.
    public void shuffleDeck()
    {
        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }
    
    public double bustChance(int handTotal)
    {
      double numCards = 0;
      for (Card card: deck)
          if (card.points + handTotal > 21)
              numCards++;
           
      return (numCards/(double)deck.size()) * 100;
       }
    
       //Method to peek at the top card of the deck. Used for Hard mode AI.
       public Card peekCard()
       {
           return deck.peek();
       }
}
