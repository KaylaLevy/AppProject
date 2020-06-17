package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.Link);
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
                        startActivity(new Intent(getApplicationContext(),MessagingUser.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Link:

                        return true;
                }
                return false;
            }
        });
    }
}
