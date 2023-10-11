package com.example.cs2340a_team43;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.Player;

import java.util.Calendar;

public class JUnitTest {

    //Vishnu Test #1
    @Test
    public void testPlayerHPDifficultyChanges() {
        Player player = Player.getInstance();
        player.setHP("Easy");
        assertEquals(50, player.getHP());
        player.setHP("Medium");
        assertEquals(30, player.getHP());
        player.setHP("Hard");
        assertEquals(15, player.getHP());

    }
    //Vishnu Test #2
    @Test
    public void testPlayerSingleton() {
        Player player = Player.getInstance();
        player.setHP("Easy");
        player.setDamage(10.0);
        assertEquals(50, player.getHP());
        assertEquals(10.0, player.getDamage(), 0);

        Player player2 = Player.getInstance();
        player2.setHP("Medium");
        player2.setDamage(20.0);
        assertEquals(30, player.getHP());
        assertEquals(20.0, player.getDamage(), 0);

        assertEquals(player2.getHP(), player.getHP());
        assertEquals(player2.getDamage(), player.getDamage(), 0);
    }

    // Jackson Test #1
    @Test
    public void testLeaderboardUpdatesOrder() {
        Leaderboard leaderboard = Leaderboard.getInstance();
        Calendar calendar = Calendar.getInstance();

        leaderboard.addAttempt("player1", 60, calendar, calendar);

        // Players are sorted in descending score order from index 0 to size-1 of the leaderboard
        assertEquals("player1", leaderboard.get(0).getName());
        assertEquals(60, leaderboard.get(0).getScore());

        leaderboard.addAttempt("player2", 65, calendar, calendar);

        assertEquals("player2", leaderboard.get(0).getName());
        assertEquals(65, leaderboard.get(0).getScore());
        assertEquals("player1", leaderboard.get(1).getName());
        assertEquals(60, leaderboard.get(1).getScore());

        leaderboard.addAttempt("player3", 62, calendar, calendar);

        assertEquals("player2", leaderboard.get(0).getName());
        assertEquals(65, leaderboard.get(0).getScore());
        assertEquals("player3", leaderboard.get(1).getName());
        assertEquals(62, leaderboard.get(1).getScore());
        assertEquals("player1", leaderboard.get(2).getName());
        assertEquals(60, leaderboard.get(2).getScore());

        leaderboard.clear();
    }

    // Jackson Test #2
    @Test
    public void checkMostRecentAttemptUpdated() {
        Leaderboard leaderboard = Leaderboard.getInstance();
        Calendar calendar = Calendar.getInstance();

        leaderboard.addAttempt("player1", 60, calendar, calendar);
        assertEquals("player1", leaderboard.getMostRecentAttempt().getName());
        assertEquals(60, leaderboard.getMostRecentAttempt().getScore());

        leaderboard.addAttempt("player2", 50, calendar, calendar);
        assertEquals("player2", leaderboard.getMostRecentAttempt().getName());
        assertEquals(50, leaderboard.getMostRecentAttempt().getScore());

        leaderboard.addAttempt("player3", 70, calendar, calendar);
        assertEquals("player3", leaderboard.getMostRecentAttempt().getName());
        assertEquals(70, leaderboard.getMostRecentAttempt().getScore());

        leaderboard.clear();
    }
}
