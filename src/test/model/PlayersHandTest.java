package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersHandTest {
    private PlayersHand testPlayersHand;
    private DeckOfCards testDeck;
    int initialPlayerHandSum;
    int initialPlayerAceCount;

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
    void testAddAceCount() {
        Card drawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(drawnCard);
        testPlayersHand.addAceCount(drawnCard);

        if (drawnCard.isAce()) {
            assertTrue(testPlayersHand.getPlayerAceCount() > initialPlayerAceCount);
        } else {
            assertEquals(testPlayersHand.getPlayerAceCount(), initialPlayerAceCount);
        }
    }

    @Test
    void testAddMultipleAceCounts() {
        Card firstDrawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(firstDrawnCard);
        testPlayersHand.addAceCount(firstDrawnCard);

        if (firstDrawnCard.isAce()) {
            assertTrue(testPlayersHand.getPlayerAceCount() > initialPlayerAceCount);
        } else {
            assertEquals(testPlayersHand.getPlayerAceCount(), initialPlayerAceCount);
        }

        int firstAddAceCountResult = testPlayersHand.getPlayerAceCount();

        Card secondDrawnCard = testDeck.drawCard();
        testPlayersHand.addCardAndHandSum(secondDrawnCard);
        testPlayersHand.addAceCount(secondDrawnCard);

        if (secondDrawnCard.isAce()) {
            assertTrue(testPlayersHand.getPlayerAceCount() > firstAddAceCountResult);
        } else {
            assertEquals(testPlayersHand.getPlayerAceCount(), firstAddAceCountResult);
        }
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
