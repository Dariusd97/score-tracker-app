package com.example.scoretracker;

import android.os.Bundle;

import com.example.scoretracker.database.PlayerEntity;
import com.example.scoretracker.utilities.Constants;
import com.example.scoretracker.viewModel.EditViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.scoretracker.utilities.Constants.*;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.player_name_text)
    EditText etPlayerName;

    private EditViewModel editViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        editViewModel = new ViewModelProvider(this).get(EditViewModel.class);
        editViewModel.mutableLivePlayer.observe(this, playerEntity -> etPlayerName.setText(playerEntity.getName()));
        Bundle extras = getIntent().getExtras();
        setTitle("Edit player");
        int playerId = extras.getInt(PLAYER_ID_KEY);
        editViewModel.loadPlayer(playerId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.delete_player){
            editViewModel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        editViewModel.savePlayer(etPlayerName.getText().toString());
        finish();
    }
}
