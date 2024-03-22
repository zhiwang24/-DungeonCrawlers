package com.example.cs2340team2.model;

import java.util.ArrayList;

public class LeaderboardMain {
    // create leaderboard to store entries
    private ArrayList<Leaderboard> leaderboard;
    // designate "top" leaderboard person
    private static volatile LeaderboardMain leader;
    private LeaderboardMain(ArrayList<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }
    // use design pattern to ensure only one leaderboard is created
    public static LeaderboardMain getInstance() {
        if (leader == null) {
            synchronized (LeaderboardMain.class) {
                if (leader == null) {
                    leader = new LeaderboardMain(new ArrayList<Leaderboard>());
                }
            }
        }
        return leader;
    }
    public void setLeaderboard(ArrayList<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }
    public ArrayList<Leaderboard> getLeaderboard() {
        return this.leaderboard;
    }

}
