import model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RecipeBook;
import model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RecipeBookTest {

    private RecipeBook firstBook;
    private ArrayList<Recipe> recipes;

    @BeforeEach
    void setUp() {
        firstBook = new RecipeBook();
        recipes = firstBook.getRecipeList();
    }

    @Test
    void testConstructor() {
        ArrayList<String> recipeNames = new ArrayList<>(
                Arrays.asList("1:Chicken Alfredo Pasta", "2:Vegetable Fajitas", "3:Mongolian Pork", "4:Broccoli Pasta"));
        ArrayList<String> rNames = firstBook.getRecipeNames();
        assertEquals(rNames, recipeNames);
    }

    @Test
    void testFilterByAllergy() {
        ArrayList<Recipe> filteredRecipes = firstBook.getRecipeList();
        firstBook.filterByAllergy("yes", filteredRecipes);
        for (Recipe fr : filteredRecipes) {
            assertNotEquals("3:Mongolian Pork", fr.getRecipeName());
        }
    }

    @Test
    void testGetRecipes() {
        ArrayList<Recipe> recipes = firstBook.getRecipes();
        assertEquals(4,recipes.size());
    }

    @Test
    void testToggleFav() {
       firstBook.toggleFav(1);
       assertTrue(recipes.get(0).isFavourite());
       assertFalse(recipes.get(1).isFavourite());
    }

    @Test
    void testFilterByRestriction() {
        ArrayList<Recipe> filteredRecipes = firstBook.getRecipeList();
        firstBook.filterByRestriction("vegan", filteredRecipes);
        for (Recipe fr : filteredRecipes) {
            assertNotEquals("3:Mongolian Pork", fr.getRecipeName());
            assertNotEquals("1:Chicken Alfredo Pasta", fr.getRecipeName());
        }
        filteredRecipes = firstBook.getRecipeList();
        firstBook.filterByRestriction("halal", filteredRecipes);
        for (Recipe fr : filteredRecipes) {
            assertNotEquals("3:Mongolian Pork", fr.getRecipeName());
        }
    }
    @Test
    void testGetHalalRecipes(){
        ArrayList<Recipe> halalRecipes = firstBook.getHalalRecipes();
        for (Recipe r : halalRecipes){
            assertNotEquals(r.getRecipeName(),"Mongolian Pork");
        }
    }

    @Test
    void testGetRecipeByNum() {
        Recipe chickenAlfredoNumber = firstBook.getRecipeByNum(1);
        assertEquals(chickenAlfredoNumber, recipes.get(0));
    }


}
