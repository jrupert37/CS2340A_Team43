package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.WalkMovement;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private String difficulty;
    private String playerName;
    private volatile TextView scoreTextView;
    private int score;
    private final int initialScore = 60;
    private Timer scoreTimer;
    private Leaderboard leaderboard;
    private Calendar startTime;
    private Calendar endTime;
    private PlayerViewModel playerViewModel;
    private MapViewModel mvm;
    private final List<EnemyViewModel> firstFloorEnemies = new ArrayList<>();
    private final List<EnemyViewModel> secondFloorEnemies = new ArrayList<>();
    private final List<EnemyViewModel> thirdFloorEnemies = new ArrayList<>();
    private List<EnemyViewModel> currentEnemies;
    private GameView gameView;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mvm = new MapViewModel(this);

        playerViewModel = PlayerViewModel.getInstance();
        playerViewModel.setPlayerName(playerName);
        playerViewModel.setPlayerInitialHP(difficulty);
        playerViewModel.setInitialPlayerXY(2, 2);
        playerViewModel.setSprite(imageId, this);
        playerViewModel.setMap(mvm);
        playerViewModel.setPlayerMovementBehavior(new WalkMovement());

        firstFloorEnemies.add(new EnemyViewModel(this, difficulty, "eyeball", mvm,
                20, 8));
        firstFloorEnemies.add(new EnemyViewModel(this, difficulty, "cat", mvm,
                4, 12));

        secondFloorEnemies.add(new EnemyViewModel(this, difficulty, "skeleton", mvm,
                19, 8));
        secondFloorEnemies.add(new EnemyViewModel(this, difficulty, "eyeball", mvm,
                34, 5));

        thirdFloorEnemies.add(new EnemyViewModel(this, difficulty, "grimreaper", mvm,
                23, 10));
        thirdFloorEnemies.add(new EnemyViewModel(this, difficulty, "cat", mvm,
                35, 2));

        currentEnemies = firstFloorEnemies;
        gameView = new GameView(this, playerViewModel, mvm, screenWidth, screenHeight,
                                currentEnemies);
        playerViewModel.addViewObserver(gameView);
        setEnemyObservers();



        //        Button upButton = findViewById(R.id.upButton);
        //        upButton.setOnClickListener(v -> {
        //            playerViewModel.movePlayerUp();
        //        });
        //
        //        Button downButton = findViewById(R.id.downButton);
        //        downButton.setOnClickListener(v -> {
        //            playerViewModel.movePlayerDown();
        //        });
        //
        //        Button rightButton = findViewById(R.id.rightButton);
        //        rightButton.setOnClickListener(v -> {
        //            playerViewModel.movePlayerRight();
        //        });
        //
        //        Button leftButton = findViewById(R.id.leftButton);
        //        leftButton.setOnClickListener(v -> {
        //            playerViewModel.movePlayerLeft();
        //        });

        LinearLayout linearLayout = findViewById(R.id.gameLayout);
        linearLayout.addView(gameView);

        score = initialScore; // set score to initial value
        scoreTextView = findViewById(R.id.scoreTextView);
        int hp = playerViewModel.getPlayerHP();
        String text = "Score: " + initialScore + "    Difficulty: " + difficulty + "    HP: " + hp;
        scoreTextView.setText(text);
        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (score > 0) {
                        score--;
                    } else {
                        scoreTimer.cancel();
                    }
                    String text1 = "Score: " + score + "    Difficulty: "
                            + difficulty + "    HP: " + hp;
                    scoreTextView.setText(text1);
                });
            }
        }, 1000, 1000); // delay 1sec, then execute run() every 1sec til score is 0

        isRunning = false;
        startGameLoop();
    }

    private void startGameLoop() {
        this.isRunning = true;
        Intent game = new Intent(GameActivity.this, EndScreenActivity.class);
        game.addCategory(Intent.CATEGORY_HOME);
        //if (!playerViewModel.isAlive()) {
        // TEMPORARY!! ^ Use above instead
        Thread gameThread = new Thread(() -> {
            runCurrentEnemies();
            while (isRunning) {
                if (!playerViewModel.isAlive()) {
                    isRunning = false;
                }
                if (playerViewModel.playerIsAtExit()) {
                    if (mvm.isThirdFloor()) {
                        isRunning = false;
                    } else {
                        terminateCurrentEnemies();
                        moveToNextFloor();
                        runCurrentEnemies();
                    }
                }
                //scoreTextView = findViewById(R.id.scoreTextView);
                String text1 = "Score: " + score + "    Difficulty: "
                            + difficulty + "    HP: " + playerViewModel.getPlayerHP();
                scoreTextView.setText(text1);
            }
            terminateCurrentEnemies();
            scoreTimer.cancel();
            endTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
            leaderboard.addAttempt(playerName, score, startTime, endTime);
            game.putExtra("isAlive", playerViewModel.isAlive());
            startActivity(game);
            finish();
        });
        gameThread.start();
    }
    public void runCurrentEnemies() {
        for (EnemyViewModel enemy : currentEnemies) {
            enemy.runMovementPattern();
        }
    }

    public void terminateCurrentEnemies() {
        for (EnemyViewModel enemy : currentEnemies) {
            enemy.cancelMovement();
        }
    }

    public void moveToNextFloor() {
        removeEnemyObservers();
        //this.currentEnemies = new ArrayList<>(); // TEMPORARY, add logic to set next floor enemies
        if (mvm.isFirstFloor()) {
            this.currentEnemies = secondFloorEnemies;
        } else if (mvm.isSecondFloor()) {
            this.currentEnemies = thirdFloorEnemies;
        } else {
            this.currentEnemies = new ArrayList<>();
        }
        setEnemyObservers();
        gameView.setCurrentEnemies(currentEnemies);
        this.mvm.moveToNextFloor();
        this.playerViewModel.resetPlayerXY();
    }

    public void removeEnemyObservers() {
        for (EnemyViewModel evm : currentEnemies) {
            evm.removeViewObserver(gameView);
            evm.removeCollisionObserver(playerViewModel);
            playerViewModel.removeCollisionObserver(evm);
        }
    }

    public void setEnemyObservers() {
        for (EnemyViewModel evm : currentEnemies) {
            evm.addViewObserver(gameView);
            evm.addCollisionObserver(playerViewModel);
            playerViewModel.addCollisionObserver(evm);
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
} // GameActivity
