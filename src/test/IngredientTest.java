import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Ingredient;

class IngredientTest {
    private Ingredient ing;
    private String name;

    @BeforeEach
    void setUp() {
        ing = new Ingredient("garlic");
    }

    @Test
    void testConstructor() {
        assertEquals("garlic", ing.getIngredientName());
    }

}
