package project1;
import javax.swing.*;
/**
 * Airplane class 
 * contains variables and methods required to fill or empty each seat in an airplane
 * @author Carole-Anne Franco
 */

public class Airplane {

	//variables
	private Seat[] seats;
	
	/**
	 * Airplane Constructor
	 * creates an Airplane object with 4 First Class seats and 6 Coach class seats
	 */
	public Airplane()
	{
		seats = new Seat[10];
		int firstClass = 4;
		for (int i = 0; i < firstClass; i++)
		{
			seats[i] = new Seat(i + 1, "First");
		}
		
		for (int i = firstClass; i < seats.length; i++)
		{
			seats[i] = new Seat(i + 1, "Coach");
		}
		
	}
	
	/**
	 * Get Seat
	 * Reserves a seat that was previously available
	 */
	public void getSeat()
	{
		//variables
		boolean repeat = true;
		String errorMessage = new String("");
		int pick = 0;
		String type = new String();
		
		//while loop so that the user will continue picking until a correct answer has been chosen
		while (repeat == true)
		{
			//sets repeat to false so that , if all goes well, the user does not have to repeat
			repeat = false;
			
			//User chooses between First and Coach seats
			String[] choices = 
			{"First", "Coach"};

			int choice = JOptionPane.showOptionDialog(
					null,
					errorMessage + "What kind of seat would you like?",//text displayed in the window
					"Choosing a Seat",         			//text displayed in the window's title bar
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					choices,                	//text to be displayed in each button
					choices[0]);
			if (choice == -1)
			{
				return;
			}
		
			//Respectively adapts a string object to match the user's choices
			if (choice == 0)
			{
				type = "First";
			}
			else
			{
				type = "Coach";
			}
			
			//grabs the available seats for this method, and which that particular seat type choice		
			String displaySeats = this.showAvailableSeats(type, true);
			
			//displays the available seats and prompts for user imput
			String question = JOptionPane.showInputDialog("Which Seat Would You Like? \n" 
					+ "Please Enter a seat number\n" + displaySeats + "Enter 0 to quit\n" );
			
			//adapts the program should the user close the box without answering
			if (question == null)
			{
				return;
			}
			
			//transforms the choice into a number that represents the seat numbers in the array
			else
			pick = Integer.parseInt(question) - 1;
			
			//closes the program if the user inputed 0 (the "quit" number)
			if (pick == -1)
			{
				return;
			}
			
			//ensures that only user-inputed numbers from 0 - 10 are usable
			//turns the loop back on
			if (pick >= seats.length || pick < -1)
			{
				repeat = true;
				errorMessage = "Error: You've chosen a number out of range (0-10)\n";
			}
			
			//ensures that the user cannot pick a seat out of its type (first or coach)
			//turns the loop back on
			else if (!seats[pick].getSeatType().equals(type))
			{
				repeat = true;
				errorMessage = "Error: You've chosen a seat of the wrong type\n";
			}	
			
			//ensure that the user cannot pick a seat that was already taken
			//turns the loop back on
			else if (seats[pick].checkEmpty() == false)
			{
				repeat = true;
				errorMessage = "Error: You've chosen an already occupied seat\n";
			}	
		} // while loop ends
		
		//reserves a seat
			seats[pick].reserveSeat();
			
		//shows a successful message
		JOptionPane.showMessageDialog(null, 
				"You've reserved the " + type +" class seat " + (pick + 1),
				"Reserve Complete",
				JOptionPane.INFORMATION_MESSAGE);;
	}
	
