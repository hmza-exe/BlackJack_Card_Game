package model;

public class Card {
    private String denomination;
    private String suit;

    public Card(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String toString() {
        return this.denomination + " of " + this.suit;
    }

    public int getValue() {
        if ("AJQK".contains(denomination)) {
            if (denomination == "A") {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(denomination);
    }

    public boolean isAce() {
        return this.denomination == "A";
    }
}
