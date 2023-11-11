package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.Enemy;
import com.example.dungeoncrawling.model.Enemy1;
public class EnemyFactory {
    public Enemy getEnemy(String enemyType) {
        if (enemyType == null) {
            return null;
        }
        if (enemyType.equalsIgnoreCase("enemy1")) {
            return new Enemy1();
        } else if (enemyType.equalsIgnoreCase("enemy2")) {
            return new Enemy2();
        } else if (enemyType.equalsIgnoreCase("enemy3")) {
            return new Enemy3();
        } else if (enemyType.equalsIgnoreCase("enemy4")) {
            return new Enemy4();
        }

        return null;
    }
}
