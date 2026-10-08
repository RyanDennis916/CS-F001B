import org.junit.Assert;
import org.junit.Test;

/**
 * Tests CardInterface.Suit
 */
public class CardInterface_SuitTest {
	private static final boolean DEBUG = false;

	/**
	 * Checks the values of members of the CardInterface.Suit enumeration.
	 */
	@Test
	public void testValueOf() {
		CardInterface.Suit expected = null;
		expected = CardInterface.Suit.clubs;
		Assert.assertEquals(expected, CardInterface.Suit.valueOf("clubs"));
		expected = CardInterface.Suit.diamonds;
		Assert.assertEquals(expected, CardInterface.Suit.valueOf("diamonds"));
		expected = CardInterface.Suit.hearts;
		Assert.assertEquals(expected, CardInterface.Suit.valueOf("hearts"));
		expected = CardInterface.Suit.spades;
		Assert.assertEquals(expected, CardInterface.Suit.valueOf("spades"));
		expected = null;
		boolean exceptionCaught = false;
		try {
			expected = CardInterface.Suit.valueOf(null);
			System.out.println("CardInterface.Suit.valueOf(null):" + expected);
		} catch (java.lang.NullPointerException e) {
			exceptionCaught = true;
			Assert.assertNotNull(e);
			if (DEBUG) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(
				"expected = CardInterface.Suit.valueOf(null); should have thrown a java.lang.NullPointerException.",
				exceptionCaught);
		exceptionCaught = false;
		try {
			expected = CardInterface.Suit.valueOf("");
			System.out.println("CardInterface.Suit.valueOf(\"\"):" + expected);
		} catch (java.lang.IllegalArgumentException e) {
			exceptionCaught = true;
			Assert.assertNotNull(e);
			if (DEBUG) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(
				"expected = CardInterface.Suit.valueOf(\"\"); should have thrown a java.lang.IllegalArgumentException.",
				exceptionCaught);
	}

	/**
	 * Checks the order of the members of the CardInterface.Suit enumeration.
	 */
	@Test
	public void testValues() {
		CardInterface.Suit[] expected = { CardInterface.Suit.clubs, CardInterface.Suit.diamonds,
				CardInterface.Suit.hearts, CardInterface.Suit.spades };
		Assert.assertArrayEquals(
				"Student didn't put the suits in the enum in the specified order with the specified names, or has the wrong number of suit elements",
				expected, CardInterface.Suit.values());
	}
}
