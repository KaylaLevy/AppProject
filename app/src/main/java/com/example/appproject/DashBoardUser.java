package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardUser extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_user);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.AbtUs:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Library:
                        startActivity(new Intent(getApplicationContext(), Libruary.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Chat:
                        startActivity(new Intent(getApplicationContext(), MessagingUser.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Link:
                        startActivity(new Intent(getApplicationContext(), Quiz.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


        Button LogOut = (Button) findViewById(R.id.SignOut);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });

    }
    private void openDialog() {
        ExampleDialog dialog = new ExampleDialog();
        dialog.show(getSupportFragmentManager(),"example dialog");

    }

    @Override
    public void onYesClicked() {
        Intent i = new Intent(DashBoardUser.this, LoginUser.class);
        startActivity(i);
    }

    public void BtnSetEmergency_onClick(View view) {
        String number = "0800212223";
        Intent intent = new Intent(Intent.  ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }


}
