package com.Recipe.RecipeApi.Controller;

import com.Recipe.RecipeApi.Service.RecipeService;
import com.Recipe.RecipeApi.Entitiy.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    // ✅ Import JSON file into database
    @GetMapping("/import")
    public String importRecipes() {
        recipeService.importRecipesFromJson("US_recipes_null.json");
        return "✅ Data imported successfully!";
    }

    // ✅ Get all recipes
    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // ✅ Get recipe by ID
    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }
    @GetMapping("/search/rating")
    public List<Recipe> filterByRating(@RequestParam Double rating) {
        return recipeService.getRecipesByRating(rating);
    }
    @GetMapping("/search")
    public List<Recipe> searchByTitle(@RequestParam String title){
        return recipeService.getRecipesByTitle(title);
    }


}
