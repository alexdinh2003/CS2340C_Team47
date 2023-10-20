package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class VerticalWallTile extends Tile {
    private final Sprite sprite;

    public VerticalWallTile(SpriteSheet spriteSheet, Rect mapLocationRect, boolean left) {
        super(mapLocationRect);
        sprite = spriteSheet.getVWallSprite(left);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}