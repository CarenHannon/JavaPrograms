package project1;
import javax.swing.*;
/**
 * Airplane App
 * Offers the user the ability to reserve, cancel, or view a seating chart of the seats
 * in the Airplane
 * @author Carole-Anne Franco
 *
 */

public class AirplaneApp {

	public static void main(String[]args)
	{
		// creates an airplane object
		Airplane jet = new Airplane();
		
		//sets up while loop so that the user keeps using the program
		boolean repeat = true;
		while (repeat == true)
		{
			//Shows menu options
			String[] choices = 
			{"Make a reservation", "Cancel a reservation", "View a Seating Chart", "Quit"};

			int choice = JOptionPane.showOptionDialog(
					null,
					"What would you like to do?",			//text displayed in the window
					"Main Menu",         			//text displayed in the window's title bar
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					choices,                	//text to be displayed in each button
					choices[0]);            	//default button
			
			//sets up choices to match up with their respective methods
			if (choice == 0)
			{
				jet.getSeat();
			}
			else if (choice == 1)
			{
				jet.cancelSeat();
			}
			else if (choice == 2)
			{
				//displays the seating chart using the airplane's tostring method
				JTextArea text = new JTextArea(20,30);
				JScrollPane scroll = new JScrollPane(text);
				text.setText("Seating Chart:" + jet.toString());  //objRef is a reference to an object
				JOptionPane.showMessageDialog(
				null,
				scroll,
				"Seat Assignments", //text displayed in the window's title bar
				JOptionPane.DEFAULT_OPTION);

			}
			else
			{
				//closes the program if the user chooses "Quit" (when choice = 3)
				repeat = false;
			}
		}

		
		
	

	}
}
