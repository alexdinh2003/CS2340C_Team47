package com.example.dungeoncrawling.model.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }
    public enum TileType {
            FILLER,
            V_WALL_TILE_L,
            V_WALL_TILE_R,
            FLOOR_TILE,
            H_WALL_TILE,
            CORNER_L,
            CORNER_R,
            BANNER,
            STEPS,
            PIT
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
        case FILLER:
            return null;
        case V_WALL_TILE_L:
            return new VerticalWallTile(spriteSheet, mapLocationRect, true);
        case V_WALL_TILE_R:
            return new VerticalWallTile(spriteSheet, mapLocationRect, false);
        case FLOOR_TILE:
            return new DirtTile(spriteSheet, mapLocationRect);
        case H_WALL_TILE:
            return new HorizontalWallTile(spriteSheet, mapLocationRect, false);
        case CORNER_L:
            return new CornerWallTile(spriteSheet, mapLocationRect, true);
        case CORNER_R:
            return new CornerWallTile(spriteSheet, mapLocationRect, false);
        case BANNER:
            return new HorizontalWallTile(spriteSheet, mapLocationRect, true);
        case STEPS:
            return new ExitTile(spriteSheet, mapLocationRect);
        case PIT:
            return new PitTile(spriteSheet, mapLocationRect);
        default:
            return new DirtTile(spriteSheet, mapLocationRect);
        }

    }

    public abstract void draw(Canvas canvas);
}
