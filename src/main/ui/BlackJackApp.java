package ui;

import model.Dealer;
import model.GameLogic;
import model.GameState;
import model.PlayersHand;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The BlackJackApp class serves as the user interface for a simplified Blackjack game.
// It allows the player to interact with the game, make decisions (Hit or Stand), and displays game progress.

public class BlackJackApp {
    static String STARTING_MESSAGE;

    static {
        STARTING_MESSAGE =
                "!! WELCOME BLACKJACK ROYALE !!"
                        + "\n\nMenu:"
                        + "\n- Start New Round ('new')"
                        + "\n- Load Old session ('load')"
                        + "\n- Exit ('exit')\n";
    }

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private GameLogic newGame;
    private PlayersHand player;
    private Dealer dealer;
//    private int this.gameState.getPlayerPoints() = 0;
//    private int this.gameState.getDealerPoints() = 0;
//    private int this.gameState.getPlayerWins() = 0;
//    private int this.gameState.getPlayerLosses() = 0;
    private GameState gameState;
    private GameState loadedGameState;

    // EFFECTS: Initializes a new BlackJackApp object and starts the user interface.
    public BlackJackApp() {
        jsonWriter = new JsonWriter("./data/Saved_Game.json");
        jsonReader = new JsonReader("./data/Saved_Game.json");

        mainMenu();
    }

