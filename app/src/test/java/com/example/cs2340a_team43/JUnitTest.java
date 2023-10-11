package com.example.cs2340a_team43;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.Player;

import java.util.Calendar;
import java.util.TimeZone;

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

    // Joseph Test #1
    public void testLeaderboardSingleton() {
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addAttempt("Player", 2, Calendar.getInstance(),
                Calendar.getInstance(TimeZone.getTimeZone("GMT - 3:00")));
        // Node attempt1 = leaderboard.getMostRecentAttempt();
        // assertEquals(attempt1, leaderboard.getMostRecentAttempt());
        assertEquals(leaderboard.getMostRecentAttempt(),
                leaderboard.get(leaderboard.getSize() - 1));
        // Last element in the Array in the Leaderboard class should be the mostRecentAttempt.

        Leaderboard leaderboard2 = Leaderboard.getInstance();
        // Node attempt2 = leaderboard2.getMostRecentAttempt();
        // assertEquals(attempt2, leaderboard2.getMostRecentAttempt());
        leaderboard2.getMostRecentAttempt().setName("Player2");
        leaderboard2.getMostRecentAttempt().setScore(5);
        leaderboard2.getMostRecentAttempt().setStartTime(Calendar.getInstance(TimeZone.getTimeZone
                ("GMT - 1:00")));
        leaderboard2.getMostRecentAttempt().setEndTime(Calendar.getInstance(TimeZone.getTimeZone
                ("GMT - 4:00")));
        assertEquals(leaderboard2.getMostRecentAttempt(),
                leaderboard2.get(leaderboard2.getSize() - 1));

        assertEquals(leaderboard2.getMostRecentAttempt(), leaderboard.getMostRecentAttempt());
        // The next 4 tests below are for safety, they should be covered in the test above.
        assertEquals(leaderboard2.getMostRecentAttempt().getName(),
                leaderboard.getMostRecentAttempt().getName());
        assertEquals(leaderboard2.getMostRecentAttempt().getScore(),
                leaderboard.getMostRecentAttempt().getScore());
        assertEquals(leaderboard2.getMostRecentAttempt().getStartTime(),
                leaderboard.getMostRecentAttempt().getStartTime());
        assertEquals(leaderboard2.getMostRecentAttempt().getEndTime(),
                leaderboard.getMostRecentAttempt().getEndTime());
    }
}
