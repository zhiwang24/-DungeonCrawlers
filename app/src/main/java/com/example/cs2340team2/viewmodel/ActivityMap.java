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
import com.example.cs2340team2.model.Map1Enemy;
import com.example.cs2340team2.model.PlayerMove;
import com.example.cs2340team2.model.PowerUpMove;

public class ActivityMap extends AppCompatActivity {

    private TextView textName;
    private TextView textDiff;
    private TextView textHp;
    private TextView textScore;
    private TextView textPlayerCoord;
    private TextView textEnemy1Coord;
    private TextView textEnemy2Coord;
    private ImageView map1;
    private ImageView character;
    private ImageView enemy1;
    private ImageView enemy2;
    private ImageView power1;
    private ImageView power2;
    private ImageView power3;
    private int imageValue;
    private Button btnUP;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnAttack;
    private int scoreValue;
    private int n = 0;
    private static boolean mapOne = true; //test to see we are on map 1
    private int enemyPath1 = 0;
    private int enemyPath2 = 0;
    private int difficultyNum = 1;
    private PlayerMove player;
    private int initialDeath1 = 0;
    private int initialDeath2 = 0;

    private boolean[][] isWall1 = new boolean[64][36];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // save game state
        super.onCreate(savedInstanceState);
        
        // set map view
        setContentView(R.layout.activity_map);
        
