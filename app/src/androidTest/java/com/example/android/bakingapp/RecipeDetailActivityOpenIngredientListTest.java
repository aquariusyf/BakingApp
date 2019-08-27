package com.example.android.bakingapp;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityOpenIngredientListTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickIngredient_openIngredientList() throws InterruptedException {
        for(int i = 0; i < 4; i++) {
            onView(withId(R.id.rv_recipe_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            Thread.sleep(2000);
            onView(withId(R.id.rv_recipe_detail_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.rv_ingredient_list)).check(matches(isDisplayed()));
            Thread.sleep(2000);
            Espresso.pressBack();
            Thread.sleep(1000);
            Espresso.pressBack();
        }
    }

}
