package com.example.dungeoncrawling.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.graphics.Sprite;
import com.example.dungeoncrawling.graphics.SpriteSheet;

class DirtTile extends Tile {
    private final Sprite sprite;

    public DirtTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getDirtSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}