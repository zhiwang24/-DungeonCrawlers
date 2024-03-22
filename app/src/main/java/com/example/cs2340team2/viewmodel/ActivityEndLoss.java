package com.example.cs2340team2.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340team2.model.Leaderboard;
import com.example.cs2340team2.model.LeaderboardMain;
import com.example.cs2340team2.R;

import java.util.ArrayList;
import java.util.Comparator;


public class ActivityEndLoss extends AppCompatActivity {
    private TextView rank;
    private TextView recent;
    private TextView username;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // save and display loss state
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_loss);

        btn = (Button) findViewById(R.id.goToStart);
        rank = (TextView) findViewById(R.id.ranking);
        recent = (TextView) findViewById(R.id.recentScore);

        Intent intent = getIntent();

        // creating leadboard string
        String fname = "";
        String recentPlayer = "";
        ArrayList<Leaderboard> leaderboard = intent.getParcelableArrayListExtra("leaderboard");

        // update and display leaderboard information
        if (leaderboard != null) {
            LeaderboardMain leader = LeaderboardMain.getInstance();
            leader.setLeaderboard(leaderboard);

            Comparator<Leaderboard> scoreComparator =
                    (l1, l2) -> (int) (l2.getScore() - l1.getScore());
            leaderboard.sort(scoreComparator);

            // update all entries of the leaderboard
            for (Leaderboard user : leader.getLeaderboard()) {
                fname += "Name: " + user.getName() + ", Time: "
                        + user.getTime() + ", Score: " + user.getScore() + "\n";
            }
        }
        rank.setText(fname);

        // return to start screen
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEndLoss.this, MainActivity.class);
                intent.putExtra("leaderboard", leaderboard);
                startActivity(intent);
            }
        });

    }
}
