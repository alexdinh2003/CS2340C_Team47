package com.example.dungeoncrawling.model.powerups;

import android.graphics.Canvas;

//import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public interface PowerUp {

    void powerUp();
    SpriteSheet getSpriteSheet();
    void draw(Canvas c);
    int[] getPos();
    boolean isActive();
}
