package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.cs2340a_team43.R;
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
    
    // helpful enumeration for distinguishing between floors
    public enum Floor {
        FIRST_FLOOR,
        SECOND_FLOOR,
        THIRD_FLOOR
    }
    
    // another helpful enumeration for distinguishing between an empty tile, wall, and exit
    public enum MapObject {
        WALL,
        EXIT,
        EMPTY
    }
    private Floor currentFloor;
    private Bitmap floorBitmap;
    private Context context;
    private MapObject[][] roomLayout;
    private final int rows;
    private final int cols;


    public Map(Context context, int rows, int cols) {
        // initialize the currentFloor to First Floor
        this.rows = rows;
        this.cols = cols;
        this.currentFloor = Floor.FIRST_FLOOR;
        this.context = context;
        // use createFloor to create the 2d layout array and set the floor background image
        setFloor(currentFloor);
    }

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.currentFloor = Floor.FIRST_FLOOR;
    }

    public Floor getFloor() {
        return this.currentFloor;
    }
    
    public void setFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
        switch (currentFloor) {
        case FIRST_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.floor1);
            this.roomLayout = createFloorLayout("first_floor_blueprint.txt");
            break;
        case SECOND_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.floor2);
            this.roomLayout = createFloorLayout("second_floor_blueprint.txt");
            break;
        case THIRD_FLOOR:
            this.floorBitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.floor3);
            this.roomLayout = createFloorLayout("third_floor_blueprint.txt");
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

    public void setRoomLayout(MapObject[][] x) {
        this.roomLayout = x;
    }
    
    /* 
    * This method creates the 2d layout array of walls/empty tiles/exit based
    * on a "blueprint" text file. 
    * Uses an input stream to read from the text file and create the layout accordingly.
    */
    private MapObject[][] createFloorLayout(String pathname) {
        InputStream stream = null;
        try {
            stream = this.context.getAssets().open(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0; // "index" refers to the nth character from the text file
        MapObject[][] layout = new MapObject[rows][cols];
        Scanner streamScanner = new Scanner(stream);
        while (streamScanner.hasNext()) {
            // use "index" to determine the corresponding row and col of the next map object
            int row = index / cols;
            int col = index % cols;
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
        return layout;
    }

    public int getXBound() {
        return this.cols - 1;
    }

    public int getYBound() {
        return this.rows - 1;
    }
} // Map
