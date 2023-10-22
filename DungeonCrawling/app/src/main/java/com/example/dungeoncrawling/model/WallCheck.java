package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.map.MapLayout;
import com.example.dungeoncrawling.model.map.Tilemap;

import java.util.ArrayList;
import java.util.List;

public class WallCheck {
    private List<Subscriber> subscribers = new ArrayList<>();
    private Tilemap tilemap;
    private int row;
    private int col;

    public WallCheck (Tilemap tilemap) {
        this.tilemap = tilemap;
    }

    public void check(int newRow, int newCol) {
        //check if the surrounding tiles are wall tiles
        if (!tilemap.isWallCollision(newRow, newCol)) {
            this.row = newRow;
            this.col = newCol;
        }
        System.out.println(this.row + ", " + this.col);
        notifySubscribers();
    }
    public void subscribe (Subscriber subscriber) {
        subscribers.add(subscriber);
        this.row = subscriber.getRow();
        this.col = subscriber.getCol();

    }

    public void unsubscribe (Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    protected void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }
}
