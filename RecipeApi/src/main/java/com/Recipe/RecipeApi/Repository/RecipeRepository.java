package com.Recipe.RecipeApi.Repository;

import com.Recipe.RecipeApi.Entitiy.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContainingIgnoreCase(String title);
  List<Recipe> findByRating(Double rating);
}
