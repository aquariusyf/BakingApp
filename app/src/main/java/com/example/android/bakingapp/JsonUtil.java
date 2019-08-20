package com.example.android.bakingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final String JSON_FILE = "recipe.json";
    private static final String UTF_8 = "UTF-8";

    public static List<Recipe> getRecipes(Context context) {
        String recipeJson;
        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            recipeJson = new String(buffer, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Type listType = new TypeToken<ArrayList<Recipe>>(){}.getType();
        List<Recipe> recipes = new Gson().fromJson(recipeJson, listType);
        return recipes;
    }
}
