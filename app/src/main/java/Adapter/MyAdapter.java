package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MusicPlayer.twicemusicplayer.twicemusicplayer.R;
import com.MusicPlayer.twicemusicplayer.twicemusicplayer.SongActivity;

import java.util.List;

import Model.Song;

/**
 * Created by RaymondTsang on 12/25/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Song> list;

    public MyAdapter(Context context, List<Song> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        private TextView songName;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewId);

            songName = (TextView) itemView.findViewById(R.id.textViewId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            Song song = list.get(pos);

            Intent mainToSong = new Intent(context, SongActivity.class);
            mainToSong.putExtra("songName", song.getSongName());
            context.startActivity(mainToSong);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Song song = list.get(position);

        //Below is commented out bc images are too big in terms of data size, which slows down app
        //Find better images or leave as is?
        holder.imageView.setImageDrawable(song.getImageView().getCurrent());
        //holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.cheer_up));

        holder.songName.setText(song.getSongName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

