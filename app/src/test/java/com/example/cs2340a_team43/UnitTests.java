package com.example.cs2340a_team43;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import android.content.Context;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.TempScoreFeature;
import static org.junit.Assert.*;
import android.os.Handler;
import android.os.Looper;


public class UnitTests {
    private Context context;
    //Lauren Test #1
    @Test
    public void testNameEmptyCheck() {
        Player player = Player.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            player.setName(null);
        });
    }
    @Test
    public void testNameNullCheck() {
        Player player = Player.getInstance();
        assertTrue(player.getName() == null || player.getName().trim().isEmpty());
    }
/*
    @Test
    public void scoreSet() {
        TempScoreFeature temp = new TempScoreFeature();
        int initialScore = temp.getHealthScore();
        for (int i = 0; i < 5; i++) {
            temp.onCreate();
            int updatedScore = temp.getHealthScore();
            assertTrue("True", updatedScore < initialScore);
            initialScore = updatedScore;

            final Object lock = new Object();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }, 1000);
            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
