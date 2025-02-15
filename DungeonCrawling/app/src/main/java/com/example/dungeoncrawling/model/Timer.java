package com.example.dungeoncrawling.model;

import android.os.Handler;
import android.widget.TextView;


public class Timer {

    private TextView timerTextView;
    private long startTime = 0;
    private static long oldTime = 0;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private TextView scoreText;
    private int score;
    private Player player;

    private boolean isPaused = false;
    
    //constructs the timer
    public Timer(long startTime, TextView timerTextView, TextView scoreText) {
        this.startTime = startTime;
        this.timerTextView = timerTextView;
        this.scoreText = scoreText;
        this.player = Player.getInstance();
        this.scoreText.setText(Integer.toString(player.getScore()));

        this.timerHandler = new Handler();
        this.timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime + oldTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                //currScore = 100;
                if (scoreText != null) {
                    scoreText.setText(Integer.toString(player.getScore()));
                    score = player.getScore();
                    if (seconds % 5 == 0) {
                        player.setScore(--score);
                    }
                }
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 1000);
            }
        };
    }

    public Timer(long startTime, TextView timerTextView) {
        this(startTime, timerTextView, null);
    }

    public Timer() {
        this.player = Player.getInstance();
    }

    public boolean runTimer() {
        return this.timerHandler.postDelayed(this.timerRunnable, 0);
    }

    public void stopTimer() {
        this.timerHandler.removeCallbacks(this.timerRunnable);
        oldTime = System.currentTimeMillis() - startTime + oldTime;
    }

    public void resetTimer() {
        oldTime = 0;
        player.setScore(100);
    }

    public long getOldTime() {
        return this.oldTime;
    }

    public void displayOldTime(TextView timerTextView) {
        long millis = oldTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        timerTextView.setText(String.format("%d:%02d", minutes, seconds));
    }

    public void displayScore(TextView scoreText) {
        scoreText.setText(Integer.toString(player.getScore()));
    }

    public int getScore() {
        return player.getScore();
    }

    public void setScore(int score) {
        player.setScore(score);
    }

}


