package com.example.cs2340a_team43.models;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;



public class GameActivity extends AppCompatActivity {

    int hp;
    String difficulty;
    String playerName;
    int choice;
    ConstraintLayout gameLayout;
    PlayerView playerView;
    int screenWidth;
    int screenHeight;
    private float playerX, playerY;
    private TextView hpTextView;
    private Button endButton;
    private TextView difficultyTextView;
    private TextView nameTextView;

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
        choice = getIntent().getIntExtra("choice", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        difficultyTextView = findViewById(R.id.difficultyTextView);
        difficultyTextView.setText("Difficulty: " + difficulty);
        playerName = getIntent().getStringExtra("inputText");
        nameTextView = findViewById(R.id.playerNameTextView);
        nameTextView.setText(playerName);
        //playerView = new PlayerView(this, playerX, playerY, hp, choice);
        //gameLayout.addView(playerView);
        endButton = findViewById(R.id.endScreenButton);
        endButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, EndScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        });
    }


}
