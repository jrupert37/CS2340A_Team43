package com.example.cs2340a_team43;
import org.junit.Test;
import androidx.test.core.app.ActivityScenario;
import static org.junit.Assert.assertEquals;
import com.example.cs2340a_team43.Models.Player;

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
    public void checkIfPlayerInBounds(){

    }

}