	/**
	 * cancel Seat
	 * Allows the user to cancel, and therefore empty, a previously occupied seat
	 */
	public void cancelSeat()
	{
		//variables
		boolean repeat = true;
		String errorMessage = new String("");
		int pick = 0;
		
		//while loop so that only proper numbers are used
		while (repeat == true)
		{
			//sets the loop off just in case all goes well
			repeat = false;
			
			//calls to a method that will grab seats from both types, but only if they are taken
			String displaySeats = this.showAvailableSeats("both" , false);
			
			//Prompts the user for a choice of seat to cancel
			String question = JOptionPane.showInputDialog(errorMessage 
					+ "Which Seat # Would you like to cancel?\n" + displaySeats + "\nPress 0 to quit\n");
			
			//adapts the method in case the user closes the dialog box manually
			//the method will terminate without errors
			if (question == null)
			{
				return;
			}
			
			else
				pick = Integer.parseInt(question) - 1;
			
			//closes the program if the user inputed 0 (the "quit" number)
			if (pick == -1)
			{
				return;
			}
			
			//ensures that only numbers within a 0-10 user-inputed range can be used
			//turns the loop back on
			if (pick >= seats.length || pick < -1)
			{
				repeat = true;
				errorMessage = "Error: You've chosen a number out of range (0-10)\n";
			}
			
			//ensures that seats that were already empty cannot be re-emptied
			//turns the loop back on
			else if (seats[pick].checkEmpty() == true)
			{
				errorMessage = "Error: You've chosen an already empty seat\n";
				repeat = true;
			}
		}
		
		//cancels a seat
		seats[pick].cancelSeat();
			
		//shows a successful canceling
		JOptionPane.showMessageDialog(null, 
				"You've canceled the " + seats[pick].getSeatType() +" class seat " + (pick + 1),
				"Canceled Complete",
				JOptionPane.INFORMATION_MESSAGE);;		
	}
	
	/**
	 * Show Available Seats
	 * Handy method to grab only seats of a certain needed type and availability
	 * @param type - a String that allows only 3 types of answers
	 *    "First" : To show only seats that are First
	 *    "Coach" : To show only seats that are Coach
	 *    "Both" : To show both First and Coach seats
	 * @param emptiness - a boolean. True means only seats that are empty.
	 *    False means only seats that are reserved.
	 * @return String. Returns a string of all the seats that fit the criterias passed on by the parameters
	 *    A default message of "There are no seats available for this command" will occur if no seats are found.
	 */
	private String showAvailableSeats(String type, boolean emptiness)
	{
		//title of this list
		String available = new String("Seats available: \n");
		
		//variable
		int seatsAvailable = 0;
		
		//Allows both First and Coach seats to be listed
		if (type == "both")
		{
			//for loop that will list all of the seats who are empty or occupied 
			//as required by the "emptiness" parameter
			for (int i = 0; i < seats.length; i++)
			{
				if (seats[i].checkEmpty() == emptiness)
				{
					//raises this variable so that the default message will not occur
					seatsAvailable++;
					
					//stores the seat numbers and the seat's type into a message
					available += "Seat: " + seats[i].getSeatNumber() + "\n " + seats[i].getSeatType() + " Class";
				}
			}
		}
		
		//steps to be used if a specific seat type was passed
		else
		{
			//for loop that will list all of the seats who are empty or occupied 
			//as required by the "emptiness" parameter
			//Also checks that the seats's actual types match the required "type" parameter
			for (int i = 0; i < seats.length; i++)
			{
				if (seats[i].checkEmpty() == emptiness && type.equals(seats[i].getSeatType()))
				{
					//raises this variable so that the default message will not occur
					seatsAvailable++;
					
					//stores the seat numbers into a message
					available += "Seat: " + seats[i].getSeatNumber() + "\n";
				}
			}
		}
		
		//default message if no seats are available according to the required parameters
		if (seatsAvailable == 0)
		{
			//returns the default message
			return available + "\nThere are no seats available for this command";
		}
		
		//returns the list
		return available;
	}
	
	/**
	 * ToString
	 * Shows the state of each seat within the airplane object
	 * @return String. A message containing the state of the Airplane object
	 */
	public String toString()
	{
		String message = "\n";
		for (int i = 0; i < seats.length; i++)
		{
			message += seats[i].toString() + "\n";
		}
		return message;
	}
}
