import model.Event;
import model.EventLog;
import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private EventLog theLog;
    private RecipeBook recipeBook;


    private ArrayList<Recipe> recipes;

    @BeforeEach
    void setUp(){
        recipeBook = new RecipeBook();
        recipes = recipeBook.getRecipes();
        theLog = EventLog.getInstance();
        theLog.clear();
    }

    @Test
    void testAddingChickenToFav(){
        recipeBook.toggleFav(1);
        List<Event> eventList = new ArrayList<>();
        for (Event e : theLog){
            eventList.add(e);
        }
        assertEquals("Added Chicken Alfredo Pasta to favorites",eventList.get(1).getDescription());
    }

    @Test
    void testRemovingChickenFromFav(){
        recipeBook.getRecipeByNum(1).setFavourite(true);
        recipeBook.toggleFav(1);
        List<Event> eventList = new ArrayList<>();
        for (Event e : theLog){
            eventList.add(e);
        }
        assertEquals("Removed Chicken Alfredo Pasta from favorites",eventList.get(1).getDescription());
    }

    @Test
    void testCheckIfChickIsVegan(){
        recipeBook.getRecipeByNum(1).isRecipeVegan();
        List<Event> eventList = new ArrayList<>();
        for (Event e : theLog){
            eventList.add(e);
        }
        assertEquals("Checking if Chicken Alfredo Pasta in recipe book is Vegan",eventList.get(1).getDescription());
    }

    @Test
    void testCheckIfChickIsHalal(){
        recipeBook.getHalalRecipes();
        List<Event> eventList = new ArrayList<>();
        for (Event e : theLog){
            eventList.add(e);
        }
        assertEquals("Filtered Favorites for Halal Recipes",eventList.get(1).getDescription());
    }






}
