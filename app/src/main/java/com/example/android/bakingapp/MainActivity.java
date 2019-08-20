package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

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

                    }
                });
        mRecipeListRv.setAdapter(mAdapter);
    }
}
