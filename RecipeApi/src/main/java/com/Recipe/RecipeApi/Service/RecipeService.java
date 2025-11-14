package com.Recipe.RecipeApi.Service;

import com.Recipe.RecipeApi.Entitiy.Recipe;
import com.Recipe.RecipeApi.Repository.RecipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    private final RecipeRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    // ✅ Reads JSON file from resources folder
    public void importRecipesFromJson(String fileName) {
        try {
            // Use ClassPathResource so it works even after packaging as a jar
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();

            Map<String, Map<String, Object>> jsonData =
                    mapper.readValue(inputStream, new TypeReference<>() {});

            List<Recipe> recipes = new ArrayList<>();

            for (Map.Entry<String, Map<String, Object>> entry : jsonData.entrySet()) {
                Map<String, Object> data = entry.getValue();
                Recipe recipe = new Recipe();

                recipe.setContinent((String) data.get("Contient"));
                recipe.setCountryState((String) data.get("Country_State"));
                recipe.setCuisine((String) data.get("cuisine"));
                recipe.setTitle((String) data.get("title"));
                recipe.setUrl((String) data.get("URL"));
                recipe.setRating(data.get("rating") != null ? Double.valueOf(data.get("rating").toString()) : null);
                recipe.setTotalTime((Integer) data.get("total_time"));
                recipe.setPrepTime((Integer) data.get("prep_time"));
                recipe.setCookTime((Integer) data.get("cook_time"));
                recipe.setDescription((String) data.get("description"));
                recipe.setIngredients((List<String>) data.get("ingredients"));
                recipe.setInstructions((List<String>) data.get("instructions"));
                recipe.setNutrients((Map<String, String>) data.get("nutrients"));
                recipe.setServes((String) data.get("serves"));

                recipes.add(recipe);
            }

            repository.saveAll(recipes);
            System.out.println("✅ Successfully imported " + recipes.size() + " recipes into database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Fetch all recipes
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    // ✅ Fetch recipe by ID
    public Recipe getRecipeById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public List<Recipe> getRecipesByRating(Double rating) {
        return repository.findByRating(rating);
    }
    public List<Recipe> getRecipesByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

}
