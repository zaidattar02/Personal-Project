package ui;

import model.Ingredient;
import model.Recipe;
import model.RecipeBook;


import java.util.ArrayList;
import java.util.Scanner;

public class RecipeApp {
    private ArrayList<Recipe> favorites;
    private Scanner input = new Scanner(System.in);
    private RecipeBook recipesList;


    public RecipeApp() {
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

    public void favorite() {
        System.out.print("Enter the recipe number you want to add to favorites \n");
        recipesList.printRecipeNames();
        String userFav = input.next();
        Recipe favRecipe = recipesList.getRecipeByNum(Integer.parseInt(userFav));
        favorites.add(favRecipe);
    }

    public void printR() {
        recipesList.printRecipes();
    }


}
