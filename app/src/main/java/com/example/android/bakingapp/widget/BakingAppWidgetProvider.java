package com.example.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Recipe;

import java.util.ArrayList;
import java.util.List;

public class BakingAppWidgetProvider extends AppWidgetProvider {

    public static final String INGREDIENT_BUNDLE_KEY = "ingredient_bundle_key";
    public static final String INGREDIENT_PARCEL_KEY = "ingredient_key";

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId,
                                String recipeName,
                                List<Recipe.Ingredients> ingredients) {
        RemoteViews views =
                new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
        views.setTextViewText(R.id.appwidget_tv_recipe_name, recipeName);
        views.setOnClickPendingIntent(R.id.base_layout_widget, createOpenAppPendingIntent(context));
        if(ingredients != null) {
            views.setRemoteAdapter(R.id.appwidget_ingredient_list,
                    createUpdateListViewIntent(context, ingredients));
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateWidgetContent(Context context,
                                           AppWidgetManager appWidgetManager,
                                           int[] appWidgetIds,
                                           String recipeName,
                                           List<Recipe.Ingredients> ingredients) {
        for(int appWidgetId: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeName, ingredients);
        }
    }

    private static PendingIntent createOpenAppPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    private static Intent createUpdateListViewIntent(Context context,
                                                     List<Recipe.Ingredients> ingredients) {
        Intent intent = new Intent(context, ListViewService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INGREDIENT_PARCEL_KEY, (ArrayList<? extends Parcelable>) ingredients);
        intent.putExtra(INGREDIENT_BUNDLE_KEY, bundle);
        return intent;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context,
                    appWidgetManager,
                    appWidgetId,
                    context.getString(R.string.widget_initial_text),
                    null);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

