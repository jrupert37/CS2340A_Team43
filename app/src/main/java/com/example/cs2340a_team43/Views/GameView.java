package com.example.cs2340a_team43.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Observer {
    private final PlayerViewModel playerViewModel;
    private final MapViewModel mapViewModel;
    private final Rect floorBounds;
    private final int xLimit;
    private final int yLimit;
    private List<EnemyViewModel> currentEnemies;

    public GameView(Context cont, PlayerViewModel pvm, MapViewModel mvm, int xLimit, int yLimit,
                    List<EnemyViewModel> evms) {
        super(cont);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        floorBounds = new Rect(0, 0, xLimit, yLimit - 125);
        this.playerViewModel = pvm;
        this.mapViewModel = mvm;
        this.currentEnemies = evms;
        playerViewModel.addObserver(this);
        for (EnemyViewModel evm : currentEnemies) {
            evm.addObserver(this);
        }
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

    @Override
    public void update() {
        draw();
        System.out.println("Screen Redrawn");
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

                for (EnemyViewModel evm: currentEnemies) {
                    float xEnemyDraw = (evm.getEnemyX() * xScale);
                    float yEnemyDraw = (evm.getEnemyY() * yScale);
                    RectF rect2;
                    rect2 = new RectF(xEnemyDraw, yEnemyDraw, xEnemyDraw + (xLimit / 40),
                            yEnemyDraw + (yLimit / 20));
                    Bitmap enemyBitmap = evm.getEnemyBitmap();
                    canvas.drawBitmap(enemyBitmap, null, rect2, null);
                }

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

    public void setCurrentEnemies(List<EnemyViewModel> currentEnemies) {
        this.currentEnemies = currentEnemies;
    }
} // GameView
