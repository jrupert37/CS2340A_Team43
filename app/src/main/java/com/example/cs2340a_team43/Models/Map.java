package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/*
 * This class holds information about the physical layout of each room, 
 * including the floor image/bitmap for each floor, and the layout of walls.
 * The current layout of a map is determined by the floor in which the player 
 * is currently located.
 * "Blueprint" text files are provided for creating a 2d array layout of empty
 * tiles, walls, and an exit for each floor. 
 */
public class Map {
    // helpful enumeration for distinguishing between an empty tile, wall, and exit
    public enum MapObject {
        WALL,
        EXIT,
        EMPTY
    }
    private Bitmap floorBitmap;
    private PowerUp thisFloorsPowerUp;
    private Key thisFloorsKey;
    private MapObject[][] roomLayout;
    private int yBound;
    private int xBound;


    public Map(Context cont, int imgId, String blueprint, PowerUp pwrUp, Key key, XYPair bounds) {
        this.floorBitmap = BitmapFactory.decodeResource(cont.getResources(), imgId);
        this.yBound = bounds.y();
        this.xBound = bounds.x();
        createFloorLayout(cont, blueprint);
        this.thisFloorsPowerUp = pwrUp;
        this.thisFloorsKey = key;
    }

    public Map(XYPair bounds) {
        this.yBound = bounds.y();
        this.xBound = bounds.x();
        this.floorBitmap = null;
        this.thisFloorsKey = null;
        this.thisFloorsPowerUp = null;
        //this.currentFloor = Floor.FIRST_FLOOR;
    }

    
    public Bitmap getFloorBitmap() {
        return floorBitmap;
    }
    
    public MapObject[][] getRoomLayout() {
        return roomLayout;
    }

    public void setRoomLayout(MapObject[][] x) {
        this.roomLayout = x;
    }

    public int getXBound() {
        return xBound - 1;
    }

    public int getYBound() {
        return yBound - 1;
    }

    public void setThisFloorsPowerUp(PowerUp powerUp) {
        thisFloorsPowerUp = powerUp;
    }

    public PowerUp getThisFloorsPowerUp() {
        return thisFloorsPowerUp;
    }

    public Key getThisFloorsKey() {
        return thisFloorsKey;
    }

    private void createFloorLayout(Context context, String pathname) {
        InputStream stream = null;
        try {
            stream = context.getAssets().open(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0; // "index" refers to the nth character in the text file
        MapObject[][] layout = new MapObject[yBound][xBound];
        Scanner streamScanner = new Scanner(stream);
        while (streamScanner.hasNext()) {
            // use "index" to determine the corresponding row and col of the next map object
            int row = index / xBound;
            int col = index % xBound;
            char value = streamScanner.next().charAt(0); // grab the next character
            if (value == 'W') {  // W indicates a wall
                layout[row][col] = MapObject.WALL;
            } else if (value == 'N') { // N indicates an empty space
                layout[row][col] = MapObject.EMPTY;
            } else if (value == 'E') { // E indicates an exit
                layout[row][col] = MapObject.EXIT;
            }
            index++;
        }
        this.roomLayout = layout;
    }
} // Map
