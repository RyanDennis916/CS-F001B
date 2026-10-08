
//import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.util.Scanner;
import java.util.logging.Logger;

//import javax.annotation.Generated;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

//import Card.Suit;

//@Generated(value = "org.junit-tools-1.0.6")
/**
 * Checks to make sure all the classes in the application work well together.
 */
public class IntegrationTests {

	private static final boolean DEBUG = true;
	private static final int MAX_HANDS = 10;
	private static final String LINE_SEPARATOR = java.lang.System.getProperty("line.separator");
//	@Generated(value = "org.junit-tools-1.0.6")
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(IntegrationTests.class.toString());
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final static PrintStream sysOutStream = System.out;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

	}

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (DEBUG) {
			System.setErr(sysOutStream);
			System.out.println("errContent:");
			System.out.println(errContent);
		}
	}

	/**
	 * Run all the code that would be in main methods for A1 and A2
	 *
	 * @throws Exception
	 */
	@MethodRef(name = "main", signature = "([QString;)V")
	@Test
	public void testMain() throws Exception {
		// Assignment #1 Phase 1: Card Class ============
		Card card1 = new Card(), card2 = new Card('Z', Card.Suit.spades), card3 = new Card('Y', Card.Suit.clubs);

		// show initial values
		System.out.println(card1 + LINE_SEPARATOR + card2 + LINE_SEPARATOR + card3 + LINE_SEPARATOR);

		// make some changes
		card2.set('Q', Card.Suit.spades); // turns card2 good
		card1.set('V', Card.Suit.diamonds); // turns card1 bad

		System.out.println(card1 + LINE_SEPARATOR + card2 + LINE_SEPARATOR + card3 + LINE_SEPARATOR);
		/*
		 * ---------------------- run Card class test ------------------ A of spades
		 ** illegal ** J of clubs
		 **
		 * illegal ** Q of spades J of clubs
		 * ---------------------------------------------------------------
		 */
		// Assignment #1 Phase 2: Hand Class ============
		Card card12_1 = new Card('3', Card.Suit.clubs), card2_2 = new Card('T', Card.Suit.clubs),
				card2_3 = new Card('9', Card.Suit.hearts);

		Hand hand = new Hand();

		boolean handNotFull = true;
		while (handNotFull) {
			if (!hand.takeCard(card12_1)) {
				System.out.println("Hand full");
				handNotFull = false;
			}
			if (!hand.takeCard(card2_2)) {
				System.out.println("Hand full");
				handNotFull = false;
			}
			if (!hand.takeCard(card2_3)) {
				System.out.println("Hand full");
				handNotFull = false;
			}
		}

		System.out.println("After deal" + LINE_SEPARATOR + hand.toString() + LINE_SEPARATOR);

		System.out.println("Testing inspectCard()");
		System.out.println(hand.inspectCard(5).toString());
		System.out.println(hand.inspectCard(200).toString());

		while (hand.getNumCards() > 0)
			System.out.println("Playing " + hand.playCard().toString());

		System.out.println(LINE_SEPARATOR + "After playing all cards" + LINE_SEPARATOR + hand.toString());
		// Assignment #2 Phase 1: Deck Class ============
		Deck deck1 = new Deck(2);

		// deal first deck (two packs), unshuffled.
		int tooManyIterations = 100000;
		int iterationCounter = 0;
		for (iterationCounter = 0; deck1.getNumCards() > 0
				&& iterationCounter < tooManyIterations; iterationCounter++) {
			System.out.print(deck1.dealCard() + " /  ");
		}
		if (iterationCounter == tooManyIterations) {
			Assert.fail("Students' getNumCards method never returns 0 even if dealCard() is called " + tooManyIterations
					+ " times.");
		}
		System.out.println(LINE_SEPARATOR);
		String expected = "A of spades" + LINE_SEPARATOR + "-Invalid Card-" + LINE_SEPARATOR + "-Invalid Card-"
				+ LINE_SEPARATOR + LINE_SEPARATOR + "-Invalid Card-" + LINE_SEPARATOR + "Q of spades" + LINE_SEPARATOR
				+ "-Invalid Card-" + LINE_SEPARATOR + LINE_SEPARATOR + "Hand full" + LINE_SEPARATOR + "After deal"
				+ LINE_SEPARATOR
				+ "Hand =  ( 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs )"
				+ LINE_SEPARATOR + LINE_SEPARATOR + "Testing inspectCard()" + LINE_SEPARATOR + "9 of hearts"
				+ LINE_SEPARATOR + "-Invalid Card-" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR
				+ "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs"
				+ LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR
				+ "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts"
				+ LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR
				+ "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs"
				+ LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR
				+ "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs"
				+ LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR
				+ "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts"
				+ LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR
				+ "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs"
				+ LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR
				+ "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs"
				+ LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR
				+ "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts"
				+ LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR
				+ "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR + "Playing 3 of clubs"
				+ LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs" + LINE_SEPARATOR
				+ "Playing 3 of clubs" + LINE_SEPARATOR + "Playing 9 of hearts" + LINE_SEPARATOR + "Playing T of clubs"
				+ LINE_SEPARATOR + "Playing 3 of clubs" + LINE_SEPARATOR + LINE_SEPARATOR + "After playing all cards"
				+ LINE_SEPARATOR + "Hand =  (  )" + LINE_SEPARATOR
				+ "X of spades /  A of spades /  K of spades /  Q of spades /  J of spades /  T of spades /  9 of spades /  8 of spades /  7 of spades /  6 of spades /  5 of spades /  4 of spades /  3 of spades /  2 of spades /  X of hearts /  A of hearts /  K of hearts /  Q of hearts /  J of hearts /  T of hearts /  9 of hearts /  8 of hearts /  7 of hearts /  6 of hearts /  5 of hearts /  4 of hearts /  3 of hearts /  2 of hearts /  X of diamonds /  A of diamonds /  K of diamonds /  Q of diamonds /  J of diamonds /  T of diamonds /  9 of diamonds /  8 of diamonds /  7 of diamonds /  6 of diamonds /  5 of diamonds /  4 of diamonds /  3 of diamonds /  2 of diamonds /  X of clubs /  A of clubs /  K of clubs /  Q of clubs /  J of clubs /  T of clubs /  9 of clubs /  8 of clubs /  7 of clubs /  6 of clubs /  5 of clubs /  4 of clubs /  3 of clubs /  2 of clubs /  X of spades /  A of spades /  K of spades /  Q of spades /  J of spades /  T of spades /  9 of spades /  8 of spades /  7 of spades /  6 of spades /  5 of spades /  4 of spades /  3 of spades /  2 of spades /  X of hearts /  A of hearts /  K of hearts /  Q of hearts /  J of hearts /  T of hearts /  9 of hearts /  8 of hearts /  7 of hearts /  6 of hearts /  5 of hearts /  4 of hearts /  3 of hearts /  2 of hearts /  X of diamonds /  A of diamonds /  K of diamonds /  Q of diamonds /  J of diamonds /  T of diamonds /  9 of diamonds /  8 of diamonds /  7 of diamonds /  6 of diamonds /  5 of diamonds /  4 of diamonds /  3 of diamonds /  2 of diamonds /  X of clubs /  A of clubs /  K of clubs /  Q of clubs /  J of clubs /  T of clubs /  9 of clubs /  8 of clubs /  7 of clubs /  6 of clubs /  5 of clubs /  4 of clubs /  3 of clubs /  2 of clubs /  "
				+ LINE_SEPARATOR + LINE_SEPARATOR;
		Assert.assertEquals("Your code's output differs from the expected ouput", expected, outContent.toString());
		System.setOut(sysOutStream);
		System.out.println("outContent:");
		System.out.println(outContent);
		// remaining tests are of shuffled content

		// restock the deck and remove some aces
		deck1.init(2);
		deck1.shuffle();

		while (deck1.getNumCards() > 0) {
			System.out.print(deck1.dealCard() + " / ");
		}
		System.out.println(LINE_SEPARATOR);

		// deal one pack in the second deck, first unshuffled ...
		Deck deck2 = new Deck();
		while (deck2.getNumCards() > 0) {
			System.out.print(deck2.dealCard() + " /  ");
		}
		System.out.println(LINE_SEPARATOR);

		// now shuffled (don't forget to reload and shuffle the deck)
//		deck2.init();
		deck2.init(1); // Some students don't have a null init method, which is OK
		deck2.shuffle();
		while (deck2.getNumCards() > 0) {
			System.out.print(deck2.dealCard().toString() + " /  ");
		}
		System.out.println(LINE_SEPARATOR);
		// Assignment #2 Phase 2: Hand + Deck Client
		// ============
		int k, numHands;
		Deck deck = new Deck(1);
		Hand hands[] = new Hand[MAX_HANDS];
//        String strUserIn;
//        Scanner inputStream = new Scanner(System.in);
		// get the input from the user --------------------------
//        do
//        {
//            System.out.print("How many hands? (1 - " + MAX_HANDS + ", please): ");
//            // get the answer in the form of a string:
//            strUserIn = inputStream.nextLine();
//            // and convert it to a number
//            numHands = Integer.parseInt(strUserIn);
//        } while (numHands < 1 || numHands > MAX_HANDS);
//        inputStream.close();
		numHands = 4;

		// allocate the hands
		for (k = 0; k < numHands; k++) {
			hands[k] = new Hand();
		}

		// deal deck, unshuffled --------------------------------
		boolean numCardsNotZero = deck.getNumCards() != 0;
		while (deck.getNumCards() > 0 && numCardsNotZero) {
			for (k = 0; k < numHands; k++) {
				numCardsNotZero = deck.getNumCards() != 0;
				if (numCardsNotZero) {
					hands[k].takeCard(deck.dealCard());
				} else {
					// Stop the inner for-loop by setting k to numHands
					k = numHands;
				}
			}
		}

		System.out.println("Here are our hands, from unshuffled deck:");
		for (k = 0; k < numHands; k++) {
			System.out.println(hands[k] + LINE_SEPARATOR);
		}
		System.out.println();

		// restock, deal deck, shuffled --------------------------
		deck.init(1);
		deck.shuffle();

		// clear hands
		for (k = 0; k < numHands; k++)
			hands[k].resetHand();

		while (deck.getNumCards() > 0) {
			for (k = 0; k < numHands; k++) {
				if (deck.getNumCards() == 0) {
					k = numHands;
				} else {
					hands[k].takeCard(deck.dealCard());
				}
			}
		}
		System.out.println("Here are our hands, from SHUFFLED deck:");
		for (k = 0; k < numHands; k++) {
			System.out.println(hands[k] + LINE_SEPARATOR);
		}
	}
}
