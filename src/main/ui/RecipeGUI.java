package ui;

import model.Recipe;
import model.RecipeBook;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeGUI implements ActionListener {
    private ImageIcon image = new ImageIcon("./data/chef.jpeg");
    private JPanel recipeBookPanel;
    private JPanel recipeFavPanel;
    private JPanel vegPanelRB;
    private JPanel halalPanelFav;
    private JLabel recipeBookLabel;
    private JLabel recipeFavLabel;
    private JLabel vegRecInBookLabel;
    private JLabel halalRecInFavLabel;
    private JFrame frame = new JFrame("RecipeBook");
    private JButton loadButton = new JButton("Load");
    private JButton filterHalalInFavButton = new JButton("Halal Recipes in Favorites");
    private JButton filterVeganButton = new JButton("Vegan Recipes in Book");
    private JButton saveButton = new JButton("Save");
    private RecipeBook recipeBook = new RecipeBook();
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    private DefaultListModel updatedListModel;

    private JList recipesList;
    private JList veganRecipes = new JList<>();
    private JList fav = new JList<>();
    private JList savedFav = new JList<>();
    private JList halalInFav = new JList<>();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/MyState.json";
    Border border = BorderFactory.createLineBorder(Color.black, 3);

    //Runs the RecipeGUI and displays main frame
    public RecipeGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        splashScreen();
        jsonReader = new JsonReader(JSON_STORE);
        initializePanels();
        initializeButtons();
        recipeBookPanel.add(recipeBookLabel);
        vegPanelRB.add(veganRecipes);
        vegPanelRB.add(vegRecInBookLabel);
        recipeFavPanel.add(recipeFavLabel);
        recipeFavPanel.add(fav);
        recipeBookPanel.add(getRecipesJList());
        halalPanelFav.add(halalRecInFavLabel);
        halalPanelFav.add(halalInFav);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 750);
        frame.setResizable(true);
        addToFrame();

    }

    //MODIFIES: frame
    //EFFECTS: adds panels to frame
    private void addToFrame() {
        frame.add(recipeBookPanel);
        frame.add(vegPanelRB);
        frame.add(recipeFavPanel);
        frame.add(halalPanelFav);
        frame.add(loadButton);
        frame.add(filterHalalInFavButton);
        frame.add(filterVeganButton);
        frame.add(saveButton);
    }

    //MODIFIES: panels
    //EFFECTS: initializes all panels in GUI
    private void initializePanels() {
        recipeBookPanel = new JPanel();
        recipeBookPanel.setBackground(Color.LIGHT_GRAY);
        recipeBookPanel.setBounds(0, 0, 300, 300);
        recipeBookPanel.setBorder(border);
        recipeBookLabel.setText("Welcome to your Recipe Book");

        recipeFavPanel = new JPanel();
        recipeFavPanel.setBackground(Color.LIGHT_GRAY);
        recipeFavPanel.setBounds(445, 0, 300, 300);
        recipeFavPanel.setBorder(border);
        recipeFavLabel = new JLabel();
        recipeFavLabel.setText("Here are your favorite recipes");

        vegPanelRB = new JPanel();
        vegPanelRB.setBackground(Color.LIGHT_GRAY);
        vegPanelRB.setBounds(0, 300, 300, 300);
        vegPanelRB.setBorder(border);
        vegRecInBookLabel = new JLabel();
        vegRecInBookLabel.setText("Vegan recipes in your recipe book");

        halalPanelFav = new JPanel();
        halalPanelFav.setBackground(Color.LIGHT_GRAY);
        halalPanelFav.setBounds(445, 300, 300, 300);
        halalPanelFav.setBorder(border);
        halalRecInFavLabel = new JLabel();
        halalRecInFavLabel.setText("Halal recipes in your favorites");
    }

    //MODIFIES: buttons
    //EFFECTS: initializes all buttons in GUI and adds listeners to them
    private void initializeButtons() {
        loadButton.setBounds(0, 640, 100, 50);
        loadButton.setVisible(true);
        loadButton.setLayout(null);
        loadButton.setSize(105, 50);
        loadButton.addActionListener(this);

        filterHalalInFavButton.setBounds(450, 640, 100, 50);
        filterHalalInFavButton.setVisible(true);
        filterHalalInFavButton.setLayout(null);
        filterHalalInFavButton.setSize(175, 50);
        filterHalalInFavButton.addActionListener(this);

        filterVeganButton.setBounds(125, 640, 100, 50);
        filterVeganButton.setVisible(true);
        filterVeganButton.setLayout(null);
        filterVeganButton.setSize(175, 50);
        filterVeganButton.addActionListener(this);

        saveButton.setBounds(640, 640, 100, 50);
        saveButton.setVisible(true);
        saveButton.setLayout(null);
        saveButton.setSize(105, 50);
        saveButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: returns a JList of recipeNames
    private JList getRecipesJList() {
        ArrayList<Recipe> arrayList = recipeBook.getRecipes();
        listModel = new DefaultListModel<>();

        // listmodel has all the recipe names inside of it as elements
        for (Recipe recipe : arrayList) {
            listModel.addElement(recipe.getRecipeName());
        }
        // recipesList is a Jlist created from listmodel
        recipesList = new JList<>(listModel);
        recipesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                recipeBook.toggleFav(recipesList.getSelectedIndex() + 1); // toggles isFavorite field
                //Recipe r = arrayList.get(recipesList.getSelectedIndex());
                filterForFav();
            }
        });
        recipesList.updateUI();
        return recipesList;
    }

    //MODIFIES: this
    //EFFECTS: loops through each recipe and returns a JList of the vegan recipes
    private JList filterForVegan() {
        ArrayList<Recipe> recipeArrayList = recipeBook.getRecipes();
        //recipeArrayList = recipeBook.getRecipes();
        listModel2 = new DefaultListModel<>();

        for (Recipe r : recipeArrayList) {
            if (r.isRecipeVegan()) {
                listModel2.addElement(r.getRecipeName());
            }
        }

        veganRecipes.setModel(listModel2);
        return veganRecipes;
    }

    //MODIFIES: this
    //EFFECTS: loops through each recipe and returns a JList of the favorite recipes
    private JList filterForFav() {
        ArrayList<Recipe> recipeArrayList = recipeBook.getRecipes();
        //recipeArrayList = recipeBook.getRecipes();
        listModel2 = new DefaultListModel<>();

        for (Recipe r : recipeArrayList) {
            if (r.isFavourite()) {
                listModel2.addElement(r.getRecipeName());
            }
        }

        fav.setModel(listModel2);
        return fav;
    }

    //MODIFIES: this
    //EFFECTS: loops through each recipe and returns a JList of the halal recipes
    private JList filterForHalalInFav() {
        DefaultListModel listModelHalal;
        listModelHalal = new DefaultListModel<>();
        ArrayList<Recipe> halalRecipes = recipeBook.getHalalRecipes();
        for (int i=0; i < fav.getModel().getSize(); i++){
            for(Recipe r : halalRecipes) {
                if (r.getRecipeName().equals(fav.getModel().getElementAt(i))){
                    listModelHalal.addElement(fav.getModel().getElementAt(i));
                }
            }
        }
        halalInFav.setModel(listModelHalal);
        return halalInFav;
    }

    //EFFECTS: saves the current favorites of the RecipeApp to a Json file
    private void saveFav(){
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
        }
        catch(FileNotFoundException e){
            throw new RuntimeException();
        }
    }

    //MODIFIES: fav
    //EFFECTS: updates favorites with the recipebook's favorites
    private void updateFav(RecipeBook r){
        updatedListModel = new DefaultListModel<>();
        ArrayList<Recipe> updatesRecipe = new ArrayList<>();
        try{
            r = jsonReader.read(); // r is RecipeBook with favorites set to true for based on Mystate.json
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        JSONArray jsonArray = r.favToJson(); // returns favorites in this state as a JSON array of Recipe JSON objects
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject recipe = jsonArray.getJSONObject(i);
            updatedListModel.addElement(recipe.get("name"));
        }
        fav.setModel(updatedListModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterVeganButton) {
            vegPanelRB.add(filterForVegan());
        } else if (e.getSource() == filterHalalInFavButton) {
            halalPanelFav.add(filterForHalalInFav());
        } else if (e.getSource() == saveButton) {
            saveFav();
        } else if (e.getSource() == loadButton) {
            updateFav(recipeBook);
        }
    }

    //MODIFIES: frame
    //EFFECTS: displays a Splash Screen with an image before main frame is displayed
    public void splashScreen() {
        frame.setVisible(false);
        JWindow window = new JWindow();
        Dimension size = window.getSize();
        JLabel jLabel = new JLabel();
        jLabel.setIcon(image);
        jLabel.setSize(size);
        window.getContentPane().add(jLabel);
        jLabel.setVisible(true);
        window.setBounds(300, 300, 600, 600);
        window.setVisible(true);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        frame.setVisible(true);
        window.dispose();
    }
}




