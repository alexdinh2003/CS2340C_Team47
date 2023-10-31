package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy implements Subscriber2 {
    private String name;
    private int spriteId;
    public int row;
    public int col;
    private int health;
    private static Enemy enemy;
    private Sprite sprite;
    private SpriteSheet spriteSheet;

    private Enemy(String name, SpriteSheet spriteSheet, int id, int health, int row, int col) {
        this.spriteId = id;
        this.name = name;
        this.health = health;
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    public Enemy(String name, SpriteSheet spriteSheet) {
        this(name, spriteSheet, 0, 5, 0, 0);
    }

    public Enemy(String name) {
        this(name, null, 0, 5, 0, 0);
    }

    public Enemy() {
        this("n/a", null, 0, 5, 0, 0);
    }

    public static Enemy getInstance() {
        return getInstance("n/a", null, 0, 5, 0, 0);
    }

    public static Enemy getInstance(String name) {
        return getInstance(name, null, 0, 5, 0, 0);
    }

    public static Enemy getInstance(SpriteSheet spriteSheet) {
        return getInstance("n/a", spriteSheet, 0, 5, 0, 0);
    }

    public static Enemy getInstance(String name, SpriteSheet spriteSheet) {
        return getInstance(name, spriteSheet, 0, 5, 0, 0);
    }

    public static Enemy getInstance(String name, SpriteSheet spriteSheet, int id, int health,
                                    int row, int col) {
        if (enemy == null) {
            enemy = new Enemy(name, spriteSheet, id, health, row, col);
        }
        return enemy;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpriteId() {
        return this.spriteId;
    }
    public void setSpriteId(int id) {
        this.spriteId = id;
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.sprite = this.spriteSheet.getEnemy(this.spriteId);
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
