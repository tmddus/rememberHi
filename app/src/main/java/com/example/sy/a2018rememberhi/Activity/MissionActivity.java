package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.Adapter.missionAdapter;
import com.example.sy.a2018rememberhi.missionItem;

import java.util.ArrayList;

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

        ArrayList<missionItem> arrayList = new ArrayList<missionItem>();

        missionItem item = new missionItem(0, "꽃에 물주기");
        arrayList.add(item);

         item = new missionItem(1, "내 아이돌 영상 보기");
         arrayList.add(item);

        adapter.setArray(arrayList);

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
