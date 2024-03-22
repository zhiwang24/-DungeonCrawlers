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


import android.widget.ImageView;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Sprint4 {
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

    @Before
    public void setUp() {
        ImageView imageView1 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView1.setImageResource(R.drawable.enemy1);
        ImageView imageView2 = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView2.setImageResource(R.drawable.enemy2);
        ImageView imageView = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView.setImageResource(R.drawable.sprite2);
        enemyOne = new EnemyMove(imageView1, 500, 570);
        enemyTwo = new EnemyMove(imageView2, 500, 390);
        player = new PlayerMove(imageView, 1000, 750);

        //setting up the walls for the two maps
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
        for (int i = 0; i < 64; i++) {
            isWall1[i][0] = true;
            isWall1[i][35] = true;
        }
        for (int j = 0; j < 36; j++) {
            isWall1[0][j] = true;
            isWall1[63][j] = true;
        }
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
        for (int i = 0; i < 64; i++) {
            isWall2[i][0] = true;
            isWall2[i][35] = true;
        }
        for (int j = 0; j < 36; j++) {
            isWall2[0][j] = true;
            isWall2[63][j] = true;
        }
    }


    @Test
    // test for enemy movement - UP
    public void enemyMovementUp() {
        assertEquals("" + enemyOne.getY(),"" + 570);
        enemyOne.moveUp(isWall1, 1);
        assertEquals("" + enemyOne.getY(), "" + 540);
    }

    @Test
    // check Enemy-Player interaction
    // (player should take damage)
    public void enemyPlayerCollision() {
        player.setX(500);
        player.setY(570);
        enemyOne.setX(500);
        enemyOne.setY(570);
        assertEquals("" + enemyOne.collisionCheck(player, enemyOne), "" + true);
    }

    @Test
    // check Enemy-Player interaction
    // (player should not take damage) 
    public void enemyPlayerAvoidance() {
        player.setX(500);
        enemyOne.setX(570);
        assertEquals("" + enemyOne.collisionCheck(player, enemyOne), "" + false);
    }

    @Test
    // check health-update after player-enemy collision
    public void healthUpdateSuccess() {
        player.setX(500);
        player.setY(570);
        assertEquals("" + player.getHp(), "" + 100);
        if (enemyOne.collisionCheck(player, enemyOne)) {
            player.setHp(Integer.parseInt(player.getHp()) - (10 * 1) + "");
        }
        assertEquals("" + player.getHp(), "" + 90);
    }
    
    @Test
    // check health-nonUpdate for no player-enemy collision
    public void healthUpdateFail() {
        assertEquals("" + player.getHp(), "" + 100);
        if (enemyOne.collisionCheck(player, enemyOne)) {
            player.setHp(Integer.parseInt(player.getHp()) - (10 * 1) + "");
        }
        assertEquals("" + player.getHp(), "" + 100);
    }

    @Test
    // test enemy-wall interaction - UP
    public void moveUpWall() {
        assertEquals("" + enemyOne.getY(), "" + 570);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        assertEquals("" + enemyOne.getY(), "" + 510); //shouldn't move past this coordinate
    }
    
    @Test
    // test enemy-wall interaction - DOWN
    public void moveDownIntoWall() {
        assertEquals("" + enemyOne.getY(), "" + 570);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        enemyOne.moveDown(isWall1,1);
        assertEquals("" + enemyOne.getY(), "" + 630); //shouldn't move past this coordinate
    }

    @Test
    // test enemy-wall interaction - LEFT
    public void moveLeftIntoWall() {
        assertEquals("" + enemyOne.getX(), "" + 500);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        assertEquals("" + enemyOne.getX(), "" + 500); //shouldn't move past this coordinate
    }

    @Test
    // test enemy-wall interaction - RIGHT
    public void moveRightIntoWall() {
        assertEquals("" + enemyOne.getX(), "" + 500);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        enemyOne.moveRight(isWall1,1);
        assertEquals("" + enemyOne.getX(), "" + 710); //shouldn't move past this coordinate
    }

    @Test
    // ensure enemy cannot move off-screen
    public void EnemyMoveOffScreen() {
        enemyOne.setX(40);
        enemyOne.setY(40);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        enemyOne.moveLeft(isWall1,1);
        enemyOne.moveUp(isWall1,1);
        assertEquals("" + 40, "" + enemyOne.getX());
        assertEquals("" + 40, "" + enemyOne.getY());
    }
}
