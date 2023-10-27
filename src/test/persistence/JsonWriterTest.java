package persistence;

import model.GameState;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {

    @Test
    void testWriter() {
        try {
            GameState gameState = new GameState();
            gameState.setPlayerWins(3);
            gameState.setPlayerLosses(2);
            gameState.setPlayerPoints(150);
            gameState.setDealerPoints(120);

            JsonWriter writer = new JsonWriter("./data/testWriter.json");
            writer.open();
            writer.write(gameState);
            writer.close();

            Scanner scanner = new Scanner(new File("./data/testWriter.json"));
            String jsonContent = scanner.useDelimiter("\\Z").next();
            scanner.close();

            JSONObject json = new JSONObject(jsonContent);

            assertEquals(3, json.getInt("Player's Wins"));
            assertEquals(2, json.getInt("Player's Losses"));
            assertEquals(150, json.getInt("Player's Points"));
            assertEquals(120, json.getInt("Dealer's Points"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            GameState gameState = new GameState();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write(gameState);
            writer.close();
            fail("FileNotFoundException was expected");
        } catch (IOException e) {
            // pass
        }
    }
}

