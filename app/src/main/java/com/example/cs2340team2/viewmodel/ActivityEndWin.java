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


public class ActivityEndWin extends AppCompatActivity {
    //private int lastScore, score1, score2, score3;
    private TextView rank;
    private TextView recent;
    private TextView username;
    private TextView status;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // save game state
        super.onCreate(savedInstanceState);

        // set game visual information
        setContentView(R.layout.activity_end);

        btn = (Button) findViewById(R.id.goToStart);
        rank = (TextView) findViewById(R.id.ranking);
        recent = (TextView) findViewById(R.id.recentScore);
        status = (TextView) findViewById(R.id.playerWin);
        // get information from previous activity
        String currname = getIntent().getStringExtra("NAME");
        String currTime = getIntent().getStringExtra("time");
        int score = Integer.parseInt(getIntent().getStringExtra("StartScore"));

        Intent intent = getIntent();
        // creating leadboard string
        String fname = "";
        String recentPlayer = "";
        ArrayList<Leaderboard> leaderboard = intent.getParcelableArrayListExtra("leaderboard");

        // set leaderboard information
        LeaderboardMain leader = LeaderboardMain.getInstance();
        leader.setLeaderboard(leaderboard);

        Comparator<Leaderboard> scoreComparator = (l1, l2) -> (int) (l2.getScore() - l1.getScore());
        leaderboard.sort(scoreComparator);

        // add leaderboard information
        recentPlayer += "Name: " + currname + ", Time: " + currTime + ", Score: " + score;
        recent.setText(recentPlayer);
        for (Leaderboard user : leader.getLeaderboard()) {
            fname +=  "Name: " + user.getName() + ", Time: "
                    + user.getTime() + ", Score: " + user.getScore() + "\n";
        }
        rank.setText(fname);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to start screen
                Intent intent = new Intent(ActivityEndWin.this, MainActivity.class);
                intent.putExtra("leaderboard", leaderboard);
                startActivity(intent);
            }
        });

    }
}
