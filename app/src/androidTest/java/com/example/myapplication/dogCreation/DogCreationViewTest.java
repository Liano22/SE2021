package com.example.myapplication.dogCreation;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DogCreationViewTest {

    @Rule
    public ActivityScenarioRule<DogCreationView> mActivityScenarioRule = new ActivityScenarioRule<DogCreationView>(DogCreationView.class);

    private ActivityScenario<DogCreationView> mActivity = null;

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testLaunch(){

    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;

    }
}