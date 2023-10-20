package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class ExitTile extends Tile {
    private final Sprite sprite;

    public ExitTile(SpriteSheet spriteSheet, Rect mapLocationRect, boolean banner) {
        super(mapLocationRect);
        sprite = spriteSheet.getExitSprite(banner);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
