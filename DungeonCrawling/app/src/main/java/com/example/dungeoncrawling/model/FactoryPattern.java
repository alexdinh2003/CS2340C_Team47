package com.example.dungeoncrawling.model;
import android.graphics.Canvas;
public class FactoryPattern {
    String type;
    Canvas canvas;

    public FactoryPattern(String type, Canvas canvas) {
        this.type = type;
        this.canvas = canvas;
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.getEnemy(type);
        enemy.draw(canvas);
    }
}
