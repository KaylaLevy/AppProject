package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class q_completed_message extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_match.php";
    private String un = LoginUser.getCurrentUsername();
    //private String lang1 = q_language.getLang1();
    //private String lang2 = q_language.getLang2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_completed_message);

        Button btnNext = (Button)findViewById(R.id.btnNext);

        btnNext .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new insert().execute(un); //, lang1, lang2
            }
        });
    }
    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String uName = strings[0];
            //String y1 = strings[1];
            //String y2 = strings[2];
            String finalURL = url_Register + "?USERNAME=" + uName;
                    //+ "&Y1=" + y1 + "&Y2=" + y2;


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String result = response.body().string();
                    showToast(result);

                    //if (result.equalsIgnoreCase("21")) {
                      //  showToast("21");

                        Intent i =  new Intent(q_completed_message.this, LoginUser.class);
                        startActivity(i);
                    //} else {
                      //  showToast("oops! please try again!");
                    //}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String text)
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(q_completed_message.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}

