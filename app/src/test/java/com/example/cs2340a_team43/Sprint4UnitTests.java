package com.example.cs2340a_team43;

import static org.junit.Assert.assertEquals;

import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;

import org.junit.Test;

public class Sprint4UnitTests {
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
        //assertEquals(48, pvm.getPlayerHP());
    }

    @Test
    public void testEnemyCollisionWithPlayerDecreasesHealth() {

    }
}
