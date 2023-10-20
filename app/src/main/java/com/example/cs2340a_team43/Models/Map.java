package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cs2340a_team43.R;

public class Map {
    public enum Floor {
        FIRST_FLOOR,
        SECOND_FLOOR,
        THIRD_FLOOR
    }

    public enum MapObject {
        WALL,
        EMPTY
    }
    private Floor floor;
    private Bitmap floorBitmap;
    private Context context;
    private MapObject[][] roomLayout;
    private final int rows = 34;
    private final int cols = 87;
    public Map(Context context) {
        this.context = context;
        this.floor = Floor.FIRST_FLOOR;
        this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.firstfloor);
        roomLayout = getFirstFloorLayout();
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
        switch (floor) {
        case FIRST_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.firstfloor);
            break;
        case SECOND_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.secondfloor);
            break;
        case THIRD_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.thirdfloor);
            break;
        default:
            break;
        }
    }
    public Bitmap getFloorBitmap() {
        return this.floorBitmap;
    }
    private MapObject[][] getFirstFloorLayout() {
        MapObject[][] layout = new MapObject[rows][cols];

        // Populate map borders with walls, everywhere else with empty spaces
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || col == 0 || col == 86 || row == 33) {
                    layout[row][col] = MapObject.WALL;
                } else {
                    layout[row][col] = MapObject.EMPTY;
                }
            }
        }
        //Blue room
        //top wall
        for (int col = 10; col <= 47; col++) {
            layout[1][col] = MapObject.WALL;
        }
        //left wall
        for (int row = 0; row <= 20; row++) {
            layout[row][8] = MapObject.WALL;
            layout[row][9] = MapObject.WALL;
        }
        //middle wall
        for (int row = 5; row <= 20; row++) {
            layout[row][27] = MapObject.WALL;
            layout[row][28] = MapObject.WALL;
            layout[row][29] = MapObject.WALL;
        }
        //right wall
        for (int row = 0; row <= 20; row++) {
            layout[row][48] = MapObject.WALL;
            layout[row][47] = MapObject.WALL;
        }
        //bottom left wall
        for (int col = 10; col <= 19; col++) {
            layout[20][col] = MapObject.WALL;
            layout[19][col] = MapObject.WALL;

        }
        //bottom right wall
        for (int col = 23; col <= 47; col++) {
            layout[20][col] = MapObject.WALL;
            layout[19][col] = MapObject.WALL;
        }

        //Main atrium
        //bottom wall
        for (int col = 21; col <= 85; col++) {
            layout[32][col] = MapObject.WALL;
        }

        return layout;
    }
    public MapObject[][] getRoomLayout() {
        return this.roomLayout;
    }

} // Map
