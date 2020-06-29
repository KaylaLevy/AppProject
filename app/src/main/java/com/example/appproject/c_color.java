package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class c_color extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/c_color.php";////////////////////////////
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_color);

        Button btnYes_next = (Button)findViewById(R.id.btnNext_yes);
        Button btnNo_next = (Button)findViewById(R.id.btnNext_no);

        btnYes_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String y = "Y";
                new insert().execute(y, un);
            }
        });

        btnNo_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "N";
                new insert().execute(n, un);
            }
        });
    }

    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String ans = strings[0];
            String uName = strings[1];
            String finalURL = url_Register + "?GENDER=" + ans
                    + "&USERNAME=" + uName;

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

                    if (result.equalsIgnoreCase("Color entered successfully")) {
                        showToast("Color entered successfully");
                        //showToast(un);
                        Intent i = new Intent(c_color.this, c_issues.class);
                        c_color.this.startActivity(i);
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
                Toast.makeText(c_color.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
