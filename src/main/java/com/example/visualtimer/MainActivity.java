package com.example.visualtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button button;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.start);

        final SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        player = MediaPlayer.create(this,R.raw.alarm_bell);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.bulb_off);
                new CountDownTimer(seekBar.getProgress()*1000, 1000) {
                    public void onTick(long sec) {
                        button.setEnabled(false);
                        int time = (int) (sec/1000);
                        int minutes = time / 60;
                        int seconds = time % 60;

                        if(minutes < 10 || seconds < 10){
                            if(seconds < 10){
                                textView.setText(String.format("%d:%02d",minutes,seconds));
                            }

                            else if(minutes < 10){
                                textView.setText(String.format("%d:%02d",minutes,seconds));
                            }
                        }
                        else{
                            textView.setText(String.format("%d:%02d",minutes,seconds));
                        }

                    }
                    public void onFinish() {
                        imageView.setImageResource(R.drawable.bulb_on);
                        player.start();
                        button.setEnabled(true);
                    }

                }.start();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int time = progress;
                int min = time /60;
                int sec = time%60;

                String timeView;

                if(min > 9){
                    timeView = min + ":" + "0"+sec;
                    textView.setText( timeView );
                } else if(min < 10 || sec < 10){
                    if(sec < 10){
                        timeView = "0"+min + ":" + "0"+sec;
                        textView.setText( timeView );
                    }

                    else if(min < 10){
                        timeView = "0"+min + ":" +sec;
                        textView.setText( timeView );
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
