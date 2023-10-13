package ui;

import java.util.Scanner;

import model.Dealer;
import model.GameLogic;
import model.PlayersHand;

    // The BlackJackApp class serves as the user interface for a simplified Blackjack game.
    // It allows the player to interact with the game, make decisions (Hit or Stand), and displays game progress.

public class BlackJackApp {
    static String STARTING_MESSAGE = "!! WELCOME BLACKJACK ROYALE !!";
    private GameLogic newGame;
    private PlayersHand player;
    private Dealer dealer;

    Scanner inputScanner = new Scanner(System.in);

    // EFFECTS: Initializes a new BlackJackApp object and starts the user interface.
    public BlackJackApp() {
        userInterface();
    }

    // MODIFIES: this, newGame, player, dealer
    // EFFECTS: Initializes a new game, displays starting message, and allows player interaction.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void userInterface() {
        System.out.println(STARTING_MESSAGE);

        newGame = new GameLogic();

        player = newGame.getPlayerHand();
        dealer = newGame.getDealer();

        displayPlayerAndHiddenDealerHand();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to Hit or Stand?");
            String choice = scanner.next();
            scanner.nextLine();

            if (choice.equals("Hit")) {
                newGame.playerHits();

                if (!newGame.isPlayerBust()) {
                    displayPlayerAndHiddenDealerHand();
                } else if (player.getPlayerHandSum() == 21) {
                    displayPlayerAndDealerHand();
                    System.out.println("Player hit 21! Player Wins!");
                    break;
                } else {
                    displayPlayerAndDealerHand();
                    System.out.println("Player Bust! House Wins!");
                    break;
                }

            } else if (choice.equals("Stand")) {
                newGame.playerStands();

                if (newGame.isPlayerBust()) {
                    displayPlayerAndDealerHand();
                    System.out.println("Player Bust! House Wins!");
                    break;
                } else if (newGame.isDealerBust()) {
                    displayPlayerAndDealerHand();
                    System.out.println("House Bust! Player Wins!");
                    break;
                } else if (newGame.isPlayerTie()) {
                    displayPlayerAndDealerHand();
                    System.out.println("Its a tie");
                    break;
                } else if (newGame.isPlayerWin()) {
                    displayPlayerAndDealerHand();
                    System.out.println("House has a lower hand! Player Wins!");
                    break;
                } else if (!newGame.isPlayerWin()) {
                    displayPlayerAndDealerHand();
                    System.out.println("Player has a lower hand! House Wins!");
                    break;
                }

            } else {
                System.out.println("Invalid choice. Please enter Hit or Stand.");
            }
        }
    }

    // EFFECTS: Prints the dealer's hand with the first card hidden and the player's hand.
    public void displayPlayerAndHiddenDealerHand() {
        System.out.println("DEALER:");
        System.out.println(dealer.getDealersHandWithHiddenCard());
        System.out.println("PLAYER:");
        System.out.println(player.getPlayerHand());
    }

    // EFFECTS: Prints the complete dealer's hand and the player's hand.
    public void displayPlayerAndDealerHand() {
        System.out.println("DEALER:");
        System.out.println(dealer.getDealersHand());
        System.out.println("PLAYER:");
        System.out.println(player.getPlayerHand());
    }

}
