package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardCouncellor extends AppCompatActivity implements ExampleDialog.ExampleDialogListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_councellor);

        Button btnDBC_next = (Button)findViewById(R.id.btnNext);
        btnDBC_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardCouncellor.this, c_gender.class);
                DashBoardCouncellor.this.startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView2 = findViewById(R.id.bottomNav2);

        bottomNavigationView2.setSelectedItemId(R.id.Home);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.Home:
                        return true;

                    case R.id.Chat:
                        startActivity(new Intent(getApplicationContext(),CouncellorMessage.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });


        Button LogOut = (Button) findViewById(R.id.SignOut2);
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
        Intent i = new Intent(DashBoardCouncellor.this, LoginUser.class);
        startActivity(i);
    }

    public void BtnSetEmergency2_onClick(View view) {
        String number = "0800212223";
        Intent intent = new Intent(Intent.  ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
