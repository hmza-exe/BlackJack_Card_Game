package persistence;

import model.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads GameState from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads GameState from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GameState from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        int playerWins = jsonObject.optInt("Player's Wins", 0);
        int playerLosses = jsonObject.optInt("Player's Losses", 0);
        int playerPoints = jsonObject.optInt("Player's Points", 0);
        int dealerPoints = jsonObject.optInt("Dealer's Points", 0);

        GameState gameState = new GameState();
        gameState.setPlayerPoints(playerPoints);
        gameState.setDealerPoints(dealerPoints);
        gameState.setPlayerWins(playerWins);
        gameState.setPlayerLosses(playerLosses);

        return gameState;
    }

}


