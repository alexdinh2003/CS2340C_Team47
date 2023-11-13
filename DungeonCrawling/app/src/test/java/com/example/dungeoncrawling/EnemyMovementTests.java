package com.example.dungeoncrawling;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.Enemy;
import com.example.dungeoncrawling.model.EnemyFactory;
import com.example.dungeoncrawling.model.Enemy1;
import com.example.dungeoncrawling.model.Enemy2;
import com.example.dungeoncrawling.model.Enemy3;
import com.example.dungeoncrawling.model.Enemy4;
public class EnemyMovementTests {

    @Test
    public void Enemy1MovementTest() {
        Enemy enemy1 = EnemyFactory.getEnemy("enemy1",2, 5);
        int startX = 2;
        int startY = 5;
        enemy1.move();
        int endX = enemy1.getRow();
        int endY = enemy1.getCol();
        assertTrue(startX != endX);
        assertTrue(startY == endY);
    }

    @Test
    public void Enemy2MovementTest() {
        Enemy enemy2 = EnemyFactory.getEnemy("enemy2",20, 3);
        int startX = 20;
        int startY = 3;
        enemy2.move();
        int endX = enemy2.getRow();
        int endY = enemy2.getCol();
        assertTrue(startX == endX);
        assertTrue(startY != endY);
    }

    @Test
    public void Enemy3MovementTest() {
        Enemy enemy3 = EnemyFactory.getEnemy("enemy3",15, 3);
        int startX = 15;
        int startY = 3;
        enemy3.move();
        int endX = enemy3.getRow();
        int endY = enemy3.getCol();
        assertTrue(startX != endX);
        assertTrue(startY != endY);
    }

    @Test
    public void Enemy4MovementTest() {
        Enemy enemy4 = EnemyFactory.getEnemy("enemy1",2, 5);
        int startX = 2;
        int startY = 5;
        enemy4.move();
        int endX = enemy4.getRow();
        int endY = enemy4.getCol();
        assertTrue(startX != endX);
        assertTrue(startY == endY);
    }
    @Test
    public void EnemyGetRowTest() {
        Enemy enemy4 = EnemyFactory.getEnemy("enemy1",2, 5);
        assertEquals(enemy4.getCol(), 5);
    }

    @Test
    public void EnemyGetColTest() {
        Enemy enemy4 = EnemyFactory.getEnemy("enemy1",2, 5);
        assertEquals(enemy4.getRow(), 2);
    }
    @Test
    public void EnemySetPositionTest() {
        Enemy enemy4 = EnemyFactory.getEnemy("enemy1",2, 5);
        enemy4.setPosition(10, 11);
        assertEquals(enemy4.getCol(), 11);
        assertEquals(enemy4.getRow(), 10);
    }



}
