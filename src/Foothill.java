public class Foothill {
    public static void main(String[] args) {
        Deck deck = new Deck(2);

        for (int i=deck.getNumCards(); i>0; i--) {
            System.out.print(deck.dealCard() + " /  ");
            System.out.println();
        }
        
        deck.init(2);
        deck.shuffle();

        for (int j=deck.getNumCards(); j>0; j--) {
            System.out.print(deck.dealCard() + " /  ");
            System.out.println();
        }
       
    }
}

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

    public void shuffle() {
        for (int i = topCard - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

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

    public Card inspectCard(int k) {
        if (k < 0 || k >= topCard) {
            return new Card('?', Card.Suit.spades);
        }
        return new Card(cards[k]);
    }
}

 /*---Phase 1--
A of spades
** illegal **
J of clubs
** illegal **
Q of spades
J of clubs

---Phase 2--
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
*/
