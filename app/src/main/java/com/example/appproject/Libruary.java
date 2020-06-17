package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Libruary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libruary);

        TextView L1 = (TextView) findViewById(R.id.linkLibruary1);
        L1.setMovementMethod(LinkMovementMethod.getInstance());
        L1.setLinkTextColor(Color.BLACK);

        TextView L2 = (TextView) findViewById(R.id.linkLibruary2);
        L2.setMovementMethod(LinkMovementMethod.getInstance());
        L2.setLinkTextColor(Color.BLACK);

        TextView L3 = (TextView) findViewById(R.id.linkLibruary3);
        L3.setMovementMethod(LinkMovementMethod.getInstance());
        L3.setLinkTextColor(Color.BLACK);

        TextView L4 = (TextView) findViewById(R.id.linkLibruary4);
        L4.setMovementMethod(LinkMovementMethod.getInstance());
        L4.setLinkTextColor(Color.BLACK);

        TextView L5 = (TextView) findViewById(R.id.linkLibruary5);
        L5.setMovementMethod(LinkMovementMethod.getInstance());
        L5.setLinkTextColor(Color.BLACK);

        TextView L6 = (TextView) findViewById(R.id.linkLibruary6);
        L6.setMovementMethod(LinkMovementMethod.getInstance());
        L6.setLinkTextColor(Color.BLACK);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.Library);
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
                        return true;
                    case R.id.Chat:
                        startActivity(new Intent(getApplicationContext(),MessagingUser.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Link:
                        startActivity(new Intent(getApplicationContext(),Quiz.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
}
