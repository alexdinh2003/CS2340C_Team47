package com.example.dungeoncrawling.model;

public class EnemyFactory {

    public static Enemy getEnemy(String enemyType, int row, int col) {
        if (enemyType == null) {
            return null;
        }
        if (enemyType.equalsIgnoreCase("enemy1")) {
            return new Enemy1(row, col);
        } else if (enemyType.equalsIgnoreCase("enemy2")) {
            return new Enemy2(row, col);
        } else if (enemyType.equalsIgnoreCase("enemy3")) {
            return new Enemy3(row, col);
        } else if (enemyType.equalsIgnoreCase("enemy4")) {
            return new Enemy4(row, col);
        }

        return null;
    }
}
