package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Quiz2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        BottomNavigationView bottomNavigationView2 = findViewById(R.id.bottomNav2);

        bottomNavigationView2.setSelectedItemId(R.id.Connect);
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
                        startActivity(new Intent(getApplicationContext(),CouncellorMessage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Connect:

                        return true;
                }
                return false;
            }
        });
    }
}
