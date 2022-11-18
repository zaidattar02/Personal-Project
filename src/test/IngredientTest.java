import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import model.Ingredient;

import java.util.Objects;

class IngredientTest {
    private Ingredient ing;
    private Ingredient copyOfIng;
    private Ingredient butter;
    private String name;

    @BeforeEach
    void setUp() {
        ing = new Ingredient("garlic");
        butter = new Ingredient("butter");
        name = ing.getIngredientName();
        copyOfIng = ing;

    }

    @Test
    void testConstructor() {
        assertEquals("garlic", ing.getIngredientName());
    }

    @Test
    void testEquals(){
        assertEquals(ing,copyOfIng);
        assertNotEquals(ing,butter);
    }

    @Test
    void testIngHashCode(){
        assertEquals(ing.hashCode(), Objects.hash(name));
    }


}
