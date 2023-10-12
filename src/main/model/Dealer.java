package model;

import model.Card;
import model.DeckOfCards;

import java.util.ArrayList;

public class Dealer {
    private Card hiddenCard;
    private ArrayList<Card> dealersHand;
    private int dealerHandSum;
    // keep check of how many aces the dealer has.
    // Since the dealer is supposed to draw past 17, in case they go over 21
    // and carry an ace in their hand they change the value of the ace from 11 to 1.
    private int dealerAceCount;
    private DeckOfCards deck = new DeckOfCards();

    public Dealer() {
        this.dealersHand = new ArrayList<>();
        this.dealerHandSum = 0;
        this.dealerAceCount = 0;

        ArrayList<Card> currentDeck = deck.getDeckOfCards();
        hiddenCard = currentDeck.remove(currentDeck.size() - 1);
        this.dealerHandSum += hiddenCard.getValue();
        this.dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card cardGoingToHand = currentDeck.remove(currentDeck.size() - 1);
        this.dealerHandSum += cardGoingToHand.getValue();
        this.dealerAceCount += cardGoingToHand.isAce() ? 1 : 0;
        this.dealersHand.add(cardGoingToHand);

        System.out.println("DEALER:");
        System.out.println(hiddenCard);
        System.out.println(dealersHand);
        System.out.println(dealerHandSum);
        System.out.println(dealerAceCount);
    }

    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }

    public int getDealerHandSum() {
        return dealerHandSum;
    }

    public void addCard(Card drawnCard) {
        this.dealersHand.add(drawnCard);
    }

    public void addHandSum(Card drawnCard) {
        int cardValue = drawnCard.getValue();
        this.dealerHandSum += cardValue;
    }
}
