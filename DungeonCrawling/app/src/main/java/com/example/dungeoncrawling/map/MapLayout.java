package com.example.dungeoncrawling.map;

public class MapLayout {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int NUM_ROWS = 26;
    public static final int NUM_COLS = 17;

    private int[][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {
        layout = new int[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (i == 0 || j == 0 || i == NUM_ROWS -1 || j == NUM_COLS-1) {
                    layout[i][j] = 2;
                } else if (j < NUM_COLS - 6 && i == NUM_ROWS -8 ) {
                    layout[i][j] = 2;
                } else if ((i > 3 && i < 9) && (j > 3 && j < 9) ) {
                    layout[i][j] = 0;
                } else if (i % 4 == 0 && j % 4 == 0 && i != NUM_ROWS-2){
                    layout[i][j] = 3;
                }
                else {
                    layout[i][j] = 1;
                }
            }
        }
    }
}
