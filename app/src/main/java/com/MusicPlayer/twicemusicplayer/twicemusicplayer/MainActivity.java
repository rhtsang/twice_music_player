package com.MusicPlayer.twicemusicplayer.twicemusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.Song;

public class MainActivity extends AppCompatActivity {

    private List<Song> list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        populateList();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);

    }

    public void populateList() {
        list = new ArrayList<>();

        list.add(new Song(getResources().getDrawable(R.drawable.cheer_up), "Cheer Up"));

        list.add(new Song(getResources().getDrawable(R.drawable.heart_shaker), "Heart Shaker"));

        list.add(new Song(getResources().getDrawable(R.drawable.knock_knock), "Knock Knock"));

        list.add(new Song(getResources().getDrawable(R.drawable.likey), "Likey"));

        list.add(new Song(getResources().getDrawable(R.drawable.one_more_time), "One More Time"));

        list.add(new Song(getResources().getDrawable(R.drawable.one_to_ten), "One To Ten"));

        list.add(new Song(getResources().getDrawable(R.drawable.ooh_ahh), "Ooh-Ahh"));

        list.add(new Song(getResources().getDrawable(R.drawable.signal), "Signal"));

        list.add(new Song(getResources().getDrawable(R.drawable.tt), "TT"));

    }

}
