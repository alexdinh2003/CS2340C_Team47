package com.example.dungeoncrawling.model;
import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class FactoryPattern {
    String type;
    Canvas canvas;
    Enemy enemy;

    public FactoryPattern(String type, Canvas canvas) {
        this.type = type;
        this.canvas = canvas;
        EnemyFactory enemyFactory = new EnemyFactory();
        enemy = enemyFactory.getEnemy(type);
        enemy.draw(canvas);
    }
    public FactoryPattern(String type) {
        this(type, null);
    }
    public void draw(Canvas canvas) {
        this.enemy.draw(canvas);
    }
}
