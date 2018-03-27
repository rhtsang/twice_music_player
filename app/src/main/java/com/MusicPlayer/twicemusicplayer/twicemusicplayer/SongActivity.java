package com.MusicPlayer.twicemusicplayer.twicemusicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private Bundle extras;
    private SeekBar seekBar;
    private TextView remainingTime;
    private TextView currentTime;
    private Button prevButton;
    private Button playButton;
    private Button nextButton;
    private MediaPlayer mediaPlayer;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        setUpUI();
        startMusic();

    }

    public void setSong() {
        switch (extras.getString("songName")) {
            case ("Cheer Up"):
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cheer_up);
                break;
            case "Heart Shaker":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.heart_shaker);
                break;
            case "Knock Knock":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.knock_knock);
                break;
            case "Likey":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.likey);
                break;
            case "One More Time":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one_more_time);
                break;
            case "One To Ten":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one_to_ten);
                break;
            case "Ooh-Ahh":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ooh_ahh);
                break;
            case "Signal":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.signal);
                break;
            case "TT":
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tt);
                break;
        }
        seekBar.setMax(mediaPlayer.getDuration());

    }

    public void setUpUI() {
        imageView = (ImageView) findViewById(R.id.songImageViewId);
        textView = (TextView) findViewById(R.id.songNameId);
        extras = getIntent().getExtras();
        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        remainingTime = (TextView) findViewById(R.id.remainingTimeId);
        currentTime = (TextView) findViewById(R.id.currentTimeId);
        prevButton = (Button) findViewById(R.id.prevButtonId);
        playButton = (Button) findViewById(R.id.playButtonId);
        nextButton = (Button) findViewById(R.id.nextButtonId);
        mediaPlayer = new MediaPlayer();


        prevButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        textView.setText(extras.getString("songName"));

        setImage();
        setSong();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                int currentPos = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();
                currentTime.setText(dateFormat.format(new Date(currentPos)));
                remainingTime.setText(dateFormat.format(new Date(duration - currentPos)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setImage() {
        switch (extras.getString("songName")) {
            case ("Cheer Up"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.cheer_up));
                break;
            case ("Heart Shaker"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.heart_shaker));
                break;
            case ("Knock Knock"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.knock_knock));
                break;
            case ("Likey"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.likey));
                break;
            case ("One More Time"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.one_more_time));
                break;
            case ("One To Ten"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.one_to_ten));
                break;
            case ("Ooh-Ahh"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ooh_ahh));
                break;
            case ("Signal"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.signal));
                break;
            case ("TT"):
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.tt));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButtonId:
                //Toast.makeText(this, "Play Button", Toast.LENGTH_SHORT).show();
                if (mediaPlayer.isPlaying()) {
                    pauseMusic();
                } else {
                    startMusic();
                }
                break;
            case R.id.prevButtonId:
                //Toast.makeText(this, "Prev Button", Toast.LENGTH_SHORT).show();
                prevMusic();
                break;
            case R.id.nextButtonId:
                //Toast.makeText(this, "Next Button", Toast.LENGTH_SHORT).show();
                nextMusic();
                break;
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            playButton.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

    public void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            updateThread();
            playButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void updateThread() {
        thread = new Thread(){
            @Override
            public void run() {
                try {
                    while (mediaPlayer != null && mediaPlayer.isPlaying()) {


                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int newCurrentTime = mediaPlayer.getCurrentPosition();
                                //int newRemainingTime = mediaPlayer.getDuration();
                                //seekBar.setMax(newRemainingTime);
                                seekBar.setProgress(newCurrentTime);

                                //currentTime.setText(String.valueOf(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getCurrentPosition()))));
                                //remainingTime.setText(String.valueOf(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()))));


                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        thread.start();
    }

    public void prevMusic() {
        //if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            seekBar.setProgress(0);
            mediaPlayer.start();
        //}
    }

    public void nextMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getDuration() - 1000);
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        thread.interrupt();
        thread = null;

        super.onDestroy();
    }
}
