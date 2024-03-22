package com.example.cs2340team2.model;

import android.widget.ImageView;

public class Map2Enemy extends EnemyFactory {

    @Override
    public EnemyMove[] getEnemy(ImageView enemy2, ImageView enemy3) {
        EnemyMove enemyTwo = new EnemyMove(enemy2, 430, 170);
        EnemyMove enemyThree = new EnemyMove(enemy3, 1200, 170);
        return new EnemyMove[]{enemyTwo, enemyThree};

    }
}