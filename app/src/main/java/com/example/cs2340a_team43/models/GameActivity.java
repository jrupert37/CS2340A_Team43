package com.example.cs2340a_team43.models;

import android.os.Bundle;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;

import org.w3c.dom.Text;


public class GameActivity extends AppCompatActivity {

    int hp;
    String difficulty;
    int choice;
    ConstraintLayout gameLayout;
    PlayerView playerView;
    int screenWidth;
    int screenHeight;
    private float playerX, playerY;
    private TextView hpTextView;
    private TextView difficultyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.gameLayout);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        // Spawn player in middle of screen
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;
        hp = getIntent().getIntExtra("hp", 50);
        hpTextView = findViewById(R.id.healthTextView);
        hpTextView.setText("Health: " + hp);
        difficulty = getIntent().getStringExtra("difficulty");
        difficultyTextView = findViewById(R.id.difficultyTextView);
        difficultyTextView.setText("Difficulty: " + difficulty);
        choice = getIntent().getIntExtra("choice", 0);
        //playerView = new PlayerView(this, playerX, playerY, hp, choice);
        //gameLayout.addView(playerView);


    }
}
