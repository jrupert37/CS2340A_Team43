package com.example.cs2340a_team43.models;

import android.os.Bundle;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;





public class GameActivity extends AppCompatActivity {

    int hp;
    int choice;
    ConstraintLayout gameLayout;
    //private PlayerView playerView;
    int screenWidth;
    int screenHeight;
    private float playerX, playerY;
    private TextView hpTextView;
    private ImageView playerImageView;



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
        choice = getIntent().getIntExtra("sprite", 0);
        //playerView = new PlayerView(this, playerX, playerY, hp, choice);

        playerImageView = findViewById(R.id.playerImageView);
        if (choice == 0) {
            playerImageView.setImageResource(R.drawable.frowny);
        } else if (choice == 1) {
            playerImageView.setImageResource(R.drawable.medium_face);
        } else if (choice == 2) {
            playerImageView.setImageResource(R.drawable.smiley);
        }



        //playerView = new PlayerView(this, R.drawable.frowny, playerX, playerY, hp);
        //gameLayout.addView(playerView);


    }








}
