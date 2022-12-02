package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import model.MyState;
import model.Recipe;
import model.RecipeBook;
import model.Ingredient;

import java.util.ArrayList;

public class JsonTest {
    protected void checkFav(String name, boolean halal, boolean nuts, boolean vegan, int num,
                            ArrayList<Ingredient>ingredients,Recipe r){
        assertEquals(name,r.getRecipeName());
        assertEquals(halal,r.isRecipeHalal());
        assertEquals(vegan,r.isRecipeVegan());
        assertEquals(nuts,r.doesRecipeContainNuts());
        assertEquals(num,r.getRecipeNumber());
        assertEquals(num,r.getRecipeNumber());
        assertEquals(ingredients,r.getIngredients());
    }



    protected void checkEdited(String name, boolean halal, boolean nuts, boolean vegan, int num,
                               ArrayList<Ingredient>ingredients,Recipe r){
        assertEquals(name,r.getRecipeName());
        assertEquals(halal,r.isRecipeHalal());
        assertEquals(vegan,r.isRecipeVegan());
        assertEquals(nuts,r.doesRecipeContainNuts());
        assertEquals(num,r.getRecipeNumber());
        assertEquals(num,r.getRecipeNumber());
        assertEquals(ingredients,r.getIngredients());
    }
}
