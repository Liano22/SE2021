package com.example.myapplication.filter;

import static org.junit.Assert.*;

import android.app.DownloadManager;

import com.example.myapplication.logIn.LogInModel;
import com.example.myapplication.logIn.LogInPresenter;
import com.example.myapplication.logIn.LogInView;
import com.google.firebase.database.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilterPresenterTest {

    FilterPresenter filterPresenterTest;

    @Mock
    FilterModel mockModel;

    @Mock
    FilterView mockView;

    @Mock
    Query query;

    @Before
    public void setUp() {
        mockView = Mockito.mock(FilterView.class);
        mockModel = Mockito.mock(FilterModel.class);

        filterPresenterTest = new FilterPresenter(mockView, mockModel);
    }

    @Test
    public void validateMinPriceEmpty() {
        assertFalse(filterPresenterTest.validateFields("Terrier", "12", "", "50", "optional"));
    }

    @Test
    public void validateMaxPriceEmpty() {
        assertFalse(filterPresenterTest.validateFields("Terrier", "12", "0", "", "optional"));
    }

    @Test
    public void validateFieldsAlright() {
        assertTrue(filterPresenterTest.validateFields("Terrier", "12", "0", "50", "vorhanden"));
    }



}