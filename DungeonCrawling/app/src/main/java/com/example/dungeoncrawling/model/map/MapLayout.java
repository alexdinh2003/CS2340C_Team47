package com.example.dungeoncrawling.model.map;

public class MapLayout {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int NUM_ROWS = 26;
    public static final int NUM_COLS = 17;

    private int[][] layout;

    public MapLayout(int ind) {
        initializeLayout(ind);
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout(int ind) {
        layout = new int[NUM_ROWS][NUM_COLS];
        if (ind == 0) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (i == 0 || j == 0 || i == NUM_ROWS - 1 || j == NUM_COLS - 1) {
                        layout[i][j] = 2;
                    } else if (j != 3 && i == NUM_ROWS - 8) {
                        layout[i][j] = 2;
                    } else if (j != 13 && i == 10) {
                        layout[i][j] = 2;
                    } else if (i > 11 && i < NUM_ROWS - 8 && j == 6) {
                        layout[i][j] = 2;
                    } else if ((i > 0 && i < 10) && (j > 7 && j < 11)) {
                        layout[i][j] = 0;
                    } else if ((i % 3 == 0 && j % 3 == 0) || (i % 4 == 0 && j % 4 == 0)) {
                        layout[i][j] = 3;
                    } else {
                        layout[i][j] = 1;
                    }

                    if (i == 0 && j == 5 || (j == 10 && i == NUM_ROWS - 8) || ((i == 5) && j == 8)
                            || ((i == 6 || i == 5) && j == 9) || (i == 6 && j == 10)) {
                        layout[i][j] = 1;
                    }
                }
            }
        } else if (ind == 1) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (i == 0 || j == 0 || i == NUM_ROWS - 1 || j == NUM_COLS - 1) {
                        layout[i][j] = 2;
                    } else if (j != NUM_COLS - 4 && i == NUM_ROWS - 5) {
                        layout[i][j] = 2;
                    } else if ((i < NUM_ROWS - 5 && i != 5) && j == 10) {
                        layout[i][j] = 2;
                    } else if ((j < 10 && j != 3) && i == 11) {
                        layout[i][j] = 2;
                    } else if (i == 3 && (j > 0 && j < 7)) {
                        layout[i][j] = 0;
                    } else if (j == 6 && (i > 3 && i < 11)) {
                        layout[i][j] = 0;
                    } else if ((i % 4 == 0 && j % 4 == 0) || (i % 7 == 0 && j % 7 == 0)) {
                        layout[i][j] = 3;
                    } else {
                        layout[i][j] = 1;
                    }

                    if (i == 15 && j == 0) {
                        layout[i][j] = 1;
                    }
                }
            }
        } else {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (i == 0 || j == 0 || i == NUM_ROWS - 1 || j == NUM_COLS - 1) {
                        layout[i][j] = 2;
                    } else if (j != NUM_COLS - 5 && i == NUM_ROWS - 20) {
                        layout[i][j] = 2;
                    } else if (j == 10 && i != 19) {
                        layout[i][j] = 2;
                    } else if ((i > 8 && i < 13) && (j > 0 && j < 10)) {
                        layout[i][j] = 0;
                    } else if ((i % 5 == 0 && j % 5 == 0) || (i % 7 == 0 && j % 7 == 0)) {
                        layout[i][j] = 3;
                    } else {
                        layout[i][j] = 1;
                    }

                    if ((i == 25 && j == 7) || (i == 3 && j == 10)) {
                        layout[i][j] = 1;
                    }
                }
            }
        }
    }
}
