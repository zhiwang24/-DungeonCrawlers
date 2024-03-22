package com.example.cs2340team2.model;

import android.widget.ImageView;

public abstract class EnemyFactory {
    public abstract EnemyMove[] getEnemy(ImageView enemy, ImageView enemy2);
}
