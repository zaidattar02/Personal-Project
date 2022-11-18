package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import model.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyState ms = new MyState("My State");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMyState() {
        try {
            RecipeBook ms = new RecipeBook();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMyState.json");
            writer.open();
            writer.write(ms);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMyState.json");
            ms = reader.read();
//            assertEquals("My State", ms.getName());
//            assertEquals(0, ms.numFav());
//            assertEquals(0, ms.numEdited());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMyState() {
        try {
            MyState ms = new MyState("My State");
            ArrayList<Ingredient> ingList = new ArrayList<>();
            ArrayList<String> ingredientNames = new ArrayList<String>(
                    Arrays.asList("chicken", "pasta", "butter", "oregano", "basil", "parmesan"));
            for (String i : ingredientNames) {
                Ingredient ing;
                ing = new Ingredient(i);
                ingList.add(ing);
            }
            Recipe r = new Recipe("Chicken Alfredo Pasta",
                    true, false, false, ingList, 1);
            ms.addFavorites(r);
            ms.addEdited(r);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMyState.json");
            writer.open();
            writer.write(ms);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralMyState.json");
//            ms = reader.read();
            assertEquals("My State", ms.getName());
            assertEquals(1, ms.numFav());
            assertEquals(1,ms.numEdited());
            checkFav("Chicken Alfredo Pasta", true, false, false, 1, ingList, r);
            //checkEdited()

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}





