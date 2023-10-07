package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
//import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.PlayerView;
import com.example.cs2340a_team43.R;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;

public class GameActivity extends AppCompatActivity {

    private int hp;
    private String difficulty;
    private String playerName;
    private int spriteChoice;
    private ConstraintLayout gameLayout;
    private int screenWidth;
    private int screenHeight;
    private int playerX;
    private int playerY;
    private TextView hpTextView;
    private ImageView playerImageView;

    private Button endButton;
    private TextView difficultyTextView;
    private TextView nameTextView;

    private Player thePlayer;
    private PlayerView playerView;
    private TextView scoreTextView;
    private int score;
    private final int initialScore = 60;
    private Timer scoreTimer;
    private Leaderboard leaderboard;
    private Calendar startTime;
    private Calendar endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.gameLayout);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        // Spawn player in middle of screen
        //playerX = screenWidth / 2;
        //playerY = screenHeight / 2;
        leaderboard = Leaderboard.getInstance();
        startTime = Calendar.getInstance(TimeZone.getTimeZone("EST"));

        hp = getIntent().getIntExtra("hp", 50);
        hpTextView = findViewById(R.id.healthTextView);
        hpTextView.setText("Health: " + hp);

        difficulty = getIntent().getStringExtra("difficulty");
        difficultyTextView = findViewById(R.id.difficultyTextView);
        difficultyTextView.setText("Difficulty: " + difficulty);

        playerName = getIntent().getStringExtra("nameText");
        nameTextView = findViewById(R.id.playerNameTextView);
        nameTextView.setText(playerName);

        //playerView = new PlayerView(this, playerX, playerY, hp, choice);
        spriteChoice = getIntent().getIntExtra("sprite", 0);

        //playerImageView = findViewById(R.id.playerImageView);
        if (spriteChoice == 0) {
            playerView = new PlayerView(this, R.drawable.footballplayersprite, 0);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            //playerImageView.setImageResource(R.drawable.footballplayersprite);
        } else if (spriteChoice == 1) {
            playerView = new PlayerView(this, R.drawable.nerdplayersprite, 1);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            //playerImageView.setImageResource(R.drawable.nerdplayersprite);
        } else if (spriteChoice == 2) {
            playerView = new PlayerView(this, R.drawable.gymbroplayersprite, 2);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            //playerImageView.setImageResource(R.drawable.gymbroplayersprite);
        }

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
        }, 1000, 2000); // delay 1sec, then execute run() every 2sec til score is 0


        //playerView = new PlayerView(this, R.drawable.frowny, playerX, playerY, hp);
        //gameLayout.addView(playerView);


        endButton = findViewById(R.id.endScreenButton);
        endButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, EndScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            scoreTimer.cancel();
            endTime = Calendar.getInstance(TimeZone.getTimeZone("EST"));
            leaderboard.addGame(playerName, score, startTime, endTime );
            //intent.putExtra("final score", score);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                playerX -= 50;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                playerX += 50;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                playerY -= 50;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                playerY += 50;
                break;
        }
        playerView.updatePosition(playerX, playerY);
        return true;
    }


}
