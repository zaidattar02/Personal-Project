package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represents an Ingredient having an ingredient name
public class Ingredient implements Writable {
    private String name;

    //EFFECTS: Contructs a new Ingredient with name set ingName
    public Ingredient(String ingName) {
        this.name = ingName;
    }

    public String getIngredientName() {
        return this.name;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}

