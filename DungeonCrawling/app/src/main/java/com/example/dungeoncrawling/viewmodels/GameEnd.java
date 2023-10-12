package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;

import com.example.dungeoncrawling.model.ScoreEntry;
import com.example.dungeoncrawling.model.Timer;
import com.example.dungeoncrawling.views.MainActivity;

import java.util.List;


public class GameEnd extends AppCompatActivity {

    private Timer timer;
    private TextView timerText;
    private TextView scoreText;
    private Button resetGame;

    private ListView leaderboardListView;
    private TextView mostRecentAttemptTextView;


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.displayOldTime(timerText);
        timer.displayScore(scoreText);
        timer.stopTimer();

        // Initialize views
        leaderboardListView = findViewById(R.id.leaderboardListView);
        mostRecentAttemptTextView = findViewById(R.id.mostRecentAttemptTextView);


        // Display the leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        List<ScoreEntry> scores = leaderboard.getScores();
        ArrayAdapter<ScoreEntry> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, scores);
        leaderboardListView.setAdapter(adapter);


        // Display the most recent attempt
        ScoreEntry mostRecentAttempt = scores.isEmpty() ? null : leaderboard.getRecentAttempt();

        if (mostRecentAttempt != null) {
            mostRecentAttemptTextView.setText("Most Recent Attempt:\n"
                    + "Player: " + mostRecentAttempt.getPlayerName()
                    + "\n"
                    + "Score: " + mostRecentAttempt.getScore() + "\n"
                    + "Date: " + mostRecentAttempt.getDate());
        }

        //Reset button
        resetGame = findViewById(R.id.ResetButton);

        resetGame.setOnClickListener(v -> {
            timer.stopTimer();
            timer.resetTimer();
            //scoreText.setText("100");
            Intent createPlayer = new Intent(GameEnd.this, MainActivity.class);
            startActivity(createPlayer);
            finish();
        });
    }

}

