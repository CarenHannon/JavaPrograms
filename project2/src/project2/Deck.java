package project2;

/**
 * <p>Title: The Deck class</p>
 *
 * <p>Description: This class represents a Deck of 52 playing cards.  It
 * gets created in order and then must be shuffled to randomize the order of
 * the cards.  Cards are dealt from the top of the deck.</p>
 * 
 * @author Carolanne Franco
 */
public class Deck
{

    //instance variables
    private Card[] cards;
    private int numCards;

    /**
     * default constructor which creates the deck of 52 cards in order
     */
    public Deck()
    {
        numCards = 52;
        cards = new Card[numCards];
        for (int i = 0; i < cards.length; i++)
            cards[i] = new Card(i);
    }

    /**
     * Deck - parameterized constructor for an empty Discard Pile
     * @param number - Amount of card slots
     */
    public Deck(int number)
    {
    	numCards = 0;
    	cards = new Card[number];
    }
    
    /**
     * shuffleDeck method -- places the cards in the deck in a random order
     */
    public void shuffleDeck()
    {
        Card temp;
        int ran1, ran2;
        for (int i = 0; i < 100; i++)
        {
            ran1 = (int)(Math.random()*52);
            ran2 = (int)(Math.random()*52);
            temp = cards[ran1];
            cards[ran1] = cards[ran2];
            cards[ran2] = temp;
        }
        numCards = 52;
    }
    
    /**
     * Shuffle New Deck -- alteration of the ShuffleDeck method so that it can adapt
     * to a partially empty deck. Used on a deck that was subject to the Cut method.
     */
    public void shuffleNewDeck()
    {
        Card temp;
        int ran1, ran2;
        for (int i=0; i < 100; i++)
        {
            ran1 = (int)(Math.random() * numCards);
            ran2 = (int)(Math.random() * numCards);
            temp = cards[ran1];
            cards[ran1] = cards[ran2];
            cards[ran2] = temp;
        }
        numCards = 37;
    }
    
    /**
     * dealCard -- deals the top card from the deck and decreases the number
     * of cards in the deck by 1
     * @return a reference to the Card being dealt
     */
    public Card dealCard()
    {
    	if (numCards == 0)
    	{
    		throw new DeckException("The Deck is empty");
    	}
        numCards--;
        return cards[numCards];
    }
    
    /**
     * Add Card -- adds a card to the first available null spot in a deck
     * @param aCard - card to be added
     */
    public void addCard(Card aCard)
    {
    	boolean end = false;
    	int count = 0;
    	while (end == false)
    	{
    		if (cards[count] == null)
    		{
    			cards[count] = aCard;
    			numCards++;
    			end = true;
    		}
			count++;
    	}
    }
    
    /**
     * Remove Card -- Alteration of the dealCard method so that it not only takes a card
     * but also turns the card's spot empty. It's only used on the discard pile deck.
     * @return Card - card to be removed
     */
    public Card removeCard()
    {
    	Card dealt = new Card(0);
		dealt = cards[numCards - 1];
		cards[numCards - 1] = null;
		numCards--;
    	return dealt;
    }
    
    /**
     * Returns the top card of any deck
     * @return card - last card of a deck
     */
    public Card getTopCard()
    {    	
    	return cards[numCards - 1];
    }
    
    /**
     * Cut -- Renews the playing deck by taking all but the top card of the discard pile.
     * @param stockPile - the full discard pile that is used to renew the deck
     */
    public void cut(Deck stockPile)
    {
    	//removes and saves the discard pile's top card
    	Card stockTop = stockPile.removeCard();
    	
    	//creates a blank array and resets the number of Cards in the deck
    	Card[] newDeck = new Card[cards.length];
    	cards = newDeck;
    	this.numCards = 0;
    	
    	//assigns all of the remaining card from the discard pile to this deck
    	//-15 accounts for the 7 cards in both player's hands at the time of a possible Cut
    	//and for the discard pile's saved top card
    	for (int x = 0; x < cards.length - 15; x++)
    	{
    		this.addCard(stockPile.removeCard());
    	}
    	
    	//shuffles the new deck
    	this.shuffleNewDeck();
    	
    	//reassigns the saved top card to the discard pile
    	stockPile.addCard(stockTop);
    }
    

}