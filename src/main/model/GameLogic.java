package model;

// The GameLogic class represents the logic of a card game, managing player actions and determining outcomes.
// It coordinates interactions between the player, dealer, and deck of cards.


public class GameLogic {
    private PlayersHand playerHand;
    private Dealer dealer;
    private DeckOfCards deck;
    private boolean playerBust;
    private boolean playerWin;
    private boolean playerTie;
    private boolean dealerBust;

    // EFFECTS: Initializes a new GameLogic object with a shuffled deck, dealer, and player's hand.
    public GameLogic() {
        deck = new DeckOfCards();
        deck.buildDeckOfCards();
        deck.shuffleDeck();
        dealer = new Dealer(deck);
        playerHand = new PlayersHand(deck);
        playerBust = false;
        playerWin = false;
        playerTie = false;
        dealerBust = false;
    }

    //  MODIFIES: this, playerHand
    // EFFECTS: Draws a card from the deck, adds it to the player's hand, and updates the hand's sum.
    //          Checks if the player has busted (hand sum > 21).
    public void playerHits() {
        Card drawnCard = deck.drawCard();
        playerHand.addCardAndHandSum(drawnCard);
        playerHand.addAceCount(drawnCard);

        if (playerHand.reducePlayerAce() > 21) {
            playerBust = true;
        }
    }

    // MODIFIES: this, playerHand, dealer
    // EFFECTS: Allows the dealer to draw cards until their hand sum is at least 17.
    //          Reduces Ace values if needed for both player and dealer.
    //          Determines game outcomes: player win, tie, bust, or dealer bust.
    public void playerStands() {
        while (dealer.getDealerHandSum() < 17) {
            Card drawnCard = deck.drawCard();
            dealer.addCardAndHandSum(drawnCard);
            dealer.addAceCount(drawnCard);
        }

        playerHand.reducePlayerAce();
        dealer.reduceDealerAce();

        int finalPlayerHandSum = playerHand.getPlayerHandSum();
        int finalDealerHandSum = dealer.getDealerHandSum();

        if (finalPlayerHandSum > 21) {
            playerBust = true;
        } else if (finalDealerHandSum > 21) {
            dealerBust = true;
        } else if (finalDealerHandSum == finalPlayerHandSum) {
            playerTie = true;
        } else if (finalDealerHandSum > finalPlayerHandSum) {
            playerWin = false;
        } else {
            playerWin = true;
        }
    }

    // EFFECTS: Returns the player's hand.
    public PlayersHand getPlayerHand() {
        return playerHand;
    }

    // EFFECTS: Returns the dealer object.
    public Dealer getDealer() {
        return dealer;
    }

    // EFFECTS: Returns the deck of cards used in the game.
    public DeckOfCards getDeck() {
        return deck;
    }

    // EFFECTS: Returns true if the player has busted (hand sum > 21), false otherwise.
    public boolean isPlayerBust() {
        return playerBust;
    }

    // EFFECTS: Returns true if the player has won the game, false otherwise.
    public boolean isPlayerWin() {
        return playerWin;
    }

    // EFFECTS: Returns true if the game ended in a tie, false otherwise.
    public boolean isPlayerTie() {
        return playerTie;
    }

    // EFFECTS: Returns true if the dealer has busted (hand sum > 21), false otherwise.
    public boolean isDealerBust() {
        return dealerBust;
    }
}

