package com.example.dungeoncrawling;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.Player;

public class PlayerTests {
    @Test
    public void playerCreatedProperly() {
        Player p3 = Player.getInstance("Pedro");
        assertEquals(p3.getName(), "Pedro");
        assertEquals(p3.getHealth(), 5);
        assertEquals(p3.getScore(), 100);
        assertEquals(p3.getSpriteId(), 0);
    }

    @Test
    public void playerIsSingleton() {
        Player p1 = Player.getInstance();
        Player p2 = Player.getInstance();
        p2.setHealth(5);
        p2.setName("Pedro");
        p2.setSpriteId(3);
        p2.setScore(100);
        assertEquals(p1, p2);
    }


}
