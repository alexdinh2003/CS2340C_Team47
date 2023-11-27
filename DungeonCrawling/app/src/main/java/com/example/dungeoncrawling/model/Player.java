package com.example.dungeoncrawling.model;

import android.graphics.Canvas;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Player implements Subscriber {
    private String name;
    private int spriteId;
    private int row;
    private int col;
    private int health;
    private int points;
    private static Player player;
    private Sprite sprite;
    private Sprite sword;
    private SpriteSheet spriteSheet;
    private int speed = 1;

    //creates player class
    private Player(String name, SpriteSheet spriteSheet, int id, int health,
                   int points, int row, int col) {
        this.spriteId = id;
        this.name = name;
        this.health = health;
        this.points = points;
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    public Player(String name, SpriteSheet spriteSheet) {
        this(name, spriteSheet, 0, 5, 100, 0, 0);
    }

    public Player(String name) {
        this(name, null, 0, 5, 100, 0, 0);
    }

    public Player() {
        this("n/a", null, 0, 5, 100, 0, 0);
    }

    public static Player getInstance() {
        return getInstance("n/a", null, 0, 5, 100, 0, 0);
    }

    public static Player getInstance(String name) {
        return getInstance(name, null, 0, 5, 100, 0, 0);
    }

    public static Player getInstance(String name, int health, int row, int col) {
        return getInstance(name, null, 0, health, 100, row, col);
    }

    public static Player getInstance(SpriteSheet spriteSheet) {
        return getInstance("n/a", spriteSheet, 0, 5, 100, 0, 0);
    }

    public static Player getInstance(String name, SpriteSheet spriteSheet) {
        return getInstance(name, spriteSheet, 0, 5, 100, 0, 0);
    }

    public static Player getInstance(String name, SpriteSheet spriteSheet, int id, int health,
                                     int points, int row, int col) {
        if (player == null) {
            player = new Player(name, spriteSheet, id, health, points, row, col);
        }
        return player;
    }

    public int getScore() {
        return points;
    }
    public void setScore(int score) {
        if (score < 0) {
            this.points = 0;
        } else {
            this.points = score;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void loseHealth(int amountLost) {
        this.health -= amountLost;
        this.points -= (amountLost * 5);
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
        this.sprite = this.spriteSheet.getPlayer(this.spriteId);
        this.sword = this.spriteSheet.getSword();
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
                    + "sprite sheet for the player.");
            return;
        }
        sprite.draw(
                canvas,
                this.col * MapLayout.TILE_WIDTH,
                this.row * MapLayout.TILE_HEIGHT + 256);
        sword.draw(
                canvas,
                this.col * MapLayout.TILE_WIDTH,
                this.row * MapLayout.TILE_HEIGHT + 256);
    }

    @Override
    public void update(WallCheck subject) {
        setPosition(subject.getRow(), subject.getCol());
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
