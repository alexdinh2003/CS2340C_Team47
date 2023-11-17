package com.example.dungeoncrawling.model.map;

public class MapLayout {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int NUM_ROWS = 26;
    public static final int NUM_COLS = 17;
    private int[][] layout;
    private int[] startPos;
    private int[][] powerUpPos;

    private static int exitX; // Set the actual X-coordinate of the exit
    private static int exitY; // Set the actual Y-coordinate of the exit
    public MapLayout(int ind) {
        if (ind == 0) {
            this.startPos = new int[]{23, NUM_COLS - 2};
            this.powerUpPos = new int[][] {
                    {NUM_ROWS - 2, 1},
                    {12, NUM_COLS - 4}
            };
        } else if (ind == 1) {
            this.startPos = new int[]{24, 1};
            this.powerUpPos = new int[][] {
                    {5, 1},
                    {13, 5}
            };
        } else {
            this.startPos = new int[]{1, 1};
            this.powerUpPos = new int[][] {
                    {3, NUM_COLS - 4},
                    {NUM_ROWS - 5, 5}
            };
        }
        initializeLayout(ind);
    }

    public MapLayout(int[][] customLayout, int[] startPos) {
        this.layout = customLayout;
        this.startPos = startPos;
    }

    public int[][] getLayout() {
        return layout;
    }

    public int[] getStartPos() {
        return this.startPos;
    }

    public int[][] getPowerUpPos() {
        return this.powerUpPos;
    }

    private void initializeLayout(int ind) {
        dungeonBorders();

        if (ind == 0) {
            //mark exit
            layout[0][4] = 7;
            layout[0][6] = 7;
            //actual exit & entrance
            layout[0][5] = 8;
            layout[startPos[0]][startPos[1] + 1] = 10;

            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (j != 4 && i == NUM_ROWS - 8) {
                        //horizontal wall @ row 18
                        layout[i][j] = 4;
                    } else if (j != 13 && i == 10) {
                        //horizontal wall @ row 10
                        layout[i][j] = 4;
                    } else if (i > 10 && i < 13 && j == 6) {
                        // vertical wall (top half) @ col 6
                        layout[i][j] = 1;
                    } else if (i > 13 && i < 18 && j == 6) {
                        // vertical wall (bottom half) @ col 6
                        layout[i][j] = 1;
                    } else if ((i > 6 && i < 9) && (j > 7 && j < 11)) {
                        //hole central
                        layout[i][j] = 9;
                    }
                }
            }

            // Set the exit coordinates
            exitX = 1; // Set the X-coordinate of the exit
            exitY = NUM_ROWS / 2; // Set the Y-coordinate of the exit

            //make pits (for fun)
            layout[3][8] = 9;
            layout[5][13] = 9;
            layout[13][13] = 9;
            layout[15][10] = 9;
            layout[13][4] = 9;
            layout[20][3] = 9;
            layout[22][10] = 9;
            layout[23][4] = 9;

            //add corners where needed
            layout[10][6] = 5;
            layout[NUM_ROWS - 8][0] = 5;
            layout[10][0] = 5;
            layout[NUM_ROWS - 8][NUM_COLS - 1] = 6;
            layout[10][NUM_COLS - 1] = 6;

        } else if (ind == 1) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (j != NUM_COLS - 4 && i == NUM_ROWS - 5) {
                        layout[i][j] = 4;
                    } else if ((i < NUM_ROWS - 5 && i != 5) && j == 10) {
                        layout[i][j] = 1;
                    } else if ((j < 10 && j != 3) && i == 11) {
                        layout[i][j] = 4;
                    } else if (i == 3 && ((j > 1 && j < 4) || (j > 4 && j < 6))) {
                        layout[i][j] = 9;
                    } else if (j == 6 && ((i > 3 && i < 7) || (i > 7 && i < 11))) {
                        layout[i][j] = 9;
                    }
                }
            }

            // Set the exit coordinates
            exitX = NUM_COLS / 2; // Set the X-coordinate of the exit
            exitY = NUM_ROWS - 1; // Set the Y-coordinate of the exit

            //mark exit
            layout[14][0] = 7;
            layout[16][0] = 7;
            //actual exit & entrance
            layout[15][0] = 8;
            layout[startPos[0]][startPos[1] - 1] = 10;

            //make pits (for fun)
            layout[3][8] = 9;
            layout[5][13] = 9;
            layout[10][15] = 9;
            layout[20][15] = 9;
            layout[14][13] = 9;
            layout[16][11] = 9;
            layout[13][7] = 9;
            layout[15][3] = 9;
            layout[18][5] = 9;
            layout[22][10] = 9;
            layout[23][4] = 9;

            //add corners where needed
            layout[NUM_ROWS - 5][0] = 5;
            layout[NUM_ROWS - 5][NUM_COLS - 1] = 6;
            layout[11][0] = 5;
            layout[0][10] = 5;

        } else {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    if (j != NUM_COLS - 5 && i == NUM_ROWS - 20) {
                        layout[i][j] = 4;
                    } else if (j == 10 && i != 19 && i != 3) {
                        layout[i][j] = 1;
                    } else if ((i > 8 && i < 12) && (j > 2 && j < 7)) {
                        layout[i][j] = 9;
                    }
                }
            }

            // Set the exit coordinates
            exitX = NUM_COLS - 1; // Set the X-coordinate of the exit
            exitY = NUM_ROWS / 2; // Set the Y-coordinate of the exit

            layout[10][5] = 0;
            layout[11][3] = 0;

            //make pits (for fun)
            layout[3][6] = 9;
            layout[2][13] = 9;
            layout[14][15] = 9;
            layout[21][15] = 9;
            layout[10][12] = 9;
            layout[23][11] = 9;
            layout[13][7] = 9;
            layout[15][3] = 9;
            layout[19][5] = 9;
            layout[19][13] = 9;
            layout[23][6] = 9;

            //mark exit
            layout[25][4] = 7;
            layout[25][6] = 7;
            //actual exit & entrance
            layout[25][5] = 8;
            layout[startPos[0]][startPos[1] - 1] = 10;

            //add corners where needed
            layout[NUM_ROWS - 1][10] = 5;
            layout[NUM_ROWS - 20][NUM_COLS - 1] = 6;
            layout[NUM_ROWS - 20][0] = 5;
            layout[0][10] = 5;
            layout[6][10] = 5;
        }
    }

    private void dungeonBorders() {
        layout = new int[NUM_ROWS][NUM_COLS];

        //populate horizontal wall tiles
        for (int j = 0; j < NUM_COLS; j++) {
            layout[0][j] = 4;
            layout[NUM_ROWS - 1][j] = 4;
        }

        //corner tiles
        layout[0][0] = 5;
        layout[0][NUM_COLS - 1] = 6;

        //populate vertical wall tiles
        for (int i = 1; i < NUM_ROWS - 1; i++) {
            layout[i][0] = 1;
            layout[i][NUM_COLS - 1] = 2;
        }
    }
}
