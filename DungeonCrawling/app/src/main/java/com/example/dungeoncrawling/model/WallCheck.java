package com.example.dungeoncrawling.model;
import java.util.ArrayList;
import java.util.List;

public class WallCheck {
    private List<Subscriber> subscribers = new ArrayList<>();

    public WallCheck () {}

    public void check() {
        //check if the surrounding tiles are wall tiles
        notifySubscribers();
    }
    public void subscribe (Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe (Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    protected void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }
}
