package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.Models.HealthPowerUp;
import com.example.cs2340a_team43.Models.Key;
import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.Models.PowerUp;
import com.example.cs2340a_team43.Models.ScoreBoostPowerUp;
import com.example.cs2340a_team43.Models.WalkMovement;
import com.example.cs2340a_team43.Models.WallWalkerPowerUp;
import com.example.cs2340a_team43.Models.XYPair;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.R;
import com.example.cs2340a_team43.R.drawable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameActivity extends AppCompatActivity {
    private Timer gameTimer;
    private int gameTime = 0;
    private Leaderboard leaderboard;
    private Calendar startTime;
    private Calendar endTime;
    private PlayerViewModel pvm;
    private MapViewModel mvm;
    private final List<List<EnemyViewModel>> allFloorEnemies = new CopyOnWriteArrayList<>();
    private int currentFloor;
    private GameView gameView;
    private boolean isRunning;
    private int screenHeight;
    private int screenWidth;
    private TextView scoreTextView;
    private TextView powerUpsTextView;
    private ImageView keyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        leaderboard = Leaderboard.getInstance();

        scoreTextView = findViewById(R.id.scoreTextView);
        powerUpsTextView = findViewById(R.id.powerUpsTextView);
        keyImageView = findViewById(R.id.keyImageView);

        initializeMap();
        initializeEnemies();
        initializePlayer();
        initializeGameView();
        setAllObservers();

        isRunning = false;
        startTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        runTimeElapsedTimer();
        runGameLoop();
    }

    private void initializeMap() {
        this.currentFloor = 0;
        XYPair bounds = new XYPair(40, 18);
        PowerUp flr0PwrUp = new ScoreBoostPowerUp(this, 38, 1);
        PowerUp flr1PwrUp = new WallWalkerPowerUp(this, 27, 6);
        PowerUp flr2PwrUp = new HealthPowerUp(this, 36, 3);
        Key flr0Key = new Key(this, 37, 15);
        Key flr1Key = new Key(this, 3, 12);
        Key flr2Key = new Key(this, 4, 11);
        List<Map> maps = new ArrayList<>(3);
        maps.add(new Map(this, drawable.floor0, "flr_0_blueprint.txt", flr0PwrUp, flr0Key, bounds));
        maps.add(new Map(this, drawable.floor1, "flr_1_blueprint.txt", flr1PwrUp, flr1Key, bounds));
        maps.add(new Map(this, drawable.floor2, "flr_2_blueprint.txt", flr2PwrUp, flr2Key, bounds));
        this.mvm = new MapViewModel(maps);
    }

    private void initializeEnemies() {
        List<EnemyViewModel> zeroFloorEnemies = new CopyOnWriteArrayList<>();
        List<EnemyViewModel> firstFloorEnemies = new CopyOnWriteArrayList<>();
        List<EnemyViewModel> secondFloorEnemies = new CopyOnWriteArrayList<>();

        zeroFloorEnemies.add(new EnemyViewModel(this, "eyeball", mvm, 20, 8));
        zeroFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 4, 12));
        zeroFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 36, 14));
        firstFloorEnemies.add(new EnemyViewModel(this, "skeleton", mvm, 19, 8));
        firstFloorEnemies.add(new EnemyViewModel(this, "eyeball", mvm, 34, 5));
        firstFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 2, 11));
        secondFloorEnemies.add(new EnemyViewModel(this, "grimreaper", mvm, 23, 10));
        secondFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 35, 2));
        secondFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 3, 10));

        allFloorEnemies.add(zeroFloorEnemies);
        allFloorEnemies.add(firstFloorEnemies);
        allFloorEnemies.add(secondFloorEnemies);
    }

    private void initializePlayer() {
        String difficulty = getIntent().getStringExtra("difficulty");
        String playerName = getIntent().getStringExtra("nameText");
        int imageId = R.drawable.footballplayersprite;
        int spriteChoice = getIntent().getIntExtra("sprite", 0);
        if (spriteChoice == 1) {
            imageId = R.drawable.nerdplayersprite;
        } else if (spriteChoice == 2) {
            imageId = R.mipmap.gymbroplayersprite;
        }
        pvm = PlayerViewModel.getInstance();
        pvm.setPlayerName(playerName);
        pvm.setPlayerInitialHP(difficulty);
        pvm.setInitialPlayerXY(2, 2);
        pvm.setSprite(imageId, this);
        pvm.setMap(mvm);
        pvm.setPlayerMovementBehavior(new WalkMovement());
        pvm.setXYBounds(mvm.getXBound(), mvm.getYBound());
        pvm.resetPowerUps();
        pvm.resetScore();
        pvm.doesHaveKey(false);
    }

    private void initializeGameView() {
        gameView = new GameView(this, pvm, mvm, screenWidth, screenHeight,
                allFloorEnemies.get(currentFloor));
        LinearLayout linearLayout = findViewById(R.id.gameLayout);
        linearLayout.addView(gameView);
        pvm.addViewObserver(gameView);
    }

    private void setAllObservers() {
        List<EnemyViewModel> currentEnemies = allFloorEnemies.get(currentFloor);
        for (EnemyViewModel evm : currentEnemies) {
            addObservers(evm);
        }
    }

    private void addObservers(EnemyViewModel evm) {
        evm.addViewObserver(gameView);
        evm.addCollisionObserver(pvm);
        pvm.addCollisionObserver(evm);
        pvm.addAttackObserver(evm);
    }
    private void removeAllObservers() {
        List<EnemyViewModel> currentEnemies = allFloorEnemies.get(currentFloor);
        for (EnemyViewModel evm : currentEnemies) {
            removeObservers(evm);
        }
    }

    private void removeObservers(EnemyViewModel evm) {
        evm.removeViewObserver(gameView);
        evm.removeCollisionObserver(pvm);
        pvm.removeCollisionObserver(evm);
        pvm.removeAttackObserver(evm);
    }

    private void runTimeElapsedTimer() {
        TextView timeElapsedTextView = findViewById(R.id.timeElapsedTextView);
        gameTimer = new Timer();
        String timePlayed = "Time Played: " + gameTime + " s";
        timeElapsedTextView.setText(timePlayed);
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameTime++;
                String timePlayed = "Time Played: " + gameTime + " s ";
                timeElapsedTextView.setText(timePlayed);
            }
        }, 1000, 1000); // delay 1sec, then execute run() every 1sec
    }

    private int getTimeBonus() {
        return (pvm.isAlive() ? Math.max(0, 75 - gameTime) : 0);
    }

    private void runGameLoop() {
        isRunning = true;
        Thread gameThread = new Thread(() -> {
            runCurrentEnemies();
            while (isRunning) {
                if (!pvm.isAlive()) {
                    isRunning = false;
                }
                if (pvm.playerIsAtExit()) {
                    attemptToExit();
                }
                checkEnemiesStatus();
                displayInfo();
            }
            gameOver();
        });
        gameThread.start();
    }
    private void attemptToExit() {
        if (pvm.hasKey()) {
            if (currentFloor == 2) {
                isRunning = false;
            } else {
                moveToNextFloor();
            }
        }
    }
    private void checkEnemiesStatus() {
        List<EnemyViewModel> currentEnemies = allFloorEnemies.get(currentFloor);
        for (EnemyViewModel evm : currentEnemies) {
            if (evm.isAttacked()) {
                removeObservers(evm);
                currentEnemies.remove(evm);
                gameView.setCurrentEnemies(currentEnemies);
            }
        }
    }

    private void displayInfo() {
        String text = "Difficulty: " + pvm.getDifficulty()
                + "   Score: " + pvm.getScore()
                + "   HP: " + pvm.getPlayerHP();
        runOnUiThread(() -> {
            scoreTextView.setText(text);
            powerUpsTextView.setText(pvm.listPowerUps());
            if (pvm.hasKey()) {
                keyImageView.setVisibility(View.VISIBLE);
            } else {
                keyImageView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void runCurrentEnemies() {
        List<EnemyViewModel> currentEnemies = allFloorEnemies.get(currentFloor);
        for (EnemyViewModel evm : currentEnemies) {
            evm.runMovementPattern();
        }
    }

    private void terminateCurrentEnemies() {
        List<EnemyViewModel> currentEnemies = allFloorEnemies.get(currentFloor);
        for (EnemyViewModel evm : currentEnemies) {
            evm.cancelMovement();
        }
    }

    private void moveToNextFloor() {
        terminateCurrentEnemies();
        removeAllObservers();
        currentFloor++;
        setAllObservers();
        gameView.setCurrentEnemies(allFloorEnemies.get(currentFloor));
        mvm.moveToFloor(currentFloor);
        pvm.resetPlayerXY();
        pvm.doesHaveKey(false);
        runCurrentEnemies();
    }

    private void gameOver() {
        terminateCurrentEnemies();

        endTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        gameTimer.cancel();

        int baseScore = pvm.getScore();
        int timeBonus = getTimeBonus();
        pvm.setScore(pvm.getScore() + timeBonus);
        leaderboard.addAttempt(pvm.getPlayerName(), pvm.getScore(), startTime, endTime);

        Intent end = new Intent(GameActivity.this, EndScreenActivity.class);
        end.addCategory(Intent.CATEGORY_HOME);
        end.putExtra("isAlive", pvm.isAlive());
        end.putExtra("baseScore", baseScore);
        end.putExtra("timeBonus", timeBonus);
        startActivity(end);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            pvm.movePlayerLeft();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            pvm.movePlayerRight();
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            pvm.movePlayerUp();
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            pvm.movePlayerDown();
            break;
        case KeyEvent.KEYCODE_W:
            pvm.attackUp();
            break;
        case KeyEvent.KEYCODE_A:
            pvm.attackLeft();
            break;
        case KeyEvent.KEYCODE_S:
            pvm.attackDown();
            break;
        case KeyEvent.KEYCODE_D:
            pvm.attackRight();
            break;
        default:
            break;
        }
        return true;
    }
} // GameActivity
