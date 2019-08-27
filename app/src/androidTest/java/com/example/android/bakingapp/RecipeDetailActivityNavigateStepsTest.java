package com.example.android.bakingapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityNavigateStepsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickNextPrevious_navigateRecipeSteps() throws InterruptedException {
        onView(withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rv_recipe_detail_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        List<Recipe.Steps> steps = RecipeDetailsActivity.getCurrentRecipe().getSteps();
        int min = 0;
        int max = steps.size() - 1;
        int position = min;

        while(position < max) {
            if(steps.get(position).getVideoURL() != null
                    && !steps.get(position).getVideoURL().isEmpty()) {
                onView(withId(R.id.player_view)).check(matches(isDisplayed()));
            } else if(steps.get(position).getThumbnailURL() != null
                    && !steps.get(position).getThumbnailURL().isEmpty()) {
                onView(withId(R.id.player_view)).check(matches(isDisplayed()));
            }
            String description = steps.get(position).getDescription();
            onView(withId(R.id.tv_description)).check(matches(withText(description)));
            onView(withId(R.id.btn_next)).perform(click());
            Thread.sleep(2000);
            position++;
        }

        while (position > min) {
            if(steps.get(position).getVideoURL() != null
                    && !steps.get(position).getVideoURL().isEmpty()) {
                onView(withId(R.id.player_view)).check(matches(isDisplayed()));
            } else if(steps.get(position).getThumbnailURL() != null
                    && !steps.get(position).getThumbnailURL().isEmpty()) {
                onView(withId(R.id.player_view)).check(matches(isDisplayed()));
            }
            String description = steps.get(position).getDescription();
            onView(withId(R.id.tv_description)).check(matches(withText(description)));
            onView(withId(R.id.btn_previous)).perform(click());
            Thread.sleep(2000);
            position--;
        }
    }

}
