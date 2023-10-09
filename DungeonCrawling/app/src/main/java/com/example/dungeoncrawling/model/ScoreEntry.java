package com.example.dungeoncrawling.model;

import java.util.Date;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private int score;
    private Date date;

    public ScoreEntry(String playerName, int score, Date date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        // Compare score in descending order
        return Integer.compare(other.score, this.score);
    }
}
