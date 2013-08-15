package project2;

/**
 * <p>Title: The GUI Class</p>
 *
 * <p>Description: The GUI is used to display cards, decks, and discard piles</p>
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;

public class GUI implements ActionListener
{
      final int MAX_CARDS = 5; // max number of cards
      private int numCards; // number of cards
      private int num; // number of cards currently displayed

      private JPanel mainPanel; // main window
      private JPanel playerPanel; // player panel
      private JPanel deckPanel; // card deck panel
      private JPanel discardPilePanel; //discard pile panel

      private ImageIcon images[]; // stores the .gif files
      private JLabel imageIconLabels[]; // displays the images and appropriate text

      private ImageIcon deckTopImage; // stores the .gif file
      private JLabel deckTopImageLabel; // displays the image and text
      private ImageIcon discardPileImage; // stores the .gif file

      private JLabel discardPileImageLabel; // displays the image and text

      private ImageIcon defaultImage;

      private boolean hasDeck;
      private boolean hasDiscardPile;

      /**
       * GUI constructor -- gets called when an object of the GUI class is
       * instantiated -- provides space for the number of cards specified,
       * the deck of cards, and the discard pile
       * @param num the number of cards to display
       * @param showDeck should be true if the deck should appear, false if the deck should not appear
       */
      public GUI(int num, boolean showDeck)
      {
            numCards = num;
            num = 0;

            hasDeck = true;
            hasDiscardPile = true;

            createPlayerPanel();
            if(showDeck)
                  createDeckPanel();
            createDiscardPilePanel();
            createMainPanel();
            mainPanel.add(deckPanel);
            mainPanel.add(discardPilePanel);
            finishSetup();
      }

      /**
       * private method -- creates the player panel that can have a
       * maximum of 5 cards - sets the cards to the default gray image and
       * labels each  card
       */
      private void createPlayerPanel()
      {
            images = new ImageIcon[numCards];
            imageIconLabels = new JLabel[numCards];
            playerPanel = new JPanel();
            playerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Card Game"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            getDefaultImage();

            for(int i = 0; i < numCards; i++)
            {
                  images[i] = defaultImage;
                  imageIconLabels[i] = new JLabel();
                  imageIconLabels[i].setVerticalTextPosition(3);
                  imageIconLabels[i].setHorizontalTextPosition(0);
                  imageIconLabels[i].setIcon(images[i]);
                  imageIconLabels[i].setText(String.valueOf(String.valueOf((new StringBuffer("Card ")).append(i + 1))));
                  playerPanel.add(imageIconLabels[i]);
            }
      }

      /**
       * private method -- creates the panel that displays the top card of the discard pile
       */
      private void createDiscardPilePanel()
      {
          discardPilePanel = new JPanel();
          discardPilePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("The Discard Pile"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
          discardPileImageLabel = new JLabel();
          discardPileImageLabel.setVerticalTextPosition(3);
          discardPileImageLabel.setHorizontalTextPosition(0);
          discardPileImageLabel.setIcon(defaultImage);
          discardPileImageLabel.setText("Top Card");
          discardPilePanel.add(discardPileImageLabel);
      }

      /**
       * private method -- creates the panel to display the top card of the deck
       * sets the card to the default gray and the label to Top Card
       */
      private void createDeckPanel()
      {
            deckPanel = new JPanel();
            deckPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("The Deck"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            deckTopImageLabel = new JLabel();
            deckTopImageLabel.setVerticalTextPosition(3);
            deckTopImageLabel.setHorizontalTextPosition(0);
            deckTopImageLabel.setIcon(defaultImage);
            deckTopImageLabel.setText("Top Card");
            deckPanel.add(deckTopImageLabel);
      }

      /**
       * private method -- creates the main window and adds the player panel to it
       */
      private void createMainPanel()
      {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, 0));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            mainPanel.add(playerPanel);
      }

      /**
       * private method -- completes the setup by packing the cardFrame and
       * making the window visible
       */
      private void finishSetup()
      {
            JFrame cardFrame = new JFrame("Card");
            cardFrame.setDefaultCloseOperation(3);
            cardFrame.setContentPane(mainPanel);
            cardFrame.pack();
            cardFrame.setVisible(true);
      }

      /**
       * private method -- loads and stores the default image
       */
      private void getDefaultImage()
      {
            defaultImage = new ImageIcon("images/b.gif");
      }

      /**
       * showHand method -- displays the specified number of card images in the GUI
       * @param cards a reference to an array of cards
       * @param numCardsInHand the number of cards in the array --
       * assumes numCardsInHand card objects have been instantiated
       */
      public void showHand(Card[] cards, int numCardsInHand)
      {
            if (cards.length > numCards || numCardsInHand > numCards)
                  System.out.println("There are too many cards to display");
            else
            {
                  for (int i=0; i<numCardsInHand; i++)
                  {
                        images[i] = new ImageIcon(String.valueOf(String.valueOf((new StringBuffer("images/")).
                             append(cards[i].getValue()).append(cards[i].getSuit().charAt(0)).append(".gif"))));
                        imageIconLabels[i].setIcon(images[i]);
                        num++;
                  }
            }
      }

      /**
        * showDeckCard method -- determines the value and suit of the
        * Card, theCard, and displays the appropriate image
        * @param theCard a reference to a Card object -- assumes the Card object has been instantiated
         */
       public void showDeckCard(Card theCard)
       {
             if (hasDeck)
             {
                   deckTopImage = new ImageIcon(String.valueOf(String.valueOf((new StringBuffer("images/")).
                         append(theCard.getValue()).append(theCard.getSuit().charAt(0)).append(".gif"))));
                   deckTopImageLabel.setIcon(deckTopImage);
             }
             else
             {
                   System.out.println("No deck appears in the GUI");
             }
       }

       /**
        * method showDiscardPileCard -- determines the value and suit of the Card, theCard,
        * and displays the appropriate image
        * @param theCard a reference to a Card object -- assumes the Card object has been instantiated
        */
       public void showDiscardPileCard(Card theCard)
       {
             if (hasDiscardPile)
             {
                   discardPileImage = new ImageIcon(String.valueOf(String.valueOf((new StringBuffer("images/")).
                         append(theCard.getValue()).append(theCard.getSuit().charAt(0)).append(".gif"))));
                   discardPileImageLabel.setIcon(discardPileImage);
             }
             else
             {
                   System.out.println("No deck appears in the GUI");
             }
       }

      /**
       * clearPlayerCards method -- clears the window of the player's cards
       */
      public void clearPlayerCards()
      {
            for (int i=0; i<numCards; i++)
                  imageIconLabels[i].setIcon(defaultImage);
            num = 0;
      }

      /**
       * clearDiscardPileCard method -- clears the window of the discard pile card
       */
      public void clearDiscardPileCard()
      {
            if (hasDiscardPile)
                  discardPileImageLabel.setIcon(defaultImage);
            else
                  System.out.println("No discard pile appears in the GUI");
      }

      /**
       * clearDeckCard method -- clears the window of the deck card
       */
      public void clearDeckCard()
      {
            if (hasDeck)
                  deckTopImageLabel.setIcon(defaultImage);
            else
                  System.out.println("No deck appears in the GUI");
      }

      public void actionPerformed(ActionEvent actionevent)
      {
      }
}
