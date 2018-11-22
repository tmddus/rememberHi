package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.MissionDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.missionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MissionActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView missionList;
    missionAdapter adapter;
    Button addMission;
    int Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        String loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/mission");

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

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                MissionDTO missionDTO = fileSnapshot.getValue(MissionDTO.class);
                                Num++;
                                adapter.addItem(missionDTO.getStringTitle(), missionDTO.getMissionComple());
                                adapter.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }
                return;
            }
            @Override
            public void onCancelled (DatabaseError error){
            }
        });

    }
}
