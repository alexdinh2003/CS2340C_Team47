package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy implements Subscriber2,EnemyMovement {
    public int row;
    public int col;
    private static Enemy enemy;
    private Sprite sprite;
    private SpriteSheet spriteSheet;

    private Enemy(int row, int col, SpriteSheet spriteSheet) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        createSprite();
    }

    public Enemy(SpriteSheet spriteSheet) {
        this(0, 0, spriteSheet);
    }

    public Enemy() {
        this(0, 0, null);
    }

    public static Enemy getInstance(SpriteSheet spriteSheet) {
        return getInstance(0,0,spriteSheet);
    }


    public static Enemy getInstance(int row, int col, SpriteSheet spriteSheet) {
        if (enemy == null) {
            enemy = new Enemy(row, col, spriteSheet);
        }
        return enemy;
    }

    public SpriteSheet getSpriteSheet() {
        return this.spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        createSprite();
    }

    public int[] getPosition() {
        return new int[]{this.row, this.col};
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setInitalPosition(int[] rowCol) {
        setPosition(rowCol[0], rowCol[1]);
    }

    private void createSprite() {
        this.sprite = this.spriteSheet.getEnemy(0);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void draw(Canvas canvas) {
        if (this.spriteSheet == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the enemy.");
            return;
        }
        sprite.draw(
                canvas,
                this.col * MapLayout.TILE_WIDTH,
                this.row * MapLayout.TILE_HEIGHT + 256);
    }
    @Override
    public void update2(Enemy_Player_Collision subject) {
        setPosition(subject.getRow(), subject.getCol());
    }

}
