package com.example.scoretracker.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.scoretracker.database.AppRepository;
import com.example.scoretracker.database.PlayerEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditViewModel extends AndroidViewModel {

    public MutableLiveData<PlayerEntity> mutableLivePlayer = new MutableLiveData<>();
    private AppRepository appRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(getApplication());
    }

    public void loadPlayer(int playerId) {
        executor.execute(() -> {
            PlayerEntity playerEntity = appRepository.getPlayerById(playerId);
            mutableLivePlayer.postValue(playerEntity);
        });
    }

    public void savePlayer(String playerName) {
        PlayerEntity playerEntity = mutableLivePlayer.getValue();
        if(playerEntity == null){

        } else {
          playerEntity.setName(playerName);
        }
        appRepository.insertPlayer(playerEntity);
    }

    public void deleteNote() {
        appRepository.deletePlayer(mutableLivePlayer.getValue());
    }
}
