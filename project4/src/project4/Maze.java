package project4;

/**
 * Maze Class
 * @author Carole-Anne
 * Variables and methods for a Maze object
 */
public class Maze 
{	
	//variables
	private int height = 0;
	private int width = 0;
	private String[] items = null;
	private Square[][] square;
	private Point start;
	private Point finish;
	
	/**
	 * Maze Constructor
	 * Creates a Maze object and instantiates variables
	 * @param heightA - number of rows of the maze
	 * @param widthA - number of columns of the maze
	 * @param itemsA - String array with characters of the maze
	 */
	public Maze(int heightA,int widthA, String[]itemsA)
	{		
		height = heightA;
		width = widthA;
		items = itemsA;
		square = this.list();
		
	}
	
	/**
	 * List
	 * Private method that turns the Scanner's string readings
	 * from the constructor into a double array of characters
	 * @returns char[][] a char double array that create the maze
	 */
	private Square[][] list()
	{
		Square[][] list = new Square[height][width];
		for (int y = 0; y < height; y++)
		{
			for(int p = 0; p < width; p++)
			{
				list[y][p] = Square.fromChar(items[y].charAt(p));
				if (list[y][p] == Square.finish)
				{
					finish = new Point(y,p);
				}
				else if (list[y][p] == Square.start)
				{
					start = new Point(y,p);
				}
				
			}
			
		}
		return list;
	}
	
	/**
	 * Square
	 * Locates a square at a specified position
	 * @param x - row value
	 * @param y -column value
	 * @return a Square
	 */
	public Square get(int x, int y)
	{
		//returns a wall square if the position is out of bounds
		//since walls & out of bound points are both unsearchable
		if(x < 0 || x >= height)
			return Square.walls;
		if(y < 0 || y >= width)
			return Square.walls;
		return square[x][y];
		
	}
	
	/**
	 * Square
	 * Locates a square at a specified position
	 * @param x - row value
	 * @param y -column value
	 * @return a Square
	 */
	public Square get(Point pt)
	{
		return square[pt.getX()][pt.getY()];
	}
	
	/**
	 * GetHeight
	 * Returns the height of a maze
	 * @return int height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * GetWidth
	 * Returns the width of a maze
	 * @return int width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * get Finish
	 * Returns the finish point coordinate
	 * @return Point - finish
	 */
	public Point getFinish()
	{
		return finish;
	}
	
	/**
	 * get start
	 * Returns the starting point coordinate
	 * @return Point - start
	 */
	public Point getStart()
	{
		return start;
	}
	
	/**
	 * ToString
	 * Returns the state of a Maze object
	 * @return String - message containing rows, columns, and the maze itself
	 */
	public String toString()
	{
		String message = new String();
		message += height;
		message += " " + width +"\n";
		for (int x = 0; x < height; x++)
		{
			for (int i = 0; i < width; i++)
			{
				message += square[x][i];
			}
			message += "\n";
		}
		return message;
	}
}
