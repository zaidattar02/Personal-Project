package model;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a preset of recipes of type Recipe
public class RecipeBook implements Writable {
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
        ArrayList<Ingredient> brocIngr = new ArrayList<>();
        createBrocIngredientList(brocIngr);
        createChkIngredientList(chkingr);
        createPrkIngredientList(prkingr);
        createVegIngredientList(vegingr);
        recipes.add(new Recipe("Chicken Alfredo Pasta",
                true, false, false, chkingr, 1));
        recipes.add(new Recipe("Vegetable Fajitas",
                true, true, false, vegingr, 2));
        recipes.add(new Recipe("Mongolian Pork",
                false, false, true, prkingr, 3));
        recipes.add(new Recipe("Broccoli Pasta", true, true, true, brocIngr, 4));
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



    //EFFECTS: returns recipe names ArrayList of type strings
    public ArrayList<String> getRecipeNames() {
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
        return recipeNames;
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
     * EFFECTS: creates new list of type string and loops through it to add each ingredient into ingredient list
     * of type Ingredient.
     */
    public void createBrocIngredientList(ArrayList<Ingredient> ingList) {
        ArrayList<String> ingredientNames = new ArrayList<String>(
                Arrays.asList("pasta", "broccoli", "onion", "garlic", "nuts", "beans"));
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


    //EFFECTS: returns favorites as a list of Recipes
    public ArrayList<Recipe> getFavRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r : this.recipes) {
            if (r.isFavourite()) {
                recipes.add(r);
            }
        }
        return recipes;
    }

    public ArrayList<Recipe> getHalalRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r : this.recipes) {
            if (r.isRecipeHalal()) {
                recipes.add(r);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered Favorites for Halal Recipes"));
        return recipes;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fav", favToJson());

        return json;
    }

    // EFFECTS: returns favorites in this state as a JSON array
    public JSONArray favToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe re : getFavRecipes()) {
            jsonArray.put(re.toJson());
        }
        return jsonArray;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    //MODIFIES: Recipe
    //EFFECTS: Acts as a toggle by switching the recipe's isFavourtie field from true to false or false to true
    public void toggleFav(int recipeNumber) {
        Recipe r = recipes.get(recipeNumber - 1);
        if (r.isFavourite()) {
            r.setFavourite(false);
            EventLog.getInstance().logEvent(new Event("Removed " + r.getRecipeName() + " from favorites"));
        } else if (!r.isFavourite()) {
            r.setFavourite(true);
            EventLog.getInstance().logEvent(new Event("Added " + r.getRecipeName() + " to favorites"));
        }
    }
}










