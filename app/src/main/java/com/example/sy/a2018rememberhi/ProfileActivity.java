package com.example.sy.a2018rememberhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button missionBtn = findViewById(R.id.mission_btn);
        Button todayBtn = findViewById(R.id.how_today);
        Button preventBtn = findViewById(R.id.prevent);
        Button homeCommuBtn = findViewById(R.id.homecommu);

        missionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MissionActivity.class);
                startActivity(intent);
            }
        });

        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,TodayendActivity.class);
                startActivity(intent);
            }
        });

        homeCommuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MessageActivity.class);
                startActivity(intent);
            }
        });

    }
}
