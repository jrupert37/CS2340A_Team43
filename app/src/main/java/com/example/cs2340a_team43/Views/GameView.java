package com.example.cs2340a_team43.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.cs2340a_team43.Models.EyeballMovement;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Observer {
    private PlayerViewModel playerViewModel;
    private MapViewModel mapViewModel;
    private Rect floorBounds;
    private int xLimit;
    private int yLimit;
    private EnemyViewModel enemyViewModel;

    public GameView(Context cont, PlayerViewModel pvm, MapViewModel mvm, int xLimit, int yLimit, EnemyViewModel evm) {
        super(cont);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        floorBounds = new Rect(0, 0, xLimit, yLimit - 125);
        this.playerViewModel = pvm;
        this.mapViewModel = mvm;
        this.enemyViewModel = evm;
        playerViewModel.addObserver(this);
        enemyViewModel.addObserver(this);
        this.xLimit = xLimit;
        this.yLimit = yLimit;
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
    public void runEnemies() {
        EyeballMovement eyeballMovement = new EyeballMovement(enemyViewModel);
        eyeballMovement.run();
    }

    @Override
    public void update() {
        draw();
        System.out.println("Redrew player");
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
                Bitmap floorBitmap = mapViewModel.getMapFloorBitmap();
                canvas.drawBitmap(floorBitmap, null, floorBounds, null);

                float xScale = (float) ((xLimit / 40) + 0.5);
                float yScale = (float) ((yLimit / 20) - 0.5);
                float xDraw = (playerViewModel.getPlayerX() * xScale);
                float yDraw = (playerViewModel.getPlayerY() * yScale);
                RectF rect;
                rect = new RectF(xDraw, yDraw, xDraw + (xLimit / 40), yDraw + (yLimit / 20));
                Bitmap playerBitmap = playerViewModel.getPlayerBitmap();
                canvas.drawBitmap(playerBitmap, null, rect, null);

                float xEnemyDraw = (enemyViewModel.getEnemyX() * xScale);
                float yEnemyDraw = (enemyViewModel.getEnemyY() * yScale);
                RectF rect2;
                rect2 = new RectF(xEnemyDraw, yEnemyDraw, xEnemyDraw + (xLimit / 40), yEnemyDraw + (yLimit / 20));
                Bitmap enemyBitmap = enemyViewModel.getEnemyBitmap();
                canvas.drawBitmap(enemyBitmap, null, rect2, null);

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(35);
                float nameX = xDraw + 10;
                float nameY = yDraw - 15;
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
            playerViewModel.movePlayerDown();
            break;
        default:
            break;
        }
        return true;
    }
} // GameView
