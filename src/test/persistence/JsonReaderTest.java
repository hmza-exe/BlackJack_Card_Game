package persistence;

import model.GameState;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReader() {
        try {
            PrintWriter writer = new PrintWriter("./data/testReader.json");
            JSONObject json = new JSONObject();
            json.put("Player's Wins", 3);
            json.put("Player's Losses", 2);
            json.put("Player's Points", 150);
            json.put("Dealer's Points", 120);
            writer.println(json.toString());
            writer.close();

            JsonReader reader = new JsonReader("./data/testReader.json");
            GameState gameState = reader.read();

            assertEquals(3, gameState.getPlayerWins());
            assertEquals(2, gameState.getPlayerLosses());
            assertEquals(150, gameState.getPlayerPoints());
            assertEquals(120, gameState.getDealerPoints());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameState gameState = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFile() {
        try {
            PrintWriter writer = new PrintWriter("./data/emptyTestReader.json");
            writer.println("{}"); // Write an empty JSON object
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyTestReader.json");
            GameState gameState = reader.read();

            assertEquals(0, gameState.getPlayerWins());
            assertEquals(0, gameState.getPlayerLosses());
            assertEquals(0, gameState.getPlayerPoints());
            assertEquals(0, gameState.getDealerPoints());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

