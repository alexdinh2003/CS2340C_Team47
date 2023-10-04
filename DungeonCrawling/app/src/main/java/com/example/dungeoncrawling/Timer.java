package com.example.dungeoncrawling;

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
                if (!(timerTextView.getText().equals(String.format("%d:%02d", minutes, seconds)))) {
                    int currScore = Integer.parseInt(scoreText.getText().toString());
                    currScore--;
                    scoreText.setText(Integer.toString(currScore));
                }
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 500);
            }
        };
    }

    public boolean runTimer() {
        return this.timerHandler.postDelayed(this.timerRunnable, 0);
    }

    public void stopTimer() {
        this.timerHandler.removeCallbacks(this.timerRunnable);
        this.oldTime = System.currentTimeMillis() - startTime;
    }

    public void resetTimer(long startTime, TextView timerTextView) {
        this.startTime = startTime;
        this.timerTextView = timerTextView;
    }

    public long getOldTime() {
        return this.oldTime;
    }

    public void schedule(TimerTask task, int i, int i1) {
    }
    }


