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

    private RecyclerView ingredientsListRv;
    private RecipeDetailListAdapter mAdapter;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if(!getParcelFromIntent()) {
            Log.v(LOG_TAG, "No recipe details!");
            finish();
        }
        setTitle(mRecipe.getName());
        initViews();
    }

    private void initViews() {
        ingredientsListRv = findViewById(R.id.rv_recipe_detail_list);
        ingredientsListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeDetailListAdapter(
                this,
                mRecipe.getIngredients(),
                mRecipe.getSteps(),
                new RecipeListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

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
}
