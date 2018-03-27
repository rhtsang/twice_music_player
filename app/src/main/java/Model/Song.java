package Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by RaymondTsang on 12/25/17.
 */

public class Song {

    private Drawable imageView;

    private String songName;

    public Drawable getImageView() {
        return imageView;
    }

    public void setImageView(Drawable imageView) {
        this.imageView = imageView;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Song(Drawable imageView, String songName) {

        this.imageView = imageView;
        this.songName = songName;
    }
}
