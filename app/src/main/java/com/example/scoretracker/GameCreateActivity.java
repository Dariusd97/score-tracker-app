package com.example.scoretracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.adapters.GameCreateAdapter;
import com.example.scoretracker.database.PlayerEntity;
import com.example.scoretracker.viewModel.GameCreateViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameCreateActivity extends AppCompatActivity {

    @BindView(R.id.recycleViewPlayers)
    RecyclerView recyclerViewPlayers;

    private EditText etPlayerName;
    private ImageButton btnAddPlayerName;
    private FloatingActionButton fbBtnStart;
    private ArrayList<PlayerEntity> playerList = new ArrayList<>();
    private GameCreateAdapter gameCreateAdapter;
    private GameCreateViewModel gameCreateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_create);

        recyclerViewPlayers = findViewById(R.id.recycleViewPlayers);
        etPlayerName = findViewById(R.id.EtPlayerName);
        btnAddPlayerName = findViewById(R.id.BtnAddPalyerName);
        fbBtnStart = findViewById(R.id.floatingBtnStart);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

        btnAddPlayerName.setOnClickListener((v)->{
                if(etPlayerName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Numele nu poate fi gol", Toast.LENGTH_SHORT).show();
                }else {
                    PlayerEntity playerEntity = new PlayerEntity(etPlayerName.getText().toString(), 0);
                    playerList.add(playerEntity);
                    gameCreateAdapter.notifyDataSetChanged();
                    addPlayer(playerEntity);
                    etPlayerName.setText("");
                }
            }
        );

        fbBtnStart.setOnClickListener((v) -> {
            Intent intent = new Intent(GameCreateActivity.this, GameInProgressActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("playersList",playerList);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            deleteAllPlayers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllPlayers() {
        gameCreateViewModel.deleteAllPlayers();
    }

    private void addPlayer(PlayerEntity playerEntity) {
        gameCreateViewModel.addPlayer(playerEntity);
    }



    private void initViewModel() {
        final Observer<List<PlayerEntity>> playerObserver = playerEntities -> {
            playerList.clear();
            playerList.addAll(playerEntities);
            if(gameCreateAdapter == null) {
                gameCreateAdapter = new GameCreateAdapter(playerList, GameCreateActivity.this);
                recyclerViewPlayers.setAdapter(gameCreateAdapter);
            }else{
                gameCreateAdapter.notifyDataSetChanged();
            }
        };
        gameCreateViewModel = new ViewModelProvider(this).get(GameCreateViewModel.class);
        gameCreateViewModel.playerList.observe(this, playerObserver);
    }

    private void initRecyclerView() {
        recyclerViewPlayers.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPlayers.setLayoutManager(linearLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerViewPlayers.getContext(), linearLayoutManager.getOrientation());
        recyclerViewPlayers.addItemDecoration(divider);
    }
}
