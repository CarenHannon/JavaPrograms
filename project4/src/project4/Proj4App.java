package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 * Project4App
 * @author Carole-Anne
 * Calls the Mazesolver class to find out if a maze has a solution
 */
public class Proj4App 
{
	
	public static void main(String[]args)
	{
		//variables
		MazeSolver newSolve;
		int height = 0;
		int width = 0;
		String[] items = null;
		boolean repeat = true;
		boolean read = true;
		String aFile = null;
		String message = new String();
		
		//lets program run as many times as the user wants
		while(repeat)
		{
			//asks for user input
			while(read)
			{
				aFile = JOptionPane.showInputDialog(
						message +
						"Enter a maze file name\nPress the Close button to quit");
				//terminates program if X button or Cancel were clicked
				if (aFile == null)	
				{
					read = false;
					repeat = false;
				}
				
				//reads from the Scanner class
				else
				{
					try
					{
						read = false;
						Scanner fileScan = new Scanner(new File(aFile));
						height = fileScan.nextInt();
						width = fileScan.nextInt();
						int i = 0;
						items = new String[height];
						while(fileScan.hasNext())
						{
							items[i] = fileScan.next();
							i++;
						}
						fileScan.close();
						message = "";
						
						
					}
					//forces user to retype the file name if input was invalid
					catch(FileNotFoundException ex)
					{
						message = "Invalid file name\n";
						read = true;
					}
				}
			}
			
			//runs the Mazesolver
			if(aFile != null)
				{
				newSolve = new MazeSolver(height,width, items);
				boolean results = newSolve.SolveMaze();	
				if(results)
					System.out.println("Solvable");
				else
					System.out.println("Unsolvable");
				read = true;
				}
		}
	}
}

