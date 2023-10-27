package model;

import org.json.JSONObject;
import persistence.Writable;

public class GameState implements Writable {
    private int playerWins;
    private int playerLosses;
    private int playerPoints;
    private int dealerPoints;

    // EFFECTS: Constructs a new GameState object with default values for
    //          playerWins, playerLosses, playerPoints, and dealerPoints.
    public GameState() {
        this.playerWins = 0;
        this.playerLosses = 0;
        this.playerPoints = 0;
        this.dealerPoints = 0;
    }

    // EFFECTS: Returns the number of player wins.
    public int getPlayerWins() {
        return playerWins;
    }

    // MODIFIES: this
    // EFFECTS: Sets the number of player wins.
    public void setPlayerWins(int playerWins) {
        this.playerWins = playerWins;
    }

    // EFFECTS: Returns the number of player losses.
    public int getPlayerLosses() {
        return playerLosses;
    }

    // MODIFIES: this
    // EFFECTS: Sets the number of player losses.
    public void setPlayerLosses(int playerLosses) {
        this.playerLosses = playerLosses;
    }

    // EFFECTS: Returns the player's points.
    public int getPlayerPoints() {
        return playerPoints;
    }

    // MODIFIES: this
    // EFFECTS: Sets the player's points.
    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    // EFFECTS: Returns the dealer's points.
    public int getDealerPoints() {
        return dealerPoints;
    }

    // MODIFIES: this
    // EFFECTS: Sets the dealer's points.
    public void setDealerPoints(int dealerPoints) {
        this.dealerPoints = dealerPoints;
    }

    // EFFECTS: Converts the GameState object to a JSONObject.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Player's Wins", playerWins);
        json.put("Player's Losses", playerLosses);
        json.put("Player's Points", playerPoints);
        json.put("Dealer's Points", dealerPoints);
        return json;
    }
}
