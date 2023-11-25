package com.example.cs2340a_team43.EnemyMovementPatterns;

import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
* This pattern of movement is made with the Cat enemy in mind, but could be used by 
* other enemy types. 
* Movement pattern is on a timer. An enemy with this pattern will move in a clockwise
* square pattern.
* After every "moveTime" milliseconds, the enemy will move to the next square in the
* pattern determined by the "nextDirection" in "directions".
* "moveTime" is generated one time randomly at the beginning of movement execution,
* between 500 (inclusive) and 1000 (inclusive).
* This movement pattern uses the given EnemyViewModel's movement methods, which will
* handle wall collisions.
*/
public class CatMovementPattern extends Timer implements ExecutableMovementPattern {
    private final EnemyViewModel evm;
    private int nextDirection = 0;
    private final String[] directions = new String[] {
        "right", "right", "down", "down", "left", "left", "up", "up"
    };
    private final long moveTime;

    public CatMovementPattern(EnemyViewModel evm) {
        this.evm = evm;
        // generate a random moveTime to be used by the movement timer
        moveTime = new Random().nextInt(301) + 200;
    }

    @Override
    public void stop() {
        // cancel the Timer, stops enemy movement
        super.cancel();
    }

    @Override
    public void start() {
        // start new TimerTask that moves enemy to next position every "moveTime" milliseconds
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                String next = directions[nextDirection];
                switch (next) {
                    case "right":
                        evm.moveEnemyRight();
                        break;
                    case "down":
                        evm.moveEnemyDown();
                        break;
                    case "left":
                        evm.moveEnemyLeft();
                        break;
                    case "up":
                        evm.moveEnemyUp();
                        break;
                    default:
                        break;
                }
                // limit nextDirection index from moving out of bounds of directions[]
                nextDirection = (nextDirection + 1) % directions.length;
            }
        }, moveTime, moveTime);
    }
} // CatMovementPattern (ExecutableMovementPattern implementer)