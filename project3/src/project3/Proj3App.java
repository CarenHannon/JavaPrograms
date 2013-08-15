package project3;

/**
 * <p>Title: Project 3 Application Class</p>
 *
 * <p>Description: Lets the user choose one of 3 lotto games to play</p>
 * 
 * @author Carole-Anne
 */

import javax.swing.JOptionPane;

public class Proj3App {
	public static void main (String[]args)
	{
		//local variables
		Lottery game;
		boolean quit = false;
		
		//allows the user to continue playing as many times as desired
		while (quit == false)
		{
			//lets the user pick a game
			String[] choices = 
			{"Pick-3", "Lotto-54", "Crazy/Mixed Up Lotto", "Quit"};
	
			int choice = JOptionPane.showOptionDialog(
					null,
					"What would you like to play?",			//text displayed in the window
					"Main Menu",         			//text displayed in the window's title bar
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					choices,                	//text to be displayed in each button
					choices[0]); 
			//terminates the program is "quit" is picked
			if (choice == 3)
			{
				quit = true;
				
			}
			
			//starts a game as long as the dialog box was not closed (choice -1)
			else if (choice > -1)
				game = new Lottery (choice);
		}
		
		
				
		
			
		
		
	}

}
