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
import com.example.android.bakingapp.adapters.RecipeListAdapter.OnListItemClickListener;

import java.util.List;

public class RecipeDetailListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Recipe.Ingredients> mIngredients;
    private List<Recipe.Steps> mSteps;
    private OnListItemClickListener mListener;

    public RecipeDetailListAdapter(
            Context context,
            List<Recipe.Ingredients> ingredients,
            List<Recipe.Steps> steps,
            OnListItemClickListener listener) {
        mContext = context;
        mIngredients = ingredients;
        mSteps = steps;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.single_item_recipe_detail, parent, false);
        RecipeDetailViewHolder viewHolder = new RecipeDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position) == 0) {
            ((RecipeDetailViewHolder) holder).detailTv
                    .setText(mContext.getResources().getString(R.string.dummy_single_recipe_detail));
        } else {
            setDetailText(position, holder);
        }
    }

    private void setDetailText(int position, ViewHolder holder) {
        if(mSteps.get(position - 1).getId() == 0) {
            ((RecipeDetailViewHolder) holder).detailTv.
                    setText(mSteps.get(position - 1).getShortDescription());
        } else {
            ((RecipeDetailViewHolder) holder).detailTv
                    .setText(mSteps.get(position - 1).getId()
                            + "."
                            + mSteps.get(position - 1).getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        if(mSteps == null || mSteps.isEmpty()) {
            return 1;
        } else {
            return mSteps.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class RecipeDetailViewHolder extends ViewHolder implements View.OnClickListener {

        private TextView detailTv;

        public RecipeDetailViewHolder(View itemView) {
            super(itemView);
            detailTv = itemView.findViewById(R.id.tv_single_recipe_detail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

}
