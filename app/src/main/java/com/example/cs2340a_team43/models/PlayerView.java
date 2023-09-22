package com.example.cs2340a_team43.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;
import com.example.cs2340a_team43.R;



public class PlayerView extends View {
    private float x, y;
    ImageView playerImageView = findViewById(R.id.playerImageView);
    public PlayerView(Context context, float x, float y, int radius) {
        super(context);
        this.x = x;
        this.y = y;
        //playerImageView.setImageResource(R.drawable.frowny);
        pickSprite(0);
    }

    public void pickSprite(int choice) {
        if (choice == 0) {
            playerImageView.setImageResource(R.drawable.frowny);
        } else if (choice == 1) {
            playerImageView.setImageResource(R.drawable.medium_face);
        } else if (choice == 2) {
            playerImageView.setImageResource(R.drawable.smiley);
        }
    }




}
