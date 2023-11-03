package com.example.cs2340a_team43;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.UiDevice;
import androidx.test.espresso.Espresso;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import java.util.Random;

import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.LeaderboardNode;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.LeaderboardNode;
import com.example.cs2340a_team43.Models.RunMovement;
import com.example.cs2340a_team43.Views.StartScreenActivity;
import com.example.cs2340a_team43.Views.GameActivity;
import androidx.test.espresso.action.ViewActions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.os.RemoteException;
import android.view.KeyEvent;


@RunWith(AndroidJUnit4.class)
public class Sprint3InstrumentalUnitTest {
    @Rule
    public ActivityScenarioRule<StartScreenActivity> activityRule = new ActivityScenarioRule<>(StartScreenActivity.class);

    @Test
    public void playerNeverLeaveScreen() throws RemoteException{
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Player player = Player.getInstance();
        device.setOrientationLeft();

        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        Random random = new Random(System.currentTimeMillis());
        int randomInt;
        for(int i = 0; i < 100; i++) {
            randomInt = random.nextInt(4);
            switch(randomInt) {
                case 0:
                    onView(withId(R.id.upButton)).perform(click());
                    break;
                case 1:
                    onView(withId(R.id.downButton)).perform(click());
                    break;
                case 2:
                    onView(withId(R.id.leftButton)).perform(click());
                    break;
                case 3:
                    onView(withId(R.id.rightButton)).perform(click());
                    break;
            }
        }
        assertTrue(player.getX() > 0 && player.getX() < 41);
        assertTrue(player.getY() > 0 && player.getY() < 41);
    }
    @Test
    public void playerExitResetsPosition() throws RemoteException{
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Player player = Player.getInstance();
        device.setOrientationLeft();

        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        for(int i = 0; i < 4; i++) {
            onView(withId(R.id.rightButton)).perform(click());
        }
        for(int i = 0; i < 12; i++) {
            onView(withId(R.id.downButton)).perform(click());
        }
        for(int i = 0; i < 4; i++) {
            onView(withId(R.id.leftButton)).perform(click());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }
        assertEquals(0, player.getX());
        assertEquals(0,player.getY());
    }
    @Test
    public void testFloor1Border() throws RemoteException{
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Player player = Player.getInstance();
        device.setOrientationLeft();

        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        for(int i = 0; i < 40; i++) {
            onView(withId(R.id.upButton)).perform(click());
        }
        for(int i = 0; i < 40; i++) {
            onView(withId(R.id.downButton)).perform(click());
        }
        for(int i = 0; i < 40; i++) {
            onView(withId(R.id.leftButton)).perform(click());
        }
        for(int i = 0; i < 40; i++) {
            onView(withId(R.id.rightButton)).perform(click());
        }
        assertTrue(player.getX() > 0 && player.getX() < 41);
        assertTrue(player.getY() > 0 && player.getY() < 41);
    }
}
