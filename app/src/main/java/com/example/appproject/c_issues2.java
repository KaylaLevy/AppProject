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

public class c_issues2 extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/councellor_issues2.php";
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_issues2);

        Button btnNext = (Button)findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x9 = "-1", x10 = "-1", x11 = "-1", x12 = "-1", x13 = "-1", x14 = "-1", x15 = "-1";
                CheckBox c9 = (CheckBox) findViewById(R.id.chk9);
                CheckBox c10 = (CheckBox) findViewById(R.id.chk10);
                CheckBox c11 = (CheckBox) findViewById(R.id.chk11);
                CheckBox c12 = (CheckBox) findViewById(R.id.chk12);
                CheckBox c13 = (CheckBox) findViewById(R.id.chk13);
                CheckBox c14 = (CheckBox) findViewById(R.id.chk14);
                CheckBox c15 = (CheckBox) findViewById(R.id.chk15);
                if (c9.isChecked() == true) {
                    x9 = "9";
                }
                if (c10.isChecked() == true) {
                    x10 = "10";
                }
                if (c11.isChecked() == true) {
                    x11 = "11";
                }
                if (c12.isChecked() == true) {
                    x12 = "12";
                }
                if (c13.isChecked() == true) {
                    x13 = "13";
                }
                if (c14.isChecked() == true) {
                    x14 = "14";
                }
                if (c15.isChecked() == true) {
                    x15 = "15";
                }

                new insert().execute(un, x9, x10, x11, x12, x13, x14, x15);
            }
        });

    }

    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String uName = strings[0];
            String y9 = strings[1];
            String y10 = strings[2];
            String y11 = strings[3];
            String y12 = strings[4];
            String y13 = strings[5];
            String y14 = strings[6];
            String y15 = strings[7];

            String finalURL = url_Register + "?USERNAME=" + uName
                    + "&Y1=" + y9 + "&Y2=" + y10 + "&Y3=" + y11 + "&Y4=" + y12 + "&Y5=" + y13
                    + "&Y6=" + y14 + "&Y7=" + y15;

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

                    if (result.equalsIgnoreCase("issue entered successfully")) {
                        showToast("issue entered successfully");

                        Intent i = new Intent(c_issues2.this, LoginUser.class);
                        c_issues2.this.startActivity(i);
                    } else {
                        showToast("oops! please try again!");
                    }

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
                Toast.makeText(c_issues2.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
