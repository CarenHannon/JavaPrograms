package project4;

/**
 * Point class
 * Creates variables and methods for a point object
 * @author Carole-Anne
 */
public class Point 
{
	private int x;
	private int y;
	
	/**
	 * Point constructor
	 * Instantiates coordinates of a point object
	 * @param a - x coordinate
	 * @param b - y coordinate
	 */
	public Point(int a, int b)
	{
		x = a;
		y = b;
	}
	
	/**
	 * Equals method
	 * Checks if two points are equal
	 * @param otherPoint - point to be compared
	 * @return true if equal, false if not
	 */
	public boolean equals(Point otherPoint)
	{
		if ((otherPoint.x == this.x) && (otherPoint.y == this.y))
			return true;
		else
			return false;
	}
	
	/**
	 * get X
	 * Gets the x coordinate of a point
	 * @return int x.
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * get Y
	 * Gets the y coordinate of a point
	 * @return int y.
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * toString
	 * Returns the state of a point object
	 * @return String- a message with the state of the point
	 */
	public String toString()
	{
		return "Point: " + x +"," + y;
	}
}
