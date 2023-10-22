package com.example.cs2340a_team43;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.Views.GameActivity;

import android.app.Activity;
import android.content.Context;
import android.media.metrics.PlaybackStateEvent;

import androidx.appcompat.app.AppCompatActivity;


public class Sprint3UnitTests {
    //Lauren Test 1
    @Test
    public void leftMovement() {
        Player player = Player.getInstance();
        player.setInitialXY(1, 1);
        player.moveLeft();
        int newX = player.getX();
        assertEquals(0, newX);
    }
    @Test
    public void testInitialXY() {
        Player player = Player.getInstance();
        player.setInitialXY(0,0);
        int x = player.getX();
        int y = player.getY();
        assertEquals(0, x);
        assertEquals(0, y);
    }
    @Test
    public void testPlayerDoesNotMoveIfWall() {
        MapViewModel mvm = new MapViewModel();
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setMap(mvm);
        pvm.setInitialPlayerXY(1,1);
        mvm.getRoomLayout()[1][0] = Map.MapObject.WALL;
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.movePlayerLeft(); // player coordinates should NOT change
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
    }
    @Test
    public void testPlayerDoesMoveIfEmpty() {
        MapViewModel mvm = new MapViewModel();
        PlayerViewModel pvm = PlayerViewModel.getInstance();
        pvm.setMap(mvm);
        pvm.setInitialPlayerXY(1,1);
        mvm.getRoomLayout()[1][0] = Map.MapObject.EMPTY;
        assertEquals(1, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
        pvm.movePlayerLeft(); // player x coordinate should change
        assertEquals(0, pvm.getPlayerX());
        assertEquals(1, pvm.getPlayerY());
    }
}


