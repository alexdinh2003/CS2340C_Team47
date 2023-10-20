package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class CornerWallTile extends Tile {
    private final Sprite sprite;

    public CornerWallTile(SpriteSheet spriteSheet, Rect mapLocationRect, boolean left) {
        super(mapLocationRect);
        sprite = spriteSheet.getCornerWallSprite(left);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}