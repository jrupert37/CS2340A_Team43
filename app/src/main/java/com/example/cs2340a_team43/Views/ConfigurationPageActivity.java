package com.example.cs2340a_team43.Views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.EditText;
import com.example.cs2340a_team43.R;
import com.example.cs2340a_team43.ViewModels.PlayerViewModel;


import androidx.appcompat.app.AppCompatActivity;


//import com.example.cs2340a_team43.R;
public class ConfigurationPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to activity_configuration.xml
        setContentView(R.layout.activity_configuration);

        //create instances for name input and next button
        EditText nameEditText = findViewById(R.id.nameInputBox);
        Button nextBtn = findViewById(R.id.nextButton);

        //Create button listener for setted attributes
        nextBtn.setOnClickListener(v -> {
            //find radio button group
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            //default values
            int hp = 50;
            String difficulty = "Easy";
            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.mediumButton) {
                hp = 30;
                difficulty = "Medium";
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.hardButton) {
                hp = 15;
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

            //get text box for name
            String nameText = nameEditText.getText().toString().trim();
            //Do not allow white space or empty name, give error
            if (nameText.isEmpty() || nameText == null) {
                nameEditText.setError("Please enter text.");
            } else {
                Intent game = new Intent(ConfigurationPageActivity.this, GameActivity.class);
                game.putExtra("nameText", nameText);
                game.putExtra("hp", hp);
                game.putExtra("sprite", choice);
                game.putExtra("difficulty", difficulty);
                startActivity(game);
                finish();
            }

        });



    }
}