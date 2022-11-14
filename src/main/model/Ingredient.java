package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ingredient)) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}

