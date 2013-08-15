package project1;

/**
 * <p>Seat Class
 * variables and methods that allow to create a seat and get its seat number, its type, 
 * and whether it is empty, as well as reserving or canceling a seat.</p>
 * @author Carole-Anne Franco
 *
 */
public class Seat {

	private String seatType;
	private boolean empty;
	private int seatNum;
	
	/**
	 * Seat -- parameterized constructor
	 * @param seatNumber - the number of the seat
	 * @param typeSeat - the type of seat ("Coach" or "First")
	 */
	public Seat(int seatNumber, String typeSeat)
	{
		seatType = typeSeat;
		seatNum = seatNumber;
		empty = true;
	}
	
	/**
	 * getSeatNumber -- returns the seat number
	 * @return int - The seat's number.
	 */
	public int getSeatNumber()
	{
		return seatNum;
	}
	
	/**
	 * getSeatType -- returns the type of seat
	 * @return String - A string that contains the type of seat.
	 */
	public String getSeatType()
	{
		return seatType;
	}
	
	/**
	 * checkEmpty -- checks whether a seat is empty or not
	 * @return boolean - Returns true if the seat is empty, and false if it isn't.
	 */
	public boolean checkEmpty()
	{
		return empty;
	}
	
	/**
	 * ReserveSeat -- reserves a seat, by not making it empty
	 */
	public void reserveSeat()
	{
		empty = false;
	}
	
	/**
	 * CancelSeat -- cancels a seat's reservation by making it empty.
	 */
	public void cancelSeat()
	{
		empty = true;
	}
	
	/**
	 * toString -- returns the state of the seat object.
	 * @return String - The number of the seat, its type, and whether it is empty.
	 */
	public String toString()
	{
		//Transforms the "true" and "false" of the empty/occupied seat into a "Yes" or "No"
		String temporary;
		if (empty == true)
			temporary = "Yes";
		else
			temporary = "No";
		return "\nSeat Number: " + seatNum + "\n Seat Type: " + seatType + "\n Available: " + temporary;
	}
}
