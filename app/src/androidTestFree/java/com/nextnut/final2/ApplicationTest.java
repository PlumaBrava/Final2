package com.nextnut.final2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void InstructionsIsNotNull(){
    //  Verify instructions is a not null text

        onView(withId(R.id.instructions_text_view)).check(matches(notNullValue()));

    }



    @Test
    public void checkButton_Add_AsyncTask(){

        String expectedText = "Tell Joke free";
        pauseTestFor(4000);

        //Perform a click to use te asyncktask and interstitail add
        onView(withId(R.id.jokebutton)).perform(click());
        pauseTestFor(10000); //wait to display the add
        onView(withClassName(endsWith("View"))).perform(pressBack());//go back to
        pauseTestFor(10000); //wait to backend responds

        //Verify the answer is not null
        onView(withId(R.id.libraryText)).check(matches(notNullValue()));

        //Verify that the asyncTask does not return an error. Example is backend is off.
        String errorText = "error:";
        onView(withId(R.id.libraryText)).check(matches(not(withText(startsWith(errorText)))));
        //Verify that the asyncTask returns an joke.
        String jokeText = "This is totally a funny joke";
        onView(withId(R.id.libraryText)).check(matches(withText(jokeText)));


    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}