package model;

//Represents an Ingredient having an inredient name
public class Ingredient {
    private String name;


    public Ingredient(String ingName) {
        this.name = ingName;
    }

    public String getIngredientName() {
        return this.name;
    }

}

