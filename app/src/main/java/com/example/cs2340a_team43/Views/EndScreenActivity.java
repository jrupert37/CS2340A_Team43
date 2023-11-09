package com.example.cs2340a_team43.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340a_team43.Models.Leaderboard;
import com.example.cs2340a_team43.R;

public class EndScreenActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end);

        Leaderboard leaderboard = Leaderboard.getInstance();
        TableLayout leaderTable = findViewById(R.id.leaderTableLayout);
        /*
         *  Loop over each row of the xml leaderboard table.
         *  For each row, display the player's name, score, start time, and end time
         *  Player for each row comes from a sorted list of players (sorted by score)
         *  Top player at index 0, bottom player at index size - 1 of leaderboard
         */
        for (int i = 0; i < 5 && i < leaderboard.getSize(); i++) {
            TableRow row = (TableRow) leaderTable.getChildAt(i + 1);
            TextView playerName = (TextView) row.getChildAt(0);
            TextView playerScore = (TextView) row.getChildAt(1);
            TextView startTime = (TextView) row.getChildAt(2);
            TextView endTime = (TextView) row.getChildAt(3);

            playerName.setText(leaderboard.get(i).getName());
            playerScore.setText(String.format(Integer.toString(leaderboard.get(i).getScore())));
            startTime.setText(leaderboard.get(i).toString("start"));
            endTime.setText(leaderboard.get(i).toString("end"));
        }
        TextView mostRecentName = findViewById(R.id.mostRecentName);
        TextView mostRecentScore = findViewById(R.id.mostRecentScore);
        TextView mostRecentStartTime = findViewById(R.id.mostRecentStartTime);
        TextView mostRecentEndTime = findViewById(R.id.mostRecentEndTime);
        mostRecentName.setText(leaderboard.getMostRecentAttempt().getName());
        String score;
        score = String.format(Integer.toString(leaderboard.getMostRecentAttempt().getScore()));
        mostRecentScore.setText(score);
        mostRecentStartTime.setText(leaderboard.getMostRecentAttempt().toString("start"));
        mostRecentEndTime.setText(leaderboard.getMostRecentAttempt().toString("end"));

        TextView winLoseTextView = findViewById(R.id.winLoseTextView);
        boolean playerIsAlive = getIntent().getBooleanExtra("isAlive", true);
        if (!playerIsAlive) {
            winLoseTextView.setText(R.string.you_lose);
            winLoseTextView.setTextColor(Color.RED);
        }

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndScreenActivity.this, StartScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
        });
    }
} // EndScreenActivity
