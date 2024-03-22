package com.example.cs2340team2.model;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
public class EnemyMove implements CollisionStrategy {
    private ImageView enemy;
    private int x;
    private int y;
    private boolean checkLive;
    public EnemyMove(ImageView enemy, int initialX, int initialY) {
        this.enemy = enemy;
        this.x = initialX;
        this.y = initialY;
        this.checkLive = true;
        updatePosition();
    }
    public void moveUp(boolean[][] isWall, int speed) {
        // check if the next position is a wall
        if (!isWall[x / 30][(y - 30) / 30]) {
            y -= (speed * 30);
            updatePosition();
        }
    }
    public void moveDown(boolean[][] isWall, int speed) {
        // check if the next position is a wall
        if (!isWall[x / 30][(y + 30) / 30]) {
            y += (speed * 30);
            updatePosition();
        }
    }
    public void moveLeft(boolean[][] isWall, int speed) {
        if (!isWall[(x - 30) / 30][y / 30]) {
            x -= (speed * 30);
            updatePosition();
        }
    }
    public void moveRight(boolean[][] isWall, int speed) {
        if (!isWall[(x + 30) / 30][y / 30]) {
            x += (speed * 30);
            updatePosition();
        }
    }
    private void updatePosition() {
        enemy.setX(x);
        enemy.setY(y);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public boolean getLive() { return this.checkLive; }

    public void setX(int x) {
        this.x = x;
        if (x < 0) {
            setX(0);
        }
    }
    public void setY(int y) {
        this.y = y;
        if (y < 0) {
            setY(0);
        }
    }

    public void setLive(boolean live) {
        this.checkLive = live;
    }
    public boolean collisionCheck(PlayerMove player, EnemyMove enemy) {
        if (player.getX() - enemy.getX() > -50 && player.getX() - enemy.getX() < 50) {
            if (player.getY() - enemy.getY() > -50 && player.getY() - enemy.getY() < 50) {
                return true;
            }
        }
        return false;
    }
}