package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMyState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: ms
    // EFFECTS: parses mystate from JSON object and returns it
    private MyState parseMyState(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MyState ms = new MyState(name);
        addFavorites(ms,jsonObject);
        addEdited(ms,jsonObject);
        return ms;
    }

    //MODIFIES: This
    //EFFECTS: creates new recipe object with jsonObject parameters
    private Recipe readRecipe(JSONObject jsonObject) {

        String name = jsonObject.getString("name");
        Integer num = jsonObject.getInt("number");
        Boolean halal = jsonObject.getBoolean("halal");
        Boolean vegan = jsonObject.getBoolean("vegan");
        Boolean nuts = jsonObject.getBoolean("nuts");

        ArrayList<Ingredient> ingredients = readIngredients(jsonObject.getJSONArray("Ingredients"));

        Recipe r = new Recipe(name, halal, vegan, nuts, ingredients, num);
        return r;
    }

    //EFFECTS: loops through each element in JSONArray and converts it into an object
    private ArrayList<Ingredient> readIngredients(JSONArray jsonArray) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ingredients.add(readIngredient(jsonObject));
        }
        return ingredients;
    }

    //MODIFIES: This
    //EFFECTS: Creates new Ingredient with JsonObject Ingredient Name and returns it
    private Ingredient readIngredient(JSONObject jsonObject) {
        String ingredientName = jsonObject.getString("name");
        Ingredient ingredient = new Ingredient(ingredientName);
        return ingredient;
    }


    // MODIFIES: ms
    // EFFECTS: parses fav from JSON object and adds them to MyState
    private void addFavorites(MyState ms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fav");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            Recipe r = readRecipe(jsonObject);
            ms.addFavorites(r);
            //for loop with call to read recipe and that recipe using addfavorites
            // don't need addFav for this or edited
        }
    }

    // MODIFIES: ms
    // EFFECTS: parses edited from JSON object and adds them to MyState
    private void addEdited(MyState ms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("edited");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            Recipe r = readRecipe(jsonObject);
            ms.addEdited(r);
        }
    }

}