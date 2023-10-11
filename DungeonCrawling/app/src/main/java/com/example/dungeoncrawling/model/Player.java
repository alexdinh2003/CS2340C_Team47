package com.example.dungeoncrawling.model;

import android.graphics.Rect;

public class Player {
    private String name;
    private int spriteId;
    private Rect mapLocation;
    private int health;
    private int points;
    private static Player player;

    private Player(String name, int id, int health, int points, Rect mapLocation) {
        this.spriteId = id;
        this.name = name;
        this.mapLocation = mapLocation;
        this.health = health;
        this.points = points;
    }

    public Player(String name) {
        this(name, 0, 5, 100, new Rect(0, 0,
                64, 64));
    }

    public Player() {
        this("n/a", 0, 5, 100, new Rect(0, 0,
                64, 64));
    }

    public static Player getInstance() {
        return getInstance("n/a", 0, 5, 100,
                new Rect(0, 0, 64, 64));
    }

    public static Player getInstance(String name) {
        return getInstance(name, 0, 5, 100,
                new Rect(0, 0, 64, 64));
    }

    public static Player getInstance(String name, int id, int health, int points,
                                     Rect mapLocation) {
        if (player == null) {
            player = new Player(name, id, health, points, mapLocation);
        }
        return player;
    }

    public int getScore() {
        return points;
    }
    public void setScore(int score) {
        this.points = score;
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
