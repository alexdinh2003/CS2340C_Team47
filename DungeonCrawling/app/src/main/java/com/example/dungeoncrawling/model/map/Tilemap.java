package com.example.dungeoncrawling.model.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet, int ind) {
        mapLayout = new MapLayout(ind);
        this.spriteSheet = spriteSheet;
        initializeTilemap(ind);
    }

    private void initializeTilemap(int ind) {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[MapLayout.NUM_ROWS][MapLayout.NUM_COLS];
        for (int iRow = 0; iRow < MapLayout.NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < MapLayout.NUM_COLS; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                        layout[iRow][iCol],
                        spriteSheet,
                        getRectByIndex(iRow, iCol)
                );
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                MapLayout.NUM_COLS* MapLayout.TILE_WIDTH,
                MapLayout.NUM_ROWS* MapLayout.TILE_HEIGHT,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < MapLayout.NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < MapLayout.NUM_COLS; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }

    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol* MapLayout.TILE_WIDTH,
                idxRow* MapLayout.TILE_HEIGHT,
                (idxCol + 1)* MapLayout.TILE_WIDTH,
                (idxRow + 1)* MapLayout.TILE_HEIGHT
        );
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                mapBitmap,
                0, 256,
                null
        );
    }
}