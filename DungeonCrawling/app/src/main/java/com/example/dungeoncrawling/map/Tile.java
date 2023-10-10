package com.example.dungeoncrawling.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.graphics.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }
    public enum TileType {
            WATER_TILE,
            DIRT_TILE,
            STONE_TILE,
            GRASS_TILE,
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
        case WATER_TILE:
            return new WaterTile(spriteSheet, mapLocationRect);
        case DIRT_TILE:
            return new DirtTile(spriteSheet, mapLocationRect);
        case STONE_TILE:
            return new StoneTile(spriteSheet, mapLocationRect);
        case GRASS_TILE:
            return new GrassTile(spriteSheet, mapLocationRect);
        default:
            return new DirtTile(spriteSheet, mapLocationRect);
        }

    }

    public abstract void draw(Canvas canvas);
}
