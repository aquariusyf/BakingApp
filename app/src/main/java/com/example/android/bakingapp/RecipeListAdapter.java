package com.example.android.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;;

public class RecipeListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String LOG_TAG = RecipeListAdapter.class.getSimpleName();

    private Context mContext;
    private List<Recipe> mRecipes;
    private OnListItemClickListener mListener;

    public RecipeListAdapter(Context context, List<Recipe> recipes, OnListItemClickListener listener) {
        mContext = context;
        mRecipes = recipes;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_recipe, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((RecipeViewHolder) holder).nameTv.setText(mRecipes.get(position).getName());
        ((RecipeViewHolder) holder).servingTv.setText(mRecipes.get(position).getServings() + "");
    }

    @Override
    public int getItemCount() {
        if(mRecipes == null || mRecipes.isEmpty()) {
            return 0;
        } else {
            return mRecipes.size();
        }
    }

    public void updateDataSet(List<Recipe> newRecipes) {
        mRecipes = newRecipes;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {
        void onItemClick(int position);
    }

    class RecipeViewHolder extends ViewHolder implements View.OnClickListener {

        private TextView nameTv;
        private TextView servingTv;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_recipe_name);
            servingTv = itemView.findViewById(R.id.tv_servings);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }
}
