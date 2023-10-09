package com.example.dungeoncrawling.model;

import android.os.Handler;
import android.widget.TextView;

import java.util.TimerTask;

public class Timer {

    private TextView timerTextView;
    private long startTime = 0;
    private static long oldTime = 0;
    private Handler timerHandler;
    private Runnable timerRunnable;

    private TextView scoreText;
    private static int currScore = 100;
    private int score;

    public Timer(long startTime, TextView timerTextView, TextView scoreText) {
        this.startTime = startTime;
        this.timerTextView = timerTextView;
        this.scoreText = scoreText;

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
                    scoreText.setText(Integer.toString(currScore));
                    currScore--;
                }
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 1000);
            }
        };
    }

    public Timer(long startTime, TextView timerTextView) {
        this(startTime, timerTextView, null);
    }

    public boolean runTimer() {
        return this.timerHandler.postDelayed(this.timerRunnable, 0);
    }

    public void stopTimer() {
        this.timerHandler.removeCallbacks(this.timerRunnable);
        oldTime = System.currentTimeMillis() - startTime;
    }

    public void resetTimer() {
        oldTime = 0;
        currScore = 100;
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
        scoreText.setText(Integer.toString(currScore));
    }

    public void schedule(TimerTask task, int i, int i1) {
    }

    public int getScore() {
        return currScore;
    }

    public void setScore(int score) {
        this.currScore = score;
    }
}


