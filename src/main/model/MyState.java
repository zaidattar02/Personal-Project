package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a state having a list of favorites and edited recipes
public class MyState implements Writable {
    private String name;
    private ArrayList<Recipe> fav;
    private ArrayList<Recipe> edited;

    // Constructs a state with a name and an empty list of favorites and edited recipes
    public MyState(String name) {
        this.name = name;
        fav = new ArrayList<>();
        edited = new ArrayList<>();
    }

    public int numFav() {
        return fav.size();
    }

    public int numEdited() {
        return edited.size();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    //EFFECTS: adds favorite recipe to this state
    public void addFavorites(Recipe r) {
        fav.add(r);
    }

    // EFFECTS: returns list of fav in this state
    public ArrayList<Recipe> getFav() {
        return fav;
    }

    // MODIFIES: this
    //EFFECTS: adds edited recipe to this state
    public void addEdited(Recipe re) {
        edited.add(re);
    }

    // EFFECTS: returns list of edited recipes in this state
    public ArrayList<Recipe> getEdited() {
        return edited;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("fav", favToJson());
        json.put("edited", editedToJson());

        return json;
    }

    // EFFECTS: returns favorites in this state as a JSON array
    private JSONArray favToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe re : fav) {
            jsonArray.put(re.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns edited recipes in this state as a JSON array
    private JSONArray editedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : edited) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }

}
