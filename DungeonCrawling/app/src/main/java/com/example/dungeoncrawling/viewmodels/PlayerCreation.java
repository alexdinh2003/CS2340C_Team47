package com.example.dungeoncrawling.viewmodels;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.UsernameValidator;
import com.example.dungeoncrawling.model.graphics.HP;

public class PlayerCreation extends AppCompatActivity {

    private EditText playerName;
    private Button createPlayer;
    private ImageButton sprite1;
    private ImageButton sprite2;
    private ImageButton sprite3;
    private HP health;
    private Player player;
    private UsernameValidator usernameValidator; // Initialize the validator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        playerName = findViewById(R.id.playerName);
        createPlayer = findViewById(R.id.createPlayerButton);

        // Initialize the username validator
        usernameValidator = new UsernameValidator();
        health = HP.getInstance();

        createPlayer.setOnClickListener(v -> {
            player = Player.getInstance();
            player.setScore(100);

            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyToggles);
            int checkedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();

            if (checkedRadioButtonId == R.id.radioEasy) {
                player.setHealth(10);
                health.setDifficulty(1);
            } else if (checkedRadioButtonId == R.id.radioMedium) {
                player.setHealth(8);
                health.setDifficulty(2);
            } else {
                player.setHealth(6);
                health.setDifficulty(3);
            }

            RadioGroup playerSpriteGroup = findViewById(R.id.player_sprites);
            int radioButtonId = playerSpriteGroup.getCheckedRadioButtonId();
            if (radioButtonId == R.id.char1Sprite) {
                player.setSpriteId(0);
            } else if (radioButtonId == R.id.char2Sprite) {
                player.setSpriteId(1);
            } else {
                player.setSpriteId(2);
            }

            String name = playerName.getText().toString();
            // Use the validator method
            boolean isValidName = usernameValidator.isValidUsername(name);

            if (isValidName) {
                player.setName(name);
                Intent game = new Intent(PlayerCreation.this, GameScreen1.class);
                // Add other intent extras if needed
                startActivity(game);
                finish();
            } else {
                // Handle invalid username here
                // For example, show a popup or provide a message to the user
                // Here's the code for displaying a popup similar to your original code
                // inflate the layout of the popup window
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView,
                        width, height, focusable);

                // show the popup window
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View w, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
}
