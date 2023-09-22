package com.example.cs2340a_team43.models;

import android.os.Bundle;
import android.widget.Button;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.content.Intent;


import com.example.cs2340a_team43.R;


import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;
public class ConfigurationPage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Button nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            int hp = 1;

            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.easyButton) {
                hp = 50;
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.mediumButton) {
                hp = 30;
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.hardButton) {
                hp = 15;
            } else {
                hp = 50; // Default value
            }

            RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);
            int choice = 0;

            if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                choice = 0;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                choice = 1;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.radioButton4) {
                choice = 2;
            } else {
                choice = 0;
            }

            Intent game = new Intent(ConfigurationPage.this, GameActivity.class);
            game.putExtra("hp", hp);
            game.putExtra("sprite", choice);
            startActivity(game);
            finish();
        });



    }
}
