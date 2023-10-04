package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team43.R;

public class EndScreenActivity extends AppCompatActivity {

    private Button restartButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndScreenActivity.this, StartScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
        });
    }
}
