package project2;

/**
* <p>Title: Deck Exception Class</p>
*
* <p>Description: Creates an exception if a deck is empty</p>
* 
* @author Carolanne Franco
*/

@SuppressWarnings("serial")
public class DeckException extends RuntimeException
{
	/**
	 * Deck Exception
	 * creates a DeckException object
	 */
	public DeckException()
	{
		super("The Deck is Empty");
	}
	
	/**
	 * Deck Exception
	 * creates a DeckException object using a pre-written error message
	 * @param message -error message to be used
	 */
	public DeckException(String message)
	{
		super(message);
	}
}
