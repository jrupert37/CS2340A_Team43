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
import com.example.cs2340a_team43.Interfaces.ViewObserver;
import com.example.cs2340a_team43.Models.Key;
import com.example.cs2340a_team43.Models.PowerUp;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import java.util.List;
// * This class is responsible for drawing and redrawing the game screen. * //
public class GameView extends SurfaceView implements SurfaceHolder.Callback, ViewObserver {
    private final PlayerViewModel playerViewModel;
    private final MapViewModel mapViewModel;
    private final Rect floorBounds;
    private final int xLimit;
    private final int yLimit;
    private List<EnemyViewModel> currentEnemies;

    public GameView(Context cont, PlayerViewModel pvm, MapViewModel mvm, int xLimit, int yLimit,
                    List<EnemyViewModel> initialEnemies) {
        super(cont);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        floorBounds = new Rect(0, 0, xLimit, yLimit - 125);
        this.playerViewModel = pvm;
        this.mapViewModel = mvm;
        this.currentEnemies = initialEnemies;
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
                drawMap(canvas);
                drawPlayer(canvas);
                writePlayerName(canvas);
                drawEnemies(canvas);
            }
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas); // Unlock the canvas after drawing
            }
        }
    }

    private void drawMap(Canvas canvas) {
        Bitmap floorBitmap = mapViewModel.getMapFloorBitmap();
        canvas.drawBitmap(floorBitmap, null, floorBounds, null);

        PowerUp powerUp = mapViewModel.getThisFloorsPowerUp();
        float xPowerUpDraw = (powerUp.getX() * xScale());
        float yPowerUpDraw = (powerUp.getY() * yScale());
        RectF rect = new RectF(xPowerUpDraw, yPowerUpDraw, xPowerUpDraw + xDrawBound(),
                yPowerUpDraw + yDrawBound());
        Bitmap powerUpBitmap = powerUp.getSprite();
        canvas.drawBitmap(powerUpBitmap, null, rect, null);

        Key key = mapViewModel.getThisFloorsKey();
        float xKeyDraw = (key.getX() * xScale());
        float yKeyDraw = (key.getY() * yScale());
        rect = new RectF(xKeyDraw, yKeyDraw, xKeyDraw + xDrawBound(), yKeyDraw + yDrawBound());
        Bitmap keyBitmap = key.getSprite();
        canvas.drawBitmap(keyBitmap, null, rect, null);
    }

    private void drawPlayer(Canvas canvas) {
        float xDraw = (playerViewModel.getPlayerX() * xScale());
        float yDraw = (playerViewModel.getPlayerY() * yScale());
        RectF rect = new RectF(xDraw, yDraw, xDraw + xDrawBound(), yDraw + yDrawBound());
        Bitmap playerBitmap = playerViewModel.getPlayerSprite();
        Paint opacity = new Paint();
        opacity.setAlpha((playerViewModel.canWalkThroughWalls() ? 150 : 255));
        canvas.drawBitmap(playerBitmap, null, rect, opacity);
        if (playerViewModel.hasScoreBoost()) {
            Paint line = new Paint();
            line.setColor(Color.RED);
            line.setStyle(Paint.Style.STROKE);
            line.setStrokeWidth(2);
            canvas.drawRect(xDraw, yDraw + yDrawBound() - 2, xDraw + xDrawBound(),
                    yDraw + yDrawBound(), line);
        }
    }

    private void writePlayerName(Canvas canvas) {
        float xDraw = (playerViewModel.getPlayerX() * xScale());
        float yDraw = (playerViewModel.getPlayerY() * yScale());
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(35);
        float nameX = xDraw + 10;
        float nameY = yDraw - 15;
        canvas.drawText(playerViewModel.getPlayerName(), nameX, nameY, paint);
    }

    private void drawEnemies(Canvas canvas) {
        for (EnemyViewModel evm: currentEnemies) {
            float xEnemyDraw = (evm.getEnemyX() * xScale());
            float yEnemyDraw = (evm.getEnemyY() * yScale());
            RectF rect = new RectF(xEnemyDraw, yEnemyDraw, xEnemyDraw + xDrawBound(),
                    yEnemyDraw + yDrawBound());
            Bitmap enemyBitmap = evm.getEnemySprite();
            canvas.drawBitmap(enemyBitmap, null, rect, null);
        }
    }

    private float xScale() {
        return (float) (xDrawBound() + 0.5);
    }

    private float yScale() {
        return (float) (yDrawBound() - 0.5);
    }

    private int xDrawBound() {
        return xLimit / 40;
    }

    private int yDrawBound() {
        return yLimit / 20;
    }

    public void setCurrentEnemies(List<EnemyViewModel> currentEnemies) {
        this.currentEnemies = currentEnemies;
        draw();
    }
} // GameView
