package com.example.dungeoncrawling.viewmodels;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.map.MapLayout;

public class GameMap implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Tilemap tilemap;
    private Paint white;
    private Player player;
    private WallCheck wallCheck;
    private GameLoop gameLoop;
    private Square sq;
    private TextView difficulty;
    private ImageView health;

    public GameMap(SurfaceHolder holder, SpriteSheet spriteSheet, int roomInd) {
        this.holder = holder;
        this.tilemap = new Tilemap(spriteSheet, roomInd);
        this.white = new Paint();
        this.white.setColor(-1);

        this.player = Player.getInstance();
        this.player.setSpriteSheet(spriteSheet);
        this.player.setInitalPosition(this.tilemap.getStartPos());

        this.wallCheck = new WallCheck(this.tilemap);
        this.wallCheck.subscribe(this.player, this.player.getRow(), this.player.getCol());

        this.gameLoop = new GameLoop(this);

        this.sq = new Square(1, 1);
    }

    public void render() {
        Canvas c = this.holder.lockCanvas();
        if (c != null) {
            c.drawRect(new Rect(0, 0, 4000, 1000), white);
            this.tilemap.draw(c);
            this.player.draw(c);
            this.sq.draw(c);
            this.holder.unlockCanvasAndPost(c);
        }
    }

    public void update() {
        this.sq.move();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.gameLoop.startGameLoop();
        render();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}

class Square {
    private int row;
    private int col;
    private int xDir = 1, yDir = 1;
    private int width = 64;
    private Paint white;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.white = new Paint();
        this.white.setColor(-1);
    }

    public void move() {
        this.row += xDir;
        if (this.row >= MapLayout.NUM_ROWS - 1 || this.row < 0) {
            xDir *= -1;
        }

        this.col += yDir;
        if (this.col >= MapLayout.NUM_COLS - 1 || this.col < 0) {
            yDir *= -1;
        }
    }

    public void draw(Canvas c) {
        int x = this.col * MapLayout.TILE_WIDTH;
        int y = this.row * MapLayout.TILE_HEIGHT + 256;
        c.drawRect(new Rect(x, y, x+MapLayout.TILE_WIDTH, y+MapLayout.TILE_HEIGHT), this.white);
    }

}
