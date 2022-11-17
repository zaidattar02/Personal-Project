package ui;

import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeGUI implements ActionListener {
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
    private JButton showFavButton = new JButton("Display Favorites");
    private JButton filterHalalInFavButton = new JButton("Halal Recipes in Favorites");
    private JButton filterVeganButton = new JButton("Vegan Recipes in Book");
    private JButton saveButton = new JButton("Save");
    private RecipeBook recipeBook = new RecipeBook();
    private DefaultListModel listModel;
    private DefaultListModel listModel2;

    private JList recipesList;
    private JList veganRecipes = new JList<>();
    private JList fav = new JList<>();
    private JList halalInFav = new JList<>();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/MyState.json";
    Border border = BorderFactory.createLineBorder(Color.black, 3);

    public RecipeGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        initializePanels();
        initializeButtons();
        frame.setVisible(true);
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
        frame.add(recipeBookPanel);
        frame.add(vegPanelRB);
        frame.add(recipeFavPanel);
        frame.add(halalPanelFav);
        frame.add(loadButton);
        frame.add(filterHalalInFavButton);
        frame.add(filterVeganButton);
        frame.add(saveButton);
    }

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
        halalRecInFavLabel.setText("Hala recipes in your favorites");
    }

    private void initializeButtons() {
        loadButton.setBounds(0, 640, 100, 50);
        loadButton.setVisible(true);
        loadButton.setLayout(null);
        loadButton.setSize(105, 50);

//        showFavButton.setBounds(125, 640, 100, 50);
//        showFavButton.setVisible(true);
//        showFavButton.setLayout(null);
//        showFavButton.setSize(150, 50);
//        showFavButton.addActionListener(this);

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
    }

    private JList getRecipesJList() {
        recipeBook = new RecipeBook();
        ArrayList<Recipe> arrayList = recipeBook.getRecipes();
        listModel = new DefaultListModel<>();

        for (Recipe recipe : arrayList) {
            listModel.addElement(recipe.getRecipeName());
        }
        recipesList = new JList<>(listModel);
        recipesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                recipeBook.addToFav(recipesList.getSelectedIndex() + 1);
                Recipe r = arrayList.get(recipesList.getSelectedIndex());
                filterForFav();
            }
        });
        recipesList.updateUI();
        return recipesList;
    }

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

    private JList filterForHalalInFav() {
        ArrayList<Recipe> recipeArrayList = recipeBook.getRecipes();
        listModel2 = new DefaultListModel<>();

        for (Recipe r : recipeArrayList) {
            if (r.isRecipeHalal() && r.isFavourite()) {
                listModel2.addElement(r.getRecipeName());
            }
        }

        halalInFav.setModel(listModel2);
        return halalInFav;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterVeganButton) {
            vegPanelRB.add(filterForVegan());
        } else if (e.getSource() == filterHalalInFavButton) {
            halalPanelFav.add(filterForHalalInFav());
        }
    }
}
