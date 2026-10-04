/**
 * @author Baba Kofi Weusijana
 */
public interface CardInterface
{
    public enum Suit
    {
        clubs, diamonds, hearts, spades
    }

    public static final char[] CARD_VALUES =
    { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };
    public static final int NUM_VALUES = CARD_VALUES.length;
    public static final int NUM_SUITS = Suit.values().length;;

    public boolean equals(Card card);

    public Suit getSuit();

    public char getValue();

    public boolean isErrorFlag();

    public boolean set(char value, Suit suit);

    public String toString();
}
