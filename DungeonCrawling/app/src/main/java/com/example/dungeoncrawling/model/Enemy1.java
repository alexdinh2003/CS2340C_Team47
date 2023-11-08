package com.example.dungeoncrawling.model;
import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy1 implements Enemy,EnemySubscriber {
    int row;
    int col;
    SpriteSheet spriteSheet;
    Sprite sprite;
    public Enemy1 (int row, int col, SpriteSheet spriteSheet) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        createSprite();
    }
    public Enemy1() {this(50,50,null);}

    public void createSprite() {
        //add specific sprite id for this enemy
        this.sprite = this.spriteSheet.getPlayer(1);
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //add method for this enemy's unique movement

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
