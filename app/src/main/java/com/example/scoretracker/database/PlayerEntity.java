package com.example.scoretracker.database;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class PlayerEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int score;

    @Ignore
    public PlayerEntity() {
    }

    @Ignore
    public PlayerEntity(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public PlayerEntity(int id,String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    @Ignore
    private PlayerEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        score = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }


    public static final Creator<PlayerEntity> CREATOR = new Creator<PlayerEntity>() {
        @Override
        public PlayerEntity createFromParcel(Parcel in) {
            return new PlayerEntity(in);
        }

        @Override
        public PlayerEntity[] newArray(int size) {
            return new PlayerEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(score);
    }
}
