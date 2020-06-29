package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.appproject.R.layout.activity_q_gender;

public class q_gender extends AppCompatActivity {

    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_quiz.php";

    private String un = LoginUser.getCurrentUsername();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_q_gender);

        Button btnMale = (Button)findViewById(R.id.btnMale);
        Button btnFemale = (Button)findViewById(R.id.btnFemale);

        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                String Male = "M";
                new insert().execute(Male, un);
            }
        });

        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Female = "F";
                new insert().execute(Female, un);
            }
        });
    }

    //
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

                            Intent i = new Intent(q_gender.this, q_age.class);
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
                    Toast.makeText(q_gender.this, text, Toast.LENGTH_LONG).show();
                }
            });
        }
    //
}
