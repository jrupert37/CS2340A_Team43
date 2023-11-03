package Sprint2UnitTest;
//package com.journaldev.mockito;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import android.content.Context;
import com.example.cs2340a_team43.Models.Player;

import static org.junit.Assert.*;


public class UnitTests {
    private Context context;
    //Lauren Test #1
    @Test
    public void testNameNullCheck() {
        Player player = Player.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            player.setName(null);
        });
    }
    @Test
    public void testNameEmptyCheck() {
        Player player = Player.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            player.setName("");
        });
    }
    @Test
    public void testSetName() {
        Player player = Player.getInstance();
        player.setName("Bob");
        String name = player.getName();
        assertEquals("Bob", name);
    }
    @Test
    public void testGetName() {
        Player player = Player.getInstance();
        player.setName("John");
        String name = player.getName();
        assertEquals("John", name);

    }
}
