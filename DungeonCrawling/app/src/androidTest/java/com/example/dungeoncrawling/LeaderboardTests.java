package com.example.dungeoncrawling;

import android.content.Context;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.ScoreEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LeaderboardTests {
    @Test
    public void scoreComparisonTest() {
        ScoreEntry score1 = new ScoreEntry(5);
        ScoreEntry score2 = new ScoreEntry(4);
        assertTrue(score1.compareTo(score2) > 0);
    }
    @Test
    public void scoreOrderIsDescending() {
        ScoreEntry[] scores = new ScoreEntry[10];
        Leaderboard board = Leaderboard.getInstance();
        for (int i = 0; i < scores.length; i++) {
            scores[i] = new ScoreEntry(i);
            board.addScore(scores[i]);
        }
        List<ScoreEntry> scoresExpected = new ArrayList<>(5);
        for (int i = scores.length-1; i >= 5; i--) {
            scoresExpected.add(scores[i]);
        }

        assertEquals(scoresExpected, board.getTopAttempts(5));

    }

    @Test
    public void leaderboardIsSingleton() {
        Leaderboard board1 = Leaderboard.getInstance();
        Leaderboard board2 = Leaderboard.getInstance();
        board2.addScore(new ScoreEntry(5));
        assertEquals(board1, board2);
    }
}