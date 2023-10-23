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
            EXIT,
            PIT,
            ENTER
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
        case FILLER:
            return null;
        case V_WALL_TILE_L:
            return new WallTile(spriteSheet, mapLocationRect, 1, true);
        case V_WALL_TILE_R:
            return new WallTile(spriteSheet, mapLocationRect, 1, false);
        case FLOOR_TILE:
            return new FloorTile(spriteSheet, mapLocationRect);
        case H_WALL_TILE:
            return new WallTile(spriteSheet, mapLocationRect, 0, false);
        case CORNER_L:
            return new WallTile(spriteSheet, mapLocationRect, 2, true);
        case CORNER_R:
            return new WallTile(spriteSheet, mapLocationRect, 2, false);
        case BANNER:
            return new WallTile(spriteSheet, mapLocationRect, 0, true);
        case EXIT:
            return new ExitTile(spriteSheet, mapLocationRect, true);
        case PIT:
            return new PitTile(spriteSheet, mapLocationRect);
        case ENTER:
            return new ExitTile(spriteSheet, mapLocationRect, false);
        default:
            return new FloorTile(spriteSheet, mapLocationRect);
        }

    }

    public static Tile getTile(int idxTileType, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
            case FILLER:
                return null;
            case FLOOR_TILE:
                return new FloorTile(mapLocationRect);
            case EXIT:
                return new ExitTile(mapLocationRect, true);
            case PIT:
                return new PitTile(mapLocationRect);
            case ENTER:
                return new ExitTile(mapLocationRect, false);
            default:
                return new WallTile(mapLocationRect);
        }

    }


    public boolean isWall() {
        return false;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void draw(Canvas canvas);
}



