package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.R;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
import android.widget.ImageView;





public class GameActivity extends AppCompatActivity {

    private int hp;
    private String difficulty;
    private String playerName;
    private int spriteChoice;
    private ConstraintLayout gameLayout;
    //private PlayerView playerView;
    private int screenWidth;
    private int screenHeight;
    private float playerX;
    private float playerY;
    private TextView hpTextView;
    private ImageView playerImageView;

    private Button endButton;
    private TextView difficultyTextView;
    private TextView nameTextView;

    private Player thePlayer;

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

        playerName = getIntent().getStringExtra("nameText");
        nameTextView = findViewById(R.id.playerNameTextView);
        nameTextView.setText(playerName);

        //playerView = new PlayerView(this, playerX, playerY, hp, choice);
        spriteChoice = getIntent().getIntExtra("sprite", 0);

        playerImageView = findViewById(R.id.playerImageView);
        if (spriteChoice == 0) {
            playerImageView.setImageResource(R.drawable.footballplayersprite);
        } else if (spriteChoice == 1) {
            playerImageView.setImageResource(R.drawable.nerdplayersprite);
        } else if (spriteChoice == 2) {
            playerImageView.setImageResource(R.drawable.gymbroplayersprite);
        }



        //playerView = new PlayerView(this, R.drawable.frowny, playerX, playerY, hp);
        //gameLayout.addView(playerView);
        endButton = findViewById(R.id.endScreenButton);
        endButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, EndScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
        });
    }


}
