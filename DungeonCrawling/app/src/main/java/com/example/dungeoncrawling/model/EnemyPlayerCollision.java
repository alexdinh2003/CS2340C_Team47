package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.map.Tilemap;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlayerCollision {
    private List<Subscriber2> subscribers = new ArrayList<>();
    private Tilemap tilemap;
    private int row;
    private int col;

    public EnemyPlayerCollision(Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    public void check(int newRow, int newCol) {
        //check if the surrounding tiles are wall tiles
        if (!tilemap.isWallCollision(newRow, newCol)) {
            this.row = newRow;
            this.col = newCol;
        }
        notifySubscribers();
    }

    public void subscribe(Subscriber2 subscriber, int row, int col) {
        subscribers.add(subscriber);
        this.row = row;
        this.col = col;
    }
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    protected void notifySubscribers() {
        for (Subscriber2 subscriber : subscribers) {
            subscriber.update2(this);
        }
    }
}
