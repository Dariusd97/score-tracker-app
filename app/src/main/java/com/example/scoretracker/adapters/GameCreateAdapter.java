package com.example.scoretracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.EditActivity;
import com.example.scoretracker.GameCreateActivity;
import com.example.scoretracker.R;
import com.example.scoretracker.database.PlayerEntity;
import com.example.scoretracker.utilities.Constants;
import com.example.scoretracker.viewModel.GameCreateViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.scoretracker.utilities.Constants.PLAYER_ID_KEY;

public class GameCreateAdapter extends RecyclerView.Adapter<GameCreateAdapter.ViewHolder> {

    private final List<PlayerEntity> playerList;
    private final Context mContext;

    public GameCreateAdapter(List<PlayerEntity> playerList, Context mContext ) {
        this.playerList = playerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_game_create, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PlayerEntity player = playerList.get(position);
        holder.tvPlayerName.setText(player.getName());
        holder.btnDeletePlayer.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EditActivity.class);
            intent.putExtra(PLAYER_ID_KEY, player.getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPlayerName)
        TextView tvPlayerName;

        @BindView(R.id.btnDeletePlayer)
        FloatingActionButton btnDeletePlayer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
