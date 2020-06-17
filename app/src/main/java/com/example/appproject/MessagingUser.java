package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessagingUser extends AppCompatActivity {
    final String url_MessageUser = "https://lamp.ms.wits.ac.za/home/s2141916/MessageUser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_user);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.Chat);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),DashBoardUser.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.AbtUs:
                        startActivity(new Intent(getApplicationContext(),AboutUs.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Library:
                        startActivity(new Intent(getApplicationContext(),Libruary.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Chat:
                        return true;
                    case R.id.Link:
                        startActivity(new Intent(getApplicationContext(),Quiz.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
       // should be a singleton
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2141916/MessageResponse.php")
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
                MessagingUser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {

                            JSONArray all = new JSONArray(responseData);
                            for (int i = 0; i < all.length(); i++)
                            {
                                JSONObject item = all.getJSONObject(i);
                                String Message = item.getString("MESSAGE_USER");
                                LinearLayout l = (LinearLayout) findViewById(R.id.mainView);
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
        // should be a singleton
        OkHttpClient client2 = new OkHttpClient();

        Request request2 = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2141916/MessageResponseCouncellor.php")
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
                MessagingUser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  new Timer().scheduleAtFixedRate(task, after, interval);

                        try {

                            JSONArray all = new JSONArray(responseData);
                            for (int i = 0; i < all.length(); i++)
                            {
                                JSONObject item = all.getJSONObject(i);
                                String Message = item.getString("MESSAGE_COUNCELLOR");
                                LinearLayout l = (LinearLayout) findViewById(R.id.mainView);
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

        final EditText MessageOfUser = (EditText) findViewById(R.id.MessageUser);
        ImageButton send = (ImageButton) findViewById(R.id.UserSend1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = MessageOfUser.getText().toString();
                new MessageUser().execute(message);
                MessageOfUser.getText().clear();
            }
        });


    }


    public class MessageUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Message = strings[0];

            String finalURL = url_MessageUser + "?MESSAGE_USER=" + Message ;

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
                Toast.makeText(MessagingUser.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }}


