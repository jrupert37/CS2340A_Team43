package com.example.cs2340a_team43;
import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.Models.HealthDecorator;
import com.example.cs2340a_team43.Models.HealthPowerUp;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.WallWalkerPowerUp;
import com.example.cs2340a_team43.Models.XYPair;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.Views.GameActivity;
import static org.junit.Assert.assertEquals;

import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Sprint5UnitTests {
    @Test
    public void powerupGetsPlayerHealth(){
        Player player = Player.getInstance();
        int healthBefore = 50;
        player.setHP(healthBefore);
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.attainHealth();
        assertTrue(healthBefore < player.getHP());
    }
    @Test
    public void powerupLetsPlayerGoThroughWalls(){
        Player player = Player.getInstance();
        List<Map> maps = new ArrayList<>(1);
        XYPair bounds = new XYPair(18, 40);
        maps.add(new Map(bounds));
        MapViewModel mvm = new MapViewModel(maps);
        //MapViewModel mvm = new MapViewModel(18, 40);
        Map.MapObject[][] x = new Map.MapObject[18][40];
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 40; col++) {
                if (row == 0 || row == 17 || col == 0 || col == 39) {
                    x[row][col] = Map.MapObject.WALL;
                }
            }
        }
        x[5][5] = Map.MapObject.WALL;
        mvm.setMapLayout(x);
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.attainWallWalker();
        player.setInitialXY(5,4);
        player.moveDown();
        assertTrue(player.getX() == 5 && player.getY() == 5);
    }

    @Test
    public void powerupStopsPlayerOffscreen() {
        Player player = Player.getInstance();
        List<Map> maps = new ArrayList<>(1);
        XYPair bounds = new XYPair(2, 2);
        maps.add(new Map(bounds));
        MapViewModel mvm = new MapViewModel(maps);
        Map.MapObject[][] x = new Map.MapObject[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row == 0 || row == 3 || col == 0 || col == 3) {
                    x[row][col] = Map.MapObject.EMPTY;
                }
            }
        }
        mvm.setMapLayout(x);
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setMap(mvm);
        pvm.attainWallWalker();
        pvm.setInitialPlayerXY(1, 1);
        pvm.setXYBounds(2,2);
        for (int i = 0; i < 10; i++) {
            pvm.testMovePlayerDown();
        }
        assertTrue(player.getY() == 2);
    }


}
