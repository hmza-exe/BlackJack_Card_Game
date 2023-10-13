package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card testRegularCard;
    private Card testAceCard;
    private Card testFaceCard;
    // add two more cards, one that is an ace and the other which is a face card

    @BeforeEach
    void runBefore() {
        testRegularCard = new Card("2", "Clubs");
        testAceCard = new Card("A", "Spades");
        testFaceCard = new Card("K", "Hearts");
    }

    @Test
    void testConstructor() {
        assertEquals("2", testRegularCard.getDenomination());
        assertEquals("Clubs", testRegularCard.getSuit());

        assertEquals("A", testAceCard.getDenomination());
        assertEquals("Spades", testAceCard.getSuit());

        assertEquals("K", testFaceCard.getDenomination());
        assertEquals("Hearts", testFaceCard.getSuit());
    }

    @Test
    void testToString() {
        assertEquals("2 of Clubs", testRegularCard.toString());
        assertEquals("A of Spades", testAceCard.toString());
        assertEquals("K of Hearts", testFaceCard.toString());
    }

    @Test
    void testGetValue() {
        assertEquals(2, testRegularCard.getValue());
        assertEquals(11, testAceCard.getValue());
        assertEquals(10, testFaceCard.getValue());
    }

    @Test
    void testIsAce() {
        assertFalse(testRegularCard.isAce());
        assertTrue(testAceCard.isAce());
        assertFalse(testFaceCard.isAce());
    }
}
