package com.example.scoretracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.adapters.GameInProgressAdapter;
import com.example.scoretracker.database.PlayerEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameInProgressActivity extends AppCompatActivity {

    @BindView(R.id.recyclePlayersInGame)
    RecyclerView recyclerViewPlayersInGame;

    public ArrayList<PlayerEntity> playerList = new ArrayList<>();
    public GameInProgressAdapter gameInProgressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_in_progress);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        playerList = bundle.getParcelableArrayList("playersList");

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPlayersInGame.setLayoutManager(linearLayoutManager);
        gameInProgressAdapter = new GameInProgressAdapter(playerList, this);
        recyclerViewPlayersInGame.setAdapter(gameInProgressAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerViewPlayersInGame.getContext(), linearLayoutManager.getOrientation());
        recyclerViewPlayersInGame.addItemDecoration(divider);
    }
}
