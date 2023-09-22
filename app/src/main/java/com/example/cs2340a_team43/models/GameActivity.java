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
    int choice;
    ConstraintLayout gameLayout;
    PlayerView playerView;
    int screenWidth;
    int screenHeight;
    private float playerX, playerY;
    private TextView hpTextView;
    private Button endButton;

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
