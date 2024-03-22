package com.example.cs2340team2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cs2340team2.model.PlayerMove;


import android.widget.ImageView;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Sprint3 {
    private boolean[][] isWall1 = new boolean[64][36];
    private boolean[][] isWall2 = new boolean[64][36];


    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cs2340team2", appContext.getPackageName());
    }

    private PlayerMove player;

    @Before
    public void setUp() {
        ImageView imageView = new ImageView(InstrumentationRegistry.getInstrumentation().getTargetContext());
        imageView.setImageResource(R.drawable.sprite2);
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
    // ensure player movement-UP works
    public void testMoveUp() {
        assertEquals("" + player.getY(), "" + 750);
        player.moveUp(isWall1);
        assertEquals("" + player.getY(), "" + 720);
    }

    @Test
    // ensure player movement-DOWN works
    public void testMoveDown() {
        assertEquals("" + player.getY(), "" + 750);
        player.moveDown(isWall1);
        assertEquals("" + player.getY(), "" + 780);
    }

    @Test
    // ensure player movement-LEFT works
    public void testMoveLeft() {
        assertEquals("" + player.getX(), "" + 1000);
        player.moveLeft(isWall1);
        assertEquals("" + player.getX(), "" + 970);
    }

    @Test
    // ensure player movement-RIGHT works
    public void testMoveRight() {
        assertEquals("" + player.getX(), "" + 1000);
        player.moveRight(isWall1);
        assertEquals("" + player.getX(), "" + 1030);
    }

    @Test
    // test wall UP collisions
    public void testMoveUpIntoWall() {
        assertEquals("" + player.getY(), "" + 750);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        player.moveUp(isWall1);
        assertEquals("" + player.getY(), "" + 690); //shouldn't move past this coordinate
    }

    @Test
    // test wall DOWN collisions
    public void testMoveDownIntoWall() {
        assertEquals("" + player.getY(), "" + 750);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        player.moveDown(isWall1);
        assertEquals("" + player.getY(), "" + 810); //shouldn't move past this coordinate
    }

    @Test
    // test wall LEFT collisions
    public void testMoveLeftIntoWall() {
        assertEquals("" + player.getX(), "" + 1000);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        player.moveLeft(isWall1);
        assertEquals("" + player.getX(), "" + 910); //shouldn't move past this coordinate
    }

    @Test
    // test wall RIGHT collisions
    public void testMoveRightIntoWall() {
        assertEquals("" + player.getX(), "" + 1000);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        player.moveRight(isWall1);
        assertEquals("" + player.getX(), "" + 1180); //shouldn't move past this coordinate
    }

    @Test
    // test player movement off screen
    // (player should not be able to go off-screen) 
    public void TestMoveOffScreen() {
        player.setX(40);
        player.setY(40);
        player.moveUp(isWall1);
        player.moveLeft(isWall1);
        player.moveUp(isWall1);
        player.moveLeft(isWall1);
        player.moveUp(isWall1);
        assertEquals("" + 40, "" + player.getX());
        assertEquals("" + 40, "" + player.getY());
    }
    @Test
    // test negative player input
    public void playerPositionNegative() {
        player.setX(-1);
        player.setY(-1);
        assertEquals(-1, player.getX());
        assertEquals(-1, player.getY());
    }
}
