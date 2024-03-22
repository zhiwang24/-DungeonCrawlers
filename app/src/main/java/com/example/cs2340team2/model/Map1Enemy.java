package com.example.cs2340team2.model;

import android.widget.ImageView;

public class Map1Enemy extends EnemyFactory {

    @Override
    public EnemyMove[] getEnemy(ImageView enemy, ImageView enemy2) {
        // create two enemies
        EnemyMove enemyOne = new EnemyMove(enemy, 500, 570);
        EnemyMove enemyTwo = new EnemyMove(enemy2, 500, 390);
        return new EnemyMove[]{enemyOne, enemyTwo};

    }
}
