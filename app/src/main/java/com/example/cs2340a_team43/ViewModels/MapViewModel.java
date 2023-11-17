package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.Map.Floor;
import com.example.cs2340a_team43.Models.Map.MapObject;
import com.example.cs2340a_team43.Models.PowerUp;

public class MapViewModel extends ViewModel {
    private final Map map;

    public MapViewModel(Context context, int rows, int cols) {
        this.map = new Map(context, rows, cols);
    }

    public MapViewModel(int rows, int cols) {
        this.map = new Map(rows, cols);
    }

    public Bitmap getMapFloorBitmap() {
        return this.map.getFloorBitmap();
    }

    public void moveToNextFloor() {
        Floor floor = this.map.getFloor();
        Floor newFloor;
        switch (floor) {
        case FIRST_FLOOR:
            newFloor = Floor.SECOND_FLOOR;
            break;
        case SECOND_FLOOR:
            newFloor = Floor.THIRD_FLOOR;
            break;
        default:
            newFloor = floor;
            break;
        }
        this.map.setFloor(newFloor);
    }

    public Floor getMapFloor() {
        return this.map.getFloor();
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

    public boolean isFirstFloor() {
        return getMapFloor() == Floor.FIRST_FLOOR;
    }

    public boolean isSecondFloor() {
        return getMapFloor() == Floor.SECOND_FLOOR;
    }

    public boolean isThirdFloor() {
        return getMapFloor() == Floor.THIRD_FLOOR;
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

    public void addPowerUp(PowerUp powerUp) {
        this.map.setPowerUp(powerUp);
    }

    public boolean isAPowerUp(int x, int y) {
        PowerUp pu = getThisFloorsPowerUp();
        boolean isAPowerUp = !pu.isTaken() && pu.getX() == x && pu.getY() == y;
        if (isAPowerUp) {
            pu.setAsTaken();
        }
        return isAPowerUp;
    }
} // MapViewModel
