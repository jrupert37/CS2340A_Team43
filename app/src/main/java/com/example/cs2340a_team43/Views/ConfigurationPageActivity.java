package com.example.cs2340a_team43.Views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.Toast;
//import android.view.View;


import com.example.cs2340a_team43.Models.GameActivity;
import com.example.cs2340a_team43.R;


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

//            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.easyButton) {
//                hp = 50;
//                difficulty = "Easy";
//            }
            //change difficulty and HP based on selection
            if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.mediumButton) {
                hp = 30;
                difficulty = "Medium";
            } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.hardButton) {
                hp = 15;
                difficulty = "Hard";
            }
//            else {
//                hp = 50; // Default value
//            }
            //Find radio buttons for sprites
            RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);
            //default value
            int choice = 0;

//            if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite0) {
//                choice = 0;
//            }
            if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite1) {
                choice = 1;
            } else if (spriteRadioGroup.getCheckedRadioButtonId() == R.id.sprite2) {
                choice = 2;
            }
//            else {
//                choice = 0;
//            }

            //get text box for name
            String nameText = nameEditText.getText().toString().trim();
            //Do not allow white space or empty name, give error
            if (nameText.isEmpty() || nameText == null) {
                //Toast.makeText(ConfigurationPage.t,"Input: "+inputText,Toast.LENGTH_SHORT).show();
                nameEditText.setError("Please enter text.");
            } else { //send information to viewmodel
                Intent game = new Intent(ConfigurationPageActivity.this, GameActivity.class);
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