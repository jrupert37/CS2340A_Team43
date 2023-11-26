package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cs2340a_team43.R;

public class Key {
    private XYPair xy;
    private Bitmap sprite;
    private boolean isTaken;

    public Key(Context context, int x, int y) {
        this.xy = new XYPair(x, y);
        this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.key);
        this.isTaken = false;
    }

    public Bitmap getSprite() {
        return this.sprite;
    }

    public int getX() {
        return xy.x();
    }

    public int getY() {
        return xy.y();
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void setAsTaken() {
        this.isTaken = true;
        this.sprite = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }
}
