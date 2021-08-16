package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private ImageButton playButton,forward,rewind;
    private SeekBar seekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        forward = findViewById(R.id.forward);
        rewind = findViewById(R.id.rewind);
        seekbar = findViewById(R.id.seekBar);
        prepareMediaPlayer();
        seekbar.setMax(100);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
            });
        updateSeekBar();
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.getDuration() > mediaPlayer.getCurrentPosition() + 10000) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                    updateSeekBar();
                }
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.getCurrentPosition()>10000){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                    updateSeekBar();
                }
            }
        });
        seekbar.setOnTouchListener((v, event) -> {
            SeekBar s = (SeekBar) v;
            int position = (mediaPlayer.getDuration()/100)*s.getProgress();
            mediaPlayer.seekTo(position);
            return false;
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekbar.setProgress(0);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });



    }



    void prepareMediaPlayer (){
        try {
            mediaPlayer =MediaPlayer.create(this,R.raw.song);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            seekbar.setProgress((int)((float)mediaPlayer.getCurrentPosition()/mediaPlayer.
                    getDuration()*100));
            handler.postDelayed(updater,1000);
        }
    }
    Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };


}
