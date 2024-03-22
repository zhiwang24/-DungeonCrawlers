package com.example.cs2340team2.model;
import android.widget.ImageView;
import com.example.cs2340team2.R;

public class PlayerMove extends Player implements MoveStrategy, CollisionStrategy {
    private ImageView character;
    private int x;
    private int y;

    // define player position
    public PlayerMove(ImageView character, int initialX, int initialY) {
        super();
        this.character = character;
        this.x = initialX;
        this.y = initialY;
        updatePosition();
    }

    // define player movement - UP
    public void moveUp(boolean[][] isWall) {
        if (!isWall[x / 30][(y - 30) / 30]) {
            y -= 30;
            updatePosition();
        }
    }

    // define player movement - DOWN
    public void moveDown(boolean[][] isWall) {
        if (!isWall[x / 30][(y + 30) / 30]) {
            y += 30;
            updatePosition();
        }
    }

    // define player movement - LEFT
    public void moveLeft(boolean[][] isWall) {
        if (!isWall[(x - 30) / 30][y / 30]) {
            x -= 30;
            updatePosition();
        }
    }

    // define player movement - RIGHT
    public void moveRight(boolean[][] isWall) {
        if (!isWall[(x + 30) / 30][y / 30]) {
            x += 30;
            updatePosition();
        }
    }

    // update player position
    private void updatePosition() {
        character.setX(x);
        character.setY(y);
    }

    // checks for a player-enemy collision using (x,y)
    public boolean collisionCheck(PlayerMove player, EnemyMove enemy) {
        if (player.getX() - enemy.getX() > -50 && player.getX() - enemy.getX() < 50) {
            if (player.getY() - enemy.getY() > -50 && player.getY() - enemy.getY() < 50) {
                return true;
            }
        }
        return false;
    }

    public void attack(EnemyMove enemy1, EnemyMove enemy2){
        if (x - enemy1.getX() > -100 && x - enemy1.getX() < 100) {
            if (y - enemy1.getY() > -100 && y - enemy1.getY() < 100) {
                enemy1.setLive(false);
            }
        }
        if (x - enemy2.getX() > -100 && x - enemy2.getX() < 100) {
            if (y - enemy2.getY() > -100 && y - enemy2.getY() < 100) {
                enemy2.setLive(false);
            }
        }
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
