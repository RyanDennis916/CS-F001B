import java.util.Scanner;

/**
 * 
 * @author Ryan Dennis
 * @author Atharv Dua
 */
public class Foothill {
    public static void main(String[] args) {
//        Deck deck = new Deck(2);
//
//        for (int i=deck.getNumCards(); i>0; i--) {
//            System.out.print(deck.dealCard() + " /  ");
//            System.out.println();
//        }
//        
//        deck.init(2);
//        deck.shuffle();
//
//        for (int j=deck.getNumCards(); j>0; j--) {
//            System.out.print(deck.dealCard() + " /  ");
//            System.out.println();
//        }
    	
    	Scanner scanner = new Scanner(System.in);
    	int numPlayers = 0;
    	
    	while (numPlayers <= 0 || numPlayers > 9) {
    		System.out.println("How many hands? (1 - 10, please): ");
    		if (scanner.hasNextInt()) numPlayers = scanner.nextInt();
    	}
    	
    	scanner.close();
    	
    	Deck deck = new Deck(numPlayers);
    	    	
    	Hand[] hands = new Hand[numPlayers];
    	int numCards = Hand.MAX_CARDS/numPlayers;
    	System.out.println("Here are our hands, from unshuffled deck: ");
    	printHands(numPlayers, numCards, deck, hands);
    	System.out.println();
    	
    	deck.shuffle();
    	System.out.println("Here are our hands, from shuffled deck: ");
    	printHands(numPlayers, numCards, deck, hands);
    	System.out.println();
    }
    
    /**
     * deals cards from deck into new hands and prints each hand
	 *
	 * @param numPlayers number of hands to create
	 * @param numCards one less than the intended number of cards per hand
	 * @param deck deck supplying the cards
	 * @param hands array that receives the created hands (needs space for numPlayers hands)
     */
    public static void printHands(int numPlayers, int numCards, Deck deck, Hand[] hands) {
    	for (int i= 0; i< numPlayers; i++) {
    		Hand tempHand = new Hand();
    		while (tempHand.getNumCards() <= numCards) {
    			tempHand.takeCard(deck.dealCard());
    		}
    		hands[i] = tempHand;
    	   	System.out.println(hands[i].toString());
    	}

    }
}
/**
 * Demonstrates card creation and modification
 * Examples below have both valid and invalid card values to show
 * card validation and display behaviour
 *
 * @author Ryan Dennis
 * @author Atharv Dua
 */
class FoothillPhase1 {
    public static void main(String[] args) {
        Card first = new Card();
        Card second = new Card('?', Card.Suit.hearts);
        Card third = new Card('J', Card.Suit.clubs);
        Card copy = new Card(third);

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);

        third.set('?', Card.Suit.clubs);
        System.out.println(third);

        second.set('Q', Card.Suit.spades);
        System.out.println(second);
        System.out.println(copy);
    }
}

class Card implements CardInterface {
    

    private char rank;
    private Suit suit;
    private boolean errorFlag;

    public Card() {
        set(DEFAULT_RANK, DEFAULT_SUIT);
    }

    public Card(char rank, Suit suit) {
        set(rank, suit);
    }

    public Card(Card card) {
        set(card.getRank(), card.getSuit());
    }

    public Card(char rank) {
        set(rank, Card.DEFAULT_SUIT);
    }

    public Card(Suit suit) {
        set(Card.DEFAULT_RANK, suit);
    }

    public boolean set(char rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        errorFlag = !isValid(rank, suit);
        return !errorFlag;
    }

    public char getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public boolean equals(Card card) {
        return card != null
                && rank == card.rank
                && suit == card.suit
                && errorFlag == card.errorFlag;
    }

    public String toString() {
        if (errorFlag) {
            return "** illegal **";
        }
        return rank + " of " + suit;
    }

