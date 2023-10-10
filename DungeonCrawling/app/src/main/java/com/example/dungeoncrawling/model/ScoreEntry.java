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

    public ScoreEntry(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        // Compare score in descending order
        return Integer.compare(other.score, this.score);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return "Player: " + playerName + ", Score: " + score + ", Date: " + date;
    }
}
