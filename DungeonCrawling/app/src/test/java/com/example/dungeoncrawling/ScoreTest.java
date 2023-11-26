package com.example.dungeoncrawling;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.example.dungeoncrawling.model.Timer;
public class ScoreTest {

    @Test
    public void timeChangesScore() {
        Timer timer = new Timer();
        long startScore = timer.getScore();
        long endScore = 0;
        long startTime = System.currentTimeMillis();
        if (System.currentTimeMillis() == startTime + 5) {
            endScore = timer.getScore();
        }
        assertNotEquals(startScore, endScore);
    }
}
