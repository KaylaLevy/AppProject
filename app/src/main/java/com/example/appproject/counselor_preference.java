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

public class counselor_preference extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_preferences.php";
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselor_preference);

        Button btnNext = (Button)findViewById(R.id.btnComplete_next);

        btnNext .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x1 = "-1", x2 = "-1", x3 = "-1", x4 = "-1";
                CheckBox ck1 = (CheckBox)findViewById(R.id.chkb1); CheckBox ck2 = (CheckBox)findViewById(R.id.chkb2);
                CheckBox ck3 = (CheckBox)findViewById(R.id.chkb3); CheckBox ck4 = (CheckBox)findViewById(R.id.chkb4);

                if (ck1.isChecked() == true){ x1 = "1"; }  if (ck2.isChecked() == true){ x2 = "2"; }  if (ck3.isChecked() == true){ x3 = "3"; }
                if (ck4.isChecked() == true){ x4 = "4"; }

                new insert().execute(un, x1, x2, x3, x4);
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

            String finalURL = url_Register + "?USERNAME=" + uName
                    + "&Y1=" + y1 + "&Y2=" + y2 + "&Y3=" + y3 + "&Y4=" + y4;


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

                    if (result.equalsIgnoreCase("preference entered successfully")) {
                        showToast("preference entered successfully");

                        Intent i =  new Intent(counselor_preference.this, q_completed_message.class);
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
                Toast.makeText(counselor_preference.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
