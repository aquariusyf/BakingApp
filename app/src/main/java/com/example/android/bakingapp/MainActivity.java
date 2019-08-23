package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
    public static final String TWO_PANEL_KEY = "two_panel";
    public static boolean sTwoPanel;

    private RecyclerView mRecipeListRv;
    private RecipeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey(RECIPE_LIST_PARCEL_KEY)) {
            sTwoPanel = savedInstanceState.getBoolean(TWO_PANEL_KEY);
            initRecipeList(savedInstanceState.getParcelableArrayList(RECIPE_LIST_PARCEL_KEY));
        } else {
            checkIfTwoPanel();
            initRecipeList(JsonUtil.getRecipes(this));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_LIST_PARCEL_KEY,
                (ArrayList<? extends Parcelable>) mAdapter.getRecipeList());
        outState.putBoolean(TWO_PANEL_KEY, sTwoPanel);
    }

    private void checkIfTwoPanel() {
        if(findViewById(R.id.rv_recipe_list_tablet) != null) {
            sTwoPanel = true;
        } else {
            sTwoPanel = false;
        }
    }

    private void initRecipeList(List<Recipe> recipeList) {
        if(sTwoPanel) {
            mRecipeListRv = findViewById(R.id.rv_recipe_list_tablet);
            mRecipeListRv.setLayoutManager(new GridLayoutManager(this,
                    3, RecyclerView.VERTICAL, false));
        } else {
            mRecipeListRv = findViewById(R.id.rv_recipe_list);
            mRecipeListRv.setLayoutManager(new LinearLayoutManager(this));
        }
        mAdapter = new RecipeListAdapter(this, recipeList,
                new RecipeListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(MainActivity.this,
                                RecipeDetailsActivity.class);
                        detailIntent.putExtra(RECIPE_PARCEL_KEY,
                                mAdapter.getRecipeList().get(position));
                        detailIntent.putExtra(TWO_PANEL_KEY, sTwoPanel);
                        startActivity(detailIntent);
                    }
                });
        mRecipeListRv.setAdapter(mAdapter);
    }
}
