package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class q_relationship extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/user_relationship.php";
    private String un = LoginUser.getCurrentUsername();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_relationship);



        Button btnNext = (Button)findViewById(R.id.btn_relationship_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RadioGroup rdg = (RadioGroup)findViewById(R.id.rdgRelationship);
                int radioID = rdg.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioID);
                String selection = radioButton.getText().toString();
                //String Male = "Single";
                new insert().execute(selection,un);
            }
        });


    }

    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String selection = strings[0];
            String uName = strings[1];
            String finalURL = url_Register + "?RELATIONSHIP=" + selection
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

                    if (result.equalsIgnoreCase("relationship entered successfully")) {
                        showToast("relationship entered successfully");

                        Intent i =  new Intent(q_relationship.this, q_issues.class);
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
                Toast.makeText(q_relationship.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
