package com.example.dungeoncrawling.model.map;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

class ExitTile extends Tile {
    private final Sprite sprite;
    private boolean exit;

    public ExitTile(SpriteSheet spriteSheet, Rect mapLocationRect, boolean exit) {
        super(mapLocationRect);
        sprite = spriteSheet.getExitSprite();
        this.exit = exit;
    }

    public boolean isExit() {
        return this.exit;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }

    @Override
    public TileType getTileType() {
        return TileType.STONE_TILE;
    }
}
