package Sprint3UnitTest;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340a_team43.Models.Player;

public class Sprint3Test {
    @Test
    public void testLevel1Boarders(){
        Player player = Player.getInstance();
        for (int pressButton = 0; pressButton < 100; pressButton++) {
            player.moveLeft();
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

