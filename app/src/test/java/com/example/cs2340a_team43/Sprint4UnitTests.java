package com.example.cs2340a_team43;
import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.Views.GameActivity;
import static org.junit.Assert.assertEquals;

import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;

import org.junit.Test;

import org.junit.Test;
import static org.junit.Assert.*;
public class Sprint4UnitTests{
    //Test 1
    /*@Test
    public void EnemyDoesNotMoveOffScreen() {
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 40; col++) {
                if (row == 0 || row == 17 || col == 0 || col == 39) {
                    x[row][col] = Map.MapObject.WALL;
                }
            }
        }
        mvm.setMapLayout(x);
        EnemyViewModel enemyViewModel = new EnemyViewModel("eyeball", mvm, 20, 8);
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
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        x[0][20] = Map.MapObject.WALL;
        mvm.setMapLayout(x);
        EnemyViewModel enemyViewModel = new EnemyViewModel("eyeball", mvm, 20, 8);
        for (int i = 0; i < 20; i ++) {
            enemyViewModel.moveEnemyUp();
        }
        assertTrue(enemyViewModel.getEnemyY() <= 1);
    }
    //Test 3
    @Test
    public void EnemyCannotUseExit(){
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        EnemyViewModel enemyViewModel = new EnemyViewModel("eyeball", mvm, 2, 8);
        enemyViewModel.moveEnemyLeft();
        assertTrue(enemyViewModel.getEnemyX() == 1 && enemyViewModel.getEnemyY() == 8);
    }
    //Test 4
    @Test
    public void EnemyHaveDifferentMovementPatterns(){
        MapViewModel mvm = new MapViewModel( 18, 40);;
        EnemyViewModel enemy1 = new EnemyViewModel("eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel( "cat", mvm, 2, 8);
        EnemyViewModel enemy3 = new EnemyViewModel("grimreaper", mvm, 3, 8);
        EnemyViewModel enemy4 = new EnemyViewModel( "skeleton", mvm, 4, 8);
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
        MapViewModel mvm = new MapViewModel( 18, 40);;
        EnemyViewModel enemy1 = new EnemyViewModel("eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel("skeleton", mvm, 2, 8);
        assertTrue(enemy1.getEnemySpeed() == enemy2.getEnemySpeed());
    }
    //test6
    @Test
    public void EnemyCollisionWithEnemy(){
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        EnemyViewModel enemy1 = new EnemyViewModel( "eyeball", mvm, 1, 8);
        EnemyViewModel enemy2 = new EnemyViewModel("eyeball", mvm, 2, 8);
        enemy1.moveEnemyRight();
        assertTrue(enemy1.getEnemyX() == 2);
    }
    @Test
    public void testPlayerCollisionWithEnemyDecreasesHealth() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Easy");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        assertEquals(50, pvm.getPlayerHP());
        pvm.movePlayerDown();
        assertEquals(48, pvm.getPlayerHP());
    }

    @Test
    public void testEnemyCollisionWithPlayerDecreasesHealth() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Easy");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        evm.addCollisionObserver(pvm);
        assertEquals(50, pvm.getPlayerHP());
        evm.moveEnemyUp();
        assertEquals(48, pvm.getPlayerHP());
    }

    @Test
    public void testDamageTakenChangesWithDifficulty() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Easy");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        assertEquals(50, pvm.getPlayerHP());
        pvm.movePlayerDown();
        assertEquals(48, pvm.getPlayerHP());

        pvm.setInitialPlayerXY(1, 1);
        pvm.setPlayerInitialHP("Medium");
        pvm.removeCollisionObserver(evm);
        evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        pvm.addCollisionObserver(evm);
        assertEquals(30, pvm.getPlayerHP());
        pvm.movePlayerDown();
        assertEquals(27, pvm.getPlayerHP());

        pvm.setInitialPlayerXY(1, 1);
        pvm.setPlayerInitialHP("Hard");
        pvm.removeCollisionObserver(evm);
        evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        pvm.addCollisionObserver(evm);
        assertEquals(15, pvm.getPlayerHP());
        pvm.movePlayerDown();
        assertEquals(10, pvm.getPlayerHP());
    }

    @Test
    public void testPlayerRecoilsWhenCollideWithEnemy() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Easy");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        pvm.movePlayerDown();
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
    }

    @Test
    public void testPlayerRecoilsWhenEnemyCollidesWithThem() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Easy");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 3);
        assertEquals(1, evm.getEnemyX());
        assertEquals(3, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        evm.addCollisionObserver(pvm);
        pvm.movePlayerDown();
        assertEquals(1, pvm.getPlayerX());
        assertEquals(2, pvm.getPlayerY());
        evm.moveEnemyUp();
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
    }

    @Test
    public void testPlayerIsAliveLogic() {
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(40, 18);
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.setPlayerInitialHP("Hard");
        MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        mvm.setMapLayout(x);
        pvm.setMap(mvm);
        EnemyViewModel evm = new EnemyViewModel("eyeball", mvm, 1, 2);
        assertEquals(1, evm.getEnemyX());
        assertEquals(2, evm.getEnemyY());
        pvm.addCollisionObserver(evm);
        assertTrue(pvm.isAlive());
        pvm.movePlayerDown();
        assertTrue(pvm.isAlive());
        pvm.movePlayerDown();
        assertTrue(pvm.isAlive());
        pvm.movePlayerDown();
        assertFalse(pvm.isAlive());
    }*/
}
