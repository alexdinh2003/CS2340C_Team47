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

public class PlayerCreation extends AppCompatActivity {

    private EditText playerName;
    private Button createPlayer;
    private ImageButton sprite1;
    private ImageButton sprite2;
    private ImageButton sprite3;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        playerName = (EditText) findViewById(R.id.playerName);
        createPlayer = (Button) findViewById(R.id.createPlayerButton);

        createPlayer.setOnClickListener(v -> {
            player = Player.getInstance();
            player.setScore(100);
            int difficulty = 0;
            int spriteNum = 0;
            boolean invalidName = true;

            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyToggles);
            int radioID = difficultyRadioGroup.getCheckedRadioButtonId();

            int checkedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radioEasy) {
                difficulty = 1;
                player.setHealth(5);
            } else if (checkedRadioButtonId == R.id.radioMedium) {
                difficulty = 2;
                player.setHealth(4);
            } else {
                difficulty = 3;
                player.setHealth(3);
            }

            RadioGroup playerSpriteGroup = findViewById(R.id.player_sprites);
            int radioButtonId = playerSpriteGroup.getCheckedRadioButtonId();
            if (radioButtonId == R.id.playerSprite1) {
                spriteNum = 1;
                player.setSpriteId(0);
            } else if (radioButtonId == R.id.playerSprite2) {
                spriteNum = 2;
                player.setSpriteId(1);
            } else {
                spriteNum = 3;
                player.setSpriteId(2);
            }

            String name = playerName.getText().toString();
            boolean inValidName = true;
            if (name != null) {
                for (int i = 0; i < name.length(); i++) {
                    char c = name.charAt(i);
                    if (!Character.isWhitespace(c)) {
                        inValidName = false;
                        player.setName(name);
                    }
                }
            }

            if (inValidName) {
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width,
                        height, focusable);

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
            } else {
                Intent game = new Intent(PlayerCreation.this, GameScreen1.class);
                //game.putExtra("difficulty", difficulty);
                //game.putExtra("playerName", name);
                //game.putExtra("spriteNum", spriteNum);
                startActivity(game);
                finish();
            }
        });

    }
}