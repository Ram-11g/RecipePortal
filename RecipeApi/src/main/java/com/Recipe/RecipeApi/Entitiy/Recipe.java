package com.Recipe.RecipeApi.Entitiy;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "recipe") // optional but good for clarity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String continent;
    private String countryState;
    private String cuisine;
    private String title;
    private String url;
    private Double rating;
    private Integer totalTime;
    private Integer prepTime;
    private Integer cookTime;

    // description can be long — set a higher length limit
    @Column(length = 5000)
    private String description;

    // Each ingredient will be stored as a separate row in a new table
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient", length = 1000) // avoid 255 limit
    private List<String> ingredients;

    // Each instruction stored as a row in recipe_instructions table
    @ElementCollection
    @CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "instruction", length = 2000) // increased from 255 → 2000
    private List<String> instructions;

    // Map of nutrient name → value (stored in another table)
    @ElementCollection
    @CollectionTable(name = "recipe_nutrients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "nutrient_value", length = 500)
    @MapKeyColumn(name = "nutrient_name") // ✅ this tells Hibernate what the Map key is
    private Map<String, String> nutrients;


    private String serves;
}
