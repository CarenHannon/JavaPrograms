package project2;

/**
* <p>Title: Rummy Application Class</p>
*
* <p>Description: Creates a game of Rummy</p>
* 
* @author - Carolanne Franco
*/

import javax.swing.*;
public class RummyApp 
{
	public static void main(String[]args)
	{
		//variables to get the game ready
		Deck theDeck = new Deck();
		theDeck.shuffleDeck();
		RummyHand player1 = new RummyHand();
		RummyHand player2 = new RummyHand();
		String[] choices = 
		{"NO", "Yes"};
		int choice = 1;
		
		//deals 7 cards to players 1 & 2
		for (int i = 0; i < 7; i ++)
		{
			player1.insertCard(theDeck.dealCard());
			player2.insertCard(theDeck.dealCard());
		}

		//Instantiates GUI window
		GUI window = new GUI(8,true);
		
		//creates and deals a card to the discard pile and shows it in the GUI
		Deck discardPile = new Deck(38);
		discardPile.addCard(theDeck.dealCard());
		
		//extra variables that determine the end of a game and winnings, and also counts rounds
		boolean stop = false;
		boolean win1 = false;
		boolean win2 = false;
		
		//Start of the player phase and loop until told to stop
		while (stop == false)
		{			
			//lets player 1 play
			Round(player1,theDeck,discardPile,window, 1);
			
			//checks if player 1 has won
			//if player 1 wins, the game will then end
			if (player1.checkWin() == true)
			{
				stop = true;
				win1 = true;
			}
			
			//allows player 2's turn if player 1 has not yet won
			else
			{
				//lets player 2 play
				Round(player2,theDeck,discardPile,window, 2);
				
				//tells the game to stop if player 2 has won
				if(player2.checkWin() == true)
				{
					stop = true;
					win2 = true;
				}
			}
		}
		
			
		//determines if player 1 has won
		if (win1 == true)
			{
			JOptionPane.showMessageDialog(
					null,
					"Player 1 won",   
					"PLAYER 1",               
					JOptionPane.INFORMATION_MESSAGE);
			}
		
		//determines if player 2 has won
		else
		{
			JOptionPane.showMessageDialog(
					null,
					"Player 2 won",   
					"PLAYER 2",               
					JOptionPane.INFORMATION_MESSAGE);
			}

	}
	
	/**
	 * Round -- method that does the actual 1-player round of a game
	 * @param player - RummyHand player that will be playing
	 * @param theDeck - Deck object that has the game's cards
	 * @param discardPile - Parameterized Deck object that acts as the discard pile
	 * @param window - GUI window
	 * @param playerNumber - player number. (1 for player 1, 2 for player 2)
	 */
	private static void Round(RummyHand player, Deck theDeck, Deck discardPile, GUI window, int playerNumber)
	{

		//section 1: round start up
		//sorts the players's hand by value for easy viewing
		//as well as to make sure a later-removed card won't create a null spot
		player.sort();
		
		//shows the discard pile's top card
		window.showDiscardPileCard(discardPile.getTopCard());
		
		//section 2: adding a card
		//shows the player's hand
		window.showHand(player.getCardArray(), player.getCardsInHand());
		
		//variables for this section of the game
		int choice = -1;
		String[] choices = 
		{"Discard Pile", "Top of the deck"};
		int addedPosition = -1;
		
		//guarantees that the game only goes on if the player "passed"
		//which means only appropriate user input will be allowed
		boolean pass = false;
		while (pass == false)
		{
			while (choice == -1)
			{
				choice = JOptionPane.showOptionDialog(
						null,
						"Where would you like to draw from?",		
						"PLAYER " + playerNumber,         			
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						choices,                	
						choices[0]);  
			}
			
			
			//remove a card from discard pile and put it in the hand
			if (choice == 0)
			{
				
				Card tempCard = discardPile.removeCard();
				player.insertCard(tempCard);
				addedPosition = player.getCardPosition(tempCard);
				pass = true;
				
			}
			
			
			//removes a card from deck and puts it in the hand
			try{
				if (choice == 1)
				{					
					player.insertCard(theDeck.dealCard());		
					addedPosition = -2;
					pass = true;
				}
			}
			catch (DeckException Ex)
			{
				theDeck.cut(discardPile);
				player.insertCard(theDeck.dealCard());		
				addedPosition = -2;
				pass = true;
			}
		}
		
		
		//section 3: discarding a card
		//user removes a card
		//local variables to this section
		boolean allow = false;
		int cardRemoved = 0;
		String errorMessage = new String();
		
		//while loop to make sure only valid input is accepted
		while (allow == false)
		{
			try
			{
				//shows the player's new hand
				window.showHand(player.getCardArray(), player.getCardsInHand());
				
				//adapts the windows to the previously made choices from the previous section
				//of the game
				if (choice == 0)
				{

					window.clearDiscardPileCard();
				}
				else
				{
					window.clearDeckCard();
					window.showDiscardPileCard(discardPile.getTopCard());
				}
				
				//asks for user input of which card to remove
				String cardRemove = JOptionPane.showInputDialog(errorMessage + "Which card would you like to remove?" +
						"\nPlease enter an occupied card slot from 1 - 8");
				cardRemoved = Integer.parseInt(cardRemove);
				
				//Makes sure that the user cannot discard a card that was just taken from the discard pile
				if (cardRemoved - 1 == addedPosition)
				{
					errorMessage = "You cannot discard a card that you've drawn from the discard pile\n";
				}
				
				//Removes the card from the hand and adds it to the discard pile
				else
				{
					if (cardRemoved > 0 && cardRemoved < 9)
					{
						discardPile.addCard(player.RemoveCard(cardRemoved - 1));
						allow = true;
					}
					else
					{
						errorMessage = "Invalid Entry\n";
					}
					
				}
			}
			
			
			//catch block to catch invalid user input
			catch(NumberFormatException Ex)
			{
				errorMessage = "Invalid Entry.\n\n";
			}
			//catch block to make sure a user cannot attempt to remove a card from an empty slot
			catch(HandException Ex)
			{
				errorMessage = "That slot is already empty\n\n";
			}
			
			
			//clears the windows
			window.clearDeckCard();
			window.clearDiscardPileCard();
			window.clearPlayerCards();
		}
	}
}
