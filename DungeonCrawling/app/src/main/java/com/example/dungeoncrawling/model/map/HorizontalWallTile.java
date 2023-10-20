package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class HorizontalWallTile extends Tile {
    private final Sprite sprite;

    public HorizontalWallTile(SpriteSheet spriteSheet, Rect mapLocationRect, boolean banner) {
        super(mapLocationRect);
        sprite = spriteSheet.getHWallSprite(banner);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
