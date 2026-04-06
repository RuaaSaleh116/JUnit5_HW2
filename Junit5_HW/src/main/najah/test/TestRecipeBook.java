package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("Test RecipeBook Class")
public class TestRecipeBook {

    private Recipe createRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        return recipe;
    }

    @Test
    @DisplayName("Get recipes should return array of size 4")
    void testGetRecipes() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe[] recipes = recipeBook.getRecipes();

        assertAll("Check recipes array",
                () -> assertNotNull(recipes),
                () -> assertEquals(4, recipes.length));
    }

    @Test
    @DisplayName("Add recipe successfully")
    void testAddRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = createRecipe("Espresso");

        boolean added = recipeBook.addRecipe(recipe);

        assertAll("Check add recipe",
                () -> assertTrue(added),
                () -> assertEquals("Espresso", recipeBook.getRecipes()[0].getName()));
    }

    @Test
    @DisplayName("Add duplicate recipe should return false")
    void testAddDuplicateRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe1 = createRecipe("Latte");
        Recipe recipe2 = createRecipe("Latte");

        boolean firstAdd = recipeBook.addRecipe(recipe1);
        boolean secondAdd = recipeBook.addRecipe(recipe2);

        assertAll("Check duplicate recipe",
                () -> assertTrue(firstAdd),
                () -> assertFalse(secondAdd));
    }

    @Test
    @DisplayName("Add recipes until recipe book is full")
    void testAddRecipeWhenFull() {
        RecipeBook recipeBook = new RecipeBook();

        boolean add1 = recipeBook.addRecipe(createRecipe("R1"));
        boolean add2 = recipeBook.addRecipe(createRecipe("R2"));
        boolean add3 = recipeBook.addRecipe(createRecipe("R3"));
        boolean add4 = recipeBook.addRecipe(createRecipe("R4"));
        boolean add5 = recipeBook.addRecipe(createRecipe("R5"));

        assertAll("Check recipe book full",
                () -> assertTrue(add1),
                () -> assertTrue(add2),
                () -> assertTrue(add3),
                () -> assertTrue(add4),
                () -> assertFalse(add5));
    }

    @Test
    @DisplayName("Delete existing recipe should return its name")
    void testDeleteExistingRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.addRecipe(createRecipe("Mocha"));

        String deletedName = recipeBook.deleteRecipe(0);

        assertAll("Check delete existing recipe",
                () -> assertEquals("Mocha", deletedName),
                () -> assertEquals("", recipeBook.getRecipes()[0].getName()));
    }

    @Test
    @DisplayName("Delete empty recipe slot should return null")
    void testDeleteEmptyRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        String deletedName = recipeBook.deleteRecipe(0);

        assertNull(deletedName);
    }

    @Test
    @DisplayName("Edit existing recipe should return old recipe name")
    void testEditExistingRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.addRecipe(createRecipe("Cappuccino"));
        Recipe newRecipe = createRecipe("Americano");

        String oldName = recipeBook.editRecipe(0, newRecipe);

        assertAll("Check edit recipe",
                () -> assertEquals("Cappuccino", oldName),
                () -> assertEquals("", recipeBook.getRecipes()[0].getName()));
    }

    @Test
    @DisplayName("Edit empty recipe slot should return null")
    void testEditEmptyRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe newRecipe = createRecipe("Flat White");

        String oldName = recipeBook.editRecipe(0, newRecipe);

        assertNull(oldName);
    }

    @Test
    @DisplayName("Add null recipe should throw NullPointerException")
    void testAddNullRecipe() {
        RecipeBook recipeBook = new RecipeBook();

        assertThrows(NullPointerException.class, () -> {
            recipeBook.addRecipe(null);
        });
    }
}