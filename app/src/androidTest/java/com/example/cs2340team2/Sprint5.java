package com.example.cs2340team2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cs2340team2.model.EnemyMove;
import com.example.cs2340team2.model.Player;
import com.example.cs2340team2.model.PlayerMove;
import com.example.cs2340team2.model.PowerUpMove;


import android.view.View;
import android.widget.ImageView;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Sprint5 {
    private boolean[][] isWall1 = new boolean[64][36];
    private boolean[][] isWall2 = new boolean[64][36];


    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cs2340team2", appContext.getPackageName());
    }

    private PlayerMove player;
    private EnemyMove enemyOne;
    private EnemyMove enemyTwo;
    private PowerUpMove powerUpOne;
    private PowerUpMove powerUpTwo;
    private PowerUpMove powerUpThree;

    @Before
    public void setUp() {
        ImageView imageView1 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView1.setImageResource(R.drawable.enemy1);
        ImageView imageView2 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView2.setImageResource(R.drawable.enemy2);
        ImageView imageView = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView.setImageResource(R.drawable.sprite2);

        ImageView powerUp1 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        powerUp1.setImageResource(R.drawable.power1);
        ImageView powerUp2 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        powerUp2.setImageResource(R.drawable.power2);
        ImageView powerUp3 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        powerUp2.setImageResource(R.drawable.power3);

        enemyOne = new EnemyMove(imageView1, 500, 570);
        enemyTwo = new EnemyMove(imageView2, 500, 390);
        player = new PlayerMove(imageView, 1000, 750);

        powerUpOne = new PowerUpMove(powerUp1, 1120, 660);
        powerUpTwo = new PowerUpMove(powerUp2, 880, 480);
        powerUpThree = new PowerUpMove(powerUp3, 1180, 390);
    }
    @Test
    // check if enemy dies if player attacks when not near enemy (bug from earlier)
    public void TestAttackWhenEnemyNotNear() {
        player.setX(500);
        player.setY(500);
        enemyOne.setX(570);
        player.setY(800);
        player.attack(enemyOne,enemyTwo);
        assertEquals("" + enemyOne.getLive(), "" + true);
    }

    @Test
    // check if attack works when collision is met
    public void TestAttackWhenEnemyNear() {
        player.setX(500);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setX(500);
        player.attack(enemyOne, enemyTwo);
        assertEquals("" + enemyOne.getLive(), "" + false);
    }

    @Test
    // check scores after being damaged
    public void ScoreUpdatesAfterCollision() {
        player.setScore(100);
        assertEquals("" + player.getScore(), "" + 100);
        player.setX(500);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setY(500);
        if (enemyOne.collisionCheck(player, enemyOne)) {
            player.setScore(Integer.parseInt(player.getScore() - (10 * 1) + ""));
        }
        assertEquals("" + player.getScore(), "" + 90);
    }

    @Test
    // updating score after enemy death
    public void ScoreUpdatesAfterAttack() {
        player.setScore(100);
        assertEquals("" + player.getScore(), "" + 100);
        player.setX(500);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setY(500);
        player.attack(enemyOne, enemyTwo);
        if (!enemyOne.getLive()) {
            player.setScore(Integer.parseInt(player.getScore() + (10 * 1) + ""));
        }
        assertEquals("" + player.getScore(), "" + 110);
    }

    @Test
    // test if score updates if conditions not met (a bug we had earlier)
    public void ScoreDoesntUpdateWhenConditionNotMet() {
        player.setScore(100);
        assertEquals("" + player.getScore(), "" + 100);
        player.setX(600);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setY(500);
        if (enemyOne.collisionCheck(player, enemyOne)) {
            player.setScore(Integer.parseInt(player.getScore() - (10 * 1) + ""));
        }
        player.attack(enemyOne, enemyTwo);
        if (!enemyOne.getLive()) {
            player.setScore(Integer.parseInt(player.getScore() + (10 * 1) + ""));
        }
        assertEquals("" + player.getScore(), "" + 100);
    }

    @Test
    // test if enemy is still attackable after death
    public void AttackableAfterEnemyDeath() {
        player.setX(500);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setX(500);
        player.attack(enemyOne, enemyTwo);
        assertEquals("" + enemyOne.getLive(), "" + false);
        player.attack(enemyOne, enemyTwo);
        assertEquals("" + enemyOne.getLive(), "" + false);
    }
    
    @Test
    // test if collision still happens after enemy death
    // (bug from earlier as only the visibility were changed)
    public void CollisionAfterEnemyDeath() {
        assertEquals("" + player.getHp(), "" + 100);
        player.setX(500);
        player.setY(500);
        enemyOne.setX(500);
        enemyOne.setY(500);
        if (enemyOne.collisionCheck(player, enemyOne)) {
            player.setHp(Integer.parseInt(player.getHp()) - (10 * 1) + "");
        }
        assertEquals("" + player.getHp(), "" + 90);
        player.attack(enemyOne, enemyTwo);
        assertEquals("" + player.getHp(), "" + 90);
    }
    @Test
    public void powerUpOne() {
        player.setX(1120);
        player.setY(660);
        powerUpOne.setX(1120);
        powerUpOne.setY(660);
        if (powerUpOne.getX() == player.getX() && powerUpOne.getY() == player.getY()) {
            player.setHp(Integer.parseInt(player.getHp()) + 10 + "");
        }
        assertEquals("" + player.getHp(), "" + 110);
    }
    @Test
    public void powerUpTwo() {
        player.setX(880);
        player.setY(480);
        powerUpTwo.setX(880);
        powerUpTwo.setY(480);
        if (powerUpTwo.getX() == player.getX() && powerUpTwo.getY() == player.getY()) {
            player.setScore(Integer.parseInt(player.getScore() + 10 + ""));
        }
        assertEquals("" + player.getScore(), "" + 10);
    }
    @Test
    public void powerUpThree() {
        player.setX(1180);
        player.setY(390);
        powerUpThree.setX(1180);
        powerUpThree.setY(390);
        if (powerUpThree.getX() == player.getX() && powerUpThree.getY() == player.getY()) {
            enemyTwo.setLive(false);
        }
        assertEquals("" + enemyTwo.getLive(), "" + false);
    }

}