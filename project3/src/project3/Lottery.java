package project3;

/**
 * <p>Title: The Lottery Class</p>
 *
 * <p>Description: Lets the user play one of 3 games, along with variables and other methods 
 * to run these games</p>
 * 
 * @author Carole-Anne
 */

import javax.swing.JOptionPane;
import sets.LinkedSet;

public class Lottery {
	
	//private variables
	private LinkedSet<Integer> player = new LinkedSet<Integer>();
	private LinkedSet<Integer> winning = new LinkedSet<Integer>();
	private int supNum;
	private int turns, bottom, top;

	/**
	 * Lottery
	 * Constructor that decides which next method to use
	 * @param choice - choice of the game to play. 
	 * 0 is pick 3, 1 is fifty four, and 2 is crazy lotto.
	 */
	public Lottery(int choice)
	{
			
		if (choice == 0)
			pickThree();
		else if (choice == 1)
			fiftyFour();
		else
			crazyLotto();
	}
	
	/**
	 * Pick Three
	 * Method that'll run a Pick 3 game
	 */
	private void pickThree()
	{
		//assigns private variables according to a pick 3 game
		turns = 3;
		bottom = 0;
		top = 9;
		
		//uses the fill method to assign the linkedset private variables
		fill(turns,bottom,top);
		
		//checks for a player's win
		checkWin(0);
		
	}
	
	/**
	 * FiftyFour
	 * Method that'll run a Lotto 54 game
	 */
	private void fiftyFour()
	{
		//assigns private variables according to a lotto 54 game
		turns = 6;
		bottom = 1;
		top = 54;
		
		//uses the fill method to assign the linkedset private variables
		fill(turns,bottom,top);
		
		//gets a supplementary number
		supNum = getSupplementary(bottom,top);
		
		//checks for a player's win
		checkWin(1);
	}

	
	/**
	 * CrazyLotto
	 * Method that'll run a crazy lotto game
	 */
	private void crazyLotto()
	{
		//assigns private variables according to a crazy lotto game
		turns = 6;
		bottom = 1;
		top = 25;
		
		//uses the fill method to assign the linkedset private variables
		fill(turns,bottom,top);	
		
		//checks for a player's win
		checkWin(2);
	}
	
	/**
	 * Fill
	 * Fill's out a player's guesses and the winning numbers
	 * @param turn - amount of unique guesses a user can pick
	 * @param bottom - lowest number allowed
	 * @param top - highest number allowed
	 */
	private void fill(int turn,int bottom ,int top)
	{
		int times;
		int size = 0;
		
		//fills out a player's guesses
		for (times = 0; times < turn;)
		{
			size = player.size();
			player.add(pick(bottom,top,turn));
			if(size != player.size())
			{
				times++;
			}
		}
		
		//fills out the winning numbers
		for (times = 0; times < turn;)
		{
			size = winning.size();
			//gets a random number and checks that it is unique
			winning.add(pickRandom(bottom,top));
			if(size != winning.size())
			{
				times++;
			}
		}
	}
	
	/**
	 * Pick
	 * Prompts the user for guesses
	 * @param min - lowest number allowed
	 * @param max - highest number allowed
	 * @param turns - how many times the user will guess
	 * @return the number picked
	 */
	private int pick(int min, int max, int turns)
	{
		boolean repeat = true;
		int pick = 0;
		
		//loop to make sure only a valid, unique number is added to the list
		while (repeat == true)
			{
			repeat = false;
			
			//prompts the user for input
			String question = JOptionPane.showInputDialog("Pick " + turns + " numbers from " + min + " to " + max
					+ "\n\n Numbers so far:\n" + player.toString());
			
			//adapts the program should the user close the box without answering
			if (question == null)
			{
				repeat = true;
			}
			else
			{
				
				try
				{
					pick = Integer.parseInt(question);
					
					//guarantees the number is within the range allowed
					if (pick > max || pick < min)
					{
						repeat = true;
					}
				}
				//catches the error if the user does not enter a number
				catch(NumberFormatException ex)
				{
					repeat = true;
				}
			}
		}
		return pick;
	}
	

	/**
	 * get Supplementary
	 * Picks out a unique supplementary number
	 * @param min - lowest number allowed
	 * @param max - high number allowed
	 * @return supplemementary number
	 */
	private int getSupplementary(int min, int max)
	{
		boolean retry = true;
		int temporary = 0;
		
		//ensures only a unique number will be picked
		while (retry == true)
		{
			temporary =	pickRandom(min,max);
			if (winning.contains(temporary))
			{
				retry = true;
			}
			else
				retry = false;
		}
		return temporary;
	}
	
	/**
	 * pick Random
	 * picks a random winning number
	 * @param min - lowest number allowed
	 * @param max - highest number allowed
	 * @return number
	 */
	private int pickRandom(int min, int max)
	{
		int randomNum;
		
		//gets a random number whose minimum is 0
		if(min == 0)
		{
			randomNum = (int)(Math.random() * (max + 1));
		}
		
		//gets a random number whose minimum is 1
		else
		{
			randomNum = (int)(Math.random() * max);
			randomNum++;
		}
			
		return randomNum;
	}
	
	/**
	 * Check Win
	 * Gives out an appropriate win/lose message for each type of game
	 * @param game - the game the player chose
	 * 0 is pick 3, 1 is lotto54, 2 is crazy lotto
	 */
	private void checkWin(int game)
	{
		boolean suppMessage = false;
		
		//finds out how many numbers are the same
		int same = player.intersection(winning).size();
		
		//default message
		String winningMessage = "You have lost...";
		
		//checks for a winner in a game of pick3
		if (game == 0)
		{
			if (same == turns)
				winningMessage = "You are the winner";
		}
		
		//checks for winning and ranking in a game of lotto 54
		else if (game == 1)
		{
			//allows the supplementary number to be displayed later
			suppMessage = true;
			
			//checks for 1st -3d place
			if(same > 3)
			{
				winningMessage = "You have won " + placement((7-same));
			}
			
			//checks for 4th place
			else if (same == 3)
			{
				if (player.contains(supNum))
					winningMessage = "You have won 4th place with the supplementary number";
			}
			
		}
		
		//checks for winning and ranking in a game of crazy lotto
		else
		{
			//finds out how many of a player's numbers are wrong
			int different = player.difference(winning).size();
			
			//checks for 1st - 3d place
			if(different > 3)
				winningMessage = "You have won " + placement(7-different);
		}
		
		//displays the user's & winning number
		//as well as calls out how many are the same
		winningMessage += "\n\nThese were your numbers:\n" + player.toString()
			+ "These were the lotto's numbers:\n" + winning.toString()
			+ "\nYou had " + same +" identical numbers";
		
		//displays the supplementary message for lotto 54
		if(suppMessage)
		{
			winningMessage += "\nThe supplementary number was " + supNum;
		}
		
		JOptionPane.showMessageDialog(
				null,
				winningMessage,
				"Results" ,   //text displayed within the window
				               //text displayed in the window's title bar
				JOptionPane.INFORMATION_MESSAGE);

	}
	
	/**
	 * placement
	 * Displays a ranking from 1st to 3d
	 * @param placement - number to be turned to ordinal
	 * @return string of the ranking
	 */
	private String placement(int placement)
	{
		String ranking = new String();
		if(placement == 1)
			ranking = "1st place";
		else if (placement == 2)
			ranking = "2nd place ";
		else if (placement == 3)
			ranking = "3d place";
		return ranking;
	}
	
}
