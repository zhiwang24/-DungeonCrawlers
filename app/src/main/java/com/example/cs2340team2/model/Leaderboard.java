package com.example.cs2340team2.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Leaderboard implements Parcelable {
    private final String name;
    private final String time;
    private final int score;
    public Leaderboard(String name, String time, int score) {
        this.name = name;
        this.time = time;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
    public int getScore() {
        return score;
    }

    // convert leaderboard entities to string
    protected Leaderboard(Parcel in) {
        name = in.readString();
        time = in.readString();
        score = in.readInt();
    }

    public static final Creator<Leaderboard> CREATOR = new Creator<Leaderboard>() {
        @Override
        // obtain leaderboard
        public Leaderboard createFromParcel(Parcel in) {
            return new Leaderboard(in);
        }

        @Override
        //create leaderboard
        public Leaderboard[] newArray(int size) {
            return new Leaderboard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    // comvert inputs to string 
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(time);
        dest.writeInt(score);
    }
}
