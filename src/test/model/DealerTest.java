package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static model.DeckOfCards.denominations;
import static model.DeckOfCards.suits;

public class DealerTest {
    private Dealer testDealer;
    private DeckOfCards testDeck;
    int initialDealerHandSum;
    int initialDealerAceCount;
    private Card hiddenCard;

    @BeforeEach
    void runBefore() {
        testDeck = new DeckOfCards();
        testDeck.buildDeckOfCards();
        testDeck.shuffleDeck();
        testDealer = new Dealer(testDeck);
        initialDealerHandSum = testDealer.getDealerHandSum();
        initialDealerAceCount = testDealer.getDealerAceCount();
        hiddenCard = testDealer.getHiddenCard();
    }

    @Test
    void testConstructor() {
        assertEquals(50, testDeck.getDeckOfCards().size());
        assertEquals(2, testDealer.getDealersHand().size());
        assertEquals(2, testDealer.getDealersHandWithHiddenCard().size());
        assertTrue(initialDealerHandSum > 0);
        assertTrue(initialDealerAceCount >= 0);
        assertTrue(denominations.contains(hiddenCard.getDenomination()));
        assertTrue(suits.contains(hiddenCard.getSuit()));
    }

    @Test
    void testAddCardAndHandSum() {
        Card drawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(drawnCard);

        assertEquals(49, testDeck.getDeckOfCards().size());
        assertEquals(3, testDealer.getDealersHand().size());
        assertEquals(3, testDealer.getDealersHandWithHiddenCard().size());
        assertTrue(testDealer.getDealerHandSum() > initialDealerHandSum);
        assertTrue(testDealer.getDealerAceCount() >= initialDealerAceCount);
    }

    @Test
    void testAddMultipleCardsAndHandSum() {
        Card firstDrawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(firstDrawnCard);
        int firstHandSumIncrement = testDealer.getDealerHandSum();
        int firstAceCountIncrement = testDealer.getDealerAceCount();

        assertEquals(49, testDeck.getDeckOfCards().size());
        assertEquals(3, testDealer.getDealersHand().size());
        assertEquals(3, testDealer.getDealersHandWithHiddenCard().size());
        assertTrue(firstHandSumIncrement > initialDealerHandSum);
        assertTrue(firstAceCountIncrement >= initialDealerAceCount);

        Card secondDrawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(secondDrawnCard);
        int secondHandSumIncrement = testDealer.getDealerHandSum();
        int secondAceCountIncrement = testDealer.getDealerAceCount();

        assertEquals(48, testDeck.getDeckOfCards().size());
        assertEquals(4, testDealer.getDealersHand().size());
        assertEquals(4, testDealer.getDealersHandWithHiddenCard().size());
        assertTrue(secondHandSumIncrement > firstHandSumIncrement);
        assertTrue(secondAceCountIncrement >= firstAceCountIncrement);

        Card thirdDrawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(thirdDrawnCard);
        int thirdHandSumIncrement = testDealer.getDealerHandSum();
        int thirdAceCountIncrement = testDealer.getDealerAceCount();

        assertEquals(47, testDeck.getDeckOfCards().size());
        assertEquals(5, testDealer.getDealersHand().size());
        assertEquals(5, testDealer.getDealersHandWithHiddenCard().size());
        assertTrue(thirdHandSumIncrement > secondHandSumIncrement);
        assertTrue(thirdAceCountIncrement >= secondAceCountIncrement);
    }

    @Test
    void testAddAceCount() {
        Card drawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(drawnCard);
        testDealer.addAceCount(drawnCard);

        if (drawnCard.isAce()) {
            assertTrue(testDealer.getDealerAceCount() > initialDealerAceCount);
        } else {
            assertTrue(testDealer.getDealerAceCount() == initialDealerAceCount);
        }
    }

    @Test
    void testAddMultipleAceCounts() {
        Card firstDrawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(firstDrawnCard);
        testDealer.addAceCount(firstDrawnCard);

        if (firstDrawnCard.isAce()) {
            assertTrue(testDealer.getDealerAceCount() > initialDealerAceCount);
        } else {
            assertTrue(testDealer.getDealerAceCount() == initialDealerAceCount);
        }

        int firstAddAceCountResult = testDealer.getDealerAceCount();

        Card secondDrawnCard = testDeck.drawCard();
        testDealer.addCardAndHandSum(secondDrawnCard);
        testDealer.addAceCount(secondDrawnCard);

        if (secondDrawnCard.isAce()) {
            assertTrue(testDealer.getDealerAceCount() > firstAddAceCountResult);
        } else {
            assertTrue(testDealer.getDealerAceCount() == firstAddAceCountResult);
        }
    }

    @Test
    void testReduceDealerAce() {
        if (testDealer.getDealerHandSum() > 21 && testDealer.getDealerAceCount() > 0) {
            testDealer.reduceDealerAce();
            assertTrue(testDealer.getDealerHandSum() <= initialDealerHandSum);
            assertTrue(testDealer.getDealerAceCount() <= initialDealerAceCount);
        } else if (testDealer.getDealerHandSum() <= 21 && testDealer.getDealerAceCount() > 0) {
            assertEquals(initialDealerHandSum, testDealer.getDealerHandSum());
            assertEquals(initialDealerAceCount, testDealer.getDealerAceCount());
        } else if (testDealer.getDealerHandSum() > 21 && testDealer.getDealerAceCount() == 0) {
            assertEquals(initialDealerHandSum, testDealer.getDealerHandSum());
            assertEquals(initialDealerAceCount, testDealer.getDealerAceCount());
        } else {
            assertEquals(initialDealerHandSum, testDealer.getDealerHandSum());
            assertEquals(initialDealerAceCount, testDealer.getDealerAceCount());
        }
    }


    @Test
    void testGetDeck() {
        assertEquals(testDeck, testDealer.getDeck());
    }

}
