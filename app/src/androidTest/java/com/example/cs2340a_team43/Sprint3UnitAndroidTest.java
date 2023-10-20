package com.example.cs2340a_team43;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertTrue;

import android.os.RemoteException;

import androidx.test.uiautomator.UiDevice;

import com.example.cs2340a_team43.Models.Player;

public class Sprint3UnitAndroidTest {
    @Test
    public void testLevel1Boarder() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Player player = Player.getInstance();
        device.setOrientationLeft();
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        try {
            Thread.sleep(5000); //wait 5 seconds
        } catch (InterruptedException e) {

        }
        for (int pressButton = 0; pressButton < 100; pressButton++) {

        }
        for (int pressButton = 0; pressButton < 100; pressButton++) {
            player.moveRight();
        }
        for (int pressButton = 0; pressButton < 100; pressButton++) {
            player.moveDown();
        }
        for (int pressButton = 0; pressButton < 100; pressButton++) {
            player.moveUp();
        }
        assertTrue(player.getX() <= 33 && player.getX() >= 0);
        assertTrue(player.getY() <= 86 && player.getY() >= 0);
    }
}
