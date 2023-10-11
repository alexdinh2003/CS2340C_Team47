package com.example.dungeoncrawling.model;

import java.util.Date;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private Player player;
    private int score;
    private Date date;

    public ScoreEntry(Date date) {
        this.player = Player.getInstance();
        this.playerName = player.getName();
        this.score = player.getScore();
        this.date = date;
    }

    public ScoreEntry(int score) {
        this(new Date());
    }

    public ScoreEntry(String playName, int score) {
        this.player = Player.getInstance();
        this.playerName = player.getName();
        this.score = player.getScore();
    }

    @Override
    public int compareTo(ScoreEntry other) {
        // Compare score in descending order
        return Integer.compare(this.score, other.score);
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
