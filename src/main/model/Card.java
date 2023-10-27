package model;


// The Card class represents an individual playing card.
// Each card has a denomination (e.g., "2", "Ace") and a suit (e.g., "Hearts", "Spades").

public class Card {
    private final String denomination;
    private final String suit;

    // EFFECTS: Initializes a new Card object with the provided denomination and suit.
    public Card(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    // EFFECTS: Returns a formatted string representing the card.
    public String toString() {
        return this.denomination + " of " + this.suit;
    }

    // EFFECTS: Returns the value of the card based on its denomination.
    public int getValue() {
        if ("AJQK".contains(denomination)) {
            if (denomination.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(denomination);
    }

    // EFFECTS: Returns true if the card is an Ace, false otherwise.
    public boolean isAce() {
        return this.denomination.equals("A");
    }

    // EFFECTS: Returns the denomination of the card.
    public String getDenomination() {
        return denomination;
    }

    // EFFECTS: Returns the suit of the card.
    public String getSuit() {
        return suit;
    }

}
