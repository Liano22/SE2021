package com.example.myapplication.logIn;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.google.firebase.database.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

// --- Bennedict und Johanna ---

@RunWith(MockitoJUnitRunner.class)
public class LogInPresenterTest {

    LogInPresenter logInPresenterTest;

    @Mock
    LogInView mockView;

    @Mock
    LogInModel mockModel;

    @Mock
    Query query;

    @Before
    public void setUp() {
        mockView = Mockito.mock(LogInView.class);
        mockModel = Mockito.mock(LogInModel.class);

        logInPresenterTest = new LogInPresenter(mockView, mockModel);
    }

    @Test
    public void validateUsernameAllRightBooleanTest() {
        assertTrue(logInPresenterTest.validateUsername("TestUser"));
    }

    @Test
    public void validateUsernameEmptyBooleanTest() {
        assertFalse(logInPresenterTest.validateUsername(""));
    }

    @Test
    public void validateUsernameViewNeverCalledTest() {
        logInPresenterTest.validateUsername("TestUser");
        verify(mockView, never()).setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
    }

    @Test
    public void validateUsernameViewCalledNullTest() {
        logInPresenterTest.validateUsername("TestUser");
        verify(mockView).setErrorMessage(null);
    }

    @Test
    public void validateUsernameViewCalledEmptyTest() {
        logInPresenterTest.validateUsername("");
        verify(mockView).setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
    }
    @Test
    public void validateUsernameViewNeverCalledNullTest() {
        logInPresenterTest.validateUsername("");
        verify(mockView, never()).setErrorMessage(null);
    }

    @Test
    public void validatePasswordAllRightBooleanTest() {
        assertTrue(logInPresenterTest.validatePassword("TestPassword"));
    }

    @Test
    public void validatePasswordEmptyBooleanTest() {
        assertFalse(logInPresenterTest.validatePassword(""));
    }

    @Test
    public void validatePasswordViewNeverCalledTest() {
        logInPresenterTest.validatePassword("TestPassword");
        verify(mockView, never()).setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
    }

    @Test
    public void validatePasswordViewCalledNullTest() {
        logInPresenterTest.validatePassword("TestPassword");
        verify(mockView).setErrorMessage(null);
    }

    @Test
    public void validatePasswordViewCalledEmptyTest() {
        logInPresenterTest.validatePassword("");
        verify(mockView).setErrorMessage("Benutzername oder Passwort darf nicht leer sein");
    }
    @Test
    public void validatePasswordeViewNeverCalledNullTest() {
        logInPresenterTest.validatePassword("");
        verify(mockView, never()).setErrorMessage(null);
    }

    @Test
    public void userExistsReadUserFromDatabaseTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.readUserFromDatabase("testUsername","username")).thenReturn(query);
        logInPresenterTest.userExists("testUsername", "testPassword");
        verify(mockModel).readUserFromDatabase("testUsername", "username");
    }

    @Test
    public void userExistValueEventListenerTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.readUserFromDatabase("testUsername","username")).thenReturn(query);
        logInPresenterTest.userExists("testUsername", "testPassword");
        verify(query).addListenerForSingleValueEvent(any());
    }

// --- Bennedict und Johanna ---
}