package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.android.bakingapp.adapters.RecipeListAdapter;
import com.example.android.bakingapp.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String RECIPE_PARCEL_KEY = "single_recipe";
    public static final String RECIPE_LIST_PARCEL_KEY = "recipe_list";

    private RecyclerView mRecipeListRv;
    private RecipeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey(RECIPE_LIST_PARCEL_KEY)) {
            initRecipeList(savedInstanceState.<Recipe>getParcelableArrayList(RECIPE_LIST_PARCEL_KEY));
        } else {
            initRecipeList(JsonUtil.getRecipes(this));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_LIST_PARCEL_KEY,
                (ArrayList<? extends Parcelable>) mAdapter.getRecipeList());
    }

    private void initRecipeList(List<Recipe> recipeList) {
        mRecipeListRv = findViewById(R.id.rv_recipe_list);
        mRecipeListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeListAdapter(this, recipeList,
                new RecipeListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(MainActivity.this,
                                RecipeDetailsActivity.class);
                        detailIntent.putExtra(RECIPE_PARCEL_KEY,
                                mAdapter.getRecipeList().get(position));
                        startActivity(detailIntent);
                    }
                });
        mRecipeListRv.setAdapter(mAdapter);
    }
}
