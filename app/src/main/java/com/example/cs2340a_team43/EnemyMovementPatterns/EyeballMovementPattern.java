package com.example.cs2340a_team43.EnemyMovementPatterns;

import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
 * This pattern of movement is made with the Eyeball enemy in mind, but could be used by
 * other enemy types.
 * Movement pattern is on a timer. An enemy with this pattern will move in a random
 * direction every 1.25 seconds.
 * The next direction the enemy will move is generated randomly between up, down,
 * left, and right.
 * This movement pattern uses the given EnemyViewModel's movement methods, which will
 * handle wall collisions.
 */
public class EyeballMovementPattern extends Timer implements ExecutableMovementPattern {
    private final EnemyViewModel evm;

    public EyeballMovementPattern(EnemyViewModel evm) {
        this.evm = evm;
    }

    @Override
    public void stop() {
        super.cancel();
    }

    @Override
    public void start() {
        Random rand = new Random();
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                int randomValue = rand.nextInt(4);
                switch (randomValue) {
                case 0:
                    evm.moveEnemyDown();
                    break;
                case 1:
                    evm.moveEnemyUp();
                    break;
                case 2:
                    evm.moveEnemyRight();
                    break;
                case 3:
                    evm.moveEnemyLeft();
                    break;
                default:
                    break;
                }
            }
        }, 1000, 1250);
    }
} // EyeballMovementPattern (ExecutableMovementPattern implementer)
