package com.example.cs2340a_team43.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;
import com.example.cs2340a_team43.Models.Map.Floor;


public class GameView extends SurfaceView implements SurfaceHolder.Callback, Observer {

    private Context context;
    private PlayerViewModel playerViewModel;
    private MapViewModel mapViewModel;
    private boolean initialized = false;
    private Rect floorBounds;
    private Rect playerDrawLocation;

    public GameView(Context context, PlayerViewModel playerViewModel, MapViewModel mapViewModel, int xLimit, int yLimit) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        this.context = context;
        floorBounds = new Rect(0, 0, xLimit, yLimit - 125);
        this.playerViewModel = playerViewModel;
        this.mapViewModel = mapViewModel;
        playerViewModel.addObserver(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void update() {
        draw();
        System.out.println("Redrew player");
    }

//    public void updatePlayer(MovementDirection direction) {
//        playerViewModel.updatePosition(direction);
//        draw();
//    }

    public Floor getFloor() {
        return this.mapViewModel.getMapFloor();
    }

    public void moveToNextFloor() {
        this.mapViewModel.moveToNextFloor();
        this.playerViewModel.resetPlayerXY();
        draw();
    }

    private void draw() {
        Canvas canvas = null;
        try {
            canvas = getHolder().lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.BLACK); // Clear the canvas with a black color
                // Draw the player at the updated position
                Bitmap playerBitmap = playerViewModel.getPlayerBitmap();
                int xDraw = playerViewModel.getPlayerX() * 25;
                int yDraw = playerViewModel.getPlayerY() * 25;
                playerDrawLocation = new Rect(xDraw, yDraw, xDraw + 75, yDraw + 75);
                Bitmap floorBitmap = mapViewModel.getMapFloorBitmap();
                canvas.drawBitmap(floorBitmap, null, floorBounds, null);
                canvas.drawBitmap(playerBitmap, null, playerDrawLocation, null);
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(35);
                int nameX = xDraw + 10;
                int nameY = yDraw - 15;
                canvas.drawText(playerViewModel.getPlayerName(), nameX, nameY, paint);
            }
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas); // Unlock the canvas after drawing
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //MovementDirection direction;
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            playerViewModel.movePlayerLeft();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            playerViewModel.movePlayerRight();
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            playerViewModel.movePlayerUp();
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            //playerY += 30;
            //direction = MovementDirection.DOWN;
            playerViewModel.movePlayerDown();
            break;
        default:
            //direction = MovementDirection.NONE;
            break;
        }
        //updatePlayer(direction);
        return true;
    }
} // GameView
