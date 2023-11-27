package com.example.cs2340a_team43.EnemyMovementPatterns;

import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import java.util.Timer;
import java.util.TimerTask;

/*
 * This pattern of movement is made with the Skeleton enemy in mind, but could be used by
 * other enemy types.
 * Movement pattern is on a timer. An enemy with this pattern will start by moving left
 * until it reaches a wall, then turn around and move right until it reaches a wall, etc.
 * Enemy will move every 0.5 seconds.
 * This movement pattern uses the given EnemyViewModel's movement methods, which will
 * handle wall collisions.
 */
public class SkeletonMovementPattern extends Timer implements ExecutableMovementPattern {
    private final EnemyViewModel enemyViewModel;
    private String direction;

    public SkeletonMovementPattern(EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
        this.direction = "left"; // skeleton should be moving to the left at first
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
                int x = enemyViewModel.getEnemyX();
                int y = enemyViewModel.getEnemyY();
                switch (direction) {
                case "left":
                    if (enemyViewModel.willCollideWithWall(x - 1, y)) {
                        direction = "right";
                        enemyViewModel.moveEnemyRight();
                    } else {
                        enemyViewModel.moveEnemyLeft();
                    }
                    break;
                case "right":
                    if (enemyViewModel.willCollideWithWall(x + 1, y)) {
                        direction = "left";
                        enemyViewModel.moveEnemyLeft();
                    } else {
                        enemyViewModel.moveEnemyRight();
                    }
                    break;
                default:
                    break;
                }
            }
        }, 500, 500);
    }
} // SkeletonMovementPattern (ExecutableMovementPattern implementer)
