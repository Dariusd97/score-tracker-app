package com.example.scoretracker.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository outInstance;

    public LiveData<List<PlayerEntity>> playerList;
    private AppDatabase appDatabase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context){
        if(outInstance == null){
            outInstance = new AppRepository(context);
        }
        return outInstance;
    }

    private AppRepository(Context context){
        appDatabase = AppDatabase.getInstance(context);
        playerList = getAllPlayers();
    }

    private LiveData<List<PlayerEntity>> getAllPlayers(){
        return appDatabase.playerDao().getAll();
    }

    public void deletePlayerById(int id) {
        appDatabase.playerDao().deletePlayerById(id);
    }

    public void addPlayer(PlayerEntity playerEntity) {
        appDatabase.playerDao().insertPlayer(playerEntity);
    }

    public void deleteAllPlayers() {
        executor.execute(() -> {
            appDatabase.playerDao().deleteAllPlayers();
        });
    }

    public PlayerEntity getPlayerById(int playerId) {
        return appDatabase.playerDao().getPlayerById(playerId);
    }

    public void insertPlayer(PlayerEntity playerEntity) {
        executor.execute(() -> {
            appDatabase.playerDao().insertPlayer(playerEntity);
        });
    }

    public void deletePlayer(PlayerEntity playerEntity) {
        executor.execute(() -> {
            appDatabase.playerDao().deletePlayer(playerEntity);
        });
    }
}
