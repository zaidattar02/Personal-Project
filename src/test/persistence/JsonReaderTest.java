package persistence;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    private ArrayList<Recipe> emptyFav = new ArrayList<>();

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeBook ms = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyMyState() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMyState.json");

        try {
            RecipeBook ms = reader.read();
            //assertEquals("[1:Chicken Alfredo Pasta, 2:Vegetable Fajitas, 3:Mongolian Pork, 4:Broccoli Pasta]", ms.getRecipeNames());
            assertEquals(emptyFav, ms.getFavRecipes());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMyState.json");
        try {
            RecipeBook ms = reader.read();
            //assertEquals("My State", ms.getName());
//            List<Recipe> favorites = ms.getFav();
//            List<Recipe> edited = ms.getEdited();
            assertEquals(1, ms.getFavRecipes().size());
//            assertEquals(1, edited.size());
//            assertEquals(favorites,ms.getFav());
//            assertEquals(edited,ms.getEdited());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadRecipe() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMyState.json");
        try {
            RecipeBook ms = reader.read();
            assertEquals(1, ms.getFavRecipes().size());
            //assertEquals();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
        }

