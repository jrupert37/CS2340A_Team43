package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.view.View;
//import android.widget.ImageView;
//import android.content.Context;
//import com.example.cs2340a_team43.R;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;



public class PlayerView extends View {
    private float x;
    private float y;
    private int hp;
    private Context context;
    private int imageId;


    /*
    public PlayerView(Context context, float x, float y, int hp, int choice) {
        super(context);
        this.x = x;
        this.y = y;
        this.hp = hp;
        //playerImageView = findViewById(R.id.playerImageView);
        //playerImageView.setImageResource(R.drawable.frowny);
        pickSprite(choice);
    }*/

    public PlayerView(Context context, int imageId, float x, float y, int hp) {
        super(context);
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.context = context;
        this.imageId = imageId;
    }


    /*
    public void pickSprite(int choice) {
        if (playerImageView != null) {
            playerImageView.setImageResource(R.drawable.frowny);
        } else {
            if (choice == 0) {
                System.out.println("WRONG");
                playerImageView.setImageResource(R.drawable.frowny);
            } else if (choice == 1) {
                playerImageView.setImageResource(R.drawable.medium_face);
            } else if (choice == 2) {
                playerImageView.setImageResource(R.drawable.smiley);
            }
        }
    }*/
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = context.getDrawable(imageId);
        int right = (int) x + drawable.getIntrinsicWidth();
        int left = (int) y + drawable.getIntrinsicHeight();
        drawable.setBounds((int) x, (int) y, right, left);
        drawable.draw(canvas);
    }




}
