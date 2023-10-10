package com.example.dungeoncrawling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private static Leaderboard instance;
    private List<ScoreEntry> scores;
    private int recentAttemptInd;

    private Leaderboard() {
        scores = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public List<ScoreEntry> getScores() {
        // Scores already in descending order
        return scores;
    }

    public void addScore(ScoreEntry score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        recentAttemptInd = scores.indexOf(score);
    }

    public List<ScoreEntry> getTopAttempts(int count) {
        if (count >= scores.size()) {
            return scores; // Return all attempts if count exceeds the list size
        }
        return scores.subList(0, count);
    }

    public ScoreEntry getRecentAttempt() {
        return scores.get(recentAttemptInd);
    }
}

