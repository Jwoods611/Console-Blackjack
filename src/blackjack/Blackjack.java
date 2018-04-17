/**
 * James Woods
 * Game Programming
 * November 20, 2017
 * 
 * A simple implementation of player-versus-dealer blackjack. Includes AI and three difficulties, with AI getting progressively more intelligent
 * And flexible. Later will be ported to Android, so interface is irrelevant. Once ported to Android, a notable change to the game's mechanics will be
 * Not allowing the player to know one of the dealer's cards, like it is in a casino.
 */
package blackjack;

import blackjack.Card.Value;
import java.util.Scanner;

public class Blackjack
{

    public static void main(String[] args) 
    {
         
        int difficulty;
        while(true)
        {
            //First, the user chooses a difficulty.
            System.out.println("Let's play Blackjack!");
            System.out.println("Choose a difficulty:");
            System.out.println("Enter 1 for easy, 2 for medium, or 3 for hard");
            
            Scanner scanner = new Scanner(System.in);
            difficulty = scanner.nextInt();
            
            switch(difficulty)
            {
                case 1:
                    runEasyMode();
                    break;
                case 2:
                    runMediumMode();
                    break;
                case 3:
                    runHardMode();
                    break;
                default:
                    break;
            }
            break;
        }
        
    }
    
   static public void runEasyMode()
    {
        
       System.out.println("Easy difficulty");
       Deck deck = new Deck();
       Hand dealer = new Hand();
       Hand player = new Hand();
       
       //Shuffle the deck
       
       deck.shuffleDeck();
       Card dealerCard1 = deck.dealCard(); 
       Card playerCard1 = deck.dealCard();
       Card dealerCard2 = deck.dealCard(); //This card, however, will be known to the player (console output of suit and value)
       Card playerCard2 = deck.dealCard();
       
       //Add the generated cards to the respective hands
       dealer.addCard(dealerCard1);
       dealer.addCard(dealerCard2);
       player.addCard(playerCard1);
       player.addCard(playerCard2);
       
       System.out.println("Dealer card 1: " + dealerCard1.cardToString());
       System.out.println("Dealer card 2: " + dealerCard2.cardToString());
       System.out.println("Player card 1: " + playerCard1.cardToString());
       System.out.println("Player card 2: " + playerCard2.cardToString());
       
       int dealerTotal = dealer.getHandTotal();
       int playerTotal = player.getHandTotal();
       boolean playerBust = false;
       boolean dealerBust = false;
       while (true)
       {
          System.out.println("Dealer has " + dealerTotal + " , you have " + playerTotal);
          System.out.println("Hit or stay? Enter 1 for hit, 2 for stay");
          
          Scanner scanner = new Scanner(System.in);
          int playerChoice = scanner.nextInt();
          
          if(playerChoice == 1)
          {
              Card cardDealt = deck.dealCard();
              player.addCard(cardDealt);
              playerTotal = player.getHandTotal();
              System.out.println("Card dealt to player: " + cardDealt.cardToString());
              if (playerTotal > 21)
              {
                  playerBust = true;
                  break;
              }
          }
          if(playerChoice == 2)
             break;
          
       }
       /**
        * Easy mode AI implements basic casino-style dealer logic. The dealer must hit on anything 16 or under, and stays on anything 17 or above.
        * Medium and hard mode will implement other AI that obviously would not be found in a casino.
        */
          while(dealerTotal < 17)
          {
              Card dealerCardDealt = deck.dealCard();
              dealer.addCard(dealerCardDealt);
              System.out.println("Dealer hits, was dealt a " + dealerCardDealt.cardToString());
              dealerTotal = dealer.getHandTotal();
              
              if(dealerTotal >= 22)
                  dealerBust = true;
          }   
          
          /**
           * Winner logic based on who busted. Logic if no one busted follows below.
           * Will return if someone busted, since we don't care whose total was greater.
           */
            if (playerBust && !dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player busted, dealer wins!");
                return;
            }
            
            if (!playerBust && dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer busted, player wins!");
                return;
            }
            if (playerBust && dealerBust)
            {
               System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", you both busted!");
               return;
            }
            
