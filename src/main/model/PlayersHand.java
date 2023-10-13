package model;

import java.util.ArrayList;

// The PlayersHand class represents the hand of a player in a card game.
// It manages the player's hand, including calculating the hand's value and handling Aces.

public class PlayersHand {
    private ArrayList<Card> playerHand;
    private int playerHandSum;
    private int playerAceCount;
    private DeckOfCards deck;

    // REQUIRES: deck is not null and contains at least 2 cards.
    // MODIFIES: this
    // EFFECTS: Initializes a new PlayersHand object with an initial hand.
    public PlayersHand(DeckOfCards deck) {
        this.deck = deck;
        this.playerHand = new ArrayList<>();
        this.playerHandSum = 0;
        this.playerAceCount = 0;

        ArrayList<Card> currentDeck = this.deck.getDeckOfCards();

        for (int i = 0; i < 2; i++) {
            Card card = currentDeck.remove(currentDeck.size() - 1);
            playerHandSum += card.getValue();
            this.playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }
    }

    // REQUIRES: drawnCard is not null.
    // MODIFIES: this
    // EFFECTS: Adds the drawn card to the player's hand and updates the hand's sum.
    public void addCardAndHandSum(Card drawnCard) {
        this.playerHand.add(drawnCard);
        int cardValue = drawnCard.getValue();
        this.playerHandSum += cardValue;
    }

    // REQUIRES: drawnCard is not null.
    // MODIFIES: this
    // EFFECTS: Adds the Ace count to the player's hand if the drawn card is an Ace.
    public void addAceCount(Card drawnCard) {
        if (drawnCard.isAce()) {
            playerAceCount++;
        }
    }

    // MODIFIES: this
    // EFFECTS: If the player's hand sum is over 21 and there are Aces in the hand, reduces the Ace value.
    public int reducePlayerAce() {
        while (playerHandSum > 21 && playerAceCount > 0) {
            playerHandSum -= 10;
            playerAceCount -= 1;
        }
        return playerHandSum;
    }

    // EFFECTS: Returns the sum of the player's hand.
    public int getPlayerHandSum() {
        return this.playerHandSum;
    }

    // EFFECTS: Returns the deck of cards used by the player.
    public DeckOfCards getDeck() {
        return deck;
    }

    // EFFECTS: Returns the list of cards in the player's hand.
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    // EFFECTS: Returns the count of Aces in the player's hand.
    public int getPlayerAceCount() {
        return playerAceCount;
    }
}
