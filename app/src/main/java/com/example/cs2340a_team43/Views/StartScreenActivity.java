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

        //Init button instances from XML
        Button startBtn = findViewById(R.id.startButton);
        Button quitBtn = findViewById(R.id.quitButton);

        //Exit app with exit button
        quitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        });
        //Start game, move on to configuration page
        startBtn.setOnClickListener(v -> {
            Intent game = new Intent(StartScreenActivity.this, ConfigurationPageActivity.class);
            startActivity(game);
            finish();
        });
    }
}
