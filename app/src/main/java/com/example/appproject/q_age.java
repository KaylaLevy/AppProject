package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class q_age extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_age.php";

    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_age);

        final Spinner spnAge;
        spnAge = (Spinner)findViewById(R.id.spnAge);
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i = 13; i <= 99; i++){
            list.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, list);

        spnAge.setAdapter(adapter);

        Button btnNext = (Button)findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = spnAge.getSelectedItem().toString();
                new insert().execute(age, un); //spnAge.toString()
            }
        });
    }
    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String age = strings[0];
            String uName = strings[1];
            String finalURL = url_Register + "?AGE=" + age
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

                    if (result.equalsIgnoreCase("Age entered successfully")) {
                        showToast("Age entered successfully");

                        Intent i = new Intent(q_age.this, q_language.class);
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
                Toast.makeText(q_age.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
