package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.Models.HealthPowerUp;
import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.ScoreBoostPowerUp;
import com.example.cs2340a_team43.Models.WalkMovement;
import com.example.cs2340a_team43.Models.WallWalkerPowerUp;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameActivity extends AppCompatActivity {

    private String difficulty;
    private String playerName;
    private TextView scoreTextView;
    private TextView powerUpsTextView;
    private TextView timeElapsedTextView;
    private Timer gameTimer;
    private int gameTime = 0;
    private Leaderboard leaderboard;
    private Calendar startTime;
    private Calendar endTime;
    private PlayerViewModel pvm;
    private MapViewModel mvm;
    private final List<EnemyViewModel> firstFloorEnemies = new CopyOnWriteArrayList<>();
    private final List<EnemyViewModel> secondFloorEnemies = new CopyOnWriteArrayList<>();
    private final List<EnemyViewModel> thirdFloorEnemies = new CopyOnWriteArrayList<>();
    private List<EnemyViewModel> currentEnemies;
    private GameView gameView;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("WELCOME");
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        leaderboard = Leaderboard.getInstance();
        startTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));

        difficulty = getIntent().getStringExtra("difficulty");
        playerName = getIntent().getStringExtra("nameText");

        int imageId = R.drawable.footballplayersprite;
        int spriteChoice = getIntent().getIntExtra("sprite", 0);
        if (spriteChoice == 1) {
            imageId = R.drawable.nerdplayersprite;
        } else if (spriteChoice == 2) {
            imageId = R.mipmap.gymbroplayersprite;
        }

        mvm = new MapViewModel(this, 18, 40);

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

        firstFloorEnemies.add(new EnemyViewModel(this, "eyeball", mvm, 20, 8));
        firstFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 4, 12));

        secondFloorEnemies.add(new EnemyViewModel(this, "skeleton", mvm, 19, 8));
        secondFloorEnemies.add(new EnemyViewModel(this, "eyeball", mvm, 34, 5));

        thirdFloorEnemies.add(new EnemyViewModel(this, "grimreaper", mvm, 23, 10));
        thirdFloorEnemies.add(new EnemyViewModel(this, "cat", mvm, 35, 2));

        currentEnemies = firstFloorEnemies;
        gameView = new GameView(this, pvm, mvm, screenWidth, screenHeight,
                                currentEnemies);
        mvm.setPowerUp(new ScoreBoostPowerUp(this, 38, 1));
        pvm.addViewObserver(gameView);
        setAllObservers();



        //        Button upButton = findViewById(R.id.upButton);
        //        upButton.setOnClickListener(v -> {
        //            pvm.movePlayerUp();
        //        });
        //
        //        Button downButton = findViewById(R.id.downButton);
        //        downButton.setOnClickListener(v -> {
        //            pvm.movePlayerDown();
        //        });
        //
        //        Button rightButton = findViewById(R.id.rightButton);
        //        rightButton.setOnClickListener(v -> {
        //            pvm.movePlayerRight();
        //        });
        //
        //        Button leftButton = findViewById(R.id.leftButton);
        //        leftButton.setOnClickListener(v -> {
        //            pvm.movePlayerLeft();
        //        });

        LinearLayout linearLayout = findViewById(R.id.gameLayout);
        linearLayout.addView(gameView);

        scoreTextView = findViewById(R.id.scoreTextView);
        powerUpsTextView = findViewById(R.id.powerUpsTextView);
        timeElapsedTextView = findViewById(R.id.timeElapsedTextView);

