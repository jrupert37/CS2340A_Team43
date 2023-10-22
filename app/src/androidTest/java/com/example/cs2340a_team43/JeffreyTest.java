package com.example.cs2340a_team43;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.UiDevice;
//import androidx.test.espresso.Espresso;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.LeaderboardNode;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.LeaderboardNode;
import com.example.cs2340a_team43.Views.StartScreenActivity;
import com.example.cs2340a_team43.Views.GameActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.os.RemoteException;


@RunWith(AndroidJUnit4.class)
public class JeffreyTest {
    @Rule
    public ActivityScenarioRule<StartScreenActivity> activityRule = new ActivityScenarioRule<>(StartScreenActivity.class);

    @Test
    public void testLeaderboardNeverBelowZero() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        device.setOrientationLeft();
        //device.setOrientationNatural();
        //device.setOrientationRight();
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        //wait 32 seconds to fully make score decrease to 0
        try {
            Thread.sleep(32000);
        } catch (InterruptedException e) {

        }
        for(int pressButton = 0; pressButton < 3; pressButton++){
            onView(withId(R.id.nextButton)).perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
        Leaderboard testLeaderboard = Leaderboard.getInstance();
        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();
        assertTrue(temp.getScore() > -1);
    }

    @Test
    public void testScoreDecreases() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        device.setOrientationLeft();
        //device.setOrientationNatural();
        //device.setOrientationRight();
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        try {
            Thread.sleep(5000); //wait 5 seconds
        } catch (InterruptedException e) {

        }
        for(int pressButton = 0; pressButton < 3; pressButton++){
            onView(withId(R.id.nextButton)).perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
        Leaderboard testLeaderboard = Leaderboard.getInstance();
        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();
        assertTrue(temp.getScore() < 60);

    }

    //Joseph Test #2
    @Test
    public void testRestartButton() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        device.setOrientationLeft();
        //device.setOrientationNatural();
        //device.setOrientationRight();
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        for(int pressButton = 0; pressButton < 3; pressButton++){
            onView(withId(R.id.nextButton)).perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
        Leaderboard testLeaderboard = Leaderboard.getInstance();
        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();

        onView(withId(R.id.restartButton)).perform(click());
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        for(int pressButton = 0; pressButton < 3; pressButton++){
            onView(withId(R.id.nextButton)).perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
        LeaderboardNode temp2 = testLeaderboard.get(testLeaderboard.getSize() - 2);
        assertNotEquals(temp, temp2);
    }


    @Test
    public void testMoveDown() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        device.setOrientationLeft();
        //device.setOrientationNatural();
        //device.setOrientationRight();
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());

        onView(withId(R.id.downButton)).check(matches(isDisplayed()));



        for(int pressButton = 1; pressButton < 3; pressButton++){
            onView(withId(R.id.downButton)).perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }

        assertEquals(4, Player.getInstance().getY());

    }
}