            /**
             * Remaining winner logic. Dealer wins ties.
            **/
            if (dealerTotal >= playerTotal)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer wins!");
            
            if (playerTotal > dealerTotal && !playerBust)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player wins!");
              
    }
    
   static public void runMediumMode()
    {
     System.out.println("Medium difficulty");   
       Deck deck = new Deck();
       Hand dealer = new Hand();
       Hand player = new Hand();
       
       //Shuffle the deck
       
       deck.shuffleDeck();
       Card dealerCard1 = deck.dealCard(); //This card will be not known to the player (no console output of suit nor value)
       Card playerCard1 = deck.dealCard();
       Card dealerCard2 = deck.dealCard(); //This card, however, will be known to the player (console output of suit and value)
       Card playerCard2 = deck.dealCard();
       
       //Add the generated cards to the respective hands
       dealer.addCard(dealerCard1);
       dealer.addCard(dealerCard2);
       player.addCard(playerCard1);
       player.addCard(playerCard2);
       
       System.out.println("Dealer card 1: " + dealerCard1.cardToString());
       System.out.println("Dealer card 2: " + dealerCard2.cardToString());
       System.out.println("Player card 1: " + playerCard1.cardToString());
       System.out.println("Player card 2: " + playerCard2.cardToString());
       
       int dealerTotal = dealer.getHandTotal();
       int playerTotal = player.getHandTotal();
       boolean playerBust = false;
       boolean dealerBust = false;
       while (true)
       {
          System.out.println("Dealer has " + dealerTotal + " , you have " + playerTotal);
          System.out.println("Hit or stay? Enter 1 for hit, 2 for stay");
          
          Scanner scanner = new Scanner(System.in);
          int playerChoice = scanner.nextInt();
          
          if(playerChoice == 1)
          {
              Card cardDealt = deck.dealCard();
              player.addCard(cardDealt);
              playerTotal = player.getHandTotal();
              System.out.println("Card dealt to player: " + cardDealt.cardToString());
              if (playerTotal > 21)
              {
                  playerBust = true;
                  break;
              }
          }
          if(playerChoice == 2)
             break;
          
       }
       /**
        * Medium mode AI implements a probability model. The AI calculates the probability of going over 21 by iterating through the deck
        * and totaling the events where the hand goes over 21. The AI will only hit if the chance of busting is less than 50%.
        * 
        * Since there's a good possibility of the AI having a chance of busting that's greater than 50%, AND a hand total less than that of the player,
        * I wanted to implement a second part of the AI that always hits if its total is lower than the player's. This way, it won't automatically lose
        * some games where its chance of busting is greater than 50%. This also takes care of some of the cases where the AI won't hit on 15 or 16 because
        * its chance of busting is greater than 50%.
        */
          double bustChance = deck.bustChance(dealerTotal);
          System.out.println("Chance of busting: " + bustChance + "%");
          
          while(bustChance < 50.00)
          {
              Card dealerCardDealt = deck.dealCard();
              dealer.addCard(dealerCardDealt);
              System.out.println("Dealer hits, was dealt a " + dealerCardDealt.cardToString());
              dealerTotal = dealer.getHandTotal();
              
              if(dealerTotal >= 22)
                  dealerBust = true;
              
              bustChance = deck.bustChance(dealerTotal);
              System.out.println("Chance of busting: " + bustChance + "%");

          }   
          
          while(dealerTotal < playerTotal)
          {
              if (playerBust == false && dealerBust == false)
              {
              Card dealerCardDealt = deck.dealCard();
              dealer.addCard(dealerCardDealt);
              dealerTotal = dealerTotal + dealerCardDealt.points;
              System.out.println("Dealer hits, was dealt a " + dealerCardDealt.cardToString() + ". Total is now: " + dealerTotal);
              
              if(dealerTotal >= 22)
              {
                  dealerBust = true;
                  break;
              }
              }
              
              break;
          }
          
          /**
           * Winner logic based on who busted. Logic if no one busted follows below.
           * Will return if someone busted, since we don't care whose total was greater.
           */
            if (playerBust && !dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player busted, dealer wins!");
                return;
            }
            
            if (!playerBust && dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer busted, player wins!");
                return;
            }
            if (playerBust && dealerBust)
            {
               System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", you both busted!");
               return;
            }
            
            /**
             * Remaining winner logic. Dealer wins ties.
            **/
            if (dealerTotal >= playerTotal)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer wins!");
            
            if (playerTotal > dealerTotal && !playerBust)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player wins!");
    }
    
   static public void runHardMode()
    {
     System.out.println("Hard difficulty");
     Deck deck = new Deck();
     Hand dealer = new Hand();
     Hand player = new Hand();
       
       //Shuffle the deck
       
       deck.shuffleDeck();
       Card dealerCard1 = deck.dealCard(); //This card will be not known to the player (no console output of suit nor value)
       Card playerCard1 = deck.dealCard();
       Card dealerCard2 = deck.dealCard(); //This card, however, will be known to the player (console output of suit and value)
       Card playerCard2 = deck.dealCard();
       
       //Add the cards to the respective hands.
       
       dealer.addCard(dealerCard1);
       dealer.addCard(dealerCard2);
       player.addCard(playerCard1);
       player.addCard(playerCard2);
       
       System.out.println("Dealer card 1: " + dealerCard1.cardToString());
       System.out.println("Dealer card 2: " + dealerCard2.cardToString());
       System.out.println("Player card 1: " + playerCard1.cardToString());
       System.out.println("Player card 2: " + playerCard2.cardToString());
       
       int dealerTotal = dealer.getHandTotal();
       int playerTotal = player.getHandTotal();
       boolean playerBust = false;
       boolean dealerBust = false;
       while (true)
       {
          System.out.println("Dealer has " + dealerTotal + " , you have " + playerTotal);
          System.out.println("Hit or stay? Enter 1 for hit, 2 for stay");
          
          Scanner scanner = new Scanner(System.in);
          int playerChoice = scanner.nextInt();
          
          if(playerChoice == 1)
          {
              Card cardDealt = deck.dealCard();
              player.addCard(cardDealt);
              playerTotal = player.getHandTotal();
              System.out.println("Card dealt to player: " + cardDealt.cardToString());
              if (playerTotal > 21)
              {
                  playerBust = true;
                  break;
              }
          }
          if(playerChoice == 2)
             break;
       }
       
       /**
        * Hard mode AI is essentially cheating; instead of using probability to predict bust outcome, the AI will look at the top card of the deck
        * to make that decision instead. 
        */
       

       while (!playerBust)
       {
           Card top = deck.peekCard();           
           System.out.println("Top card is: " + top.cardToString());
           //If the top card won't result in a bust, hit
           
           int topPoints = top.points;
           
           //Ace checking is in Hand.java, so we need to add it here. Otherwise AI will think Aces are always worth 11.
           if (top.cardValue == Value.ACE && top.points + dealerTotal > 21)
               topPoints = 1;
           if (dealerTotal + topPoints <= 21)
           {
               Card dealerCardDealt = deck.dealCard();
               dealer.addCard(dealerCardDealt);
               dealerTotal = dealer.getHandTotal();
               System.out.println("Card dealt to dealer: " + dealerCardDealt.cardToString());
           }
           //If the top card will result in a bust, break out of the loop
           else 
           {
            System.out.println("Top card will result in a bust. Stay.");
            break;
           }
           
           //Flag to know when the dealer busts, and break out of the loop.
           if (dealerTotal >= 22)
           {
               dealerBust = true;
               break;
           }
       }
       
       /**
           * Winner logic based on who busted. Logic if no one busted follows below.
           * Will return if someone busted, since we don't care whose total was greater.
           */
            if (playerBust && !dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player busted, dealer wins!");
                return;
            }
            
            if (!playerBust && dealerBust)
            {
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer busted, player wins!");
                return;
            }
            if (playerBust && dealerBust)
            {
               System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", you both busted!");
               return;
            }
            
            /**
             * Remaining winner logic. Dealer wins ties.
            **/
            if (dealerTotal >= playerTotal)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", dealer wins!");
            
            if (playerTotal > dealerTotal && !playerBust)
                System.out.println("Dealer has " + dealerTotal + ", player has " + playerTotal + ", player wins!");
    }
}