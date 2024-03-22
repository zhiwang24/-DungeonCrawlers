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

import com.example.cs2340team2.R;
import com.example.cs2340team2.model.EnemyFactory;
import com.example.cs2340team2.model.EnemyMove;
import com.example.cs2340team2.model.Map2Enemy;
import com.example.cs2340team2.model.PlayerMove;
import com.example.cs2340team2.model.PowerUpMove;

public class ActivityMap2 extends AppCompatActivity {

    private TextView textName;
    private TextView textDiff;
    private TextView textHp;
    private TextView textScore;
    private TextView textPlayerCoord;
    private TextView textEnemy1Coord;
    private TextView textEnemy2Coord;
    private ImageView map2;
    private ImageView character;

    private ImageView enemy2;
    private ImageView enemy3;
    private int imageValue;
    private Button btnUP;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnAttack;
    private int scoreValue2;
    private int n = 0;
    private int enemyPath1 = 0;
    private int enemyPath2 = 0;
    private int difficultyNum = 1;
    private int initialDeath1 = 0;
    private int initialDeath2 = 0;
    private ImageView power1;
    private ImageView power2;
    private ImageView power3;

    private boolean[][] isWall2 = new boolean[64][36];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_2);
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
        power1 = (ImageView) findViewById(R.id.power1);
        power2 = (ImageView) findViewById(R.id.power2);
        power3 = (ImageView) findViewById(R.id.power3);

        map2 = (ImageView) findViewById(R.id.map2);
        character = (ImageView) findViewById(R.id.sprite);
        enemy2 = (ImageView) findViewById(R.id.enemy2);
        enemy3 = (ImageView) findViewById(R.id.enemy3);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageValue = bundle.getInt("IMAGE");
        }
        character.setImageResource(imageValue);
        PlayerMove player = new PlayerMove(character, 640, 650);

        // set bounds for walls, DO NOT CHANGE, only works in Pixel 2 API 30
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 36; j++) {
                isWall2[i][j] = false;
            }
        }
        for (int i = 10; i < 47; i++) {
            isWall2[i][3] = true;
            isWall2[i][28] = true;
        }
        for (int i = 19; i < 26; i++) {
            isWall2[i][19] = true;
        }
        for (int i = 25; i < 34; i++) {
            isWall2[i][14] = true;
        }
        for (int i = 32; i < 39; i++) {
            isWall2[i][22] = true;
        }
        for (int j = 3; j < 29; j++) {
            isWall2[10][j] = true;
        }
        for (int j = 9; j < 29; j++) {
            isWall2[25][j] = true;
        }
        for (int j = 3; j < 23; j++) {
            isWall2[37][j] = true;
        }
        for (int j = 3; j < 12; j++) {
            isWall2[45][j] = true;
        }
        for (int j = 18; j < 29; j++) {
            isWall2[45][j] = true;
        }
        Intent intent = getIntent();
        String user = intent.getStringExtra("NAME");
        String difficulty = intent.getStringExtra("DIFFICULTY");
        String hp = intent.getStringExtra("HP");
        String score = intent.getStringExtra("StartScore");
        player.setHp(intent.getStringExtra("HP"));
        scoreValue2 = Integer.parseInt(score);
        player.setScore(scoreValue2);
        textHp.setText(player.getHp());
        textName.setText(user);
        textDiff.setText(difficulty);

        EnemyFactory store = new Map2Enemy();
        EnemyMove[] temp = store.getEnemy(enemy2, enemy3);
        EnemyMove enemyTwo = temp[0];
        EnemyMove enemyThree = temp[1];

        PowerUpMove powerUp1 = new PowerUpMove(power1, 550, 470);
        PowerUpMove powerUp2 = new PowerUpMove(power2, 880, 680);
        PowerUpMove powerUp3 = new PowerUpMove(power3, 1330, 620);

        Intent intent2 = new Intent(ActivityMap2.this, ActivityMap3.class);
        Intent intentL = new Intent(ActivityMap2.this, ActivityEndLoss.class);
        CountDownTimer countdownTimer2 = new CountDownTimer(scoreValue2 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                scoreValue2 -= 1;
                textScore.setText("" + scoreValue2);
                if (enemyTwo.getLive()) {
                    if (enemyPath1 == 0) {
                        enemyTwo.moveDown(isWall2, 3);
                        if (enemyTwo.getY() >= 770) {
                            enemyPath1 = 1;
                        }
                    } else {
                        enemyTwo.moveUp(isWall2, 3);
                        if (enemyTwo.getY() <= 190) {
                            enemyPath1 = 0;
                        }
                    }
                }
                if (enemyThree.getLive()) {
                    if (enemyPath2 == 0) {
                        enemyThree.moveDown(isWall2, 2);
                        if (enemyThree.getY() >= 590) {
                            enemyPath2 = 1;
                        }
                    } else {
                        enemyThree.moveUp(isWall2, 2);
                        if (enemyThree.getY() <= 190) {
                            enemyPath2 = 0;
                        }
                    }
                }

                if (enemyTwo.getLive() && player.collisionCheck(player, enemyTwo)) {
                    Toast.makeText(ActivityMap2.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();
                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue2 -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap2.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue2);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyThree.getLive() && player.collisionCheck(player, enemyThree)) {
                    Toast.makeText(ActivityMap2.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();
                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue2 -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap2.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue2);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyTwo.getLive()) {
                    textEnemy1Coord.setText(enemyTwo.getX() + " " + enemyTwo.getY());
                } else {
                    textEnemy1Coord.setText("Dead");
                }
                if (enemyThree.getLive()) {
                    textEnemy2Coord.setText(enemyThree.getX() + " " + enemyThree.getY());
                } else {
                    textEnemy2Coord.setText("Dead");
                }
                textPlayerCoord.setText(player.getX() + " " + player.getY());
                player.setScore(scoreValue2);
            }
            @Override
            public void onFinish() {
                player.setScore(scoreValue2);
                intent2.putExtra("StartScore", "" + scoreValue2);

                Intent intentEnd = new Intent(ActivityMap2.this, ActivityEndWin.class);
                intent2.putExtra("leaderboard",
                        getIntent().getParcelableArrayListExtra("leaderboard"));
                intent2.putExtra("StartScore", "" + scoreValue2);
                intent2.putExtra("NAME", user);
                intent2.putExtra("DIFFICULTY", difficulty);
                intent2.putExtra("HP", player.getHp());
                intent2.putExtra("IMAGE", imageValue);
                startActivity(intentEnd);
            }
        };
        countdownTimer2.start();
        btnUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveUp(isWall2);
                if (player.getX() == powerUp1.getX() && player.getY() == powerUp1.getY()) {
                    player.setHp(Integer.parseInt(player.getHp()) + 10 + "");
                    textHp.setText(player.getHp());
                    power1.setVisibility(View.GONE);
                }
                if (player.getX() == powerUp3.getX() && player.getY() == powerUp3.getY()) {
                    enemyThree.setLive(false);
                    enemy3.setVisibility(View.GONE);
                    power3.setVisibility(View.GONE);
                }
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveDown(isWall2);
                if (player.getX() == powerUp2.getX() && player.getY() == powerUp2.getY()) {
                    scoreValue2 += 10;
                    power2.setVisibility(View.GONE);
                }
            }
        });

        // move button functions
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveLeft(isWall2);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveRight(isWall2);
                if ((player.getX() / 30) > 47) {
                    intent2.putExtra("leaderboard",
                            getIntent().getParcelableArrayListExtra("leaderboard"));
                    intent2.putExtra("StartScore", "" + scoreValue2);
                    intent2.putExtra("NAME", user);
                    intent2.putExtra("DIFFICULTY", difficulty);
                    intent2.putExtra("HP", player.getHp());
                    intent2.putExtra("IMAGE", imageValue);
                    startActivity(intent2);
                }
            }
        });

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.attack(enemyTwo, enemyThree);
                if (!enemyTwo.getLive()) {
                    enemy2.setVisibility(View.GONE);
                    if (initialDeath1 == 0) {
                        scoreValue2 += 10 * difficultyNum;
                        initialDeath1 = 1;
                        Toast.makeText(ActivityMap2.this,
                                "Killed Enemy 1", Toast.LENGTH_LONG).show();
                    }
                }
                if (!enemyThree.getLive()) {
                    enemy3.setVisibility(View.GONE);
                    if (initialDeath2 == 0) {
                        scoreValue2 += 10 * difficultyNum;
                        initialDeath2 = 1;
                        Toast.makeText(ActivityMap2.this,
                                "Killed Enemy 2", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
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