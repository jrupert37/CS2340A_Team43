package com.example.cs2340a_team43;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340a_team43.Models.Player;

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
}
