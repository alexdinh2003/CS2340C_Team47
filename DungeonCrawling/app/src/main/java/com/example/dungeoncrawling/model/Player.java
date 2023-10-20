package com.example.dungeoncrawling.model;

import android.graphics.Canvas;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Player {
    private String name;
    private int spriteId;
    private int row;
    private int col;
    private int health;
    private int points;
    private static Player player;
<<<<<<< HEAD
    private int x, y;
=======
    private Sprite sprite;
    private SpriteSheet spriteSheet;
>>>>>>> 20c2220e4d85f2f1bb7db71e62e3a02543defd08


    private Player(String name, SpriteSheet spriteSheet, int id, int health, int points,
                   int row, int col) {
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

<<<<<<< HEAD
=======
    public SpriteSheet getSpriteSheet() {
        return this.spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        createSprite();
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPositionArr(int[] rowCol) {
        setPosition(rowCol[0], rowCol[1]);
    }

    private void createSprite() {
        this.sprite = this.spriteSheet.getPlayer(this.spriteId);
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
    }
>>>>>>> 20c2220e4d85f2f1bb7db71e62e3a02543defd08

}
