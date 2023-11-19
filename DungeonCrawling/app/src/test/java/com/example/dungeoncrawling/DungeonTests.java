package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.dungeoncrawling.model.playermove.DirectionStrategy;
import com.example.dungeoncrawling.model.playermove.Down;
import com.example.dungeoncrawling.model.playermove.Left;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.playermove.Right;
import com.example.dungeoncrawling.model.playermove.Up;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.map.Tilemap;

import org.junit.Test;

public class DungeonTests {

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

    @Test
    public void wallCollisionPreventCollision() {
        Tilemap tilemap = createTilemap();
        Player player = Player.getInstance("name");
        player.setInitalPosition(tilemap.getStartPos());
        WallCheck wallCheck = new WallCheck(tilemap);
        wallCheck.subscribe(player, player.getRow(), player.getCol());

        int[] newLocation = new int[]{4, 0};
        wallCheck.check(newLocation[0], newLocation[1]);
        assertNotEquals(newLocation[0], player.getRow());
        assertNotEquals(newLocation[1], player.getCol());
        assertEquals(player.getRow(), tilemap.getStartPos()[0]);
        assertEquals(player.getCol(), tilemap.getStartPos()[1]);
    }

    @Test
    public void wallCollisionPermitValidMovement() {
        Tilemap tilemap = createTilemap();
        Player player = Player.getInstance("name");
        player.setInitalPosition(tilemap.getStartPos());
        WallCheck wallCheck = new WallCheck(tilemap);
        wallCheck.subscribe(player, player.getRow(), player.getCol());

        int[] newLocation = new int[]{2, 2};
        wallCheck.check(newLocation[0], newLocation[1]);

        assertEquals(player.getRow(), newLocation[0]);
        assertEquals(player.getCol(), newLocation[1]);
        assertNotEquals(player.getRow(), tilemap.getStartPos()[0]);
        assertNotEquals(player.getCol(), tilemap.getStartPos()[1]);
    }

    @Test
    public void leftStrategyTest() {
        Player player = Player.getInstance("name");
        player.setInitalPosition(new int[]{0, 0});

        DirectionStrategy strategy = new Left();
        int[] newLoc = strategy.move(player);
        assertEquals(player.getRow(), newLoc[0]);
        assertEquals(player.getCol() - 1, newLoc[1]);
    }

    @Test
    public void rightStrategyTest() {
        Player player = Player.getInstance("name");
        player.setInitalPosition(new int[]{0, 0});

        DirectionStrategy strategy = new Right();
        int[] newLoc = strategy.move(player);
        assertEquals(player.getRow(), newLoc[0]);
        assertEquals(player.getCol() + 1, newLoc[1]);
    }

    @Test
    public void upStrategyTest() {
        Player player = Player.getInstance("name");
        player.setInitalPosition(new int[]{0, 0});

        DirectionStrategy strategy = new Up();
        int[] newLoc = strategy.move(player);
        assertEquals(player.getRow() - 1, newLoc[0]);
        assertEquals(player.getCol(), newLoc[1]);
    }

    @Test
    public void downStrategyTest() {
        Player player = Player.getInstance("name");
        player.setInitalPosition(new int[]{0, 0});

        DirectionStrategy strategy = new Down();
        int[] newLoc = strategy.move(player);
        assertEquals(player.getRow() + 1, newLoc[0]);
        assertEquals(player.getCol(), newLoc[1]);
    }

}
