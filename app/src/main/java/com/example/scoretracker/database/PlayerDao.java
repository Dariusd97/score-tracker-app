package com.example.scoretracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayer(PlayerEntity playerEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlayerEntity> players);

    @Delete
    void deletePlayer(PlayerEntity playerEntity);

    @Query("SELECT * FROM players WHERE id = :id")
    PlayerEntity getPlayerById(int id);

    @Query("SELECT * FROM players")
    LiveData<List<PlayerEntity>> getAll();

    @Query("SELECT COUNT(*) FROM players")
    int getCount();

    @Query("DELETE FROM players WHERE id = :id")
    void deletePlayerById(int id);

    @Query("DELETE FROM players")
    int deleteAllPlayers();
}
