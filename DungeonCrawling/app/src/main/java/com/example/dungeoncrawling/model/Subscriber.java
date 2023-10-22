package com.example.dungeoncrawling.model;
public interface Subscriber {
    void update(WallCheck subject);

    int getRow();
    int getCol();
}
