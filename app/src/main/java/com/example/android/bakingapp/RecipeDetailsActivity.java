package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.adapters.RecipeDetailListAdapter;
import com.example.android.bakingapp.adapters.RecipeListAdapter;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeDetailsActivity.class.getSimpleName();
    public static final String INGREDIENT_TYPE = "ingredient";
    public static final String STEP_TYPE = "step";

    private RecyclerView ingredientsListRv;
    private RecipeDetailListAdapter mAdapter;
    private static Recipe mRecipe;
    private static boolean twoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if(!getParcelFromIntent()) {
            Log.v(LOG_TAG, "No recipe details!");
            finish();
        }
        setTitle(mRecipe.getName());
        checkIfTwoPanel();
        if(twoPanel) {

        } else {
            initViewsForSinglePanel();
        }

    }

    private void checkIfTwoPanel() {
        if(findViewById(R.id.fragment_container) != null) {
            twoPanel = true;
        } else {
            twoPanel = false;
        }
    }

    private void initViewsForSinglePanel() {
        ingredientsListRv = findViewById(R.id.rv_recipe_detail_list);
        ingredientsListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeDetailListAdapter(
                this,
                mRecipe.getIngredients(),
                mRecipe.getSteps(),
                new RecipeListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if(position == 0) {
                            startActivity(createIngredientsIntent());
                        }
                    }
                });
        ingredientsListRv.setAdapter(mAdapter);
    }

    private boolean getParcelFromIntent() {
        Intent intent = getIntent();
        if(!intent.hasExtra(MainActivity.RECIPE_PARCEL_KEY)) {
            return false;
        }
        mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_PARCEL_KEY);
        if(mRecipe == null) {
            return false;
        }
        return true;
    }

    private Intent createIngredientsIntent() {
        Intent intent = new Intent(RecipeDetailsActivity.this,
                RecipeIngredientsAndStepsActivity.class);
        intent.setType(INGREDIENT_TYPE);
        return intent;
    }

    public static Recipe getCurrentRecipe() {
        return mRecipe;
    }

}
