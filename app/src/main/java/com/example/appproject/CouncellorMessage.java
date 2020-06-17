package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CouncellorMessage extends AppCompatActivity {

    final String url_MessageCouncellor = "https://lamp.ms.wits.ac.za/home/s2141916/MessageCouncellor.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councellor_message);


        BottomNavigationView bottomNavigationView2 = findViewById(R.id.bottomNav2);

        bottomNavigationView2.setSelectedItemId(R.id.Chat);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),DashBoardCouncellor.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Chat:

                        return true;

                    case R.id.Connect:
                        startActivity(new Intent(getApplicationContext(),Quiz2.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // should be a singleton
        OkHttpClient client2 = new OkHttpClient();

        Request request2 = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2141916/MessageResponse.php")
                .build();

        client2.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse( Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {

                    throw new IOException("Unexpected code" + response);
                }

                final String responseData = response.body().string();
                CouncellorMessage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  new Timer().scheduleAtFixedRate(task, after, interval);

                        try {

                            JSONArray all = new JSONArray(responseData);
                            for (int i = 0; i < all.length(); i++)
                            {
                                JSONObject item = all.getJSONObject(i);
                                String Message = item.getString("MESSAGE_USER");
                                LinearLayout l = (LinearLayout) findViewById(R.id.mainView2);
                                TextView t = new TextView(getApplicationContext());
                                t.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                                t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                t.setPadding(20,20,20,20);
                                t.setBackgroundResource(R.drawable.their_message);
                                t.setText(Message);
                                l.addView(t);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2141916/MessageResponseCouncellor.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse( Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {

                    throw new IOException("Unexpected code" + response);
                }

                final String responseData = response.body().string();
                CouncellorMessage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {

                            JSONArray all = new JSONArray(responseData);
                            for (int i = 0; i < all.length(); i++)
                            {
                                JSONObject item = all.getJSONObject(i);
                                String Message = item.getString("MESSAGE_COUNCELLOR");
                                LinearLayout l = (LinearLayout) findViewById(R.id.mainView2);
                                TextView t = new TextView(getApplicationContext());

                                t.setBackgroundResource(R.drawable.my_message);
                                // t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.gravity = Gravity.RIGHT;
                                t.setLayoutParams(params);
                                t.setPadding(20,20,20,20);
                                t.setText(Message);
                                l.addView(t);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });



        final EditText MessageOfCouncellor = (EditText) findViewById(R.id.MessageCouncellor);
        ImageButton send = (ImageButton) findViewById(R.id.CouncellorSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = MessageOfCouncellor.getText().toString();
                new MessageCouncellor().execute(message);
                MessageOfCouncellor.getText().clear();
            }
        });
    }

    public class MessageCouncellor extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Message = strings[0];

            String finalURL = url_MessageCouncellor + "?MESSAGE_COUNCELLOR=" + Message ;

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

                    if (result.equalsIgnoreCase("Messsage Sent sucessfully")) {
                        showToast("Message successful");
                    }

                } else {
                    showToast("oops! please try again!");
                }

            } catch (IOException ex) {
                ex.printStackTrace();

            }

            return null;
        }
    }
    public void showToast(final String text)
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CouncellorMessage.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
    }

