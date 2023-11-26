package com.example.cs2340a_team43.Views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.EditText;
import com.example.cs2340a_team43.R;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to activity_configuration
        setContentView(R.layout.activity_configuration);
        createNextButton();
    }

    private void createNextButton() {
        Button nextBtn = findViewById(R.id.startGameButton);
        nextBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            //default values
            String difficulty = "Easy";
            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.mediumButton) {
                difficulty = "Medium";
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.hardButton) {
                difficulty = "Hard";
            }

            //Find radio buttons for sprites
            RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);
            //default value
            int choice = 0;
            if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite1) {
                choice = 1;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite2) {
                choice = 2;
            }

            EditText nameEditText = findViewById(R.id.nameInputBox);
            //get text box for name
            String nameText = nameEditText.getText().toString().trim();
            //Do not allow white space or empty name, give error
            if (nameText.isEmpty() || nameText == null) {
                nameEditText.setError("Please enter text.");
            } else {
                Intent game = new Intent(ConfigurationPageActivity.this, GameActivity.class);
                game.putExtra("nameText", nameText);
                game.putExtra("sprite", choice);
                game.putExtra("difficulty", difficulty);
                startActivity(game);
                finish();
            }
        });
    }
} // ConfigurationPageActivity