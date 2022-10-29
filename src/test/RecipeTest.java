import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Recipe;

class RecipeTest {
    private Recipe r;
    private ArrayList<Ingredient> vegIngr;


    @BeforeEach
    void setUp() {
        vegIngr = new ArrayList<>();
        ArrayList<String> ingredientNames = new ArrayList<>(
                Arrays.asList("garlic", "soysauce", "bellpepper", "paprika", "onion", "mushrooms", "tortilla"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            vegIngr.add(ing);
        }
        r = new Recipe("Vegetable Fajitas", true, true, false, vegIngr, 2);
    }

    @Test
    void testConstructor() {
        assertEquals("Vegetable Fajitas", r.getRecipeName());
        ArrayList<Ingredient> ingTest = new ArrayList<>();
        ArrayList<String> ingNames = new ArrayList<>(
                Arrays.asList("garlic", "soysauce", "bellpepper", "paprika", "onion", "mushrooms", "tortilla"));
        for (String i : ingNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingTest.add(ing);
        }
        assertEquals(7, vegIngr.size());
        assertTrue(r.isRecipeHalal());
        assertTrue(r.isRecipeVegan());
        assertFalse(r.doesRecipeContainNuts());
        assertEquals(2, r.getRecipeNumber());
    }

    @Test
    void testGetIngredientsString() {
        ArrayList<String> ingredientNames = new ArrayList<>(
                Arrays.asList("garlic", "soysauce", "bellpepper", "paprika", "onion", "mushrooms", "tortilla"));
        assertEquals(r.getIngredientsString(), ingredientNames);

    }


    @Test
    void testRemoveIngredient() {
        ArrayList<String> ingString = r.getIngredientsString();
        assertTrue(ingString.contains("garlic"));
        r.removeIngredient("garlic");
        ArrayList<String> ingRemovedString = r.getIngredientsString();
        assertFalse(ingRemovedString.contains("garlic"));
    }

}
