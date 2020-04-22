package com.example.scoretracker.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.scoretracker.database.AppRepository;
import com.example.scoretracker.database.PlayerEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GameCreateViewModel extends AndroidViewModel {

    public LiveData<List<PlayerEntity>> playerList;
    private AppRepository appRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public GameCreateViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application.getApplicationContext());
        playerList = appRepository.playerList;
    }

    public void addPlayer(PlayerEntity playerEntity) {
        executor.execute(() -> {
            appRepository.addPlayer(playerEntity);
        });
    }

//    public void deletePlayerById(int id) {
//        executor.execute(() -> {
//            appRepository.deletePlayerById(id);
//        });
//    }

    public void deleteAllPlayers() {
        appRepository.deleteAllPlayers();
    }
}
