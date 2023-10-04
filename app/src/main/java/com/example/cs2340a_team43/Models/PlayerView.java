package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.view.View;
//import android.widget.ImageView;
//import android.content.Context;
//import com.example.cs2340a_team43.R;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;



public class PlayerView extends View {
    private int x;
    private int y;
    //private int hp;
    private Context context;
    private int imageId;
    private int choice;


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

    public PlayerView(Context context, int imageId, int choice) {
        super(context);
        this.x = 900;
        this.y = 300;
        //this.hp = hp;
        this.context = context;
        this.imageId = imageId;
        this.choice = choice;
    }

    public void updatePosition(int newX, int newY) {
        x = newX;
        y = newY;
        invalidate();
    }

    public int getXPosition() {
        return this.x;
    }

    public int getYPosition() {
        return this.y;
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
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = context.getDrawable(imageId);
        //x = 900;
        //y = 300;
        int right;
        int left;
        if (choice == 0) {
            right = x + drawable.getIntrinsicWidth() - 1000;
            //int right = 1675;
            //int right = 1200;
            left = y + drawable.getIntrinsicHeight() - 1000;
        } else {
            x = 1000;
            right = x + drawable.getIntrinsicWidth() - 1000;
            left = y + drawable.getIntrinsicHeight() - 1200;
        }
        //int left = 1655;
        //int left = 600;
        System.out.println("Left: " + left + " Right: " + right + " X: " + x + " Y " + y);
        //drawable.setBounds((int) x, (int) y, right, left);
        drawable.setBounds(x, y, right, left);
        drawable.draw(canvas);
    }




}
