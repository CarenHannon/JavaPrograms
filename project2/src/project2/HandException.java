package project2;

/**
* <p>Title: Hand Exception Class</p>
*
* <p>Description: Creates and Exception when a hand is full or the player tries to
* remove a card from an already empty spot</p>
*/
@SuppressWarnings("serial")
public class HandException extends RuntimeException
{
	/**
	 * Hand Exception
	 * creates a HandException object
	 */
	public HandException()
	{
		super("Your hand is full and/or you attempted to remove a card that does not exist");
	}
	
	/**
	 * Hand Exception
	 * creates a HandException object using a pre-written error message
	 * @param message -error message to be used
	 */
	public HandException(String message)
	{
		super(message);
	}
}
