package com.example.android.bakingapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Recipe;
import com.example.android.bakingapp.RecipeDetailsActivity;
import com.example.android.bakingapp.adapters.IngredientsListAdapter;

import java.util.List;

public class RecipeIngredientsFragment extends Fragment {

    private RecyclerView mIngredientListRv;
    private IngredientsListAdapter mAdapter;

    public RecipeIngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {
        mIngredientListRv = getActivity().findViewById(R.id.rv_ingredient_list);
        mIngredientListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mIngredientListRv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new IngredientsListAdapter(getContext(),
                RecipeDetailsActivity.getCurrentRecipe().getIngredients());
        mIngredientListRv.setAdapter(mAdapter);
    }

}
