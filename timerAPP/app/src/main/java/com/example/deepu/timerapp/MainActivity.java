package com.example.deepu.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seek;
    TextView txt;
    Boolean counteractive=false;
    Button button ;
    CountDownTimer cdt;
    MediaPlayer mp;

    public void timeleft(int lefts){

        int minutes= (int) lefts/60;
        int seconds = lefts-minutes*60;
        String secos=Integer.toString(seconds);
        if(seconds<=9)
        {
            secos = "0"+secos ;
        }
        txt.setText(Integer.toString(minutes)+":"+secos);
    }

    public void resettime()
    {
        txt.setText("0:30");
        seek.setProgress(30);
        cdt.cancel();
        button.setText("GO");
        counteractive=false;
        seek.setEnabled(true);
    }

    public void btn(View view) {
        if (counteractive == false)
        {

            counteractive = true;
            button.setText("STOP");
           cdt = new CountDownTimer(seek.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    timeleft((int) l / 1000);
                    seek.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    txt.setText("0:00");
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.mario);
                    mp.start();
                    resettime();
                }
            }.start();
        }
        else
        {
         resettime();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seek =(SeekBar)findViewById(R.id.seek);
        txt =(TextView)findViewById(R.id.txt);
        button =(Button)findViewById(R.id.button);

        seek.setMax(300);
        seek.setProgress(30);
        seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 timeleft(i);
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
