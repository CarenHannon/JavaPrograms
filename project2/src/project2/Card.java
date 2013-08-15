package project2;

/**
 * <p>Title: The Card Class</p>
 *
 * <p>Description: Represents a single playing card</p>
 */
public class Card
{
    // instance variables
    private int value;
    private String suit;

    /**
     * Card constructor -- gets called when an object of the Card class
     * is instantiated -- based upon the number received it determines the
     * value and suit of the card
     * @param num a number that gets converted to a value between 1 and 13
     */
    public Card(int num)
    {
        value = num % 13;
        if (value == 0)
            value = 13;
        int s = num / 13;
        if (s == 0)
            suit = "clubs";
        else if (s == 1)
            suit = "diamonds";
        else if (s == 2)
            suit = "hearts";
        else if (s == 3)
            suit = "spades";
        else
            suit = "ERROR";
    }

    /**
     * getValue method -- returns what's stored in the instance variable value
     * @return the state of the instance variable value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * getSuit method -- returns what's stored in the instance variable suit
     * @return a reference to a String that contains the state of the instance variable suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * toString method -- this method returns the state of the card object
     * @return a reference to a string object that contains the values stored
     * in the instance variables
     */
    public String toString()
    {
        return value + " of " + suit;
    }
}