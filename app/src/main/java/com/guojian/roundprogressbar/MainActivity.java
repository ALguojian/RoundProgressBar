package com.guojian.roundprogressbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.guojian.library.RoundProgressBar;

public class MainActivity extends AppCompatActivity {

    protected RoundProgressBar progressBar1;
    protected RoundProgressBar progressBar2;
    protected RoundProgressBar progressBar3;
    protected SeekBar seekBarOne;
    protected SeekBar seekBarTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        progressBar1.setProgress(20);
        progressBar2.setProgress(80);

    }

    private void initView() {
        progressBar1 = (RoundProgressBar) findViewById(R.id.progress_bar_1);
        progressBar2 = (RoundProgressBar) findViewById(R.id.progress_bar_2);
        progressBar3 = (RoundProgressBar) findViewById(R.id.asd);

        progressBar3.setRoundColor(Color.parseColor("#648dbc"));

        progressBar3.setRoungProgressColor(Color.parseColor("#3090FF"));

        progressBar3.setMaxLong(100);

        progressBar3.setProgress(30);

        seekBarOne = (SeekBar) findViewById(R.id.seekBar_one);
        seekBarTwo = (SeekBar) findViewById(R.id.seekBar_two);
        seekBarOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                progressBar1.setProgress(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                progressBar2.setProgress(i);
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
