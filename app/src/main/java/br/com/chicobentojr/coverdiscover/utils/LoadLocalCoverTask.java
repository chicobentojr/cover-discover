package br.com.chicobentojr.coverdiscover.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.cmc.music.metadata.ImageData;
import org.cmc.music.metadata.MusicMetadata;
import org.cmc.music.metadata.MusicMetadataSet;
import org.cmc.music.myid3.MyID3;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import br.com.chicobentojr.coverdiscover.R;

/**
 * Created by Francisco on 24/05/2016.
 */
public class LoadLocalCoverTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView img;
    private Context context;

    public LoadLocalCoverTask(Context context, ImageView img) {
        this.context = context;
        this.img = img;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String path = params[0];
        return loadImage(path);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        img.setImageBitmap(bitmap);
    }

    public Bitmap loadImage(String path) {

        String pathdata = path;
        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();

        File src = new File(pathdata);
        MusicMetadataSet src_set = null;
        try {
            src_set = new MyID3().read(src);
            if (src_set == null) // perhaps no metadata
            {
                return bitmap;
            } else {
                MusicMetadata musicMetadata = (MusicMetadata) src_set.getSimplified();

                Vector<ImageData> images = musicMetadata.getPictureList();
                ImageData img = null;
                if (images.size() != 0) {
                    img = images.get(0);

                    byte[] buf = img.imageData;

                    BitmapFactory factory = new BitmapFactory();
                    bitmap = factory.decodeByteArray(buf, 0, buf.length);
                    return bitmap;

                } else {
                    return bitmap;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
