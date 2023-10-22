package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cs2340a_team43.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Map {
    public enum Floor {
        FIRST_FLOOR,
        SECOND_FLOOR,
        THIRD_FLOOR
    }
    public enum MapObject {
        WALL,
        EXIT,
        EMPTY
    }
    //private static Map map;
    private Floor floor;
    private Bitmap floorBitmap;
    private Context context;
    private MapObject[][] roomLayout;
    private final int rows = 18;
    private final int cols = 40;


    public Map(Context context) {
        this.floor = Floor.FIRST_FLOOR;
        this.context = context;
        setFloor(floor);
    }

    public Map() {
        this.roomLayout = new MapObject[rows][cols];
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
        switch (floor) {
        case FIRST_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.floor1);
            this.roomLayout = setFloorLayout("first_floor_blueprint.txt");
            break;
        case SECOND_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.floor2);
            this.roomLayout = setFloorLayout("second_floor_blueprint.txt");
            break;
        case THIRD_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.floor3);
            this.roomLayout = setFloorLayout("third_floor_blueprint.txt");
            break;
        default:
            break;
        }
    }
    public Bitmap getFloorBitmap() {
        return this.floorBitmap;
    }
    public MapObject[][] getRoomLayout() {
        return this.roomLayout;
    }
    private MapObject[][] setFloorLayout(String pathname) {
        InputStream stream = null;
        try {
            stream = this.context.getAssets().open(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0;
        MapObject[][] layout = new MapObject[rows][cols];
        Scanner streamScanner = new Scanner(stream);
        while (streamScanner.hasNext()) {
            int row = index / 40;
            int col = index % 40;
            char value = streamScanner.next().charAt(0);
            if (value == 'W') {
                layout[row][col] = MapObject.WALL;
            } else if (value == 'N') {
                layout[row][col] = MapObject.EMPTY;
            } else if (value == 'E') {
                layout[row][col] = MapObject.EXIT;
            }
            index++;
        }
        return layout;
    }

    //    private void createFirstFloorLayout() {
    //        firstFloorLayout = new MapObject[rows][cols];
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                if (row == 0 || col == 0 || col == 39 || row == 17) {
    //                    firstFloorLayout[row][col] = MapObject.WALL;
    //                } else {
    //                    firstFloorLayout[row][col] = MapObject.EMPTY;
    //                }
    //            }
    //        }
    //        for (int row = 3; row <= 7; row++) {
    //            for (int col = 24; col <= 27; col++) {
    //                firstFloorLayout[row][col] = MapObject.WALL;
    //            }
    //        }
    //        for (int row = 1; row <= 9; row++) {
    //            firstFloorLayout[row][18] = MapObject.WALL;
    //        }
    //        for (int col = 1; col <= 18; col++) {
    //            if (col != 5 && col != 6) {
    //                firstFloorLayout[10][col] = MapObject.WALL;
    //            }
    //        }
    //        for (int row = 1; row <= 12; row++) {
    //            firstFloorLayout[row][33] = MapObject.WALL;
    //        }
    //        for (int row = 10; row <= 16; row++) {
    //            firstFloorLayout[row][23] = MapObject.WALL;
    //        }
    //        for (int row = 10; row <= 16; row++) {
    //            firstFloorLayout[row][28] = MapObject.WALL;
    //        }
    //        firstFloorLayout[10][24] = MapObject.WALL;
    //        firstFloorLayout[10][27] = MapObject.WALL;
    //        firstFloorLayout[12][29] = MapObject.WALL;
    //        firstFloorLayout[12][32] = MapObject.WALL;
    //    }
} // Map
