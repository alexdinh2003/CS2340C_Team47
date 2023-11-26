package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.dungeoncrawling.model.Subscriber;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.model.playermove.DirectionStrategy;
import com.example.dungeoncrawling.model.playermove.Down;
import com.example.dungeoncrawling.model.playermove.Left;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.playermove.Right;
import com.example.dungeoncrawling.model.playermove.Up;

import org.junit.Before;
import org.junit.Test;

public class WallCheckTest {

    private WallCheck wallCheck;
    public static Tilemap createTilemap() {
        int[][] map = new int[][] {
                {1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 8},
                {1, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        Tilemap tilemap = new Tilemap(map, new int[]{3, 1});
        return tilemap;
    }

    @Before
    public void setUp() {
        // Initialize Tilemap for testing
        Tilemap tilemap = createTilemap();
        wallCheck = new WallCheck(tilemap);
    }

    @Test
    public void shouldUpdatePositionIfNotWallCollision() {
        // Arrange
        int initialRow = 1;
        int initialCol = 1;

        // Subscribe to wall check
        TestSubscriber subscriber = new TestSubscriber();
        wallCheck.subscribe(subscriber, initialRow, initialCol);

        // Act
        wallCheck.check(initialRow + 1, initialCol + 1);

        // Assert
        assertEquals(initialRow + 1, wallCheck.getRow());
        assertEquals(initialCol + 1, wallCheck.getCol());
    }

    @Test
    public void shouldNotUpdatePositionIfWallCollision() {
        // Arrange
        int initialRow = 1;
        int initialCol = 1;

        // Subscribe to wall check
        TestSubscriber subscriber = new TestSubscriber();
        wallCheck.subscribe(subscriber, initialRow, initialCol);

        // Act
        // Assuming (initialRow + 1, initialCol + 1) is a wall
        wallCheck.check(initialRow + 1, initialCol + 1);

        // Assert
        assertEquals(initialRow, wallCheck.getRow());
        assertEquals(initialCol, wallCheck.getCol());
    }

    @Test
    public void shouldNotifySubscribers() {
        // Arrange
        TestSubscriber subscriber = new TestSubscriber();
        wallCheck.subscribe(subscriber, 0, 0);

        // Act
        wallCheck.check(1, 1);

        // Assert
        assertTrue(subscriber.isUpdated());
    }

    // Custom test subscriber for checking if update is called
    private static class TestSubscriber implements Subscriber {
        private boolean updated = false;

        @Override
        public void update(WallCheck subject) {
            updated = true;
        }

        public boolean isUpdated() {
            return updated;
        }
    }
}