    private static boolean isValid(char rank, Suit suit) {
        for (char allowedRank : CardInterface.RANKS) {
            if (rank == allowedRank) {
                return true;
            }
        }
        return false;
    }
}

class Hand {
    public static final int MAX_CARDS = 50;

    private Card[] myCards;
    private int numCards;

    public Hand() {
        myCards = new Card[MAX_CARDS];
        resetHand();
    }

    public void resetHand() {
        for (int i = 0; i < numCards; i++) {
            myCards[i] = null;
        }
        numCards = 0;
    }

    public boolean takeCard(Card card) {
        if (numCards == MAX_CARDS) {
            return false;
        }
        if (card != null && !card.isErrorFlag()) {
            myCards[numCards] = new Card(card);
            numCards++;
        }
        return true;
    }

    public Card playCard() {
        if (numCards == 0) {
            return new Card('?', Card.Suit.spades);
        }
        numCards--;
        Card played = myCards[numCards];
        myCards[numCards] = null;
        return played;
    }

    public String toString() {
        if (numCards == 0) {
            return "Hand = ( )";
        }
        StringBuilder text = new StringBuilder("Hand = ( ");
        for (int i = 0; i < numCards; i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(myCards[i]);
        }
        return text.append(" )").toString();
    }

    public int getNumCards() {
        return numCards;
    }

    public Card inspectCard(int k) {
        if (k < 0 || k >= numCards) {
            return new Card('?', Card.Suit.spades);
        }
        return new Card(myCards[k]);
    }
}

/**
 * One object of this class represents a deck (which can have 1 or more packs of playing cards)
 * One can initialize to prepare a complete deck, or shuffle to change the order
 * or deal to transfer cards to the hands or inspect, to examine
 * remaining cards to be examined without dealing them
 * @author Atharv Dua
 * @author Ryan Dennis
 */
class Deck {
    private static final int MAX_PACKS = 6;
    private static final int NUM_CARDS_PER_PACK = Card.NUM_RANKS * Card.NUM_SUITS;
    private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;

    private static Card[] packTemplate;

    private Card[] cards;
    private int topCard;

    
    public Deck() {
        this(1);
    }

    public Deck(int numPacks) {
        allocatePackTemplate();
        cards = new Card[MAX_CARDS_PER_DECK];
        if (!init(numPacks)) {
            init();
        }
    }

