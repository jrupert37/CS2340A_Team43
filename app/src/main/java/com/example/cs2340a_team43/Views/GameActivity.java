package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.ViewModels.PlayerView;
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

        hp = getIntent().getIntExtra("hp", 50);
        hpTextView = findViewById(R.id.healthTextView);
        hpTextView.setText("Health: " + hp);

        difficulty = getIntent().getStringExtra("difficulty");
        difficultyTextView = findViewById(R.id.difficultyTextView);
        difficultyTextView.setText("Difficulty: " + difficulty);

        thePlayer = Player.getInstance();
        thePlayer.setHP(hp);

        playerName = getIntent().getStringExtra("nameText");
        nameTextView = findViewById(R.id.playerNameTextView);
        nameTextView.setText("");

        //playerView = new PlayerView(this, playerX, playerY, hp, choice);
        spriteChoice = getIntent().getIntExtra("sprite", 0);

        playerImageView = findViewById(R.id.playerImageView);
        if (spriteChoice == 0) {
            playerView = new PlayerView(this, R.drawable.footballplayersprite, 0, playerName);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            playerImageView.setImageResource(android.R.color.transparent);
        } else if (spriteChoice == 1) {
            playerView = new PlayerView(this, R.drawable.nerdplayersprite, 1, playerName);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            playerImageView.setImageResource(android.R.color.transparent);
            //playerImageView.setImageResource(R.drawable.nerdplayersprite);
        } else if (spriteChoice == 2) {
            playerView = new PlayerView(this, R.drawable.gymbroplayersprite, 2, playerName);
            playerX = playerView.getXPosition();
            playerY = playerView.getYPosition();
            gameLayout.addView(playerView);
            playerImageView.setImageResource(android.R.color.transparent);
            //playerImageView.setImageResource(R.drawable.gymbroplayersprite);
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
