package model;

import java.util.ArrayList;
import java.util.Arrays;


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

    public void printRecipeNames() {
        for (Recipe r : recipes) {
            System.out.println(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
    }

    public ArrayList<String> getRecipeNames() {
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
        return recipeNames;
    }

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

    public void createChkIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("chicken", "pasta", "butter", "oregano", "basil", "parmesan"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    public void createPrkIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("pork", "garlic", "ginger", "sugar", "soysauce"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    public void createVegIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("garlic", "soysauce", "bellpepper", "paprika", "onion", "mushrooms", "tortilla"));
        for (String i : ingredientNames) {
            Ingredient ing;
            ing = new Ingredient(i);
            ingList.add(ing);
        }
    }

    public Recipe getRecipeByNum(int num) {
        Recipe recipeMatch = null;
        for (Recipe r : recipes) {
            if (r.getRecipeNumber() == num) {
                recipeMatch = r;
                break;
            }
        }
        System.out.println("Added to Favorites");
        return recipeMatch;
    }


}









