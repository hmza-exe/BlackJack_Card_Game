package model;

import org.json.JSONObject;
import persistence.Writable;

public class GameState implements Writable {
//    private PlayersHand playerHand;
//    private Dealer dealerHand;
//    private DeckOfCards currentDeck;
    private int playerWins;
    private int playerLosses;
    private int playerPoints;
    private int dealerPoints;

    // Constructors, getters, and setters for playerHand,
    // dealerHand, currentDeck, playerWins, playerLosses,
    // playerPoints, and dealerPoints

    // Constructor
    public GameState() {
//        this.playerHand = playerHand;
//        this.dealerHand = dealer;
//        this.currentDeck = deck;
        this.playerWins = 0;
        this.playerLosses = 0;
        this.playerPoints = 0;
        this.dealerPoints = 0;
    }

//    public PlayersHand getPlayerHand() {
//        return playerHand;
//    }
//
//    public void setPlayerHand(PlayersHand playerHand) {
//        this.playerHand = playerHand;
//    }
//
//    public Dealer getDealerHand() {
//        return dealerHand;
//    }
//
//    public void setDealerHand(Dealer dealerHand) {
//        this.dealerHand = dealerHand;
//    }
//
//    public DeckOfCards getCurrentDeck() {
//        return currentDeck;
//    }
//
//    public void setCurrentDeck(DeckOfCards currentDeck) {
//        this.currentDeck = currentDeck;
//    }

    public int getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(int playerWins) {
        this.playerWins = playerWins;
    }

    public int getPlayerLosses() {
        return playerLosses;
    }

    public void setPlayerLosses(int playerLosses) {
        this.playerLosses = playerLosses;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getDealerPoints() {
        return dealerPoints;
    }

    public void setDealerPoints(int dealerPoints) {
        this.dealerPoints = dealerPoints;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
//        json.put("Player's Hand", playerHand.toJson());
//        json.put("Dealer's Hand", dealerHand.toJson());
//        json.put("Deck Of Cards", currentDeck.toJson());
        json.put("Player's Wins", playerWins);
        json.put("Player's Losses", playerLosses);
        json.put("Player's Points", playerPoints);
        json.put("Dealer's Points", dealerPoints);
        return json;
    }
}
