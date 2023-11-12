package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.map.Tilemap;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlayerCollision {
    private List<EnemySubscriber> subscribers = new ArrayList<>();
    private Tilemap tilemap;    // for enemy dont need this
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

    public void subscribe(EnemySubscriber subscriber, int row, int col) {
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
        for (EnemySubscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }
}
