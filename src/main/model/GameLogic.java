package model;

import model.PlayersHand;
import model.DeckOfCards;
import model.Dealer;

public class GameLogic {
    private PlayersHand playerHand;
    private Dealer dealer;
    private DeckOfCards deck;

    public void startNewRound() {
        deck = new DeckOfCards();
        deck.buildDeckOfCards();
        deck.shuffleDeck();
        dealer = new Dealer();
        playerHand = new PlayersHand();
    }

    public void playerHits() {
        Card drawnCard = deck.drawCard();
        playerHand.addCard(drawnCard);
        playerHand.addHandSum(drawnCard);
        System.out.println("DEALER:");
        System.out.println(dealer.getDealersHand());
        System.out.println("PLAYER:");
        System.out.println(playerHand.getPlayerHand());
    }

    public void playerStands() {
        System.out.println("DEALER:");
        System.out.println(dealer.getDealersHand());
        System.out.println("PLAYER:");
        System.out.println(playerHand.getPlayerHand());
    }

    public void dealerPlays() {
        while (dealer.getDealerHandSum() < 17) {
            Card drawnCard = deck.drawCard();
            dealer.addHandSum(drawnCard);
            dealer.addCard(drawnCard);
            System.out.println("DEALER:");
            System.out.println(dealer.getDealersHand());
            System.out.println("PLAYER:");
            System.out.println(playerHand.getPlayerHand());
        }
        System.out.println("Dealer stands.");
    }

    public void determineRoundOutcome() {
        int playerScore = playerHand.getPlayerHandSum();
        int dealerScore = dealer.getDealerHandSum();

        System.out.println("DEALER:");
        System.out.println(dealer.getDealersHand());
        System.out.println("PLAYER:");
        System.out.println(playerHand.getPlayerHand());

        if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
            System.out.println("Player bust, Dealer wins!");
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer bust, Player wins!");
        }
    }
}
