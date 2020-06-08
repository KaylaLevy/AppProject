package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
ProgressBar Welcome;
    int count = 0;
    private static int SPLASH_TIME_OUT = 8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar prg = (ProgressBar) findViewById(R.id.progresswelcome);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                count ++;
                prg.setProgress(count);
                if (count == 100)
                {
                    timer.cancel();
                }

            }
        }; timer.schedule(timerTask,0,100);
        new Handler().postDelayed(new Runnable() {
            @Override
              public void run(){
                Intent welcomeIntent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(welcomeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
