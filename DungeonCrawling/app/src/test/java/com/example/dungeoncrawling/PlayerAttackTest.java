package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.viewmodels.GameMap;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

import org.junit.Before;
import org.junit.Test;

public class PlayerAttackTest {

    private GameMap gameMap;
    private SurfaceHolder holder;
    private Context context;

    @Before
    public void setUp() {
        SpriteSheet spriteSheet = new SpriteSheet(context);
        int roomIndex = 0;

        SurfaceView surface = new SurfaceView(context);
        holder = surface.getHolder();

        gameMap = new GameMap(holder, spriteSheet, roomIndex, context);
    }

    @Test
    public void playerAttackRemovesEnemy() {
        // Arrange
        int initialPlayerRow = 1;
        int initialPlayerCol = 1;
        int enemyRow = 2;
        int enemyCol = 2;

        // Create an enemy at the specified position
        Enemy enemy = EnemyFactory.getEnemy("enemy1", enemyRow, enemyCol);
        gameMap.getEnemies().add(enemy);

        // Act
        gameMap.playerAttack(new int[]{initialPlayerRow, initialPlayerCol});

        // Assert
        assertEquals(0, gameMap.getEnemies().size());
        assertEquals(5, gameMap.getPlayer().getScore());
    }

    @Test
    public void playerAttackDoesNotRemoveEnemyIfOutOfRange() {
        // Arrange
        int initialPlayerRow = 1;
        int initialPlayerCol = 1;
        int enemyRow = 5;
        int enemyCol = 5;

        // Create an enemy at the specified position
        Enemy enemy = EnemyFactory.getEnemy("enemy1", enemyRow, enemyCol);
        gameMap.getEnemies().add(enemy);

        // Act
        gameMap.playerAttack(new int[]{initialPlayerRow, initialPlayerCol});

        // Assert
        assertEquals(1, gameMap.getEnemies().size());
        assertEquals(0, gameMap.getPlayer().getScore());
    }
}
