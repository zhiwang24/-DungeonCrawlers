package com.example.cs2340team2.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cs2340team2.model.EnemyFactory;
import com.example.cs2340team2.model.Leaderboard;
import com.example.cs2340team2.R;
import com.example.cs2340team2.model.EnemyMove;
import com.example.cs2340team2.model.Map3Enemy;
import com.example.cs2340team2.model.PlayerMove;
import com.example.cs2340team2.model.PowerUpMove;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ActivityMap3 extends AppCompatActivity {

    private TextView textName;
    private TextView textDiff;
    private TextView textHp;
    private TextView textScore;
    private TextView textPlayerCoord;
    private TextView textEnemy1Coord;
    private TextView textEnemy2Coord;
    private ImageView map3;
    private ImageView character;

    private ImageView enemy3;
    private ImageView enemy4;
    private int imageValue;
    private Button btnUP;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnAttack;
    private int scoreValue3;
    private int n = 0;
    private int enemyPath1 = 0;
    private int enemyPath2 = 0;
    private int difficultyNum = 1;
    private int initialDeath1 = 0;
    private int initialDeath2 = 0;
    private ImageView power1;
    private ImageView power2;
    private ImageView power3;

    private boolean[][] isWall3 = new boolean[64][36];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_3);
        textName = (TextView) findViewById(R.id.playerName);
        textDiff = (TextView) findViewById(R.id.playerDifficulty);
        textHp = (TextView) findViewById(R.id.playerHp);
        textScore = (TextView) findViewById(R.id.playerScore);
        textPlayerCoord = (TextView) findViewById(R.id.playerCoord);
        textEnemy1Coord = (TextView) findViewById(R.id.enemy1Coord);
        textEnemy2Coord = (TextView) findViewById(R.id.enemy2Coord);
        btnUP = (Button) findViewById(R.id.moveUp);
        btnDown = (Button) findViewById(R.id.moveDown);
        btnLeft = (Button) findViewById(R.id.moveLeft);
        btnRight = (Button) findViewById(R.id.moveRight);
        btnAttack = (Button) findViewById(R.id.attack);
        map3 = (ImageView) findViewById(R.id.map3);
        enemy3 = (ImageView) findViewById(R.id.enemy3);
        enemy4 = (ImageView) findViewById(R.id.enemy4);
        character = (ImageView) findViewById(R.id.sprite);
        power1 = (ImageView) findViewById(R.id.power1);
        power2 = (ImageView) findViewById(R.id.power2);
        power3 = (ImageView) findViewById(R.id.power3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageValue = bundle.getInt("IMAGE");
        }

        // set bounds for walls, DO NOT CHANGE, only works in Pixel 2 API 30
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 36; j++) {
                isWall3[i][j] = false;
            }
        }
        for (int i = 12; i < 26; i++) {
            isWall3[i][5] = true;
        }
        for (int i = 31; i < 46; i++) {
            isWall3[i][5] = true;
        }
        for (int i = 9; i < 13; i++) {
            isWall3[i][12] = true;
            isWall3[i][22] = true;
        }
        for (int i = 44; i < 49; i++) {
            isWall3[i][12] = true;
            isWall3[i][22] = true;
        }
        for (int i = 12; i < 46; i++) {
            isWall3[i][28] = true;
        }
        for (int j = 5; j < 13; j++) {
            isWall3[12][j] = true;
            isWall3[44][j] = true;
        }
        for (int j = 12; j < 23; j++) {
            isWall3[9][j] = true;
            isWall3[45][j] = true;
        }
        for (int j = 22; j < 29; j++) {
            isWall3[12][j] = true;
            isWall3[44][j] = true;
        }
        for (int j = 22; j < 29; j++) {
            isWall3[12][j] = true;
            isWall3[44][j] = true;
        }
        for (int j = 5; j < 22; j++) {
            isWall3[24][j] = true;
        }
        for (int j = 5; j < 9; j++) {
            isWall3[33][j] = true;
        }
        for (int j = 20; j < 29; j++) {
            isWall3[35][j] = true;
        }
        for (int i = 24; i < 40; i++) {
            isWall3[i][13] = true;
        }
        // set bounds for walls, DO NOT CHANGE, only works in Pixel 2 API 30
        character.setImageResource(imageValue);
        PlayerMove player = new PlayerMove(character, 300,  500);
        Intent intent = getIntent();
        String user = intent.getStringExtra("NAME");
        String difficulty = intent.getStringExtra("DIFFICULTY");
        String hp = intent.getStringExtra("HP");
        String score = intent.getStringExtra("StartScore");
        player.setHp(intent.getStringExtra("HP"));
        scoreValue3 = Integer.parseInt(score);
        player.setScore(scoreValue3);
        textHp.setText(player.getHp());
        textName.setText(user);
        textDiff.setText(difficulty);

        EnemyFactory store = new Map3Enemy();
        EnemyMove[] temp = store.getEnemy(enemy3, enemy4);
        EnemyMove enemyThree = temp[0];
        EnemyMove enemyFour = temp[1];

        PowerUpMove powerUp1 = new PowerUpMove(power1, 630, 560);
        PowerUpMove powerUp2 = new PowerUpMove(power2, 870, 710);
        PowerUpMove powerUp3 = new PowerUpMove(power3, 1020, 320);

        Intent intent3 = new Intent(ActivityMap3.this, ActivityEndWin.class);
        Intent intentL = new Intent(ActivityMap3.this, ActivityEndLoss.class);
        intent3.putExtra("StartScore", "" + scoreValue3);
        CountDownTimer countdownTimer3 = new CountDownTimer(scoreValue3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                scoreValue3 -= 1;
                textScore.setText("" + scoreValue3);
                if (enemyThree.getLive()) {
                    if (enemyPath1 == 0) {
                        enemyThree.moveDown(isWall3, 2);
                        if (enemyThree.getY() >= 760) {
                            enemyPath1 = 1;
                        }
                    } else {
                        enemyThree.moveUp(isWall3, 2);
                        if (enemyThree.getY() <= 240) {
                            enemyPath1 = 0;
                        }
                    }
                }
                if (enemyFour.getLive()) {
                    if (enemyPath2 == 0) {
                        enemyFour.moveRight(isWall3, 1);
                        if (enemyFour.getX() >= 1270) {
                            enemyPath2 = 1;
                        }
                    } else {
                        enemyFour.moveLeft(isWall3, 1);
                        if (enemyFour.getX() <= 760) {
                            enemyPath2 = 0;
                        }
                    }
                }

                if (enemyThree.getLive() && player.collisionCheck(player, enemyThree)) {
                    Toast.makeText(ActivityMap3.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();

                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue3 -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap3.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue3);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyFour.getLive() && player.collisionCheck(player, enemyFour)) {
                    Toast.makeText(ActivityMap3.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();
                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue3 -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap3.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue3);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyThree.getLive()) {
                    textEnemy1Coord.setText(enemyThree.getX() + " " + enemyThree.getY());
                } else {
                    textEnemy1Coord.setText("Dead");
                }
                if (enemyFour.getLive()) {
                    textEnemy2Coord.setText(enemyFour.getX() + " " + enemyFour.getY());
                } else {
                    textEnemy2Coord.setText("Dead");
                }
                textPlayerCoord.setText(player.getX() + " " + player.getY());
                player.setScore(scoreValue3);
            }

            // on finish
            @Override
            public void onFinish() {
                player.setScore(scoreValue3);
                intent3.putExtra("StartScore", "" + scoreValue3);
                Intent intentEnd = new Intent(ActivityMap3.this, ActivityEndWin.class);
                intent3.putExtra("NAME", user);
                intent3.putExtra("DIFFICULTY", difficulty);
                intent3.putExtra("HP", player.getHp());
                intent3.putExtra("IMAGE", imageValue);
                startActivity(intentEnd);
            }
        };
        countdownTimer3.start();

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss aa", Locale.getDefault());
        String output = dateFormat.format(currentTime);

        ArrayList<Leaderboard> leaderboard1 = getIntent()
                .getParcelableArrayListExtra("leaderboard");
        if (leaderboard1 == null) {
            leaderboard1 = new ArrayList<>();
        }
        leaderboard1.add(new Leaderboard(user, output, scoreValue3));


        ArrayList<Leaderboard> finalLeaderboard = leaderboard1;

        // move button functions
        btnUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveUp(isWall3);
                if (player.getX() == powerUp1.getX() && player.getY() == powerUp1.getY()) {
                    player.setHp(Integer.parseInt(player.getHp()) + 10 + "");
                    textHp.setText(player.getHp());
                    power1.setVisibility(View.GONE);
                }
                if ((player.getY() / 30) < 5) {
                    intent3.putExtra("time", output);
                    intent3.putExtra("leaderboard", finalLeaderboard);
                    intent3.putExtra("NAME", user);
                    intent3.putExtra("DIFFICULTY", difficulty);
                    intent3.putExtra("HP", hp);
                    intent3.putExtra("IMAGE", imageValue);
                    startActivity(intent3);
                }
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveDown(isWall3);
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveLeft(isWall3);
                if (player.getX() == powerUp3.getX() && player.getY() == powerUp3.getY()) {
                    enemyFour.setLive(false);
                    enemy4.setVisibility(View.GONE);
                    power3.setVisibility(View.GONE);
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the next position is a wall
                player.moveRight(isWall3);
                if (player.getX() == powerUp2.getX() && player.getY() == powerUp2.getY()) {
                    scoreValue3 += 10;
                    power2.setVisibility(View.GONE);
                }
            }
        });

        // attack method
        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.attack(enemyThree, enemyFour);
                if (!enemyThree.getLive()) {
                    // set imageView to Gone, should we delete the whole enemy instead?
                    enemy3.setVisibility(View.GONE);
                    if (initialDeath1 == 0) {
                        scoreValue3 += 10 * difficultyNum;
                        initialDeath1 = 1;
                        Toast.makeText(ActivityMap3.this,
                                "Killed Enemy 1", Toast.LENGTH_LONG).show();
                    }
                }
                if (!enemyFour.getLive()) {
                    enemy4.setVisibility(View.GONE);
                    if (initialDeath2 == 0) {
                        scoreValue3 += 10 * difficultyNum;
                        initialDeath2 = 1;
                        Toast.makeText(ActivityMap3.this,
                                "Killed Enemy 2", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    //key movement
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                btnUP.callOnClick();
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                btnDown.callOnClick();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                btnLeft.callOnClick();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                btnRight.callOnClick();
                break;
            case KeyEvent.KEYCODE_A:
                btnAttack.callOnClick();
            default:
        }
        return true;
    }
}