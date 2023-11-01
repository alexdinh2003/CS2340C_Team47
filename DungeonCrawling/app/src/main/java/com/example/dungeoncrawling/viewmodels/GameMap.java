package com.example.dungeoncrawling.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.dungeoncrawling.model.DirectionStrategy;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.model.Player;

public class GameMap implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Tilemap tilemap;
    private Paint white;
    private Player player;
    private WallCheck wallCheck;

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
    }

    public void render() {
        Canvas c = this.holder.lockCanvas();
        c.drawRect(new Rect(0, 0, 4000, 1000), white);
        this.tilemap.draw(c);
        this.player.draw(c);
        this.holder.unlockCanvasAndPost(c);
    }

    public boolean update(DirectionStrategy strat) {
        int[] newLoc = strat.move(this.player);
        this.wallCheck.check(newLoc[0], newLoc[1]);

        if (this.tilemap.isExit(this.player.getRow(), this.player.getCol())) {
            return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        render();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
