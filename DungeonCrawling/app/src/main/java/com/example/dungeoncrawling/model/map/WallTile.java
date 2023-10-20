package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class WallTile extends Tile {
    private final Sprite sprite;

    public WallTile(SpriteSheet spriteSheet, Rect mapLocationRect, int ind, boolean left) {
        super(mapLocationRect);
        switch (ind) {
            case 0:
                sprite = spriteSheet.getHWallSprite(left);
                break;
            case 1:
                sprite = spriteSheet.getVWallSprite(left);
                break;
            case 2:
                sprite = spriteSheet.getCornerWallSprite(left);
                break;
            default:
                sprite = spriteSheet.getHWallSprite(false);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}