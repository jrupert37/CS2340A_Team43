package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.Map.Floor;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.Models.Subject;
import com.example.cs2340a_team43.Models.Map.MapObject;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel implements Subject {
    private Map map;
    private List<Observer> observers;
    private static MapViewModel mvm;

    public MapViewModel(Context context) {
        this.map = new Map(context);
        this.observers = new ArrayList<>();
    }
    public MapViewModel() {
        this.map = new Map();
    }
    public static MapViewModel getInstance(Context context) {
        if (mvm == null) {
            mvm = new MapViewModel(context);
        }
        return mvm;
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o: observers) {
            o.update();
        }
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
}
