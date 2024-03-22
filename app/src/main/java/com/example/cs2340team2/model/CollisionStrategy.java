package com.example.cs2340team2.model;

// COLLISION
public interface CollisionStrategy {
    boolean collisionCheck(PlayerMove player, EnemyMove enemy);
}