    // EFFECTS: Displays the main menu and allows the user to choose an option (new, load, exit).
    public void mainMenu() {
        System.out.println(STARTING_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        choice = choice.toLowerCase();

        scanner.nextLine();

        if (choice.equals("new")) {
            startANewGame(false);
        } else if (choice.equals("load")) {
            loadGame();
        } else if (choice.equals("exit")) {
            System.exit(0);
        } else {
            System.out.println("PLease enter a valid input!!");
            mainMenu();
        }
    }

    // MODIFIES: this, newGame, player, dealer
    // EFFECTS: Initializes a new game, displays starting message, and allows player interaction.
    public void startANewGame(boolean isLoaded) {

        newGame = new GameLogic();

        player = newGame.getPlayerHand();
        dealer = newGame.getDealer();

        if (isLoaded) {
            gameState = loadedGameState;
        } else {
            gameState = new GameState();
        }

        displayPlayerAndHiddenDealerHand();

        Scanner scanner = new Scanner(System.in);
        playerHitOrStandLoop(scanner);
    }

    // MODIFIES: this
    // EFFECTS: Handles the player's choice to hit or stand.
    public void playerHitOrStandLoop(Scanner scanner) {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("Do you want to Hit or Stand?");
            String choice = scanner.next();
            choice = choice.toLowerCase();
            scanner.nextLine();

            if (choice.equals("hit")) {
                continueGame = playerHitRoute(scanner, continueGame);

            } else if (choice.equals("stand")) {
                continueGame = playerStandRoute(scanner, continueGame);

            } else {
                System.out.println("Invalid choice. Please enter Hit or Stand.\n");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Tally and display points for the game.
    public void tallyPoints() {
//        gameState.setPlayerPoints(gameState.getPlayerPoints() + this.gameState.getPlayerPoints());
//        gameState.setPlayerWins(gameState.getPlayerWins() + this.gameState.getPlayerWins());
//        gameState.setDealerPoints(gameState.getDealerPoints() + this.gameState.getDealerPoints());
//        gameState.setPlayerLosses(gameState.getPlayerLosses() + this.gameState.getPlayerLosses());

        System.out.println("\nPLAYER POINTS : [" + gameState.getPlayerPoints() + " point(s)]");
        System.out.println("DEALER POINTS : [" + gameState.getDealerPoints() + " point(s)]");
        System.out.println("PLAYER WINS : [" + gameState.getPlayerWins() + " win(s)]");
        System.out.println("PLAYER LOSSES : [" + gameState.getPlayerLosses() + " loss(es)]");

        if (this.gameState.getPlayerPoints() > this.gameState.getDealerPoints()) {
            System.out.println("PLAYER has more points!\n");
        } else if (this.gameState.getPlayerPoints() == this.gameState.getDealerPoints()) {
            System.out.println("PLAYER and DEALER have the same points!\n");
        } else {
            System.out.println("DEALER has more points! \n");
        }

    }

    // MODIFIES: this
    // EFFECTS: Handles the player's choice to hit and updates the game state accordingly.
    private boolean playerHitRoute(Scanner scanner, boolean continueGame) {
        newGame.playerHits();

        if (!newGame.isPlayerBust()) {
            displayPlayerAndHiddenDealerHand();
        } else if (player.getPlayerHandSum() == 21) {
            displayPlayerAndDealerHand();
            System.out.println("\nPlayer hit 21! Player Wins! +1 point - PLAYER\n");

            this.gameState.setPlayerPoints(gameState.getPlayerPoints() + 1);
            this.gameState.setPlayerWins(gameState.getPlayerWins() + 1);

            continueGame = isContinueGame(scanner, continueGame);

        } else {
            displayPlayerAndDealerHand();
            System.out.println("\nPlayer Bust! House Wins! +1 point - DEALER\n");

            this.gameState.setDealerPoints(gameState.getDealerPoints() + 1);
            this.gameState.setPlayerLosses(gameState.getPlayerLosses() + 1);

            continueGame = isContinueGame(scanner, continueGame);
        }
        return continueGame;
    }

    // MODIFIES: this
    // EFFECTS: Handles the player's choice to stand and updates the game state accordingly.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private boolean playerStandRoute(Scanner scanner, boolean continueGame) {
        newGame.playerStands();

        if (newGame.isPlayerBust()) {
            displayPlayerAndDealerHand();
            System.out.println("\nPlayer Bust! House Wins! +1 point - DEALER\n");

            this.gameState.setDealerPoints(gameState.getDealerPoints() + 1);
            this.gameState.setPlayerLosses(gameState.getPlayerLosses() + 1);

            continueGame = isContinueGame(scanner, continueGame);

        } else if (newGame.isDealerBust()) {
            displayPlayerAndDealerHand();
            System.out.println("\nHouse Bust! Player Wins! +1 point - PLAYER\n");

            this.gameState.setPlayerPoints(gameState.getPlayerPoints() + 1);
            this.gameState.setPlayerWins(gameState.getPlayerWins() + 1);

            continueGame = isContinueGame(scanner, continueGame);

        } else if (newGame.isPlayerTie()) {
            displayPlayerAndDealerHand();
            System.out.println("\nIts a tie! No points!");

            continueGame = isContinueGame(scanner, continueGame);

        } else if (newGame.isPlayerWin()) {
            displayPlayerAndDealerHand();
            System.out.println("\nHouse has a lower hand! Player Wins! +1 point - PLAYER\n");

            this.gameState.setPlayerPoints(gameState.getPlayerPoints() + 1);
            this.gameState.setPlayerWins(gameState.getPlayerWins() + 1);

            continueGame = isContinueGame(scanner, continueGame);

        } else if (!newGame.isPlayerWin()) {
            displayPlayerAndDealerHand();
            System.out.println("\nPlayer has a lower hand! House Wins! +1 point - DEALER\n");

            this.gameState.setDealerPoints(gameState.getDealerPoints() + 1);
            this.gameState.setPlayerLosses(gameState.getPlayerLosses() + 1);

            continueGame = isContinueGame(scanner, continueGame);

        }
        return continueGame;
    }

    private boolean isContinueGame(Scanner scanner, boolean continueGame) {
        if (askPlayerIfTheyWantToContinue(scanner)) {
            startANewGame(false);
        } else {
            tallyPoints();
            continueGame = false;

            if (askPlayerToSave(scanner)) {

                try {
                    savePlayerFile(gameState);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save file!");
                }

                mainMenu();
            } else {
                mainMenu();
            }
        }
        return continueGame;
    }

    // EFFECTS: Asks the player if they want to continue with a new round.
    public boolean askPlayerIfTheyWantToContinue(Scanner scanner) {
        System.out.println("Continue with a new round? (y/n?)");
        String choice = scanner.next();
        choice = choice.toLowerCase();
        scanner.nextLine();

        if (choice.equals("y")) {
            return true;
        } else if (choice.equals("n")) {
            return false;
        } else {
            System.out.println("Please answer as yes (y) or no (n)\n");
            return askPlayerIfTheyWantToContinue(scanner);
        }
    }

    // EFFECTS: Asks the player if they want to save their session.
    public boolean askPlayerToSave(Scanner scanner) {
        System.out.println("Do you want to save your session? (y/n)");
        String choice = scanner.next();
        choice = choice.toLowerCase();
        scanner.nextLine();

        if (choice.equals("y")) {
            return true;
        } else if (choice.equals("n")) {
            return false;
        } else {
            System.out.println("Please answer as yes (y) or no (n)\n");
            return askPlayerToSave(scanner);
        }
    }

    // MODIFIES: jsonWriter
    // EFFECTS: Saves the game state to a file.
    public void savePlayerFile(GameState gameState) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(gameState);
        jsonWriter.close();
    }

    // MODIFIES: loadedGameState
    // EFFECTS: Loads the game state from a file and starts a new game with the loaded state.
    public void loadGame() {
        try {
            loadedGameState = jsonReader.read();
        } catch (IOException e) {
            System.out.println("No file to be read!");
        }

        System.out.println("\nFrom the latest save:");
        System.out.println("\nPLAYER POINTS : [" + loadedGameState.getPlayerPoints() + " point(s)]");
        System.out.println("DEALER POINTS : [" + loadedGameState.getDealerPoints() + " point(s)]");
        System.out.println("PLAYER WINS : [" + loadedGameState.getPlayerWins() + " win(s)]");
        System.out.println("PLAYER LOSSES : [" + loadedGameState.getPlayerLosses() + " loss(es)]");

        startANewGame(true);
    }

    // EFFECTS: Prints the dealer's hand with the first card hidden and the player's hand.
    public void displayPlayerAndHiddenDealerHand() {
        System.out.println("\nDEALER:");
        System.out.println(dealer.getDealersHandWithHiddenCard());
        System.out.println("\nPLAYER:");
        System.out.println(player.getPlayerHand());
    }

    // EFFECTS: Prints the complete dealer's hand and the player's hand.
    public void displayPlayerAndDealerHand() {
        System.out.println("\nDEALER:");
        System.out.println(dealer.getDealersHand());
        System.out.println("\nPLAYER:");
        System.out.println(player.getPlayerHand());
    }

}
