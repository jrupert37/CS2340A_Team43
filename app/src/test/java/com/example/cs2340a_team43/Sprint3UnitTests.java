package com.example.cs2340a_team43;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;


public class Sprint3UnitTests {
    //Lauren Test 1
    @Test
    public void leftMovement() {
        Player player = Player.getInstance();
        player.setInitialXY(1, 1);
        player.moveLeft();
        int newX = player.getX();
        assertEquals(0, newX);
    }
    @Test
    public void testInitialXY() {
        Player player = Player.getInstance();
        player.setInitialXY(0,0);
        int x = player.getX();
        int y = player.getY();
        assertEquals(0, x);
        assertEquals(0, y);
    }

}


