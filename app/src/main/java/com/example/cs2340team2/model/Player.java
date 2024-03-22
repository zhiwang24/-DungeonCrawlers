package com.example.cs2340team2.model;

public class Player {
    private String name;
    private String difficulty;
    private String hp;
    private int score;
    private static volatile Player player;
    private int difficultyMultiplier;

    // sets default values for player
    public Player() {
        name = "";
        difficulty = "Easy";
        hp = "100";
        score = 0;
    }

    // if player is null, create a new player
    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
}
