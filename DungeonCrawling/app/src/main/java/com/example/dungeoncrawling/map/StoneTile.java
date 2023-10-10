package com.example.dungeoncrawling.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.graphics.Sprite;
import com.example.dungeoncrawling.graphics.SpriteSheet;

class StoneTile extends Tile {
    private final Sprite sprite;

    public StoneTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getStoneSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
