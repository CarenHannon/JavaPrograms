package project2;

/**
 * <p>Title: Rummy Hand Class</p>
 *
 * <p>Description: Subclass of the hand class that creates a rummy-specific deck and allows to check
 * if a player has won by getting both a 3 and 4 of a kind</p>
 */
public class RummyHand extends Hand
{
	/**
	 * RummyHand -- default constructor that makes a hand object with 8 card slots
	 */
	public RummyHand()
	{
		super(8);
	}
	
	/**
	 * Check Win -- Finds out if a player has a winning hand
	 * @return boolean - true if the player won, false if not
	 */
	public boolean checkWin()
	{
		if (this.checkSame(3) == true && this.checkSame(4) == true)
			return true;
		else
			return false;
	}
	
	/**
	 * check Same -- a method that finds out if there there are a certain amount of the same card values
	 * ex( 3 of a kind, 4 of a kind)
	 * @param cap - the exact amount of same-value cards wanted
	 * @return boolean - true if there is a Number(cap)- of a kind
	 */
	private boolean checkSame(int cap)
	{
		//local variables
		int tempValue = 0;
		Card[]array = this.getCardArray();
		int[] check = new int[8];
		int checked = 0;
		boolean checks = true;
		int same = 0;
		
		//loop that checks the entire hand
		for (int i = 0; i < 8; i++)
		{
			//skips a slot if it is empty
			//continues if it holds a card
			if(array[i]!= null)
			{
				//finds out the value of a card in the hand
				tempValue = array[i].getValue();
				
				//guarantees that the value hasn't already been checked for multiples
				//in a previous loop
				for (int v = 0; v < checked; v++)
				{
					if(tempValue == check[v])
					{
						checks = false;
					}
				}
				
				//Checks for multiples
				if (checks == true)
				{
					same = 0;
					for(int y = i+1; y < 8; y++)
					{
						if(array[y]!=null)
						{
							if(tempValue == array[y].getValue())
							{
								same++;
							}
						}
					}
					check[checked]=tempValue;
					checked++;
				}
				
				//If there are the required amount of total multiples, it returns true 
				checks = true;
				if (same == cap - 1)
					return true;
			}
		}
		//ends if no specific multiples were found
		return false;
	}
	
	/**
	 * To String method -- returns the state of a RummyHand object
	 */
	public String toString()
	  {
	    return  super.toString();
	}
}
