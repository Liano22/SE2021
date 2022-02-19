package com.example.myapplication.signUp;

import static org.junit.Assert.*;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

//Ralf & Karl
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterTest {

    SignUpPresenter signUpPresenterTest;

    @Mock
    SignUpModel mockModel;

    @Mock
    SignUpView mockView;

    @Mock
    Query query;

    @Before
    public void setUp() {
        mockView = Mockito.mock(SignUpView.class);
        mockModel = Mockito.mock(SignUpModel.class);
        signUpPresenterTest = new SignUpPresenter(mockView, mockModel);
    }

    @Test
    public void validateUsernameEmpty() {
        assertFalse(signUpPresenterTest.validateUsername(""));
    }

    @Test
    public void validateUsernameHasSpaces() {
        assertFalse(signUpPresenterTest.validateUsername(" r a l f"));
    }

    @Test
    public void validateUsernameTooLong() {
        assertFalse(signUpPresenterTest.validateUsername("fhrutzghvndbfhgzthfzrgthdjfh"));
    }

    @Test
    public void userExists() {
    }

    @Test
    public void validatePasswordEmpty() {
        assertFalse(signUpPresenterTest.validatePassword(""));
    }

    @Test
    public void validatePostalCodeEmpty() {
        assertFalse(signUpPresenterTest.validatePostalCode(""));
    }

    @Test
    public void validatePostalCodeTooShort() {
        assertFalse(signUpPresenterTest.validatePostalCode("1234"));
    }

    @Test
    public void validatePostalCodeTooLong() {
        assertFalse(signUpPresenterTest.validatePostalCode("123456"));
    }
}