package com.example.cs2340a_team43.models;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.Toast;
//import android.view.View;


import com.example.cs2340a_team43.R;


import androidx.appcompat.app.AppCompatActivity;
//import com.example.cs2340a_team43.R;
public class ConfigurationPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        EditText nameEditText = findViewById(R.id.nameInputBox);
        Button nextBtn = findViewById(R.id.nextButton);


        nextBtn.setOnClickListener(v -> {


            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            int hp = 1;
            String difficulty = "Easy";

            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.easyButton) {
                hp = 50;
                difficulty = "Easy";
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.mediumButton) {
                hp = 30;
                difficulty = "Medium";
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.hardButton) {
                hp = 15;
                difficulty = "Hard";
            } else {
                hp = 50; // Default value
            }

            RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);
            int choice = 0;

            if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite0) {
                choice = 0;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite1) {
                choice = 1;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite2) {
                choice = 2;
            } else {
                choice = 0;
            }

            String nameText = nameEditText.getText().toString().trim();
            if (nameText.isEmpty() || nameText == null) {
                //Toast.makeText(ConfigurationPage.t,"Input: "+inputText,Toast.LENGTH_SHORT).show();
                nameEditText.setError("Please enter text.");
            } else {
                Intent game = new Intent(ConfigurationPage.this, GameActivity.class);
                game.putExtra("hp", hp);
                game.putExtra("sprite", choice);
                game.putExtra("nameText", nameText);
                game.putExtra("difficulty", difficulty);
                startActivity(game);
                finish();
            }

        });



    }
}