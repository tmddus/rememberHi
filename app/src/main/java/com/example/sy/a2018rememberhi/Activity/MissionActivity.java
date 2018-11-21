package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.missionAdapter;

public class MissionActivity extends AppCompatActivity {
    ListView missionList;
    missionAdapter adapter;
    Button addMission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        missionList = findViewById(R.id.mission_listview);
        adapter = new missionAdapter();
        missionList.setAdapter(adapter);

        addMission = findViewById(R.id.addMission);

        adapter.addItem("꽃에 물주기", 0);
        adapter.addItem("엑소 영상보기", 1);

        addMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MissionActivity.this, Mission2Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
