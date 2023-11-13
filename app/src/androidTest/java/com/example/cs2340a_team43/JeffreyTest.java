//package com.example.cs2340a_team43;
//
//import androidx.test.espresso.IdlingPolicies;
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.runner.AndroidJUnit4;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.uiautomator.UiDevice;
////import androidx.test.espresso.Espresso;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.*;
//import static androidx.test.espresso.matcher.ViewMatchers.*;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
//
//import com.example.cs2340a_team43.Models.Leaderboard;
//import com.example.cs2340a_team43.Models.LeaderboardNode;
//import com.example.cs2340a_team43.Models.Player;
//import com.example.cs2340a_team43.Models.LeaderboardNode;
//import com.example.cs2340a_team43.Models.RunMovement;
//import com.example.cs2340a_team43.Views.StartScreenActivity;
//import com.example.cs2340a_team43.Views.GameActivity;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Rule;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//
//import android.os.RemoteException;
//
//
//@RunWith(AndroidJUnit4.class)
//public class JeffreyTest {
//    @Rule
//    public ActivityScenarioRule<StartScreenActivity> activityRule = new ActivityScenarioRule<>(StartScreenActivity.class);
//
//    @Test
//    public void testLeaderboardNeverBelowZero() throws RemoteException {
//        UiDevice device = UiDevice.getInstance(getInstrumentation());
//
//        device.setOrientationLeft();
//        //device.setOrientationNatural();
//        //device.setOrientationRight();
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//        onView(withId(R.id.nextButton)).perform(click());
//        //wait 32 seconds to fully make score decrease to 0
//        try {
//            Thread.sleep(32000);
//        } catch (InterruptedException e) {
//
//        }
//        for(int pressButton = 0; pressButton < 3; pressButton++){
//            onView(withId(R.id.nextButton)).perform(click());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){
//
//            }
//        }
//        Leaderboard testLeaderboard = Leaderboard.getInstance();
//        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();
//        assertTrue(temp.getScore() > -1);
//    }
//
//    @Test
//    public void testScoreDecreases() throws RemoteException {
//        UiDevice device = UiDevice.getInstance(getInstrumentation());
//
//        device.setOrientationLeft();
//        //device.setOrientationNatural();
//        //device.setOrientationRight();
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//        onView(withId(R.id.nextButton)).perform(click());
//        try {
//            Thread.sleep(5000); //wait 5 seconds
//        } catch (InterruptedException e) {
//
//        }
//        for(int pressButton = 0; pressButton < 3; pressButton++){
//            onView(withId(R.id.nextButton)).perform(click());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){
//
//            }
//        }
//        Leaderboard testLeaderboard = Leaderboard.getInstance();
//        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();
//        assertTrue(temp.getScore() < 60);
//
//    }
//
//    //Joseph Test #2
//    @Test
//    public void testRestartButton() throws RemoteException {
//        UiDevice device = UiDevice.getInstance(getInstrumentation());
//
//        device.setOrientationLeft();
//        //device.setOrientationNatural();
//        //device.setOrientationRight();
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//        onView(withId(R.id.nextButton)).perform(click());
//        for(int pressButton = 0; pressButton < 3; pressButton++){
//            onView(withId(R.id.nextButton)).perform(click());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){
//
//            }
//        }
//        Leaderboard testLeaderboard = Leaderboard.getInstance();
//        LeaderboardNode temp = testLeaderboard.getMostRecentAttempt();
//
//        onView(withId(R.id.restartButton)).perform(click());
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//        onView(withId(R.id.nextButton)).perform(click());
//        for(int pressButton = 0; pressButton < 3; pressButton++){
//            onView(withId(R.id.nextButton)).perform(click());
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e){
//
//            }
//        }
//        LeaderboardNode temp2 = testLeaderboard.get(testLeaderboard.getSize() - 2);
//        assertNotEquals(temp, temp2);
//    }
//
//
//    @Test
//    public void testMoveDown() throws RemoteException {
//        UiDevice device = UiDevice.getInstance(getInstrumentation());
//
//        device.setOrientationLeft();
//        //device.setOrientationNatural();
//        //device.setOrientationRight();
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//
//        onView(withId(R.id.downButton)).check(matches(isDisplayed()));
//
//
//
//        for(int pressButton = 1; pressButton < 3; pressButton++){
//            onView(withId(R.id.downButton)).perform(click());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){
//
//            }
//        }
//
//        assertEquals(4, Player.getInstance().getY());
//
//    }
//    //Cason test 1
//    @Test
//    public void testLevel1Boarder() throws RemoteException {
//        UiDevice device = UiDevice.getInstance(getInstrumentation());
//        Player player = Player.getInstance();
//        device.setOrientationLeft();
//        onView(withId(R.id.startButton)).perform(click());
//        onView(withId(R.id.nameInputBox)).perform(typeText("Name"));
//        onView(withId(R.id.nextButton)).perform(click());
//        onView(withId(R.id.downButton)).check(matches(isDisplayed()));
//        onView(withId(R.id.upButton)).check(matches(isDisplayed()));
//        onView(withId(R.id.rightButton)).check(matches(isDisplayed()));
//        onView(withId(R.id.leftButton)).check(matches(isDisplayed()));
//        try {
//            Thread.sleep(5000); //wait 5 seconds
//        } catch (InterruptedException e) {
//
//        }
//        for (int pressButton = 0; pressButton < 100; pressButton++) {
//            onView(withId(R.id.leftButton)).perform(click());
//        }
//        for (int pressButton = 0; pressButton < 100; pressButton++) {
//            onView(withId(R.id.rightButton)).perform(click());
//        }
//        for (int pressButton = 0; pressButton < 100; pressButton++) {
//            onView(withId(R.id.upButton)).perform(click());
//        }
//        for (int pressButton = 0; pressButton < 100; pressButton++) {
//            onView(withId(R.id.downButton)).perform(click());
//        }
//        assertTrue(player.getX() <= 33 && player.getX() >= 0);
//        assertTrue(player.getY() <= 86 && player.getY() >= 0);
//    }
//    //Cason test 2
//    @Test
//    public void testStrategyDesignPattern() {
//        Player player = Player.getInstance();
<<<<<<< HEAD
//        Player fastPlayer;
=======
//        Player fastPlayer = new Player(new RunMovement());
>>>>>>> main
//        player.setInitialXY(1, 1);
//        player.setInitialXY(2, 1);
//        player.moveLeft();
//        fastPlayer.moveLeft();
//        int X1 = player.getX();
//        int X2 = player.getX();
//        assertEquals(0, X1);
//        assertEquals(0, X2);
//    }
//}
