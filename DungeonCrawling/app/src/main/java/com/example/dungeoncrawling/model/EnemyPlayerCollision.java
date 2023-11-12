package com.example.dungeoncrawling.model;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlayerCollision {
    private List<EnemySubscriber> subscribers = new ArrayList<>();
    private int row;
    private int col;
    private static EnemyPlayerCollision coll;

    private EnemyPlayerCollision() {
    }

    public static EnemyPlayerCollision getInstance() {
        if (coll == null) {
            coll = new EnemyPlayerCollision();
        }
        return coll;
    }

    public void check(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        notifySubscribers();
    }

    public void subscribe(EnemySubscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void unsubscribe() {
        if (subscribers.size() > 0) {
            subscribers.remove(subscribers.size() - 1);
        }
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
