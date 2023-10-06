package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.view.View;
//import android.widget.ImageView;
//import android.content.Context;
//import com.example.cs2340a_team43.R;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Color;



public class PlayerView extends View {
    private int x;
    private int y;
    //private int hp;
    private Context context;
    private int imageId;
    private int choice;
    private String playerName;


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

    public PlayerView(Context context, int imageId, int choice, String name) {
        super(context);
        this.x = 900;
        this.y = 300;
        //this.hp = hp;
        this.context = context;
        this.imageId = imageId;
        this.choice = choice;
        this.playerName = name;
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
            //Left: 675 Right: 1275 X: 900 Y 300
        } else if (choice == 1) {
            //x = 1000;
            x += 100;
            y -= 100;
            right = x + drawable.getIntrinsicWidth() - 1000 + 200;
            left = y + drawable.getIntrinsicHeight() - 1200 + 100;
            //Left: 687 Right: 1191 X: 1000 Y 300
        } else {
            x += 100;
            right = x + drawable.getIntrinsicWidth() - 1000 - 4100 + 200;
            left = y + drawable.getIntrinsicHeight() - 1200 - 3700;
            //Left: 580 Right: 780 X: 1000 Y 300
        }
        //int left = 1655;
        //int left = 600;
        System.out.println("Left: " + left + " Right: " + right + " X: " + x + " Y " + y);
        //drawable.setBounds((int) x, (int) y, right, left);
        drawable.setBounds(x, y, right, left);
        drawable.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Style.FILL);
        paint.setTextSize(50);
        if (choice == 1) {
            canvas.drawText(playerName, x, y + 100, paint);
        } else {
            canvas.drawText(playerName, x, y, paint);
        }
    }




}
