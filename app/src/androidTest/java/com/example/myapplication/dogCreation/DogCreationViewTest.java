package com.example.myapplication.dogCreation;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DogCreationViewTest {

    @Rule
    public ActivityScenarioRule<DogCreationView> mActivityScenarioRule = new ActivityScenarioRule<DogCreationView>(DogCreationView.class);

    private String name = "TestDog";
    private String age = "12";
    private String bio = "Wau Wau";
    private String price = "1000000";
    private String race = "Hokkaido";

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testAllFields(){
        //Name:
        Espresso.onView(withId(R.id.dogNameTextInput)).perform(typeText(name));
        //Alter:
        Espresso.onView(withId(R.id.dogAgeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(age))).perform(click());
        Espresso.onView(withId(R.id.dogAgeSpinner)).check(matches(withSpinnerText(containsString(age))));
        //Geschlecht:
        Espresso.onView(withId(R.id.maleCheck)).perform(click());
        Espresso.onView(withId(R.id.maleCheck)).check(matches(isChecked()));
        //Rasse:
        Espresso.onView(withId(R.id.dogRaceSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(race))).perform(click());
        Espresso.onView(withId(R.id.dogRaceSpinner)).check(matches(withSpinnerText(containsString(race))));
        //Bio:
        Espresso.onView(withId(R.id.dogBioTextInput)).perform(typeText(bio));
        //Preis
        Espresso.onView(withId(R.id.dogPriceTextInput)).perform(typeText(price));
        //Mischling
        Espresso.onView(withId(R.id.checkDogHybrid)).perform(click());
        Espresso.onView(withId(R.id.checkDogHybrid)).check(matches(isChecked()));
        //Papiere
        Espresso.onView(withId(R.id.checkDogPapers)).perform(click());
        Espresso.onView(withId(R.id.checkDogPapers)).check(matches(isChecked()));

    }

    @Test
    public void testNoName(){
        //Alter:
        Espresso.onView(withId(R.id.dogAgeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(age))).perform(click());
        Espresso.onView(withId(R.id.dogAgeSpinner)).check(matches(withSpinnerText(containsString(age))));
        //Geschlecht:
        Espresso.onView(withId(R.id.maleCheck)).perform(click());
        Espresso.onView(withId(R.id.maleCheck)).check(matches(isChecked()));
        //Rasse:
        Espresso.onView(withId(R.id.dogRaceSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(race))).perform(click());
        Espresso.onView(withId(R.id.dogRaceSpinner)).check(matches(withSpinnerText(containsString(race))));
        //Bio:
        Espresso.onView(withId(R.id.dogBioTextInput)).perform(typeText(bio));
        //Preis
        Espresso.onView(withId(R.id.dogPriceTextInput)).perform(typeText(price));
        //Mischling
        Espresso.onView(withId(R.id.checkDogHybrid)).perform(click());
        Espresso.onView(withId(R.id.checkDogHybrid)).check(matches(isChecked()));
        //Papiere
        Espresso.onView(withId(R.id.checkDogPapers)).perform(click());
        Espresso.onView(withId(R.id.checkDogPapers)).check(matches(isChecked()));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.dogSaveBtn)).perform(click());

        Espresso.onView(withText("Name darf nicht leer sein")).check(matches(isDisplayed()));
    }

    @Test
    public void testNoBio(){
        //Name:
        Espresso.onView(withId(R.id.dogNameTextInput)).perform(typeText(name));
        //Alter:
        Espresso.onView(withId(R.id.dogAgeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(age))).perform(click());
        Espresso.onView(withId(R.id.dogAgeSpinner)).check(matches(withSpinnerText(containsString(age))));
        //Geschlecht:
        Espresso.onView(withId(R.id.maleCheck)).perform(click());
        Espresso.onView(withId(R.id.maleCheck)).check(matches(isChecked()));
        //Rasse:
        Espresso.onView(withId(R.id.dogRaceSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(race))).perform(click());
        Espresso.onView(withId(R.id.dogRaceSpinner)).check(matches(withSpinnerText(containsString(race))));
        //Preis
        Espresso.onView(withId(R.id.dogPriceTextInput)).perform(typeText(price));
        //Mischling
        Espresso.onView(withId(R.id.checkDogHybrid)).perform(click());
        Espresso.onView(withId(R.id.checkDogHybrid)).check(matches(isChecked()));
        //Papiere
        Espresso.onView(withId(R.id.checkDogPapers)).perform(click());
        Espresso.onView(withId(R.id.checkDogPapers)).check(matches(isChecked()));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.dogSaveBtn)).perform(click());

        Espresso.onView(withText("Bio darf nicht leer sein")).check(matches(isDisplayed()));
    }

    @Test
    public void testNoPrice(){
        //Name:
        Espresso.onView(withId(R.id.dogNameTextInput)).perform(typeText(name));
        //Alter:
        Espresso.onView(withId(R.id.dogAgeSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(age))).perform(click());
        Espresso.onView(withId(R.id.dogAgeSpinner)).check(matches(withSpinnerText(containsString(age))));
        //Geschlecht:
        Espresso.onView(withId(R.id.maleCheck)).perform(click());
        Espresso.onView(withId(R.id.maleCheck)).check(matches(isChecked()));
        //Rasse:
        Espresso.onView(withId(R.id.dogRaceSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(race))).perform(click());
        Espresso.onView(withId(R.id.dogRaceSpinner)).check(matches(withSpinnerText(containsString(race))));
        //Bio:
        Espresso.onView(withId(R.id.dogBioTextInput)).perform(typeText(bio));
        //Mischling
        Espresso.onView(withId(R.id.checkDogHybrid)).perform(click());
        Espresso.onView(withId(R.id.checkDogHybrid)).check(matches(isChecked()));
        //Papiere
        Espresso.onView(withId(R.id.checkDogPapers)).perform(click());
        Espresso.onView(withId(R.id.checkDogPapers)).check(matches(isChecked()));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.dogSaveBtn)).perform(click());

        Espresso.onView(withText("Preis darf nicht leer sein")).check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception {

       // mActivity = null;

    }
}