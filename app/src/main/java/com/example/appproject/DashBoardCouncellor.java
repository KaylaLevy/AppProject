package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoardCouncellor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_councellor);

        Button Chat = (Button) findViewById(R.id.Chatnow);
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardCouncellor.this, Chat.class);
                startActivity(i);
            }
        });

        Button Quiz = (Button) findViewById(R.id.Quiz);
        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardCouncellor.this, Quiz2.class);
                startActivity(i);
            }
        });
    }
}
