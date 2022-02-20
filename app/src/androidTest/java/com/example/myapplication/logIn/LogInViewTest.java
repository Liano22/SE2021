package com.example.myapplication.logIn;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

//-- Johanna und Bennedict --

public class LogInViewTest {

    @Rule
    public ActivityScenarioRule<LogInView> mLogInTestRule = new ActivityScenarioRule<LogInView>(LogInView.class);


    private String username = "TestUser";
    private String password = "123456789";

    @Before
    public void setUp() throws Exception {
    }

    // TF-1: Alle Felder sind ausfüllbar und werden angezeigt
    @Test
    public void testUserInputAllRightScenario(){
        Espresso.onView(withId(R.id.usernameLogInTextInput)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.usernameLogInTextInput)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordLogInTextInput)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.passwordLogInTextInput)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.logInBtn)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.logInBtn)).perform(click());
    }

    // TF-2: Keine Eingabe eines Benutzernamens und Passworts
    @Test
    public void testNoUserInputScenario(){
        Espresso.onView(withId(R.id.logInBtn)).perform(click());
        Espresso.onView(withText("Benutzername darf nicht leer sein")).check(matches(isDisplayed()));
        Espresso.onView(withText("Passwort darf nicht leer sein")).check(matches(isDisplayed()));
    }

    // TF-3: Eingabe eines Usernames, aber keines Passworts
    @Test
    public void testUserInputOnlyUsernameScenario(){
        Espresso.onView(withId(R.id.usernameLogInTextInput)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.logInBtn)).perform(click());
        Espresso.onView(withText("Benutzername darf nicht leer sein")).check(doesNotExist());
        Espresso.onView(withText("Passwort darf nicht leer sein")).check(matches(isDisplayed()));
    }

    // TF-4: Eingabe eines Passworts, aber keines Usernames
    @Test
    public void testUserInputOnlyPasswordScenario(){
        Espresso.onView(withId(R.id.passwordLogInTextInput)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.logInBtn)).perform(click());
        Espresso.onView(withText("Benutzername darf nicht leer sein")).check(matches(isDisplayed()));
        Espresso.onView(withText("Passwort darf nicht leer sein")).check(doesNotExist());
    }

    @After
    public void tearDown() throws Exception {
    }


}

//-- Johanna und Bennedict --