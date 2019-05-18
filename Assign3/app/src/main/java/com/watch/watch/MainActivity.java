package com.watch.watch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button stop;
    private Button reset;
    private TextView textview;
    int totalseconds = 0;
    int hour = 0;
    boolean Isrunning=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.Start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        textview = (TextView) findViewById(R.id.textView);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();
                StartTimer();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();
                StopTimer();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
                Reset();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.e("Stopwatch", "OnStart");
    }
    private void RunCode() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int minutes = (totalseconds / 3600);
                int seconds = ((totalseconds %3600)/60);
                int miliseconds = (totalseconds % 60);

                if(minutes == 60 )
                {
                    ++hour;
                    minutes = 0;

                }
                if (!Isrunning) {
                    return;
               }

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d:%02d",hour, minutes, seconds, miliseconds);
                textview.setText(time);
                Log.e("StopWatch", time);
                totalseconds++;
                Handler handler = new Handler();
                handler.postDelayed(this,0);
            }
        };
                Handler handler = new Handler();
                handler.post(runnable);
            }

            public void StartTimer(){

                if(Isrunning){
                    return;
                }

                Isrunning = true;
                RunCode();

            }

            public void StopTimer(){

                Isrunning = false;

            }

            public void Reset(){

                //StopTimer();
                Isrunning = false;
                totalseconds = 00;
                String numberAsString = String.valueOf(totalseconds);
                textview.setText(numberAsString);


            }
}


