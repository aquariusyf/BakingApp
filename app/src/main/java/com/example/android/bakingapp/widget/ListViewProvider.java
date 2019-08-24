package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Recipe;

import java.util.List;

public class ListViewProvider implements RemoteViewsFactory {

    private List<Recipe.Ingredients> mIngredients;
    private Context mContext;

    public ListViewProvider(Context context, Intent intent) {
        mContext = context;
        Bundle bundle = intent.getBundleExtra(BakingAppWidgetProvider.INGREDIENT_BUNDLE_KEY);
        mIngredients = bundle.getParcelableArrayList(BakingAppWidgetProvider.INGREDIENT_PARCEL_KEY);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(mIngredients == null || mIngredients.isEmpty()) {
            return 0;
        } else {
            return mIngredients.size();
        }
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteViews =
                new RemoteViews(mContext.getPackageName(), R.layout.single_item_ingredients);
        Recipe.Ingredients ingredient = mIngredients.get(position);
        String quantity = new java.text.DecimalFormat("#.#")
                .format(ingredient.getQuantity());
        remoteViews.setTextViewText(R.id.tv_quantity, quantity);
        remoteViews.setTextViewText(R.id.tv_measure, ingredient.getMeasure());
        remoteViews.setTextViewText(R.id.tv_ingredient, ingredient.getIngredient());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
