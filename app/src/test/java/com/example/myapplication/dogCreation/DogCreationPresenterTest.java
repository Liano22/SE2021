package com.example.myapplication.dogCreation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//import org.junit.jupiter.api.BeforeAll;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


// --- Bennedict und Johanna ---

@RunWith(MockitoJUnitRunner.class)
public class DogCreationPresenterTest {

    Dog testDog;

    DogCreationPresenter dogCreationPresenterTest;

    @Mock
    DogCreationView mockView;

    @Mock
    DogCreationModel mockModel;

    @Mock
    Query query;

    @Before
    public void setupTests(){
        mockModel = Mockito.mock(DogCreationModel.class);
        mockView = Mockito.mock(DogCreationView.class);
        dogCreationPresenterTest = new DogCreationPresenter(mockView, mockModel);
    }

    @Test
    public void saveDogGetNextDogIDTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.getNextDogID()).thenReturn(query);
        dogCreationPresenterTest.saveDog("BenniBanditos","Testine", "12", "weiblich", "Chihuahua", "12323534634534214", "Weltumrunderin","VB", false,true);
        verify(mockModel).getNextDogID();
    }

    @Test
    public void saveDogValueEventListenerTest() {
        query = Mockito.mock(Query.class);
        Mockito.when(mockModel.getNextDogID()).thenReturn(query);
        dogCreationPresenterTest.saveDog("BenniBanditos","Testine", "12", "weiblich", "Chihuahua", "12323534634534214", "Weltumrunderin","VB", false,true);
        verify(query).addListenerForSingleValueEvent(any());
    }

    @Test
    public void linkDogToUserTest() {
        dogCreationPresenterTest.linkDogToUser("Kunibert", 42);
        verify(mockModel).changeUserDogList("Kunibert", "42");
    }

    @Test
    public void validateFieldsAllRightBoolean() {
        assertTrue(dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "Ein tolles Hündeli!", "12000"));
    }

    @Test
    public void validateFieldsNameEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("", "12", "Chihuahua", "Ein tolles Hündeli!", "12000"));

    }

    @Test
    public void validateFieldsAgeEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("Kunibert", "", "Chihuahua", "Ein tolles Hündeli!", "12000"));

    }

    @Test
    public void validateFieldsRaceEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("Kunibert", "12", "", "Ein tolles Hündeli!", "12000"));

    }

    @Test
    public void validateFieldsBioEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "", "12000"));

    }

    @Test
    public void validateFieldsPriceEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "Ein tolles Hündeli!", ""));

    }

    @Test
    public void validateFieldsAllEmptyBoolean() {
        assertFalse(dogCreationPresenterTest.validateFields("", "", "", "", ""));

    }

    @Test
    public void validateFieldsAllRightViewNeverCalled() {
        dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "Ein tolles Hündeli!", "12000");
        verify(mockView, never()).setErrorMessage(anyString(), anyString());
    }

    @Test
    public void validateFieldsNameEmptyViewCalled() {
        dogCreationPresenterTest.validateFields("", "12", "Chihuahua", "Ein tolles Hündeli!", "12000");
        verify(mockView).setErrorMessage("name", "Name darf nicht leer sein");
    }

    @Test
    public void validateFieldsAgeEmptyViewCalled() {
        dogCreationPresenterTest.validateFields("Kunibert", "", "Chihuahua", "Ein tolles Hündeli!", "12000");
        verify(mockView).setErrorMessage("age", "Alter darf nicht leer sein");
    }

    @Test
    public void validateFieldsRaceEmptyViewCalled() {
        dogCreationPresenterTest.validateFields("Kunibert", "12", "", "Ein tolles Hündeli!", "12000");
        verify(mockView).setErrorMessage("race", "Rasse darf nicht leer sein");
    }

    @Test
    public void validateFieldsBioEmptyViewCalled() {
        dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "", "12000");
        verify(mockView).setErrorMessage("bio", "Bio darf nicht leer sein");
    }

    @Test
    public void validateFieldsPriceEmptyViewCalled() {
        dogCreationPresenterTest.validateFields("Kunibert", "12", "Chihuahua", "Ein tolles Hündeli!", "");
        verify(mockView).setErrorMessage("price", "Preis darf nicht leer sein");
    }

    @Test
    public void activateSnackbar() {
        dogCreationPresenterTest.activateSnackbar("Es gibt ein Problem!");
        verify(mockView).setSnackbar("Es gibt ein Problem!");
    }

    // --- Bennedict und Johanna ---
}