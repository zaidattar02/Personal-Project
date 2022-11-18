package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//public class JsonReaderTest {

//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
////            MyState ms = reader.read();
//            fail("IOException expected");
////        } catch (IOException e) {
//            // pass
//        }
//    }

//    @Test
//    void testReaderEmptyMyState() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyMyState.json");
//        try {
//            MyState ms = reader.read();
//            assertEquals("My State", ms.getName());
//            assertEquals(0, ms.numFav());
//            assertEquals(0, ms.numEdited());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

//    @Test
//    void testReaderGeneralWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralMyState.json");
//        try {
////            MyState ms = reader.read();
//            assertEquals("My State", ms.getName());
//            List<Recipe> favorites = ms.getFav();
//            List<Recipe> edited = ms.getEdited();
//            assertEquals(1, favorites.size());
//            assertEquals(1, edited.size());
//            assertEquals(favorites,ms.getFav());
//            assertEquals(edited,ms.getEdited());
//
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

//        }
