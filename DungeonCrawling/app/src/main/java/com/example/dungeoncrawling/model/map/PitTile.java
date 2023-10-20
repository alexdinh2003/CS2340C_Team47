package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class PitTile extends Tile {
    private final Sprite sprite;

    public PitTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getPitSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }

    @Override
    public TileType getTileType() {
        return TileType.DIRT_TILE;
    }
}