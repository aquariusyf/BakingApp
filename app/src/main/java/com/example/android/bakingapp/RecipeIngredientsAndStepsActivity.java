package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.android.bakingapp.fragments.RecipeIngredientsFragment;
import com.example.android.bakingapp.fragments.RecipeStepsFragment;

public class RecipeIngredientsAndStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients_steps);

        if(getIntent().getType().equals(RecipeDetailsActivity.INGREDIENT_TYPE)) {
            setTitle(getString(R.string.dummy_single_recipe_detail));
            createIngredientFragment();
        } else if(getIntent().getType().equals(RecipeDetailsActivity.STEP_TYPE)) {
            createStepFragment();
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

    private void createStepFragment() {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }
}
