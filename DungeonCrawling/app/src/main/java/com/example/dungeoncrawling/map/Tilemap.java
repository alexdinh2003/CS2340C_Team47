package com.example.dungeoncrawling.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungeoncrawling.graphics.SpriteSheet;

import static com.example.dungeoncrawling.map.MapLayout.NUM_COLS;
import static com.example.dungeoncrawling.map.MapLayout.NUM_ROWS;
import static com.example.dungeoncrawling.map.MapLayout.TILE_HEIGHT;
import static com.example.dungeoncrawling.map.MapLayout.TILE_WIDTH;

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
        tilemap = new Tile[NUM_ROWS][NUM_COLS];
        for (int iRow = 0; iRow < NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < NUM_COLS; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                        layout[iRow][iCol],
                        spriteSheet,
                        getRectByIndex(iRow, iCol)
                );
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                NUM_COLS * TILE_WIDTH,
                NUM_ROWS * TILE_HEIGHT,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < NUM_ROWS; iRow++) {
            for (int iCol = 0; iCol < NUM_COLS; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }

    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol * TILE_WIDTH,
                idxRow * TILE_HEIGHT,
                (idxCol + 1) * TILE_WIDTH,
                (idxRow + 1) * TILE_HEIGHT
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