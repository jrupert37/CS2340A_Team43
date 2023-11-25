package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;

public class HowToPlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        setInstructions();
        createNextButton();
    }

    private void setInstructions() {
        TextView instructionsTextView = findViewById(R.id.instructionsTextView);
        String instructions = "For your final exam, you must navigate through the 3 floors "
                + "of the Culc, attacking various monsters along the way to gain points on your "
                + "test without taking too much damage!"
                + "\n\n - On each floor, obtain the key to unlock the exit and move on."
                + "\n - Use the arrow keys to navigate and use W, A, S, and D to attack enemies "
                + "above, left, below, and right."
                + "\n - If you can navigate through the whole building in under 120 seconds "
                + "you get a time bonus added to your score!"
                + "\n - On the next screen, please choose a difficulty that will determine how "
                + "much health you start with and how much damage enemies can deal."
                + "\n - Certain power-ups may offer you aid along the way... Good luck!";
        instructionsTextView.setText(instructions);
    }

    private void createNextButton() {
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(HowToPlayActivity.this, ConfigurationPageActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
