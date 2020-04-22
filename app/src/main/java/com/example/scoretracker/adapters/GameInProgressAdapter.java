package com.example.scoretracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.R;
import com.example.scoretracker.database.PlayerEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameInProgressAdapter extends RecyclerView.Adapter<GameInProgressAdapter.ViewHolder> {

    private final List<PlayerEntity> playerList;
    private final Context mContext;

    public GameInProgressAdapter(List<PlayerEntity> playerList, Context mContext) {
        this.playerList = playerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_game_in_progress, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PlayerEntity player = playerList.get(position);
        holder.tvPlayerName.setText(player.getName());
        holder.tvPlayerScore.setText(Integer.toString(player.getScore()));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPlayerNameInGame)
        TextView tvPlayerName;
        @BindView(R.id.tvPlayerScoreInGame)
        EditText tvPlayerScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
