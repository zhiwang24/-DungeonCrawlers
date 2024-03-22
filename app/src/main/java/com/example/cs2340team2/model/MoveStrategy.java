package com.example.cs2340team2.model;

public interface MoveStrategy {
    // define moveUp method
    void moveUp(boolean[][] arr);
    // define moveDown method
    void moveDown(boolean[][] arr);
    // define moveLeft method
    void moveLeft(boolean[][] arr);
    // define moveRight method
    void moveRight(boolean[][] arr);
}
