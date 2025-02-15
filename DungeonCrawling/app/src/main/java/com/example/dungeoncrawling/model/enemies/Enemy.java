package com.example.dungeoncrawling.model.enemies;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public abstract class Enemy implements EnemySubscriber {
    private int row;
    private int col;
    private SpriteSheet spriteSheet;
    private Player player;
    private HP health;
    protected Sprite sprite;
    private int speed;

    //creates enemy
    public Enemy(int row, int col, SpriteSheet spriteSheet) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        this.player = Player.getInstance();
        this.health = HP.getInstance();
        createSprite();
    }

    //instantiates enemy attributes
    public Enemy(int row, int col) {
        this.row = row;
        this.col = col;
        this.player = Player.getInstance();
        this.health = HP.getInstance();
    }

    public abstract void createSprite();

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

    public void setInitialPosition(int[] rowCol) {
        setPosition(rowCol[0], rowCol[1]);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return this.player;
    }

    public abstract void move();

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

    //updates the health of the player
    public void update(EnemyPlayerCollision subject) {
        if (subject.getRow() == this.row && subject.getCol() == this.col) {
            player.loseHealth(1 * health.getDifficulty());
        }
    }

}
