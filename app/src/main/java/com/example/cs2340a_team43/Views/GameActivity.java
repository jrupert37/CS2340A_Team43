package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.ViewModels.MapViewModel;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
import com.example.cs2340a_team43.Models.Map;
import com.example.cs2340a_team43.R;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private String difficulty;
    private String playerName;
    private int spriteChoice;
    private int screenWidth;
    private int screenHeight;
    private TextView scoreTextView;
    private int score;
    private final int initialScore = 60;
    private Timer scoreTimer;
    private Leaderboard leaderboard;
    private Calendar startTime;
    private Calendar endTime;
    private PlayerViewModel playerViewModel;
    private MapViewModel mapViewModel;
    private GameView gameView;
    private LinearLayout linearLayout;
    private boolean isRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("WELCOME");
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        leaderboard = Leaderboard.getInstance();
        startTime = Calendar.getInstance(TimeZone.getTimeZone("EST"));

        difficulty = getIntent().getStringExtra("difficulty");
        playerName = getIntent().getStringExtra("nameText");

        int imageId = R.drawable.footballplayersprite;
        spriteChoice = getIntent().getIntExtra("sprite", 0);
        if (spriteChoice == 1) {
            imageId = R.drawable.nerdplayersprite;
        } else if (spriteChoice == 2) {
            imageId = R.mipmap.gymbroplayersprite;
        }


        mapViewModel = new MapViewModel(this);

        playerViewModel = PlayerViewModel.getInstance();
        playerViewModel.setPlayerName(playerName);
        playerViewModel.setPlayerHP(difficulty);
        playerViewModel.setInitialPlayerXY(2, 2);
        playerViewModel.setImageId(imageId, this);
        playerViewModel.setMap(mapViewModel);

        gameView = new GameView(this, playerViewModel, mapViewModel, screenWidth, screenHeight);

        Button upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(v -> {
            playerViewModel.movePlayerUp();
        });

        Button downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(v -> {
            playerViewModel.movePlayerDown();
        });


        linearLayout = findViewById(R.id.gameLayout);
        linearLayout.addView(gameView);

        score = initialScore; // set score to initial value
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + initialScore); // Display initial score
        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (score > 0) {
                            score--;
                        } else {
                            scoreTimer.cancel();
                        }
                        scoreTextView.setText("Score: " + score);
                    }
                });
            }
        }, 1000, 1000); // delay 1sec, then execute run() every 1sec til score is 0

        isRunning = false;
        startGameLoop();
    }

    private void startGameLoop() {
        this.isRunning = true;
        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    if (playerViewModel.playerIsAtExit()) {
                        if (mapViewModel.getMapFloor() == Map.Floor.THIRD_FLOOR) {
                            isRunning = false;
                        } else {
                            gameView.moveToNextFloor();
                        }
                    }
                }
                scoreTimer.cancel();
                endTime = Calendar.getInstance(TimeZone.getTimeZone("EST"));
                leaderboard.addAttempt(playerName, score, startTime, endTime);
                Intent intent = new Intent(GameActivity.this, EndScreenActivity.class);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                finish();
            }
        });
        gameThread.start();
    }
} // GameActivity
