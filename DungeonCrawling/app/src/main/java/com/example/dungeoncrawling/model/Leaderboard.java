package com.example.dungeoncrawling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Leaderboard {
    private static Leaderboard instance;
    private List<ScoreEntry> scores;

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
        // Sort scores in descending order
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }

    public void addScore(ScoreEntry score) {
        scores.add(score);
    }
}

