package model;

import java.util.ArrayList;
import java.util.Random;

import model.Card;

public class DeckOfCards {
    private static ArrayList<Card> deckOfCards;
    private Random random = new Random();

    public void buildDeckOfCards() {
        deckOfCards = new ArrayList<>();
        String[] denominations = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        for (String denomination : denominations) {
            for (String suit : suits) {
                Card card = new Card(denomination, suit);
                deckOfCards.add(card);
            }
        }

        System.out.println("BUILD DECK:");
        System.out.println(deckOfCards);
    }

    public void shuffleDeck() {
        for (int i = 0; i < deckOfCards.size(); i++) {
            int j = random.nextInt(deckOfCards.size());
            Card currentCard = deckOfCards.get(i);
            Card randomCard = deckOfCards.get(j);
            deckOfCards.set(i, randomCard);
            deckOfCards.set(j, currentCard);
        }

        System.out.println("AFTER SHUFFLING:");
        System.out.println(deckOfCards);
    }

    public ArrayList<Card> getDeckOfCards() {
        return this.deckOfCards;
    }

    public Card drawCard() {
        Card drawnCard = deckOfCards.remove(deckOfCards.size() - 1);
        return drawnCard;
    }
}
