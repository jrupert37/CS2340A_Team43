package com.example.cs2340a_team43;
import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.Views.GameActivity;

import org.junit.Test;
import static org.junit.Assert.*;
public class Sprint4UnitTests{
    //Test 1
    @Test
    public void EnemyDoesNotMoveOffScreen() {
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemyViewModel = new EnemyViewModel(test, "eyeball", mvm, 20, 8);
        int min = 0;
        int max = 4;
        int randomInt = (int) (Math.random() * (max - min + 1) + min);
        for(int i = 0; i < 100; i ++) {
            switch(randomInt) {
                case 0:
                    enemyViewModel.moveEnemyUp();
                    break;
                case 1:
                    enemyViewModel.moveEnemyDown();
                    break;
                case 2:
                    enemyViewModel.moveEnemyLeft();
                    break;
                case 3:
                    enemyViewModel.moveEnemyRight();
                    break;
            }
        }
        assertTrue(enemyViewModel.getEnemyX() >= 0 && enemyViewModel.getEnemyX() <= 40);
        assertTrue(enemyViewModel.getEnemyY() >= 0 && enemyViewModel.getEnemyY() <= 18);
    }
    //Test 2
    @Test
    public void EnemyDoesNotMoveThroughWall(){
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemyViewModel = new EnemyViewModel(test, "eyeball", mvm, 20, 8);
        for(int i = 0; i < 20; i ++) {
            enemyViewModel.moveEnemyUp();
        }
        assertTrue(enemyViewModel.getEnemyY() <= 1);
    }
    //Test 3
    @Test
    public void EnemyCannotUseExit(){
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemyViewModel = new EnemyViewModel(test, "eyeball", mvm, 2, 8);
        enemyViewModel.moveEnemyLeft();
        assertTrue(enemyViewModel.getEnemyX() == 1 && enemyViewModel.getEnemyY() == 8);
    }
    //Test 4
    @Test
    public void EnemyHaveDifferentMovementPatterns(){
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemy1 = new EnemyViewModel(test, "eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel(test, "cat", mvm, 2, 8);
        EnemyViewModel enemy3 = new EnemyViewModel(test, "grimreaper", mvm, 3, 8);
        EnemyViewModel enemy4 = new EnemyViewModel(test, "skeleton", mvm, 4, 8);
        EnemyFactory factoryTest = new EnemyFactory();
        ExecutableMovementPattern enemy1MP = factoryTest.getMovementPattern("eyeball", enemy1);
        ExecutableMovementPattern enemy2MP = factoryTest.getMovementPattern("cat", enemy2);
        ExecutableMovementPattern enemy3MP = factoryTest.getMovementPattern("grimreaper", enemy3);
        ExecutableMovementPattern enemy4MP = factoryTest.getMovementPattern("skeleton", enemy4);
        assertTrue(enemy1MP != enemy2MP && enemy2MP != enemy3MP && enemy3MP != enemy4MP);
    }
    //test5
    @Test
    public void EnemyHaveDifferentSpeeds(){
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemy1 = new EnemyViewModel(test, "eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel(test, "grimreaper", mvm, 2, 8);
        assertTrue(enemy1.getEnemySpeed() != enemy2.getEnemySpeed());
    }
    //test6
    @Test
    public void EnemyCollisionWithEnemy(){
        GameActivity test = new GameActivity();
        MapViewModel mvm = new MapViewModel(test, 18, 40);;
        EnemyViewModel enemy1 = new EnemyViewModel(test, "eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel(test, "eyeball", mvm, 2, 8);
        enemy1.moveEnemyRight();
        assertTrue(enemy1.getEnemyX() == 2);
    }
}

