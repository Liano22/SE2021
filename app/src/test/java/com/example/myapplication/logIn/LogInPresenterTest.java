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

    // TF-1: Eingabe eines Usernames in das Feld „Username“
    @Test
    public void validateUsernameAllRightBooleanTest() {
        assertTrue(logInPresenterTest.validateUsername("TestUser"));
    }

    // TF-2: Keine Eingabe in das Feld „Username“
    @Test
    public void validateUsernameEmptyBooleanTest() {
        assertFalse(logInPresenterTest.validateUsername(""));
    }

    // TF-3: Keine Fehlermeldung bei Eingabe eines „Usernames“
    @Test
    public void validateUsernameViewNeverCalledTest() {
        logInPresenterTest.validateUsername("TestUser");
        verify(mockView, never()).setErrorMessage("username","Benutzername darf nicht leer sein");
    }

    // TF-3: Keine Fehlermeldung bei Eingabe eines „Usernames“
    @Test
    public void validateUsernameViewCalledNullTest() {
        logInPresenterTest.validateUsername("TestUser");
        verify(mockView).setErrorMessage("username",null);
    }

    // TF-4: Fehlermeldung bei keiner Eingabe in das Feld „Username“
    @Test
    public void validateUsernameViewCalledEmptyTest() {
        logInPresenterTest.validateUsername("");
        verify(mockView).setErrorMessage("username","Benutzername darf nicht leer sein");
    }

    // TF-4: Fehlermeldung bei keiner Eingabe in das Feld „Username“
    @Test
    public void validateUsernameViewNeverCalledNullTest() {
        logInPresenterTest.validateUsername("");
        verify(mockView, never()).setErrorMessage("username",null);
    }

    // TF-5: Eingabe eines Passworts in das Feld „Passwort“
    @Test
    public void validatePasswordAllRightBooleanTest() {
        assertTrue(logInPresenterTest.validatePassword("TestPassword"));
    }

    // TF-6: Keine Eingabe in das Feld „Passwort“
    @Test
    public void validatePasswordEmptyBooleanTest() {
        assertFalse(logInPresenterTest.validatePassword(""));
    }

    // TF-6: Keine Eingabe in das Feld „Passwort“
    @Test
    public void validatePasswordViewCalledNullTest() {
        logInPresenterTest.validatePassword("TestPassword");
        verify(mockView).setErrorMessage("password",null);
    }

    // TF-6: Keine Eingabe in das Feld „Passwort“
    @Test
    public void validatePasswordViewNeverCalledNullTest() {
        logInPresenterTest.validatePassword("");
        verify(mockView, never()).setErrorMessage("password",null);
    }

    // TF-7: Keine Fehlermeldung bei Eingabe eins Passwortes
    @Test
    public void validatePasswordViewNeverCalledTest() {
        logInPresenterTest.validatePassword("TestPassword");
        verify(mockView, never()).setErrorMessage("password","Passwort darf nicht leer sein");
    }

    // TF-7: Keine Fehlermeldung bei Eingabe eins Passwortes
    @Test
    public void validatePasswordViewCalledEmptyTest() {
        logInPresenterTest.validatePassword("");
        verify(mockView).setErrorMessage("password","Passwort darf nicht leer sein");
    }

    // TF-8: User aus Datenbank auslesen lassen
    @Test
    public void userExistsReadUserFromDatabaseTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.readUserFromDatabase("testUsername","username")).thenReturn(query);
        logInPresenterTest.userExists("testUsername", "testPassword");
        verify(mockModel).readUserFromDatabase("testUsername", "username");
    }

    // TF-9: User zum Auslesen freigeben
    @Test
    public void userExistValueEventListenerTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.readUserFromDatabase("testUsername","username")).thenReturn(query);
        logInPresenterTest.userExists("testUsername", "testPassword");
        verify(query).addListenerForSingleValueEvent(any());
    }

// --- Bennedict und Johanna ---
}