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

public class c_gender extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/c_GenderQuiz.php";
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_gender);

        Button btnMale_next = (Button)findViewById(R.id.btnNextMale);
        Button btnFemale = (Button)findViewById(R.id.btnNextFemale);

        btnMale_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Male = "M";
                showToast(Male);
                new insert().execute(Male, un);
                //showToast(un);
            }
        });

        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Female = "F";
               // showToast(un);
                new insert().execute(Female, un);
            }
        });
    }

    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String gender = strings[0];
            String uName = strings[1];
            String finalURL = url_Register + "?GENDER=" + gender
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

                    if (result.equalsIgnoreCase("Gender entered successfully")) {
                        showToast("Gender entered successfully");
                        //showToast(un);
                        Intent i = new Intent(c_gender.this, c_issues.class);
                        c_gender.this.startActivity(i);
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
                Toast.makeText(c_gender.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
