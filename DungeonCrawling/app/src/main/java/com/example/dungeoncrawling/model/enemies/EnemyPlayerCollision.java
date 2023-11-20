package com.example.dungeoncrawling.model.enemies;

import com.example.dungeoncrawling.model.Subscriber;
import android.content.Context;
import android.media.MediaPlayer;
import com.example.dungeoncrawling.R;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlayerCollision {
    private List<EnemySubscriber> subscribers = new ArrayList<>();
    private int row;
    private int col;
    private MediaPlayer collisionSound; // New field for MediaPlayer

    public EnemyPlayerCollision(Context context, int row, int col) {
        this.row = row;
        this.col = col;
        collisionSound = MediaPlayer.create(context, R.raw.collision_sound); // Initialize with your collision sound
    }

    public void check(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        notifySubscribers();
        playCollisionSound(); // Play collision sound
    }

    private void playCollisionSound() {
        if (collisionSound != null) {
            collisionSound.start();
        }
    }

    public void subscribe(EnemySubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void unsubscribeAll() {
        subscribers.clear();
    }

    public void removeAll() {
        subscribers = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<EnemySubscriber> getSubscribers() {
        return subscribers;
    }

    protected void notifySubscribers() {
        for (EnemySubscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }

    public void release() {
        if (collisionSound != null) {
            collisionSound.release();
            collisionSound = null;
        }
    }
}
