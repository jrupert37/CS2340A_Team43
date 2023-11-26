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
    private int winLoseTextColor;
    private boolean playerIsAlive;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end);

        playerIsAlive = getIntent().getBooleanExtra("isAlive", true);

        // F825E138 is a bright shade of green, to signify a win!
        winLoseTextColor = playerIsAlive ? 0xF825E138 : Color.RED;

        displayBaseScoreAndBonus();
        fillInLeaderboard();
        displayWinLoseBanner();
        setRestartButton();
    }

    private void displayBaseScoreAndBonus() {
        TextView baseScoreTextView = findViewById(R.id.baseScoreTextView);
        String baseScore = "Base Score: " + getIntent().getIntExtra("baseScore", 0);
        baseScoreTextView.setText(baseScore);

        TextView timeBonusTextView = findViewById(R.id.timeBonusTextView);
        String timeBonus = "Time Score Bonus: " + getIntent().getIntExtra("timeBonus", 0);
        timeBonusTextView.setTextColor(winLoseTextColor);
        timeBonusTextView.setText(timeBonus);
    }

    private void fillInLeaderboard() {
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
    }

    private void displayWinLoseBanner() {
        TextView winLoseTextView = findViewById(R.id.winLoseTextView);
        String winLose = playerIsAlive ? "You Win!" : "You Lose!";
        winLoseTextView.setText(winLose);
        winLoseTextView.setTextColor(winLoseTextColor);
    }

    private void setRestartButton() {
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndScreenActivity.this, StartScreenActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
        });
    }
} // EndScreenActivity
