import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import model.Ingredient;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Recipe;

class RecipeTest {
    private Recipe r;
    private Recipe recipe2;
    private Recipe copyOfR;
    private ArrayList<Ingredient> vegIngr;
    private ArrayList<Ingredient> chickIngr;
    private String name;
    private boolean isVegan;
    private boolean isHalal;
    private int recipeNumber;
    private boolean containsNuts;
    private ArrayList<Ingredient> ingredients;


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

        copyOfR = r;

        chickIngr = new ArrayList<>();
        ArrayList<String> ingredientNames2 = new ArrayList<String>(
                Arrays.asList("chicken", "pasta", "butter", "oregano", "basil", "parmesan"));
        for (String i : ingredientNames2) {
            Ingredient ing;
            ing = new Ingredient(i);
            chickIngr.add(ing);
        }
        recipe2 = new Recipe("Chicken Alfredo Pasta",true,false,false,chickIngr,1);
        name = recipe2.getRecipeName();
        isHalal = recipe2.isRecipeHalal();
        isVegan = recipe2.isRecipeVegan();
        containsNuts = recipe2.doesRecipeContainNuts();
        recipeNumber = recipe2.getRecipeNumber();
        ingredients = chickIngr;

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
    void testEquals(){
        assertEquals(r,copyOfR);
        assertNotEquals(r,recipe2);
    }

    @Test
    void testHashEquals(){
        assertEquals(recipe2.hashCode(), Objects.hash(name, isVegan, isHalal, containsNuts, ingredients, recipeNumber));
    }



    @Test
    void testRemoveIngredient() {
        ArrayList<String> ingString = r.getIngredientsString();
        assertTrue(ingString.contains("garlic"));
        r.removeIngredient("garlic");
        ArrayList<String> ingRemovedString = r.getIngredientsString();
        assertFalse(ingRemovedString.contains("garlic"));
    }

    @Test
    void testPrintRecipe(){
        assertFalse(recipe2.getRecipeName().isEmpty());
    }


    @Test
    void testSetFav(){
        recipe2.setFavourite(true);
        assertTrue(recipe2.isFavourite());
        recipe2.setFavourite(false);
        assertFalse(recipe2.isFavourite());
    }

}
