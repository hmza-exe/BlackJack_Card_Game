package ui;

//import model.Dealer;
//import model.DeckOfCards;
//import model.PlayersHand;
import model.GameLogic;

public class Main {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.startNewRound();
        game.dealerPlays();
        game.playerHits();
        game.playerStands();
        game.determineRoundOutcome();
    }
}
