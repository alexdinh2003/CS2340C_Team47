package com.example.dungeoncrawling;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.Player;

public class RowColTest {
    private Player player;

    @Before
    public void setUp() {
        player = Player.getInstance("TestPlayer");
    }

    @Test
    public void testInitialRowAndCol() {
        // Verify that the initial row and col values are as expected
        assertEquals(0, player.getRow());
        assertEquals(0, player.getCol());
    }
}
