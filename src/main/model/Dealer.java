package model;

import java.util.ArrayList;

    // The Dealer class represents the dealer in a card game.
    // It manages the dealer's hand, including a hidden card, and calculates the hand's value.

public class Dealer {
    private Card hiddenCard;
    private ArrayList<Card> dealersHand;
    private ArrayList<Card> dealersHandWithHiddenCard;
    private int dealerHandSum;
    private int dealerAceCount;
    private DeckOfCards deck;

    // REQUIRES: deck is not null and contains at least 2 cards.
    // MODIFIES: this
    // EFFECTS: Initializes a new Dealer object with an initial hand.
    public Dealer(DeckOfCards deck) {
        this.deck = deck;
        this.dealersHand = new ArrayList<>();
        this.dealersHandWithHiddenCard = new ArrayList<>();
        this.dealerHandSum = 0;
        this.dealerAceCount = 0;

        ArrayList<Card> currentDeck = this.deck.getDeckOfCards();
        hiddenCard = currentDeck.remove(currentDeck.size() - 1);
        this.dealersHand.add(hiddenCard);
        this.dealersHandWithHiddenCard.add(new Card("X", "X"));
        this.dealerHandSum += hiddenCard.getValue();
        this.dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card cardGoingToHand = currentDeck.remove(currentDeck.size() - 1);
        this.dealerHandSum += cardGoingToHand.getValue();
        this.dealerAceCount += cardGoingToHand.isAce() ? 1 : 0;
        this.dealersHand.add(cardGoingToHand);
        this.dealersHandWithHiddenCard.add(cardGoingToHand);
    }

    // REQUIRES: drawnCard is not null.
    // MODIFIES: this
    // EFFECTS: Adds the drawn card to the dealer's hand and updates the hand's sum.
    public void addCardAndHandSum(Card drawnCard) {
        this.dealersHand.add(drawnCard);
        this.dealersHandWithHiddenCard.add(drawnCard);
        int cardValue = drawnCard.getValue();
        this.dealerHandSum += cardValue;
    }

    // MODIFIES: this
    // EFFECTS: If the dealer's hand sum is over 21 and there are Aces in the hand, reduces the Ace value.
    public int reduceDealerAce() {
        while (dealerHandSum > 21 && dealerAceCount > 0) {
            dealerHandSum -= 10;
            dealerAceCount -= 1;
        }
        return dealerHandSum;
    }

    // REQUIRES: drawnCard is not null.
    // MODIFIES: this
    // EFFECTS: Adds the Ace count to the dealer's hand if the drawn card is an Ace.
    public void addAceCount(Card drawnCard) {
        dealerAceCount += drawnCard.isAce() ? 1 : 0;
    }

    // EFFECTS: Returns the list of cards in the dealer's hand.
    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }

    // EFFECTS: Returns the list of cards in the dealer's hand with the hidden card not visible.
    public ArrayList<Card> getDealersHandWithHiddenCard() {
        return dealersHandWithHiddenCard;
    }

    // EFFECTS: Returns the sum of the dealer's hand.
    public int getDealerHandSum() {
        return dealerHandSum;
    }

    // EFFECTS: Returns the count of Aces in the dealer's hand.
    public int getDealerAceCount() {
        return dealerAceCount;
    }

    // EFFECTS: Returns the hidden card in the dealer's hand.
    public Card getHiddenCard() {
        return hiddenCard;
    }

    // EFFECTS: Returns the deck of cards used by the dealer
    public DeckOfCards getDeck() {
        return deck;
    }
}
