package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersHandTest {
    int initialPlayerHandSum;
    int initialPlayerAceCount;
    private PlayersHand testPlayersHand;
    private DeckOfCards testDeck;

    @BeforeEach
    void runbefore() {
        testDeck = new DeckOfCards();
        testDeck.buildDeckOfCards();
        testDeck.shuffleDeck();
        testPlayersHand = new PlayersHand(testDeck);
        initialPlayerHandSum = testPlayersHand.getPlayerHandSum();
        initialPlayerAceCount = testPlayersHand.getPlayerAceCount();
    }

    @Test
    void testConstructor() {
        assertEquals(50, testDeck.getDeckOfCards().size());
        assertEquals(2, testPlayersHand.getPlayerHand().size());
        assertTrue(initialPlayerHandSum > 0);
        assertTrue(initialPlayerAceCount >= 0);
    }

    @Test
    void testAddCardAndHandSum() {
        Card drawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(drawnCard);

        assertEquals(49, testDeck.getDeckOfCards().size());
        assertEquals(3, testPlayersHand.getPlayerHand().size());
        assertTrue(testPlayersHand.getPlayerHandSum() > initialPlayerHandSum);
        assertTrue(testPlayersHand.getPlayerAceCount() >= initialPlayerAceCount);
    }

    @Test
    void testAddMultipleCardsAndHandSum() {
        Card firstDrawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        int firstHandSumIncrement = testPlayersHand.getPlayerHandSum();
        int firstAceCountIncrement = testPlayersHand.getPlayerAceCount();

        assertEquals(49, testDeck.getDeckOfCards().size());
        assertEquals(3, testPlayersHand.getPlayerHand().size());
        assertTrue(firstHandSumIncrement > initialPlayerHandSum);
        assertTrue(firstAceCountIncrement >= initialPlayerAceCount);

        Card secondDrawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(secondDrawnCard);
        int secondHandSumIncrement = testPlayersHand.getPlayerHandSum();
        int secondAceCountIncrement = testPlayersHand.getPlayerAceCount();

        assertEquals(48, testDeck.getDeckOfCards().size());
        assertEquals(4, testPlayersHand.getPlayerHand().size());
        assertTrue(secondHandSumIncrement > firstHandSumIncrement);
        assertTrue(secondAceCountIncrement >= firstAceCountIncrement);

        Card thirdDrawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(thirdDrawnCard);
        int thirdHandSumIncrement = testPlayersHand.getPlayerHandSum();
        int thirdAceCountIncrement = testPlayersHand.getPlayerAceCount();

        assertEquals(47, testDeck.getDeckOfCards().size());
        assertEquals(5, testPlayersHand.getPlayerHand().size());
        assertTrue(thirdHandSumIncrement > secondHandSumIncrement);
        assertTrue(thirdAceCountIncrement >= secondAceCountIncrement);
    }

    @Test
    void testAddAceCountWhenDrawnCardIsAce() {
        Card drawnCard = new Card("A", "Spades"); // Simulating an Ace being drawn
        testPlayersHand.addCardAndHandSum(drawnCard);
        testPlayersHand.addAceCount(drawnCard);

        assertTrue(testPlayersHand.getPlayerAceCount() > initialPlayerAceCount);
    }

    @Test
    void testAddAceCountWhenDrawnCardIsNotAce() {
        Card drawnCard = new Card("K", "Hearts"); // Simulating a non-Ace card being drawn
        testPlayersHand.addCardAndHandSum(drawnCard);
        testPlayersHand.addAceCount(drawnCard);

        assertEquals(testPlayersHand.getPlayerAceCount(), initialPlayerAceCount);
    }

    @Test
    void testAddMultipleAceCountsWhenFirstDrawnCardIsAce() {
        Card firstDrawnCard = new Card("A", "Diamonds"); // Simulating an Ace being drawn
        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        testPlayersHand.addAceCount(firstDrawnCard);

        assertTrue(testPlayersHand.getPlayerAceCount() > initialPlayerAceCount);

        int firstAddAceCountResult = testPlayersHand.getPlayerAceCount();

        Card secondDrawnCard = new Card("9", "Clubs"); // Simulating a non-Ace card being drawn
        testPlayersHand.addCardAndHandSum(secondDrawnCard);
        testPlayersHand.addAceCount(secondDrawnCard);

        assertEquals(testPlayersHand.getPlayerAceCount(), firstAddAceCountResult);
    }

    @Test
    void testAddMultipleAceCountsWhenFirstDrawnCardIsNotAce() {
        Card firstDrawnCard = new Card("K", "Spades"); // Simulating a non-Ace card being drawn
        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        testPlayersHand.addAceCount(firstDrawnCard);

        assertEquals(testPlayersHand.getPlayerAceCount(), initialPlayerAceCount);

        int firstAddAceCountResult = testPlayersHand.getPlayerAceCount();

        Card secondDrawnCard = new Card("A", "Hearts"); // Simulating an Ace being drawn
        testPlayersHand.addCardAndHandSum(secondDrawnCard);
        testPlayersHand.addAceCount(secondDrawnCard);

        assertTrue(testPlayersHand.getPlayerAceCount() > firstAddAceCountResult);
    }

    @Test
    void testAddAceCountWhenBothCardsAreAces() {
        Card firstDrawnCard = new Card("A", "Spades");
        Card secondDrawnCard = new Card("A", "Hearts");

        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        testPlayersHand.addCardAndHandSum(secondDrawnCard);

        testPlayersHand.addAceCount(firstDrawnCard);
        testPlayersHand.addAceCount(secondDrawnCard);

        assertTrue(testPlayersHand.getPlayerAceCount() > initialPlayerAceCount);
    }

    @Test
    void testAddAceCountWhenNeitherCardIsAce() {
        Card firstDrawnCard = new Card("K", "Spades");
        Card secondDrawnCard = new Card("Q", "Hearts");

        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        testPlayersHand.addCardAndHandSum(secondDrawnCard);

        testPlayersHand.addAceCount(firstDrawnCard);
        testPlayersHand.addAceCount(secondDrawnCard);

        assertEquals(testPlayersHand.getPlayerAceCount(), initialPlayerAceCount);
    }

    @Test
    void testReducePlayerAce() {
        if (testPlayersHand.getPlayerHandSum() > 21 && testPlayersHand.getPlayerAceCount() > 0) {
            testPlayersHand.reducePlayerAce();
            assertTrue(testPlayersHand.getPlayerHandSum() <= initialPlayerHandSum);
            assertTrue(testPlayersHand.getPlayerAceCount() <= initialPlayerAceCount);
        } else if (testPlayersHand.getPlayerHandSum() <= 21 && testPlayersHand.getPlayerAceCount() > 0) {
            assertEquals(initialPlayerHandSum, testPlayersHand.getPlayerHandSum());
            assertEquals(initialPlayerAceCount, testPlayersHand.getPlayerAceCount());
        } else if (testPlayersHand.getPlayerHandSum() > 21 && testPlayersHand.getPlayerAceCount() == 0) {
            assertEquals(initialPlayerHandSum, testPlayersHand.getPlayerHandSum());
            assertEquals(initialPlayerAceCount, testPlayersHand.getPlayerAceCount());
        } else {
            assertEquals(initialPlayerHandSum, testPlayersHand.getPlayerHandSum());
            assertEquals(initialPlayerAceCount, testPlayersHand.getPlayerAceCount());
        }
    }

    @Test
    void testGetDeck() {
        assertEquals(testDeck, testPlayersHand.getDeck());
    }
}
