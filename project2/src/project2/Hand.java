package project2;
/**
 * <p>Title: Hand Class</p>
 *
 * <p>Description: Creates a playing hand for a card game</p>
 * 
 * @author Carolanne Franco
 */
public class Hand {
	
	//variables
	private int maxCards;
	private int numCards;
	private Card[] cards;
	
	/**
	 * Hand - default constructor that creates an empty hand
	 * @param maximum - amount of slots available for the hand
	 */
	public Hand(int maximum)
	{
		maxCards = maximum;
		numCards = 0;
		cards = new Card[maxCards];
	}
	
	/**
	 * Insert Card -- adds a card to the first open spot of a hand
	 * @param aCard - card to be added
	 */
	public void insertCard(Card aCard)
	{
		boolean search = true;
		int i = 0;
		if (numCards != maxCards)
		{
			while (search == true)
			{
				//searches for the first empty slot
				if (cards[i] == null)
				{
					cards[i] = aCard;
					numCards++;
					search = false;
				}
				i++;
			}
		}
		
		//Throws a Hand exception if the hand is already full
		//This never occurs
		else
		{
			throw new HandException();
		}
	}
	
	/**
	 * Remove Card - removes a card from a hand and empties the slot
	 * @param cardPosition - position of the card in the hand's card array
	 * @return Card - card to be removed
	 */
	public Card RemoveCard(int cardPosition)
	{
		//Creates an exception if the user tries to empty an already empty card slot
		if (cards[cardPosition] == null)
		{
			throw new HandException();
		}
		Card temporary = new Card(0);
		if (cardPosition >= 0 & cardPosition < numCards)
		{
			temporary = cards[cardPosition];
			cards[cardPosition] = null;
			numCards--;
		}
		return temporary;	
	}
	
	/**
	 * Get Card Array -- allows access to the array of cards of a hand
	 * @return Card Array - A player's cards
	 */
	public Card[] getCardArray()
	{
		return cards;
	}
	
	/**
	 * Get Cards In Hand -- allows access to how many cards a player holds
	 * @return int - number of cards
	 */
	public int getCardsInHand()
	{
		return numCards;
	}
	
	/**
	 * Get Card Position -- Locates the position of an exact Card Object
	 * Exact meaning they have the same object address
	 * @param aCard - card to be found within the hand
	 * @return int - the position of the card in the hand's array or -1 if not found
	 */
	public int getCardPosition(Card aCard)
	{
		int position = -1;
		for (int i = 0; i < cards.length; i++)
		{
			if(aCard == cards[i])
				position = i;
		}
		return position;
	}
	
	/**
	 * Sort - Rearranges the cards so they are in increasing value order
	 * Also makes sure empty card spots are at the end of the card array
	 */
	public void sort()
    {
    	Card[] temp = new Card[cards.length];
    	int tempCards = 0;
    	for (int k = 1; k <= 13; k++)
    	{
    		for (int j = 0; j < cards.length; j++)
    		{
    			if (cards[j] == null)
    			{}   			
    			else if (cards[j].getValue() == k)
    			{
    				temp[tempCards] = cards[j];
    				tempCards++;
    			}
    		}
    	}
    	cards = temp;
    }
	
	/**
	 * toString -- message with the state of the hand object
	 * @return String - a string message
	 */
	public String toString()
	{
		String message = new String("");
		for (int i = 0; i < cards.length; i++)
		{
			if (cards[i] != null)
			message += (i + 1) + ". " + cards[i].toString() + "\n";
			if (cards[i] == null)
				message += (i + 1) + ". Null \n";
		}
			
		return message;
	}
	
	
}
