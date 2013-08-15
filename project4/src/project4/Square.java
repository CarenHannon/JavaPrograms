package project4;

/**
 * Square Enumerated Type
 * @author Carole-Anne
 * Creates values and methods for Square variables
 */
public enum Square
{
	walls ('#'),
	openspaces ('.'),
	start ('o'),
	finish ('*');

	private final char character;
	
	/**
	 * Square
	 * Private Constructor that adjusts the local variables to the
	 * variable's value 
	 * @param character - destinguishing value of the Square variable
	 */
	private Square(char character)
	{
		this.character = character;
	}

	//
	/**
	 * ToString
	 * returns a string containing only the character corresponding to this Square
	 * @return String. A single character in string form
	 */
	public String toString()
	{
		return "" + this.character;
	}
	
	//returns the Square associated with the character that is passed to the method
	/**
	 * fromChar
	 * Static method that turns a character into its respective square
	 * or null if it does not have a respective square
	 * @returns Square. The respective square
	 */
	public static Square fromChar(char passed)
	{
		if (passed == '#')
			return walls;
		if (passed == '.')
			return openspaces;
		if (passed == 'o')
			return start;
		if (passed == '*')
			return finish;
		else
			return null;
	}

}
