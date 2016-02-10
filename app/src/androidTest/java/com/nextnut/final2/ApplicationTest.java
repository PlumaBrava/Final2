package com.nextnut.final2;


import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.InterstitialAd;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import static com.google.android.gms.ads.AdActivity.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void InstructionsIsNotNull(){
//

        onView(withId(R.id.instructions_text_view)).check(matches(notNullValue()));

    }

//    @Test
//    public void InstructionsIsNull(){
////
////        onView(withId(R.id.jokebutton)).perform(click());
////        String expectedText = "HelloWorld!";
//        onView(withId(R.id.jokebutton)).check(matches(withText(isEmptyOrNullString())));
//
//    }

//
//    @Test
//    public void hint_isDisplayedInEditText() {
//        String hintText = mActivityRule.getActivity().getResources().getString(R.string.instructions);
//
//        onView(withId(R.id.instructions_text_view)).check(matches(HintMatcher.withHint(hintText)));
//    }

    @Test
    public void checkButton_Add_AsyncTask(){

        String expectedText = "Tell Joke free";
        pauseTestFor(4000);
//        onView(withId(R.id.instructions_text_view)).check(matches(withText(expectedText)));
      onView(withId(R.id.jokebutton))
//              .check(matches(withText(expectedText)))
              .perform(click());
        pauseTestFor(10000);
        onView(withClassName(endsWith("View"))).perform(pressBack());
        pauseTestFor(10000);
        onView(withId(R.id.libraryText)).check(matches(notNullValue()));
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}