package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

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

    private GameLogic newGame;
    private PlayersHand player;
    private Dealer dealer;
    private int playerPoints = 0;
    private int dealerPoints = 0;
    private int playerWins = 0;
    private int playerLosses = 0;
    private GameState gameState;
    private GameState loadedGameState;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    Scanner inputScanner = new Scanner(System.in);

    // EFFECTS: Initializes a new BlackJackApp object and starts the user interface.
    public BlackJackApp() {
        jsonWriter = new JsonWriter("./data/Saved_Game.json");
        jsonReader = new JsonReader("./data/Saved_Game.json");

        mainMenu();
    }

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

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void playerHitOrStandLoop(Scanner scanner) {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("Do you want to Hit or Stand?");
            String choice = scanner.next();
            choice = choice.toLowerCase();
            scanner.nextLine();

            if (choice.equals("hit")) {
                newGame.playerHits();

                if (!newGame.isPlayerBust()) {
                    displayPlayerAndHiddenDealerHand();
                } else if (player.getPlayerHandSum() == 21) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nPlayer hit 21! Player Wins! +1 point - PLAYER\n");

                    playerPoints++;
                    playerWins++;

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

                } else {
                    displayPlayerAndDealerHand();
                    System.out.println("\nPlayer Bust! House Wins! +1 point - DEALER\n");

                    dealerPoints++;
                    playerLosses++;

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
                }

            } else if (choice.equals("stand")) {
                newGame.playerStands();

                if (newGame.isPlayerBust()) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nPlayer Bust! House Wins! +1 point - DEALER\n");

                    dealerPoints++;
                    playerLosses++;

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

                } else if (newGame.isDealerBust()) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nHouse Bust! Player Wins! +1 point - PLAYER\n");

                    playerPoints++;
                    playerWins++;

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

                } else if (newGame.isPlayerTie()) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nIts a tie! No points!");

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

                } else if (newGame.isPlayerWin()) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nHouse has a lower hand! Player Wins! +1 point - PLAYER\n");

                    playerPoints++;
                    playerWins++;

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

                } else if (!newGame.isPlayerWin()) {
                    displayPlayerAndDealerHand();
                    System.out.println("\nPlayer has a lower hand! House Wins! +1 point - DEALER\n");

                    dealerPoints++;
                    playerLosses++;

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

                }

            } else {
                System.out.println("Invalid choice. Please enter Hit or Stand.\n");
            }
        }

    }

    public void tallyPoints() {
        gameState.setPlayerPoints(gameState.getPlayerPoints() + playerPoints);
        gameState.setDealerPoints(gameState.getDealerPoints() + dealerPoints);
        gameState.setPlayerWins(gameState.getPlayerWins() + playerWins);
        gameState.setPlayerLosses(gameState.getPlayerLosses() + playerLosses);

        System.out.println("\nPLAYER POINTS : [" + Integer.toString(playerPoints) + " point(s)]");
        System.out.println("DEALER POINTS : [" + Integer.toString(dealerPoints) + " point(s)]");
        System.out.println("PLAYER WINS : [" + Integer.toString(playerWins) + " win(s)]");
        System.out.println("PLAYER LOSSES : [" + Integer.toString(playerLosses) + " loss(es)]");

        if (playerPoints > dealerPoints) {
            System.out.println("PLAYER has more points!\n");
        } else if (playerPoints == dealerPoints) {
            System.out.println("PLAYER and DEALER have the same points!\n");
        } else {
            System.out.println("DEALER has more points! \n");
        }

    }

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

    public void savePlayerFile(GameState gameState) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(gameState);
        jsonWriter.close();
    }

    public void loadGame() {
        try {
            loadedGameState = jsonReader.read();
        } catch (IOException e) {
            System.out.println("No file to be read!");
        }

        System.out.println("\nFrom the latest save:");
        System.out.println("\nPLAYER POINTS : [" + Integer.toString(loadedGameState.getPlayerPoints()) + " point(s)]");
        System.out.println("DEALER POINTS : [" + Integer.toString(loadedGameState.getDealerPoints()) + " point(s)]");
        System.out.println("PLAYER WINS : [" + Integer.toString(loadedGameState.getPlayerWins()) + " win(s)]");
        System.out.println("PLAYER LOSSES : [" + Integer.toString(loadedGameState.getPlayerLosses()) + " loss(es)]");

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
