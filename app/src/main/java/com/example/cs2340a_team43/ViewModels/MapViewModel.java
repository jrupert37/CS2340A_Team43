package com.example.cs2340a_team43.ViewModels;

import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team43.Models.Key;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.Map.MapObject;
import com.example.cs2340a_team43.Models.PowerUp;
import java.util.List;

public class MapViewModel extends ViewModel {
    private Map map;
    private final List<Map> maps;

    public MapViewModel(List<Map> maps) {
        this.maps = maps;
        this.map = maps.get(0);
    }

    //TEST
    //public MapViewModel(int rows, int cols) {
    //    this.map = new Map(rows, cols);
    //}

    public Bitmap getMapFloorBitmap() {
        return this.map.getFloorBitmap();
    }

    public void moveToFloor(int nextFloor) {
        this.map = maps.get(nextFloor);
    }

    public MapObject[][] getRoomLayout() {
        return this.map.getRoomLayout();
    }

    public boolean isAWall(int x, int y) {
        return getRoomLayout()[y][x] == MapObject.WALL;
    }

    public boolean xyIsAnExit(int x, int y) {
        return getRoomLayout()[y][x] == MapObject.EXIT;
    }

    public int getXBound() {
        return this.map.getXBound();
    }

    public int getYBound() {
        return this.map.getYBound();
    }

    public void setMapLayout(MapObject[][] x) {
        this.map.setRoomLayout(x);
    }

    public PowerUp getThisFloorsPowerUp() {
        return this.map.getThisFloorsPowerUp();
    }

    public Key getThisFloorsKey() {
        return map.getThisFloorsKey();
    }

    public boolean isAPowerUp(int x, int y) {
        PowerUp pu = getThisFloorsPowerUp();
        boolean isAPowerUp = !pu.isTaken() && pu.getX() == x && pu.getY() == y;
        if (isAPowerUp) {
            pu.setAsTaken();
        }
        return isAPowerUp;
    }

    public boolean isAKey(int x, int y) {
        Key key = getThisFloorsKey();
        boolean isAKey = !key.isTaken() && key.getX() == x && key.getY() == y;
        if (isAKey) {
            key.setAsTaken();
        }
        return isAKey;
    }
} // MapViewModel
