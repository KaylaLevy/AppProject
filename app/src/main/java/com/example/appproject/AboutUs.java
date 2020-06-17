package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.sax.Element;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        //FB Link
        TextView linkFb = (TextView) findViewById(R.id.FBLink);
        linkFb.setMovementMethod(LinkMovementMethod.getInstance());
        linkFb.setLinkTextColor(Color.WHITE);
        //Nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.AbtUs);
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
                        startActivity(new Intent(getApplicationContext(),Quiz.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
