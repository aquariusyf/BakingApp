package com.example.android.bakingapp;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;
    private String image;

    public Recipe(
            int id,
            String name,
            List<Ingredients> ingredients,
            List<Steps> steps,
            int servings,
            String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public List<Steps> getSteps() {
        return this.steps;
    }

    public int getServings() {
        return this.servings;
    }

    public String getImage() {
        return this.image;
    }

    public static class Ingredients {

        private float quantity;
        private String measure;
        private String ingredient;

        public float getQuantity() {
            return quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public String getIngredient() {
            return ingredient;
        }
    }

    public static class Steps {

        private int id;
        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        public int getId() {
            return id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }
    }
}
