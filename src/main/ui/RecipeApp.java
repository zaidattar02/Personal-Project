package ui;


import model.MyState;
import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeApp {
    private ArrayList<Recipe> favorites;
    private Scanner input = new Scanner(System.in);
    private RecipeBook recipesList;
    private MyState ms;
    private static final String JSON_STORE = "./data/MyState.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Runs application
    public RecipeApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        ms = new MyState("Zaid's state");
        runRecipe();
    }

    private void runRecipe() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }

        System.out.println("\nGoodbye!");

    }

    public void init() {
        recipesList = new RecipeBook();
        favorites = new ArrayList<>();
    }


    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> print recipes");
        System.out.println("\tf -> filter");
        System.out.println("\ta -> add to favorites");
        System.out.println("\te -> edit recipes");
        System.out.println("\ts -> save Favorites and Edited Recipes to file");
        System.out.println("\tl -> load Favorites and Edited Recipes from file");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equals("r")) {
            printR();
        } else if (command.equals("f")) {
            filterRestriction();
        } else if (command.equals("a")) {
            favorite();
        } else if (command.equals("e")) {
            deleteIngredient();
        } else if (command.equals("s")) {
            saveMyState();
        } else if (command.equals("l")) {
            loadMyState();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public void deleteIngredient() {
        System.out.println("Which recipe number would you like to edit");
        recipesList.printRecipeNames();
        String recipeToEditNum = input.next();
        Recipe recipeToEdit = recipesList.getRecipeByNum(Integer.parseInt(recipeToEditNum));
        System.out.println("Which ingredient from the following would you like to remove");
        for (String ing : recipeToEdit.getIngredientsString()) {
            System.out.println(ing);
        }
        String ingredientToRemove = input.next();
        recipeToEdit.removeIngredient(ingredientToRemove);
        addEditedToState(recipeToEdit);
        System.out.println("Here is the updated recipe");
        recipeToEdit.printRecipe();
    }

    public void filterRestriction() {
        ArrayList<Recipe> filteredRecipes;
        filteredRecipes = new ArrayList<>(recipesList.getRecipeList());
        System.out.print("Enter your dietary restriction (halal/vegan)");
        String restr = input.next();
        recipesList.filterByRestriction(restr, filteredRecipes);
        System.out.print("Do you have a nut allergy (yes/no)");
        String allerg = input.next();
        recipesList.filterByAllergy(allerg, filteredRecipes);
        System.out.println("Found the following recipes based on your dietary restrictions:");
        for (Recipe r : filteredRecipes) {
            System.out.println(String.valueOf(r.getRecipeNumber()) + ':' + r.getRecipeName());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds fav recipe to state
    private void addFavToState(Recipe r) {
        if (!ms.getFav().contains(r)) {
            ms.addFavorites(r);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds edited recipe to state
    private void addEditedToState(Recipe r) {
        ms.addEdited(r);
    }


    public void favorite() {
        System.out.print("Enter the recipe number you want to add to favorites \n");
        recipesList.printRecipeNames();
        String userFav = input.next();
        Recipe favRecipe = recipesList.getRecipeByNum(Integer.parseInt(userFav));
        if (favorites.contains(favRecipe)) {
            System.out.println("This recipe is already in your favorites");
        } else {
            favorites.add(favRecipe);
            addFavToState(favRecipe);
            System.out.println("Added to favorites!");
        }

    }

    public void printR() {
        recipesList.printRecipes();
    }

    // EFFECTS: saves the MyState to file
    private void saveMyState() {
        try {
            jsonWriter.open();
            jsonWriter.write(ms);
            jsonWriter.close();
            System.out.println("Saved " + ms.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads MyState from file
    private void loadMyState() {
        try {
            ms = jsonReader.read();
            System.out.println("Loaded " + ms.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