    private static void allocatePackTemplate() {
        if (packTemplate != null) {
            return;
        }
        packTemplate = new Card[NUM_CARDS_PER_PACK];
        int index = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (char rank : Card.RANKS) {
                packTemplate[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    public void init() {
        init(1);
    }

    public boolean init(int numPacks) {
        if (numPacks < 1 || numPacks > MAX_PACKS) {
            return false;
        }
        topCard = 0;
        for (int pack = 0; pack < numPacks; pack++) {
            for (int i = 0; i < NUM_CARDS_PER_PACK; i++) {
                cards[topCard] = new Card(packTemplate[i]);
                topCard++;
            }
        }
        for (int i = topCard; i < MAX_CARDS_PER_DECK; i++) {
            cards[i] = null;
        }
        return true;
    }
    
    /**
     * randomizes the order of the cards that are still in the deck
     */
    public void shuffle() {
        for (int i = topCard - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    
    /*
     * 
     */

    public Card dealCard() {
        if (topCard == 0) {
            return new Card('?', Card.Suit.spades);
        }
        topCard--;
        Card dealt = cards[topCard];
        cards[topCard] = null;
        return dealt;
    }

    public int getNumCards() {
        return topCard;
    }
    
    /*
     * returns a copy of a card but without removing it from deck
	 *
	 * @param k position of card to inspect
	 * @return copy of the selected card (or invalid card if selection is out of bounds)
     */

    public Card inspectCard(int k) {
        if (k < 0 || k >= topCard) {
            return new Card('?', Card.Suit.spades);
        }
        return new Card(cards[k]);
    }
}

 /*---------------------------Phase 1 output
A of spades
** illegal **
J of clubs
** illegal **
Q of spades
J of clubs

------------------------------Phase 2 output
Hand full
After deal
Hand = ( 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs )
Testing inspectCard()
9 of hearts
** illegal **
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
playing 9 of hearts
playing T of clubs
playing 3 of clubs
After all cards
Hand = ( )

------------------------ DECK CLASS OUTPUT (PHASE 1 ASSIGNMENT 2)

X of spades /  
A of spades /  
K of spades /  
Q of spades /  
J of spades /  
T of spades /  
9 of spades /  
8 of spades /  
7 of spades /  
6 of spades /  
5 of spades /  
4 of spades /  
3 of spades /  
2 of spades /  
X of hearts /  
A of hearts /  
K of hearts /  
Q of hearts /  
J of hearts /  
T of hearts /  
9 of hearts /  
8 of hearts /  
7 of hearts /  
6 of hearts /  
5 of hearts /  
4 of hearts /  
3 of hearts /  
2 of hearts /  
X of diamonds /  
A of diamonds /  
K of diamonds /  
Q of diamonds /  
J of diamonds /  
T of diamonds /  
9 of diamonds /  
8 of diamonds /  
7 of diamonds /  
6 of diamonds /  
5 of diamonds /  
4 of diamonds /  
3 of diamonds /  
2 of diamonds /  
X of clubs /  
A of clubs /  
K of clubs /  
Q of clubs /  
J of clubs /  
T of clubs /  
9 of clubs /  
8 of clubs /  
7 of clubs /  
6 of clubs /  
5 of clubs /  
4 of clubs /  
3 of clubs /  
2 of clubs /  
X of spades /  
A of spades /  
K of spades /  
Q of spades /  
J of spades /  
T of spades /  
9 of spades /  
8 of spades /  
7 of spades /  
6 of spades /  
5 of spades /  
4 of spades /  
3 of spades /  
2 of spades /  
X of hearts /  
A of hearts /  
K of hearts /  
Q of hearts /  
J of hearts /  
T of hearts /  
9 of hearts /  
8 of hearts /  
7 of hearts /  
6 of hearts /  
5 of hearts /  
4 of hearts /  
3 of hearts /  
2 of hearts /  
X of diamonds /  
A of diamonds /  
K of diamonds /  
Q of diamonds /  
J of diamonds /  
T of diamonds /  
9 of diamonds /  
8 of diamonds /  
7 of diamonds /  
6 of diamonds /  
5 of diamonds /  
4 of diamonds /  
3 of diamonds /  
2 of diamonds /  
X of clubs /  
A of clubs /  
K of clubs /  
Q of clubs /  
J of clubs /  
T of clubs /  
9 of clubs /  
8 of clubs /  
7 of clubs /  
6 of clubs /  
5 of clubs /  
4 of clubs /  
3 of clubs /  
2 of clubs /  
2 of hearts /  
T of spades /  
7 of clubs /  
K of hearts /  
T of hearts /  
2 of diamonds /  
5 of clubs /  
8 of clubs /  
6 of spades /  
T of hearts /  
9 of diamonds /  
K of hearts /  
7 of clubs /  
4 of spades /  
A of spades /  
X of hearts /  
4 of diamonds /  
A of clubs /  
X of spades /  
T of clubs /  
A of diamonds /  
Q of diamonds /  
K of spades /  
T of diamonds /  
Q of spades /  
3 of hearts /  
X of diamonds /  
J of clubs /  
J of clubs /  
J of diamonds /  
7 of diamonds /  
X of hearts /  
7 of hearts /  
7 of hearts /  
2 of clubs /  
6 of diamonds /  
4 of spades /  
2 of spades /  
T of clubs /  
K of spades /  
Q of diamonds /  
8 of diamonds /  
A of diamonds /  
X of clubs /  
5 of hearts /  
4 of hearts /  
4 of clubs /  
3 of hearts /  
9 of spades /  
5 of diamonds /  
Q of spades /  
9 of clubs /  
Q of clubs /  
X of diamonds /  
A of spades /  
8 of diamonds /  
8 of spades /  
4 of clubs /  
K of clubs /  
5 of hearts /  
4 of hearts /  
8 of spades /  
J of hearts /  
9 of clubs /  
9 of spades /  
Q of clubs /  
2 of diamonds /  
3 of diamonds /  
X of spades /  
6 of diamonds /  
6 of clubs /  
J of spades /  
6 of clubs /  
3 of diamonds /  
K of diamonds /  
2 of spades /  
4 of diamonds /  
3 of spades /  
J of hearts /  
A of clubs /  
2 of clubs /  
7 of spades /  
T of spades /  
X of clubs /  
6 of hearts /  
9 of hearts /  
5 of clubs /  
K of diamonds /  
T of diamonds /  
A of hearts /  
Q of hearts /  
6 of spades /  
2 of hearts /  
3 of clubs /  
Q of hearts /  
5 of spades /  
6 of hearts /  
5 of spades /  
9 of diamonds /  
K of clubs /  
5 of diamonds /  
8 of clubs /  
8 of hearts /  
8 of hearts /  
J of diamonds /  
J of spades /  
9 of hearts /  
7 of diamonds /  
A of hearts /  
3 of clubs /  
7 of spades /  
3 of spades /  

---------------------------------OUTPUT FOR PHASE 2 ASSIGNMENT 2
How many hands? (1 - 10, please): 
5
Here are our hands, from unshuffled deck: 
Hand = ( X of spades, A of spades, K of spades, Q of spades, J of spades, T of spades, 9 of spades, 8 of spades, 7 of spades, 6 of spades, 5 of spades )
Hand = ( 4 of spades, 3 of spades, 2 of spades, X of hearts, A of hearts, K of hearts, Q of hearts, J of hearts, T of hearts, 9 of hearts, 8 of hearts )
Hand = ( 7 of hearts, 6 of hearts, 5 of hearts, 4 of hearts, 3 of hearts, 2 of hearts, X of diamonds, A of diamonds, K of diamonds, Q of diamonds, J of diamonds )
Hand = ( T of diamonds, 9 of diamonds, 8 of diamonds, 7 of diamonds, 6 of diamonds, 5 of diamonds, 4 of diamonds, 3 of diamonds, 2 of diamonds, X of clubs, A of clubs )
Hand = ( K of clubs, Q of clubs, J of clubs, T of clubs, 9 of clubs, 8 of clubs, 7 of clubs, 6 of clubs, 5 of clubs, 4 of clubs, 3 of clubs )

Here are our hands, from shuffled deck: 
Hand = ( T of diamonds, 9 of spades, Q of hearts, Q of hearts, 2 of clubs, 6 of clubs, 7 of spades, 6 of diamonds, K of diamonds, 2 of clubs, T of spades )
Hand = ( 3 of clubs, K of spades, T of clubs, K of spades, 4 of hearts, 4 of hearts, 8 of spades, A of hearts, J of hearts, J of clubs, Q of hearts )
Hand = ( 3 of diamonds, K of diamonds, 4 of clubs, 4 of spades, Q of hearts, 2 of diamonds, 9 of clubs, A of spades, T of spades, Q of clubs, A of diamonds )
Hand = ( T of diamonds, 4 of clubs, 3 of diamonds, 2 of spades, J of clubs, 5 of clubs, 3 of spades, Q of clubs, 6 of diamonds, 8 of diamonds, 4 of spades )
Hand = ( J of diamonds, 3 of clubs, 9 of diamonds, 3 of spades, 4 of spades, A of diamonds, 8 of clubs, 2 of hearts, 9 of clubs, 2 of clubs, 7 of diamonds )


*/
