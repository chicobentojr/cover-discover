package br.com.chicobentojr.coverdiscover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.chicobentojr.coverdiscover.R;
import br.com.chicobentojr.coverdiscover.models.Image;
import br.com.chicobentojr.coverdiscover.models.Track;
import br.com.chicobentojr.coverdiscover.utils.LoadLocalCoverTask;

/**
 * Created by Francisco on 24/05/2016.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Track> tracks;

    public TrackAdapter(Context context, ArrayList<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_track, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Track track = tracks.get(position);

        holder.lblName.setText(track.name);
        holder.lblAlbum.setText(track.album.name);
        holder.lblArtist.setText(track.artists.get(0).name);

        new LoadLocalCoverTask(context, holder.imgCover).execute(track.path);

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgCover;
        public TextView lblName;
        public TextView lblAlbum;
        public TextView lblArtist;


        public ViewHolder(View itemView) {
            super(itemView);

            imgCover = (ImageView) itemView.findViewById(R.id.imgCover);
            lblName = (TextView) itemView.findViewById(R.id.lblName);
            lblAlbum = (TextView) itemView.findViewById(R.id.lblAlbum);
            lblArtist = (TextView) itemView.findViewById(R.id.lblArtist);
        }
    }
}
