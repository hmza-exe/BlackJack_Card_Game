package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    private GameLogic testGameLogic;
    private DeckOfCards deck;
    private PlayersHand player;
    private Dealer dealer;

    @BeforeEach
    void runBefore() {
        testGameLogic = new GameLogic();
        deck = testGameLogic.getDeck();
        player = testGameLogic.getPlayerHand();
        dealer = testGameLogic.getDealer();
    }

    @Test
    void testConstructor() {
        assertEquals(48, deck.getDeckOfCards().size());
        assertEquals(2, player.getPlayerHand().size());
        assertEquals(2, dealer.getDealersHand().size());
        assertFalse(testGameLogic.isPlayerBust());
        assertFalse(testGameLogic.isPlayerWin());
        assertFalse(testGameLogic.isPlayerTie());
        assertFalse(testGameLogic.isDealerBust());
    }

    @Test
    void testPlayerHits() {
        testGameLogic.playerHits();
        assertEquals(3, player.getPlayerHand().size());

        if (player.reducePlayerAce() > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else {
            assertFalse(testGameLogic.isPlayerBust());
        }
    }

    @Test
    void testPlayerHitsMultipleTimes() {
        testGameLogic.playerHits();
        assertEquals(3, player.getPlayerHand().size());

        testGameLogic.playerHits();
        assertEquals(4, player.getPlayerHand().size());

        testGameLogic.playerHits();
        assertEquals(5, player.getPlayerHand().size());

        if (player.reducePlayerAce() > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else {
            assertFalse(testGameLogic.isPlayerBust());
        }
    }

    @Test
    void testPlayerStandsWithDealerUnder17Points() {
        dealer.addCardAndHandSum(new Card("2", "Diamonds"));
        dealer.addCardAndHandSum(new Card("4", "Spades"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

    @Test
    void testPlayerStandsWithDealerOver17Points() {
        dealer.addCardAndHandSum(new Card("10", "Diamonds"));
        dealer.addCardAndHandSum(new Card("8", "Spades"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

    @Test
    void testPlayerStandsWithDealerExactly17Points() {
        dealer.addCardAndHandSum(new Card("7", "Clubs"));
        dealer.addCardAndHandSum(new Card("10", "Hearts"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

    @Test
    void testPlayerStandsWithEqualFinalSums() {
        dealer.addCardAndHandSum(new Card("7", "Clubs"));
        dealer.addCardAndHandSum(new Card("10", "Hearts"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

    @Test
    void testPlayerStandsWithFinalPlayerHandGreaterThanFinalDealerHand() {
        dealer.addCardAndHandSum(new Card("8", "Diamonds"));
        dealer.addCardAndHandSum(new Card("9", "Spades"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

    @Test
    void testPlayerStandsWithFinalPlayerHandLessThanFinalDealerHand() {
        dealer.addCardAndHandSum(new Card("9", "Clubs"));
        dealer.addCardAndHandSum(new Card("10", "Hearts"));
        dealer.reduceDealerAce();

        testGameLogic.playerStands();

        int finalPlayerHandSum = player.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            assertTrue(testGameLogic.isPlayerBust());
        } else if (finalDealerHandSum > 21) {
            assertTrue(testGameLogic.isDealerBust());
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            assertTrue(testGameLogic.isPlayerTie());
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            assertFalse(testGameLogic.isPlayerWin());
        } else {
            assertTrue(testGameLogic.isPlayerWin());
        }
    }

}
