package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Card;
// The DeckOfCards class represents a deck of playing cards.
// It can be used to build, shuffle, draw cards from, and check if the deck is empty.

public class DeckOfCards {
    private static ArrayList<Card> deckOfCards;
    private Random random = new Random();
    public static final List<String> denominations;
    public static final List<String> suits;

    // Static initialization blocks for denominations and suits
    static {
        denominations = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
    }

    static {
        suits = List.of("Clubs", "Diamonds", "Hearts", "Spades");
    }

    // EFFECTS: Initializes a new DeckOfCards with an empty deck.
    public DeckOfCards() {
        deckOfCards = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds 52 cards (one of each denomination for each suit) to the deck.
    public void buildDeckOfCards() {

        for (String denomination : denominations) {
            for (String suit : suits) {
                Card card = new Card(denomination, suit);
                deckOfCards.add(card);
            }
        }
    }

    // REQUIRES: The deck is not empty.
    // MODIFIES: this
    // EFFECTS: Randomly shuffles the order of cards in the deck.
    public void shuffleDeck() {
        for (int i = 0; i < deckOfCards.size(); i++) {
            int j = random.nextInt(deckOfCards.size());
            Card currentCard = deckOfCards.get(i);
            Card randomCard = deckOfCards.get(j);
            deckOfCards.set(i, randomCard);
            deckOfCards.set(j, currentCard);
        }
    }

    // EFFECTS: Returns the list of cards in the deck.
    public ArrayList<Card> getDeckOfCards() {
        return this.deckOfCards;
    }

    // REQUIRES: The deck is not empty.
    // MODIFIES: this
    // EFFECTS: Removes and returns the top card from the deck.
    public Card drawCard() {
        Card drawnCard = deckOfCards.remove(deckOfCards.size() - 1);
        return drawnCard;
    }

    // EFFECTS: Returns true if the deck is empty, false otherwise.
    public boolean isDeckEmpty() {
        return deckOfCards.isEmpty();
    }
}
