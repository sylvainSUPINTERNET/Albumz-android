package com.example.sylvain.albumz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by michaelguerfi on 21/11/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Album> listAlbums = new ArrayList<>();


    public AlbumAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = listAlbums.get(position);
        holder.albumName.setText(album.getUser().getEmail());
        holder.albumName.setText(album.getAlbumName());
    }

    @Override
    public int getItemCount() {
        return listAlbums.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView albumName;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);

            albumName = itemView.findViewById(R.id.albumName);
            userName = itemView.findViewById(R.id.userName);
        }
    }
}
