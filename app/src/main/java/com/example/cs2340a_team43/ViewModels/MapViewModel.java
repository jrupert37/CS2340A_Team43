package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.Map.Floor;
import com.example.cs2340a_team43.Models.Map.MapObject;

public class MapViewModel extends ViewModel { //implements Subject {
    private final Map map;

    public MapViewModel(Context context, int rows, int cols) {
        this.map = new Map(context, rows, cols);
    }

    public MapViewModel(int rows, int cols) {
        this.map = new Map(rows, cols);
    }

    //    public void notifyObservers() {
    //        for (Observer o: observers) {
    //            o.update();
    //        }
    //    }

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
} // MapViewModel
