package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class PowerUp {
    private boolean isTaken;
    private Bitmap sprite;
    private String type;
    private final int x;
    private final int y;
    public PowerUp(String type, Context context, int imageId, int x, int y) {
        this.type = type;
        this.isTaken = false;
        this.sprite = BitmapFactory.decodeResource(context.getResources(), imageId);
        this.x = x;
        this.y = y;
    }

    public Bitmap getSprite() {
        return this.sprite;
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void setAsTaken() {
        this.isTaken = true;
        this.sprite = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    public String getType() {
        return this.type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
