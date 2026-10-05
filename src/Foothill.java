public class Foothill {
    public static void main(String[] args) {
        Card[] deal = {
            new Card('3', Card.Suit.clubs),
            new Card('T', Card.Suit.clubs),
            new Card('9', Card.Suit.hearts)
        };
        Hand hand = new Hand();
        int next = 0;

        while (hand.takeCard(deal[next])) {
            next = (next + 1) % deal.length;
        }

        System.out.println("Hand full");
        System.out.println("After deal");
        System.out.println(hand);
        System.out.println("Testing inspectCard()");
        System.out.println(hand.inspectCard(2));
        System.out.println(hand.inspectCard(-1));

        while (hand.getNumCards() > 0) {
            System.out.println("Playing " + hand.playCard());
        }

        System.out.println("After playing all cards");
        System.out.println(hand);
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
    private static final char DEFAULT_VALUE = 'A';
    private static final Suit DEFAULT_SUIT = Suit.spades;

    private char value;
    private Suit suit;
    private boolean errorFlag;

    public Card() {
        set(DEFAULT_VALUE, DEFAULT_SUIT);
    }

    public Card(char value, Suit suit) {
        set(value, suit);
    }

    public Card(Card card) {
        set(card.getValue(), card.getSuit());
    }

    public Card(char value) {
        set(value, DEFAULT_SUIT);
    }

    public boolean set(char value, Suit suit) {
        this.value = value;
        this.suit = suit;
        errorFlag = !isValid(value, suit);
        return !errorFlag;
    }

    public char getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public boolean equals(Card card) {
        return card != null
                && value == card.value
                && suit == card.suit
                && errorFlag == card.errorFlag;
    }

    public String toString() {
        if (errorFlag) {
            return "** illegal **";
        }
        return value + " of " + suit;
    }

    private static boolean isValid(char value, Suit suit) {
        for (char allowedValue : CardInterface.CARD_VALUES) {
            if (value == allowedValue) {
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

/*Phase 1 Run (FoothillPhase1)
A of spades
** illegal **
J of clubs
** illegal **
Q of spades
J of clubs

Phase 2 Run (Foothill)
Hand full
After deal
Hand = ( 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs )
Testing inspectCard()
9 of hearts
** illegal **
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
After playing all cards
Hand = ( )
*/
