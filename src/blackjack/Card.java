package blackjack;
/**
 * Card class: Class that represents a card and all attributes a card has: suit, face, and the value
 * of the card towards the hand total. Using enums make it a lot easier to keep track of cards in the deck and makes it easier
 * to loop through each enum to fill the deck efficiently.
 * @author James
 */

public class Card
{ 
  public final Suit cardSuit;
  public final Value cardValue;
  public final int points;
 //enumeration for the possible suits of a card.
  enum Suit
  {
      HEARTS, SPADES, DIAMONDS, CLUBS;
  };
  //enumeration for the posible values (ranks) of a card.
  enum Value
  {
      TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
      
      final int points;
      //Allows other classes to access the value of a card as an integer. 
      //Makes it easier to keep track of a running total for hands.
      private Value(int points)
      {
          this.points = points;
      }  
};
  /**
   * Two-argument Card constructor. Creates a Card with its suit = argSuit and its value(rank) = to argValue.
   */
  public Card(Suit argSuit, Value argValue)
  {
      this.cardSuit = argSuit;
      this.cardValue = argValue;
      this.points = argValue.points;
      
  }
  
  /**
   * Method to output the value and suit of a card. Necessary for console Blackjack.
   * @return A string representation of the Card object.
   */
  public String cardToString()
  {
      String cardString = this.cardValue + " of " + this.cardSuit;
      return cardString;
  }
  
  
}
