package br.com.chicobentojr.coverdiscover.models;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 24/05/2016.
 */
public class Track implements Serializable {
    public Album album;
    public List<Artist> artists;
    public int disc_number;
    public int duration_ms;
    public String id;
    public String name;
    public int track_number;
    public String type;
    public String uri;
    public String path;

    public static ArrayList<Track> getAll(Context context, String query) {
        //Some audio may be explicitly marked as not being music
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        if (query != null) {
            selection += " and " + MediaStore.Audio.Media.TITLE + " like '%" + query + "%'" +
                    " or " + MediaStore.Audio.Media.ARTIST + " like '%" + query + "%'";
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM
        };

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                projection[0]);

        ArrayList<Track> tracks = new ArrayList<>();

        while (cursor.moveToNext()) {
            Track track = new Track();
            Album album = new Album();
            final Artist artist = new Artist();

            track.name = cursor.getString(0);
            track.path = cursor.getString(1);
            artist.name = cursor.getString(2);
            album.name = cursor.getString(3);

            //track.name = name;
            //item.data = data;

            track.album = album;
            track.artists = new ArrayList<Artist>() {{
                add(artist);
            }};

            //itemAlbum.name = album;
            //item.album = itemAlbum;
            //List<Artist> artistList = new ArrayList<>();
            //artistList.add(artist);
            //item.artists = artistList;

            tracks.add(track);
        }
        return tracks;
    }
}
