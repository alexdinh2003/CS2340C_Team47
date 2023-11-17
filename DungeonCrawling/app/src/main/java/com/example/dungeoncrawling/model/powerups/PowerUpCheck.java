package com.example.dungeoncrawling.model.powerups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerUpCheck {

    private List<PowerUp> subscribers = new ArrayList<>();
    private int[] pos;
    private static PowerUpCheck powerUpCheck;

    private PowerUpCheck(int[] pos) {
        this.pos = pos;
    }

    public static PowerUpCheck getInstance(int[] pos) {
        if (powerUpCheck == null) {
            powerUpCheck = new PowerUpCheck(pos);
        }
        return powerUpCheck;
    }

    public void check(int[] newPos) {
        this.pos = newPos;
        notifySubscribers();
    }

    public void subscribe(PowerUp subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(PowerUp subscriber) {
        subscribers.remove(subscriber);
    }

    public void unsubscribeAll() {
        if (subscribers.size() > 0) {
            subscribers.remove(subscribers.size() - 1);
        }
    }

    public void removeAll() {
        subscribers = new ArrayList<PowerUp>();
    }

    public int[] getPos() {
        return pos;
    }

    public List<PowerUp> getSubscribers() {
        return subscribers;
    }

    protected void notifySubscribers() {
        for (int i = 0; i < subscribers.size(); i++) {
            if (Arrays.equals(subscribers.get(i).getPos(), pos)) {
                subscribers.get(i).powerUp();
                subscribers.remove(i);
                i--;
            }
        }
    }
}
