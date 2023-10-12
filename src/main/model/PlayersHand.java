package model;

import model.Card;
import model.DeckOfCards;
import java.util.ArrayList;

public class PlayersHand {
    private ArrayList<Card> playerHand;
    private int playerHandSum;
    private int playerAceCount;
    private DeckOfCards deck = new DeckOfCards();

    public PlayersHand() {
        this.playerHand = new ArrayList<>();
        this.playerHandSum = 0;
        this.playerAceCount = 0;

        ArrayList<Card> currentDeck = deck.getDeckOfCards();

        for (int i = 0; i < 2; i++) {
            Card card = currentDeck.remove(currentDeck.size() - 1);
            playerHandSum += card.getValue();
            this.playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        System.out.println("PLAYER:");
        System.out.println(playerHand);
        System.out.println(playerHandSum);
        System.out.println(playerAceCount);
    }

    public void addCard(Card drawnCard) {
        this.playerHand.add(drawnCard);
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void addHandSum(Card drawnCard) {
        int cardValue = drawnCard.getValue();
        this.playerHandSum += cardValue;
    }

    public int getPlayerHandSum() {
        return this.playerHandSum;
    }
}
