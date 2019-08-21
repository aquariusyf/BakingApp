package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.fragments.RecipeIngredientsFragment;

public class RecipeIngredientsAndStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients_steps);

        if(getIntent().getType().equals(RecipeDetailsActivity.INGREDIENT_TYPE)) {
            createIngredientFragment();
        } else {
            finish();
        }
    }

    private void createIngredientFragment() {
        RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }
}
