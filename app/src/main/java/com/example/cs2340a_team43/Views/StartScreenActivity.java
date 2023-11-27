package com.example.cs2340a_team43.Views;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting correct XML page to view
        setContentView(R.layout.activity_start_screen);
        createButtons();
    }

    private void createButtons() {
        //Init button instances from XML
        Button startBtn = findViewById(R.id.nextButton);
        Button quitBtn = findViewById(R.id.quitButton);

        //Exit app with exit button
        quitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        });

        // Set button listener to move on to configuration page when start button clicked
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StartScreenActivity.this, HowToPlayActivity.class);
            startActivity(intent);
            finish();
        });
    }
} // StartScreenActivity
