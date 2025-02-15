package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private MediaPlayer player1;
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isGameOver = getIntent().getBooleanExtra("GameOver", false);

        if (isGameOver) {
            setContentView(R.layout.game_over_screen);
            // Handle the Game Over screen initialization here
            play3();
        } else {
            setContentView(R.layout.end_screen);
            play2();
        }

        // Initialize views
        leaderboardListView = findViewById(R.id.leaderboardListView);
        mostRecentAttemptTextView = findViewById(R.id.mostRecentAttemptTextView);

        // Display the leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        List<ScoreEntry> scores = leaderboard.getTopAttempts(5);
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
            Intent createPlayer = new Intent(GameEnd.this, MainActivity.class);
            startActivity(createPlayer);
            finish();
            stop();
        });

    }

    //winning game
    public void play2() {
        if (player1 == null) {
            player1 = MediaPlayer.create(this, R.raw.song4);
            player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player1.start();
    }

    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player1 != null) {
            player1.release();
            player1 = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT);
        }
    }

    // lose game sound
    public void play3() {
        if (player1 == null) {
            player1 = MediaPlayer.create(this, R.raw.gameover);
            player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player1.start();
    }
}

