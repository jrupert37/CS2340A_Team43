package com.example.cs2340a_team43.EnemyMovementPatterns;

import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
 * This pattern of movement is made with the Grimreaper enemy in mind, but could be used by
 * other enemy types.
 * Movement pattern is on a timer. An enemy with this pattern will move in a random
 * direction every 0.5 seconds.
 * The next direction the enemy will move is generated randomly between up, down,
 * left, and right, similar to Eyeball, but faster.
 * This movement pattern uses the given EnemyViewModel's movement methods, which will
 * handle wall collisions.
 */
public class GrimreaperMovementPattern extends Timer implements ExecutableMovementPattern {
    private final EnemyViewModel enemyViewModel;
    private final Random rand = new Random();

    public GrimreaperMovementPattern(EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
    }

    @Override
    public void stop() {
        // cancel the Timer, stops enemy movement
        super.cancel();
    }

    @Override
    public void start() {
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                int randomValue = rand.nextInt(4);
                switch (randomValue) {
                    case 0:
                        enemyViewModel.moveEnemyDown();
                        break;
                    case 1:
                        enemyViewModel.moveEnemyUp();
                        break;
                    case 2:
                        enemyViewModel.moveEnemyRight();
                        break;
                    case 3:
                        enemyViewModel.moveEnemyLeft();
                        break;
                    default:
                        break;
                }
            }
        }, 500, 500);
    }
} // GrimreaperMovementPattern (ExecutableMovementPattern implementer)
