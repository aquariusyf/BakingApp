package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.android.bakingapp.adapters.RecipeDetailListAdapter;
import com.example.android.bakingapp.adapters.RecipeListAdapter;
import com.example.android.bakingapp.fragments.RecipeIngredientsFragment;
import com.example.android.bakingapp.fragments.RecipeStepsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeDetailsActivity.class.getSimpleName();
    public static final String INGREDIENT_TYPE = "ingredient";
    public static final String STEP_TYPE = "step";
    public static int sStepIndex;

    private RecyclerView ingredientsListRv;
    private RecipeDetailListAdapter mAdapter;
    private static Recipe mRecipe;
    private static boolean sTwoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if(!getParcelFromIntent()) {
            Log.v(LOG_TAG, "No recipe details!");
            finish();
        }
        setTitle(mRecipe.getName());
        if(sTwoPanel) {
            initViewsForTwoPanel();
        } else {
            initViewsForSinglePanel();
        }

    }

    private void initViewsForTwoPanel() {
        ingredientsListRv = findViewById(R.id.rv_recipe_detail_list);
        ingredientsListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeDetailListAdapter(
                this,
                mRecipe.getIngredients(),
                mRecipe.getSteps(),
                new RecipeListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        updateFragment(position);
                    }
                });
        ingredientsListRv.setAdapter(mAdapter);
    }

    private void updateFragment(int position) {
        if(position == 0) {
            RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
        } else {
            sStepIndex = position - 1;
            RecipeStepsFragment fragment = new RecipeStepsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
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
                        } else {
                            sStepIndex = position - 1;
                            startActivity(createStepsIntent());
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
        sTwoPanel = intent.getBooleanExtra(MainActivity.TWO_PANEL_KEY, false);
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

    private Intent createStepsIntent() {
        Intent intent = new Intent(RecipeDetailsActivity.this,
                RecipeIngredientsAndStepsActivity.class);
        intent.setType(STEP_TYPE);
        return intent;
    }

    public static Recipe getCurrentRecipe() {
        return mRecipe;
    }

    public static int getStepIndex() {
        return sStepIndex;
    }

    public static boolean getIfTwoPanel() { return sTwoPanel; }

}
