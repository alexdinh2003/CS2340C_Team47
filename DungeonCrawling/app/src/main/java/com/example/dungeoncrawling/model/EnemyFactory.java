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
        }
        // add if else for each enemy type
        return null;
    }
}
