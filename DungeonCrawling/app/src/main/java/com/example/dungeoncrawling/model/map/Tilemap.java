package com.example.dungeoncrawling.model.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private Tile[][] basetiles;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;
    private int[] startPos;

    public Tilemap(SpriteSheet spriteSheet, int ind) {
        mapLayout = new MapLayout(ind);
        this.spriteSheet = spriteSheet;
        initializeTilemap(ind);
    }

    private void initializeTilemap(int ind) {
        int[][] layout = mapLayout.getLayout();
        this.startPos = mapLayout.getStartPos();
        basetiles = new Tile[MapLayout.NUM_ROWS][MapLayout.NUM_COLS];
        tilemap = new Tile[MapLayout.NUM_ROWS][MapLayout.NUM_COLS];
        for (int iRow = 0; iRow < MapLayout.NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < MapLayout.NUM_COLS; iCol++) {
                basetiles[iRow][iCol] = Tile.getTile(
                        3,
                        spriteSheet,
                        getRectByIndex(iRow, iCol));
                if (layout[iRow][iCol] == 0) {
                    tilemap[iRow][iCol] = null;
                } else {
                    tilemap[iRow][iCol] = Tile.getTile(
                            layout[iRow][iCol],
                            spriteSheet,
                            getRectByIndex(iRow, iCol));
                }
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                MapLayout.NUM_COLS * MapLayout.TILE_WIDTH,
                MapLayout.NUM_ROWS * MapLayout.TILE_HEIGHT,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < MapLayout.NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < MapLayout.NUM_COLS; iCol++) {
                basetiles[iRow][iCol].draw(mapCanvas);
                if (tilemap[iRow][iCol] != null) {
                    tilemap[iRow][iCol].draw(mapCanvas);
                }
            }
        }

    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol * MapLayout.TILE_WIDTH,
                idxRow * MapLayout.TILE_HEIGHT,
                (idxCol + 1) * MapLayout.TILE_WIDTH,
                (idxRow + 1) * MapLayout.TILE_HEIGHT
        );
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                mapBitmap,
                0, 256,
                null
        );
    }

    public int[] getStartPos() {
        return this.startPos;
    }

    public boolean isWallCollision(int row, int col) {
        // Out of bounds can be treated as walls
        if (row < 0 || row >= MapLayout.NUM_ROWS || col < 0 || col >= MapLayout.NUM_COLS) {
            return true;
        }
        return tilemap[row][col].isWall();
    }
}