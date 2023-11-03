package com.example.cs2340a_team43.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;


import com.example.cs2340a_team43.R;

public class TempScoreFeature extends AppCompatActivity {
    private TextView healthView;
    private int healthScore;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_scorefeature);

        healthScore = getIntent().getIntExtra("hp", 50);
        healthView = findViewById(R.id.healthView);
        Runnable decreaseHealthRunnable = new Runnable() {
            @Override
            public void run() {
                if (healthScore > 0) {
                    healthScore -= 5; // Decrease health by 5 (you can adjust this value)
                    updateHealthScore();
                    handler.postDelayed(this, 1000); // Run this Runnable every second
                }
            }
        };
        // Start the health decrease process
        handler.post(decreaseHealthRunnable);

    }
    public int getHealthScore() {
        return healthScore;
    }

    private void updateHealthScore() {
        healthView.setText("Health Score: " + healthScore);
    }

}

