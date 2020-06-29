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

public class q_issues extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_issues.php";
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_issues);

        Button btnNext = (Button)findViewById(R.id.btnIssues2_next);

        btnNext .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x1 = "-1", x2 = "-1", x3 = "-1", x4 = "-1", x5 = "-1", x6 = "-1", x7 = "-1", x8 = "-1";
                CheckBox c1 = (CheckBox)findViewById(R.id.chk1); CheckBox c2 = (CheckBox)findViewById(R.id.chk2);
                CheckBox c3 = (CheckBox)findViewById(R.id.chk3); CheckBox c4 = (CheckBox)findViewById(R.id.chk4);
                CheckBox c5 = (CheckBox)findViewById(R.id.chk5); CheckBox c6 = (CheckBox)findViewById(R.id.chk6);
                CheckBox c7 = (CheckBox)findViewById(R.id.chk7); CheckBox c8 = (CheckBox)findViewById(R.id.chk8);
                if (c1.isChecked() == true){ x1 = "1"; }  if (c2.isChecked() == true){ x2 = "2"; }  if (c3.isChecked() == true){ x3 = "3"; }
                if (c4.isChecked() == true){ x4 = "4"; }  if (c5.isChecked() == true){ x5 = "5"; }  if (c6.isChecked() == true){ x6 = "6"; }
                if (c7.isChecked() == true){ x7 = "7"; }  if (c8.isChecked() == true){ x8 = "8"; }

                new insert().execute(un, x1, x2, x3, x4, x5,x6,x7,x8);
            }
        });
    }

    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String uName = strings[0];
            String y1 = strings[1];
            String y2 = strings[2];
            String y3 = strings[3];
            String y4 = strings[4];
            String y5 = strings[5];
            String y6 = strings[6];
            String y7 = strings[7];
            String y8 = strings[8];
            String finalURL = url_Register + "?USERNAME=" + uName
                    + "&Y1=" + y1 + "&Y2=" + y2 + "&Y3=" + y3 + "&Y4=" + y4 + "&Y5=" + y5
                    + "&Y6=" + y6 + "&Y7=" + y7 + "&Y8=" + y8;


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

                        Intent i =  new Intent(q_issues.this, q_issues2.class);
                        startActivity(i);
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
                Toast.makeText(q_issues.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

}
