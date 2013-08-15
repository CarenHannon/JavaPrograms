package project4;
import stacks.*;

/**
 * MazeSolver
 * Solves a maze from start to finish
 * @author Carole-Anne
 *
 */
public class MazeSolver 
{
	Maze aMaze;
	ArrayStack<Point> aStack;
	boolean[][] track;
	Point aPoint;
	Point finish;
	
	/**
	 * MazeSolver constructor
	 * Instantiates a maze object and the mazesolver variables
	 * @param heightA - height of the maze
	 * @param widthA - width of the maze
	 * @param itemsA - string of characters representing the maze
	 */
	public MazeSolver(int heightA,int widthA, String[]itemsA)
	{
		aMaze = new Maze(heightA,widthA, itemsA);
		finish = aMaze.getFinish();
		aStack = new ArrayStack<Point>();
		aStack.push(aMaze.getStart());
		track = new boolean[aMaze.getHeight()][aMaze.getWidth()];
		for (int a = 0; a < track.length; a++)
		{
			for(int b = 0; b < track[0].length; b++)
			{
				track[a][b] = false;
			}
		}
	}
	
	/**
	 * SolveMaze
	 * Finds out if the maze can be solved
	 * @return boolean  - true if solvable, false if not
	 */
	public boolean SolveMaze()
	{
		System.out.println("Maze : \n" + aMaze.toString());
		//checks if the stack of points is empty
		if(aStack.isEmpty())
			return false;
		int r;
		int c;
		
		while(!aStack.isEmpty())
		{
			aPoint = aStack.pop();
			r = aPoint.getX();
			c = aPoint.getY();
		
			if(track[r][c] == false)
			{
				//checks if the point being traveled is the finish
				if(aPoint.equals(finish))
				{
					track[r][c] = true;
					System.out.println(this.showPath());
					return true;
					
				}
				else
				{
					//adds adjacent locations to the stack
					if(aMaze.get(r+1, c)!= Square.walls)
						aStack.push(new Point((r+1),c));
					if(aMaze.get(r-1, c)!= Square.walls)
						aStack.push(new Point((r-1),c));
					if(aMaze.get(r, c+1)!= Square.walls)
						aStack.push(new Point((r),c+1));
					if(aMaze.get(r, c-1)!= Square.walls)
						aStack.push(new Point((r),c-1));
					track[r][c] = true;
				}
			}
	
			System.out.println(this.showPath());
		}
		return false;
	}
	
	/**
	 * ShowPath
	 * Shows the path traveled so far
	 * @return String representation of path traveled so far
	 */
	private String showPath()
	{
		String path = new String();
		for (int y = 0; y < track.length; y++)
		{
			for(int p = 0; p < track[0].length; p++)
			{
				if(track[y][p] == true)
					path+= ">";
				else
					path+= ".";
			}
			path+="\n";
		}
		return path;
	}
}
