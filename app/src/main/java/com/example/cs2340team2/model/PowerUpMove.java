package com.example.cs2340team2.model;

import android.widget.ImageView;

public class PowerUpMove implements PowerDecorator {
    private ImageView powerUp;
    private int x;
    private int y;

    public PowerUpMove(ImageView powerUp, int initialX, int initialY) {
        this.powerUp = powerUp;
        this.x = initialX;
        this.y = initialY;
    }
    public void powerUp1() {
        powerUp.setX(1120);
        powerUp.setY(660);
    }
    public void powerUp2() {
        powerUp.setX(880);
        powerUp.setY(480);
    }
    public void powerUp3() {
        powerUp.setX(1180);
        powerUp.setY(390);
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
