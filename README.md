# FreshEats

### Functionality
This is an application that acts like a meal kit subscription plan. It allows users with little or no experience with cooking to be able to prepare fresh and 
healthy meals from the comfort of their home by following simple recipes of their choice from weekly generated presets. There will be some flexibility when it comes to editing the recipes in order to fit certain dietary restrictions. 
Also, the application takes the user's budget for groceries/ingredients in mind, as well as how much time they are willing to spend cooking per week.

### Target Audience
This application is generally for anyone who is trying to eat **fresh** and **healthy** home-cooked meals. However, it is aimed towards 
university students who are having trouble balancing their daily meal intake due to their 
heavy workload and inexperience with time management.

### Inspiration
As a university student who had just recently moved far away from the comfort of my own home where I could eat my mother's delicious home-cooked meal, to living alone and trying to avoid eating junk food three times  a day. I found it quite difficult to find a healthy work-life 
balance where I can eat a fresh nutritious meal and get all my assignments done. I am a very firm believer in the fact that 
a healthy body leads to a healthy mind, and the first step towards that is watching what you eat. So, I thought of how much easier it could be if I knew the exact ingredients I needed for the meals I wanted to cook every week, which would save me both time and money. 
In conclusion, this is a problem I am personally facing and I hope this project acts as a solution for anyone else encountering the same issue.

### User Stories
- As a user, I want to be able to browse recipes from the preset.
- As a user, I want to be able to save recipes I enjoyed to my favorites collection.
- As a user, I want to be able to remove certain ingredients from recipes.
- As a user, I want to be able to filter recipe presets for certain allergies or dietary restrictions.
- As a user, I want to be able to save my favorites list and edited recipes to file.
- As a user, I want to be able to load my favorites list and edited recipes from file.

### Instructions For Grader

- You can generate the first required event related to adding recipes to favorites by selecting a recipe in the RecipeBook panel, and it will appear in your favorites panel. You can also remove recipes by un-clicking the recipe's name, but it has not be unselected first.
- You can generate the second required event related to displaying a subset of recipes that are vegan by pressing the Vegan Recipes In Book button which will display all the vegan recipes in your RecipeBook in a panel.
- You can generate another event related to displaying a subset of favorite recipes that are halal by pressing the Halal Recipes In Favorite button which will display all the halal recipes in your favorites in a panel, if there are no halal recipes then nothing is displayed.
- You can locate my visual component when you first run the GUI as a splash screen.
- You can save the state of my application by pressing the Save button.
- You can reload the state of my application by pressing the Load button, which will display all saved favorites in the favorite panel.

### Phase 4: Task 2

- This is a sample of event logs of a user checking for vegan recipes in recipebook -> Adds mongolian pork and chicken alfredo pasta to favorites-> Filters through favorites to check for Halal recipes -> Removes mongolian pork from favorites:

Sat Nov 26 20:58:24 PST 2022
Checking if Chicken Alfredo Pasta in recipe book is Vegan


Sat Nov 26 20:58:24 PST 2022
Checking if Vegetable Fajitas in recipe book is Vegan


Sat Nov 26 20:58:24 PST 2022
Checking if Mongolian Pork in recipe book is Vegan


Sat Nov 26 20:58:24 PST 2022
Checking if Broccoli Pasta in recipe book is Vegan


Sat Nov 26 20:58:29 PST 2022
Added Mongolian Pork to favorites


Sat Nov 26 20:58:31 PST 2022
Added Chicken Alfredo Pasta to favorites


Sat Nov 26 20:58:33 PST 2022
Filtered Favorites for Halal Recipes


Sat Nov 26 20:58:36 PST 2022
Removed Mongolian Pork from favorites

### Phase 4: Task 3

- There is duplication in the RecipeBook class with the four methods that create a list of ingredients for each recipe, instead I could have applied the Composite Pattern design with the RecipeBook, Recipe, and ingredients classes. 
- Another way to refactor the three methods would be putting them all in one method to reduce duplication.
- The filter for Halal and filter for Vegan methods in the RecipeGUI also have duplication which could be reduced by pulling the code clones into methods.
- Using a hash map for the Recipe object with the key being the recipe’s name as a String and the value being the recipe’s associated list of ingredients.
- Based on my previous point, by doing so I would not have an ingredients class and instead have it as a list in the Recipe class’ field, which would have a more cohesive and focused class and in turn reduce coupling.