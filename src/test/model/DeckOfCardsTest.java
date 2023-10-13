package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.DeckOfCards.denominations;
import static model.DeckOfCards.suits;
import static org.junit.jupiter.api.Assertions.*;



public class DeckOfCardsTest {
    private DeckOfCards testDeckOfCards;

    @BeforeEach
    void runBefore() {
        testDeckOfCards = new DeckOfCards();
    }

    @Test
    void testConstructor() {
        assertTrue(testDeckOfCards.isDeckEmpty());
    }

    @Test
    void testBuildDeckOfCards() {
        testDeckOfCards.buildDeckOfCards();
        ArrayList<Card> builtDeckOfCards = testDeckOfCards.getDeckOfCards();
        int i = 0;

        for (String denomination : denominations) {
            for (String suit : suits) {

                Card card = new Card(denomination, suit);
                String cardStringRep = card.toString();

                Card currentCard = builtDeckOfCards.get(i);
                String currentCardStringRep = currentCard.toString();

                assertEquals(cardStringRep, currentCardStringRep);
                i++ ;
            }
        }
    }

    @Test
    void testShuffleDeck() {
        testDeckOfCards.buildDeckOfCards();
        testDeckOfCards.shuffleDeck();
        ArrayList<Card> builtAndShuffledDeckOfCards = testDeckOfCards.getDeckOfCards();

        for (Card currentCard: builtAndShuffledDeckOfCards) {
            String currentCardDenomination = currentCard.getDenomination();
            String currentCardSuit = currentCard.getSuit();
            assertTrue(denominations.contains(currentCardDenomination));
            assertTrue(suits.contains(currentCardSuit));
        }
    }

    @Test
    void testDrawCard() {
        testDeckOfCards.buildDeckOfCards();
        testDeckOfCards.shuffleDeck();
        Card drawnCard = testDeckOfCards.drawCard();

        String drawnCardDenomination = drawnCard.getDenomination();
        String drawnCardSuit = drawnCard.getSuit();
        assertTrue(denominations.contains(drawnCardDenomination));
        assertTrue(suits.contains(drawnCardSuit));
    }
}
