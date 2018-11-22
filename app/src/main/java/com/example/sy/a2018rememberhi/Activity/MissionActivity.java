package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sy.a2018rememberhi.MissionDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.Adapter.missionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.sy.a2018rememberhi.Adapter.missionAdapter;
import com.example.sy.a2018rememberhi.missionItem;
import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView missionList;
    missionAdapter adapter;
    Button addMission;
    missionItem item;
    int Num;
    ArrayList<missionItem> arrayList = new ArrayList<missionItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        String loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/mission");

        missionList = findViewById(R.id.mission_listview);
        adapter = new missionAdapter();

        addMission = findViewById(R.id.addMission);

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
                                Num++;
                                MissionDTO missionDTO = fileSnapshot.getValue(MissionDTO.class);
                                Log.e("log~~~~~~~~~",String.valueOf(Num));
                                if(missionDTO.getMissionComple() == 1){
                                    item = new missionItem(1, missionDTO.getStringTitle());
                                }else{
                                    item = new missionItem(0, missionDTO.getStringTitle());
                                }
                                arrayList.add(item);
                                //adapter.notifyDataSetChanged();
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
        adapter.setArray(arrayList);

        adapter.notifyDataSetChanged();
        missionList.setAdapter(adapter);



    }
}
