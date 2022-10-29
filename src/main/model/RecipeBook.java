package model;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a preset of recipes of type Recipe
public class RecipeBook {
    private ArrayList<Recipe> recipes; //creates an ArrayList of type Recipe called recipes.

    /*
     * REQUIRES: recipes list is not null
     * EFFECTS: recipes list is assigned a list of recipe(s) with each recipe assigned its ingredients list
     * and boolean values.
     */
    public RecipeBook() {
        this.recipes = new ArrayList<>();
        ArrayList<Ingredient> chkingr = new ArrayList<>();
        ArrayList<Ingredient> prkingr = new ArrayList<>();
        ArrayList<Ingredient> vegingr = new ArrayList<>();
        createChkIngredientList(chkingr);
        createPrkIngredientList(prkingr);
        createVegIngredientList(vegingr);
        recipes.add(new Recipe("Chicken Alfredo Pasta",
                true, false, false, chkingr, 1));
        recipes.add(new Recipe("Vegetable Fajitas",
                true, true, false, vegingr, 2));
        recipes.add(new Recipe("Mongolian Pork",
                false, false, true, prkingr, 3));
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

    /*
     * REQUIRES: recipes list is not null
     * MODIFIES: this
     * EFFECTS: filter list of recipes based on user input
     */
    public void filterByAllergy(String command, ArrayList<Recipe> frecipe) {
        int i = frecipe.size() - 1;
        while (i >= 0) {
            if (command.equals("yes") && frecipe.get(i).doesRecipeContainNuts()) {
                frecipe.remove(frecipe.get(i));
            }
            --i;
        }
    }

    /*
     * REQUIRES: recipes list is not null
     * MODIFIES: this
     * EFFECTS: filter list of recipes based on user input
     */
    public void filterByRestriction(String command, ArrayList<Recipe> frecipe) {
        int i = frecipe.size() - 1;
        while (i >= 0) {
            if (command.equals("halal") && !frecipe.get(i).isRecipeHalal()) {
                frecipe.remove(frecipe.get(i));
            } else if (command.equals("vegan") && !frecipe.get(i).isRecipeVegan()) {
                frecipe.remove(frecipe.get(i));
            }
            --i;
        }
    }

    //EFFECTS: loops through each recipe in list of recipes and prints its name using method getRecipeName
    public void printRecipeNames() {
        for (Recipe r : recipes) {
            System.out.println(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
    }

    //EFFECTS: returns recipe names ArrayList of type strings
    public ArrayList<String> getRecipeNames() {
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
        return recipeNames;
    }

    //EFFECTS: loops through each recipe in list of recipes and prints out its name and list of ingredients
    public void printRecipes() {
        for (Recipe r : recipes) {
            System.out.println("Recipe " + r.getRecipeName());
            System.out.println("Ingredients: ");
            ArrayList<Ingredient> ings = r.getIngredients();
            for (int i = 0; i < ings.size(); i++) {
                System.out.println(ings.get(i).getIngredientName());
            }
            System.out.println(" ");
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: creates new list of type string and loops through it to add each ingredient into ingredient list
     * of type Ingredient.
     */
    public void createChkIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("chicken", "pasta", "butter", "oregano", "basil", "parmesan"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates new list of type string and loops through it to add each ingredient into ingredient list
     * of type Ingredient.
     */
    public void createPrkIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("pork", "garlic", "ginger", "sugar", "soysauce"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates new list of type string and loops through it to add each ingredient into ingredient list
     * of type Ingredient.
     */
    public void createVegIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("garlic", "soysauce", "bellpepper", "paprika", "onion", "mushrooms", "tortilla"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loops through each recipe in list of recipes and returns the number associated with that recipe.
     */
    public Recipe getRecipeByNum(int num) {
        Recipe recipeMatch = null;
        for (Recipe r : recipes) {
            if (r.getRecipeNumber() == num) {
                recipeMatch = r;
                break;
            }
        }
        return recipeMatch;
    }


}