        // set set appropriate game information 
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
        map1 = (ImageView) findViewById(R.id.map1);
        character = (ImageView) findViewById(R.id.sprite);
        enemy1 = (ImageView) findViewById(R.id.enemy1);
        enemy2 = (ImageView) findViewById(R.id.enemy2);
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
                isWall1[i][j] = false;
            }
        }
        for (int i = 18; i < 30; i++) {
            isWall1[i][6] = true;
        }
        for (int i = 34; i < 40; i++) {
            isWall1[i][6] = true;
        }
        for (int i = 15; i < 19; i++) {
            isWall1[i][10] = true;
            isWall1[i][22] = true;
        }
        for (int i = 39; i < 43; i++) {
            isWall1[i][10] = true;
            isWall1[i][22] = true;
        }
        for (int i = 15; i < 27; i++) {
            isWall1[i][16] = true;
        }
        for (int i = 31; i < 43; i++) {
            isWall1[i][16] = true;
        }
        for (int i = 23; i < 35; i++) {
            isWall1[i][22] = true;
        }
        for (int i = 15; i < 43; i++) {
            isWall1[i][28] = true;
        }
        for (int j = 6; j < 11; j++) {
            isWall1[18][j] = true;
            isWall1[37][j] = true;
        }
        for (int j = 10; j < 29; j++) {
            isWall1[15][j] = true;
            isWall1[40][j] = true;
        }
        for (int j = 22; j < 29; j++) {
            isWall1[28][j] = true;
            isWall1[29][j] = true;
        }

        // set additional game information
        character.setImageResource(imageValue);
        player = new PlayerMove(character, 1000, 750);
        Intent intent = getIntent();
        player.setHp((intent.getStringExtra("HP")));
        String user = intent.getStringExtra("NAME");
        String difficulty = intent.getStringExtra("DIFFICULTY");
        String score = intent.getStringExtra("SCORE");
        scoreValue = Integer.parseInt(score);
        player.setScore(scoreValue);

        // set enemies
        EnemyFactory store = new Map1Enemy();
        EnemyMove[] temp = store.getEnemy(enemy1, enemy2);
        EnemyMove enemyOne = temp[0];
        EnemyMove enemyTwo = temp[1];

        // set power up
        PowerUpMove powerUp1 = new PowerUpMove(power1, 1120, 660);
        PowerUpMove powerUp2 = new PowerUpMove(power2, 880, 480);
        PowerUpMove powerUp3 = new PowerUpMove(power3, 1180, 390);

        // set HP information
        textHp.setText(player.getHp());
        textName.setText(user);
        textDiff.setText(difficulty);
        Intent intent1 = new Intent(ActivityMap.this, ActivityMap2.class);
        if (difficulty.equals("Medium")) {
            difficultyNum = 2;
        } else if (difficulty.equals("Hard")) {
            difficultyNum = 3;
        }

        // set timer information
        CountDownTimer countdownTimer = new CountDownTimer(scoreValue * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                scoreValue -= 1;
                textScore.setText("" + scoreValue);
                // set enemy movement
                if (enemyOne.getLive()) {
                    if (enemyPath1 == 0) {
                        enemyOne.moveRight(isWall1, 1);
                        if (enemyOne.getX() >= 1120) {
                            enemyPath1 = 1;
                        }
                    } else {
                        enemyOne.moveLeft(isWall1, 1);
                        if (enemyOne.getX() <= 500) {
                            enemyPath1 = 0;
                        }
                    }
                }
                if (enemyTwo.getLive()) {
                    if (enemyPath2 == 0) {
                        enemyTwo.moveRight(isWall1, 3);
                        if (enemyTwo.getX() >= 1120) {
                            enemyPath2 = 1;
                        }
                    } else {
                        enemyTwo.moveLeft(isWall1, 3);
                        if (enemyTwo.getX() <= 500) {
                            enemyPath2 = 0;
                        }
                    }
                }

                // player collision check w/ wall
                if (enemyOne.getLive() && player.collisionCheck(player, enemyOne)) {
                    Toast.makeText(ActivityMap.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();

                    // player collision check w/ enemy
                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyTwo.getLive() && player.collisionCheck(player, enemyTwo)) {
                    Toast.makeText(ActivityMap.this,
                            "Avoid Enemy", Toast.LENGTH_LONG).show();
                    player.setHp(Integer.parseInt(player.getHp()) - (10 * difficultyNum) + "");
                    textHp.setText(player.getHp());
                    scoreValue -= (5 * difficultyNum);
                    if (Integer.parseInt(player.getHp()) <= 0) {
                        Intent intentL = new Intent(ActivityMap.this, ActivityEndLoss.class);
                        intentL.putExtra("StartScore", "" + scoreValue);
                        intentL.putExtra("NAME", user);
                        intentL.putExtra("DIFFICULTY", difficulty);
                        intentL.putExtra("HP", player.getHp());
                        intentL.putExtra("IMAGE", imageValue);
                        intentL.putExtra("leaderboard",
                                getIntent().getParcelableArrayListExtra("leaderboard"));
                        startActivity(intentL);
                    }
                }
                if (enemyOne.getLive()) {
                    textEnemy1Coord.setText(enemyOne.getX() + " " + enemyOne.getY());
                } else {
                    textEnemy1Coord.setText("Dead");
                }
                if (enemyTwo.getLive()) {
                    textEnemy2Coord.setText(enemyOne.getX() + " " + enemyOne.getY());
                } else {
                    textEnemy2Coord.setText("Dead");
                }
                textPlayerCoord.setText(player.getX() + " " + player.getY());
                player.setScore(scoreValue);
            }
            @Override
            // set game finish information
            public void onFinish() {
                player.setScore(scoreValue);
                intent1.putExtra("StartScore", "" + scoreValue);
                intent1.putExtra("NAME", user);
                intent1.putExtra("DIFFICULTY", difficulty);
                intent1.putExtra("HP", player.getHp());
                intent1.putExtra("IMAGE", imageValue);
                Intent intentEnd = new Intent(ActivityMap.this, ActivityMap2.class);
                startActivity(intentEnd);
            }
        };
        countdownTimer.start();
        // move button functions
        btnUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveUp(isWall1);
                if (player.getX() == powerUp1.getX() && player.getY() == powerUp1.getY()) {
                    player.setHp(Integer.parseInt(player.getHp()) + 10 + "");
                    textHp.setText(player.getHp());
                    power1.setVisibility(View.GONE);
                }
                if (player.getX() == powerUp2.getX() && player.getY() == powerUp2.getY()) {
                    scoreValue += 10;
                    power2.setVisibility(View.GONE);
                }
                if ((player.getY() / 30) < 6) {

                    intent1.putExtra("StartScore",
                            "" + scoreValue);
                    mapOne = false;
                    intent1.putExtra("NAME", user);
                    intent1.putExtra("DIFFICULTY", difficulty);
                    intent1.putExtra("HP", player.getHp());
                    intent1.putExtra("IMAGE", imageValue);

                    startActivity(intent1);
                }
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveDown(isWall1);
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveLeft(isWall1);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.moveRight(isWall1);
                if (player.getX() == powerUp3.getX() && player.getY() == powerUp3.getY()) {
                    enemyTwo.setLive(false);
                    enemy2.setVisibility(View.GONE);
                    power3.setVisibility(View.GONE);
                }
            }
        });

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.attack(enemyOne, enemyTwo);

                if (!enemyOne.getLive()) {
                    enemy1.setVisibility(View.GONE);
                    if (initialDeath1 == 0) {
                        scoreValue += 10 * difficultyNum;
                        initialDeath1 = 1;
                        Toast.makeText(ActivityMap.this,
                                "Killed Enemy 1", Toast.LENGTH_LONG).show();
                    }
                }
                if (!enemyTwo.getLive()) {
                    enemy2.setVisibility(View.GONE);
                    if (initialDeath2 == 0) {
                        scoreValue += 10 * difficultyNum;
                        initialDeath2 = 1;
                        Toast.makeText(ActivityMap.this,
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

    public static boolean onMap() {
        return mapOne;
    }
}