//        int initialScore = 60;
//        score = initialScore; // set score to initial value
//        scoreTextView = findViewById(R.id.scoreTextView);
//        int hp = pvm.getPlayerHP();
//        String text = "Score: " + initialScore + "    Difficulty: " + difficulty + "    HP: " + hp;
//        scoreTextView.setText(text);
        gameTimer = new Timer();
        String timePlayed = "Time Played: " + Integer.toString(gameTime) + " s";
        timeElapsedTextView.setText(timePlayed);
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameTime++;
                String timePlayed = "Time Played: " + Integer.toString(gameTime) + " s";
                timeElapsedTextView.setText(timePlayed);
            }
        }, 1000, 1000); // delay 1sec, then execute run() every 1sec

        isRunning = false;
        startGameLoop();
    }

    private void startGameLoop() {
        this.isRunning = true;
        Intent end = new Intent(GameActivity.this, EndScreenActivity.class);
        end.addCategory(Intent.CATEGORY_HOME);
        Thread gameThread = new Thread(() -> {
            runCurrentEnemies();
            while (isRunning) {
                if (!pvm.isAlive()) {
                    isRunning = false;
                }
                if (pvm.playerIsAtExit()) {
                    if (mvm.isThirdFloor()) {
                        isRunning = false;
                    } else {
                        terminateCurrentEnemies();
                        moveToNextFloor();
                        runCurrentEnemies();
                    }
                }
                checkEnemiesStatus(); //-> If any enemy has been killed, needs to be removed,
                //                         remove enemy from observers, remove game view from it,
                //                         redraw game screen, remove that enemy from currentEnemi
                //                         reset gameViews current enemies
                String text = "Difficulty: " + difficulty
                                + "    Score: " + pvm.getScore()
                                + "    HP: " + pvm.getPlayerHP();
                runOnUiThread(() -> {
                    scoreTextView.setText(text);
                    powerUpsTextView.setText(pvm.listPowerUps());
                });
            }
            terminateCurrentEnemies();
            endTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
            gameTimer.cancel();
            int timeBonus = getTimeBonus();
            pvm.setScore(pvm.getScore() + timeBonus);
            leaderboard.addAttempt(pvm.getPlayerName(), pvm.getScore(), startTime, endTime);
            end.putExtra("isAlive", pvm.isAlive());
            end.putExtra("timeBonus", timeBonus);
            startActivity(end);
            finish();
        });
        gameThread.start();
    }

    private int getTimeBonus() {
        // An additional time bonus is added to the player's score if the player can
        // complete the game in under 100 seconds. The quicker the time,
        // the higher the bonus. Otherwise, no bonus given (0 returned).
        return (pvm.isAlive() ? Math.max(0, 100 - gameTime) : 0);
    }

    public void checkEnemiesStatus() {
        for (EnemyViewModel evm : currentEnemies) {
            if (evm.isAttacked()) {
                removeObservers(evm);
                currentEnemies.remove(evm);
                gameView.setCurrentEnemies(currentEnemies);
            }
        }
    }

    public void runCurrentEnemies() {
        for (EnemyViewModel evm : currentEnemies) {
            evm.runMovementPattern();
        }
    }

    public void terminateCurrentEnemies() {
        for (EnemyViewModel evm : currentEnemies) {
            evm.cancelMovement();
        }
    }

    public void moveToNextFloor() {
        removeAllObservers();
        if (mvm.isFirstFloor()) {
            this.currentEnemies = secondFloorEnemies;
            mvm.setPowerUp(new WallWalkerPowerUp(this, 27, 6));
        } else if (mvm.isSecondFloor()) {
            this.currentEnemies = thirdFloorEnemies;
            mvm.setPowerUp(new HealthPowerUp(this, 36, 3));
        } else {
            this.currentEnemies = new ArrayList<>();
        }
        setAllObservers();
        gameView.setCurrentEnemies(currentEnemies);
        this.mvm.moveToNextFloor();
        this.pvm.resetPlayerXY();
    }
    
    public void removeObservers(EnemyViewModel evm) {
        evm.removeViewObserver(gameView);
        evm.removeCollisionObserver(pvm);
        pvm.removeCollisionObserver(evm);
        pvm.removeAttackObserver(evm);
    }

    public void addObservers(EnemyViewModel evm) {
        evm.addViewObserver(gameView);
        evm.addCollisionObserver(pvm);
        pvm.addCollisionObserver(evm);
        pvm.addAttackObserver(evm);
    }
    
    public void removeAllObservers() {
        for (EnemyViewModel evm : currentEnemies) {
            removeObservers(evm);
        }
    }

    public void setAllObservers() {
        for (EnemyViewModel evm : currentEnemies) {
            addObservers(evm);
        }
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
