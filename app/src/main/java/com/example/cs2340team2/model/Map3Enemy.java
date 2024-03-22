package com.example.cs2340team2.model;

import android.widget.ImageView;

public class Map3Enemy extends EnemyFactory {

    @Override
    public EnemyMove[] getEnemy(ImageView enemy3, ImageView enemy4) {
        EnemyMove enemyThree = new EnemyMove(enemy3, 520, 220);
        EnemyMove enemyFour = new EnemyMove(enemy4, 760, 520);
        return new EnemyMove[]{enemyThree, enemyFour};

    }
}