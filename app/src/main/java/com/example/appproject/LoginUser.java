package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginUser extends AppCompatActivity {
    final String url_loginUser = "https://lamp.ms.wits.ac.za/home/s2141916/LoginsUser.php";
    final String url_loginCouncellor = "https://lamp.ms.wits.ac.za/home/s2141916/LoginsCouncellor.php";
    RadioGroup radioGroup;
    RadioButton radioButton;

    private static String currentUsername;
    public static String getCurrentUsername(){
        return  currentUsername;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        final TextView RegisterLink = (TextView) findViewById(R.id.RegisterLink);
        final TextView RegisterLink2 = (TextView) findViewById(R.id.RegisterLink2);

        RegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginUser.this, RegisterUser.class);
                LoginUser.this.startActivity(i);
            }
        });

        RegisterLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginUser.this, RegisterCouncellor.class);
                LoginUser.this.startActivity(t);
            }
        });

        final EditText UsernameLogin = (EditText) findViewById(R.id.Login);
        final EditText PasswordLogin = (EditText) findViewById(R.id.Password);


        Button Login = (Button) findViewById(R.id.blogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {


                String Username = UsernameLogin.getText().toString();
                String Password = PasswordLogin.getText().toString();
            //    final EditText Type = (EditText) findViewById(R.id.SelectRole);
              //  final String UserType = Type.getText().toString();

                RadioButton User = findViewById(R.id.RadUser);
                RadioButton Counselor = findViewById(R.id.RadCounselor);


                if (User.isChecked() )
                {
                    new loginUser().execute(Username, Password);
                }
                else
                    if(Counselor.isChecked())
                {
                    new  loginCouncellor().execute(Username, Password);
                }

                    else
                    if(!Counselor.isChecked() && !User.isChecked())
                    {
                        showToast("Please select a role");
                    }
            }
        });
    }

     /* public void checkButton(View v){

        Toast.makeText(this,"Selected Radio Button" + radioButton.getText(),Toast.LENGTH_SHORT).show() ;

      }*/

    public class loginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Username = strings[0];
            String Password = strings[1];


            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("USER_USERNAME", Username)
                    .add("USER_PASSWORD", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_loginUser)
                    .post(formBody)
                    .build();
            Response response = null;
            try {

                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if (result.equalsIgnoreCase("login")) {
                        showToast("Login Successful");
                        Intent i = new Intent(LoginUser.this, DashBoardUser.class);
                        startActivity(i);
                        finish();
                        currentUsername = Username;
                    } else
                    {
                       showToast("Username or Password mismatched!");

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
                Toast.makeText(LoginUser.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
    public class loginCouncellor extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Username2 = strings[0];
            String Password2 = strings[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("COUNCELLOR_USERNAME", Username2)
                    .add("COUNCELLOR_PASSWORD", Password2)
                    .build();

            Request request = new Request.Builder()
                    .url(url_loginCouncellor)
                    .post(formBody)
                    .build();

            try {

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if (result.equalsIgnoreCase("login")) {
                        currentUsername = Username2;
                        showToast("Login Successful");
                        Intent i = new Intent(LoginUser.this, DashBoardCouncellor.class);
                        LoginUser.this.startActivity(i);

                    } else {

                        showToast("Username or Password mismatched!");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}
