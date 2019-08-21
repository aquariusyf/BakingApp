package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Recipe;

import java.util.List;

public class IngredientsListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Recipe.Ingredients> mIngredients;

    public IngredientsListAdapter(Context context, List<Recipe.Ingredients> ingredients) {
        mContext = context;
        mIngredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.single_item_ingredients, parent, false);
        IngredientViewHolder viewHolder = new IngredientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((IngredientViewHolder) holder).quantityTv
                .setText(mIngredients.get(position).getQuantity() + "");
        ((IngredientViewHolder) holder).measureTv
                .setText(mIngredients.get(position).getMeasure());
        ((IngredientViewHolder) holder).ingredientTv
                .setText(mIngredients.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        if(mIngredients == null || mIngredients.isEmpty()) {
            return 0;
        } else {
            return mIngredients.size();
        }
    }

    class IngredientViewHolder extends ViewHolder {

        private TextView quantityTv;
        private TextView measureTv;
        private TextView ingredientTv;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.tv_quantity);
            measureTv = itemView.findViewById(R.id.tv_measure);
            ingredientTv = itemView.findViewById(R.id.tv_ingredient);
        }
    }
}
