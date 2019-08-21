package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.bakingapp.adapters.RecipeListAdapter;
import com.example.android.bakingapp.utils.JsonUtil;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String RECIPE_PARCEL_KEY = "recipe";

    private RecyclerView mRecipeListRv;
    private RecipeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecipeList();

    }

    private void initRecipeList() {
        mRecipeListRv = findViewById(R.id.rv_recipe_list);
        mRecipeListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeListAdapter(this, JsonUtil.getRecipes(this),
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
