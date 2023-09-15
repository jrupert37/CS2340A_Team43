package com.example.cs2340a_team43.models;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.R;
import android.os.Bundle;
import android.widget.Button;
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startButton);

    }
}
