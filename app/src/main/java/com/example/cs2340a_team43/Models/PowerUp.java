package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class PowerUp {
    private boolean isTaken;
    private Bitmap sprite;
    private final String type;
    private final XYPair xy;
    public PowerUp(String type, Context context, int imageId, int x, int y) {
        this.type = type;
        this.isTaken = false;
        this.sprite = BitmapFactory.decodeResource(context.getResources(), imageId);
        this.xy = new XYPair(x, y);
    }

    public PowerUp(String type, int x, int y) {
        this.type = type;
        this.isTaken = false;
        this.xy = new XYPair(x, y);
    }

    public Bitmap getSprite() {
        return this.sprite;
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void setAsTaken() {
        this.isTaken = true;
        clearSprite();
    }

    private void clearSprite() {
        if (sprite != null) {
            this.sprite = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        }
    }

    public void testSetAsTaken() {
    }

    public String getType() {
        return this.type;
    }

    public int getX() {
        return xy.x();
    }

    public int getY() {
        return xy.y();
    }
} // PowerUp (abstract parent)
