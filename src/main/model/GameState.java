package model;

import org.json.JSONObject;
import persistence.Writable;

public class GameState implements Writable {
    private int playerWins;
    private int playerLosses;
    private int playerPoints;
    private int dealerPoints;


    public GameState() {
        this.playerWins = 0;
        this.playerLosses = 0;
        this.playerPoints = 0;
        this.dealerPoints = 0;
    }

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
        json.put("Player's Wins", playerWins);
        json.put("Player's Losses", playerLosses);
        json.put("Player's Points", playerPoints);
        json.put("Dealer's Points", dealerPoints);
        return json;
    }
}
