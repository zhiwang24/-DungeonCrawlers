package com.example.cs2340team2.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340team2.R;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // save game state
        super.onCreate(savedInstanceState);
        // set game view
        setContentView(R.layout.activity_main);

        // def buttons for game start and exit - will help witgh game beginning
        btn = (Button) findViewById(R.id.start_game);
        end = (Button) findViewById(R.id.exit_game);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfig();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }

    // create new action: openConfig
    public void openConfig() {
        Intent intent = new Intent(this, ActivityConfig.class);
        intent.putExtra("leaderboard", getIntent().getParcelableArrayListExtra("leaderboard"));
        startActivity(intent);
    }

    // create new action: exitGame
    public void exitGame() {
        Intent intent = new Intent(this, ActivityEndWin.class);
        intent.putExtra("leaderboard", getIntent().getParcelableArrayListExtra("leaderboard"));
        startActivity(intent);
    }
}
