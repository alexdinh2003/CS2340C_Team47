package com.example.dungeoncrawling;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.model.enemies.Enemy1;
import com.example.dungeoncrawling.model.enemies.Enemy2;
import com.example.dungeoncrawling.model.enemies.Enemy3;
import com.example.dungeoncrawling.model.enemies.Enemy4;
public class EnemyFactoryTest {

    @Test
    public void createsCorrectEnemy() {
        Enemy enemy1 = EnemyFactory.getEnemy("enemy1",0,0);
        Enemy enemy2 = EnemyFactory.getEnemy("enemy2",0,0);
        Enemy enemy3 = EnemyFactory.getEnemy("enemy3",0,0);
        Enemy enemy4 = EnemyFactory.getEnemy("enemy4",0,0);
        assertTrue(enemy1.getClass() == Enemy1.class);
        assertTrue(enemy2.getClass() == Enemy2.class);
        assertTrue(enemy3.getClass() == Enemy3.class);
        assertTrue(enemy4.getClass() == Enemy4.class);
    }
}
