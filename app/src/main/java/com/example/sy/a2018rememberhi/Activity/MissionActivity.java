package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.Activity.Mission2Activity;
import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.MissionDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.Adapter.missionAdapter;
import com.example.sy.a2018rememberhi.TodayListItem;
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
    ArrayList<missionItem> array;
    missionItem item;
    missionAdapter adapter;
    Button addMission;
    int index;
    String key;
    String loginId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/mission");


        array = new ArrayList<missionItem>();
        item = new missionItem();
        missionList = findViewById(R.id.mission_listview);
        adapter = new missionAdapter();

        addMission = findViewById(R.id.addMission);

        addMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MissionActivity.this, Mission2Activity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(missionList);
        missionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }

        });
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adapter.clear();
                            for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                item = new missionItem();
                                int check;
                                MissionDTO missionDTO = fileSnapshot.getValue(MissionDTO.class);
                                if(missionDTO.getMissionComple() == 1){
                                    check=1;
                                }else{ check=0;}
                                adapter.addItem(check, missionDTO.getStringTitle());
                                adapter.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError error) { }
                    });
                }

                return;
            }
            @Override
            public void onCancelled (DatabaseError error){
            }
        });
        missionList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);

    }
    public void deleteData(String key){
        DatabaseReference delete = database.getInstance().getReference("User/"+loginId+"/mission/"+key);
        delete.setValue(null);
        Toast.makeText(this, " 삭제 뀨잉.", Toast.LENGTH_SHORT).show();
    }
    public boolean nodifyData(String key){
        return true;
    }
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = info.position;

        missionItem items = (missionItem) adapter.getItem(index);
        String title = items.getMissionText();

        switch(item.getItemId()){
            case R.id.delete:
                Toast.makeText(this, " 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.missionCh:
                if( ((missionItem)adapter.getItem(index)).getSuccess() == 1){
                    Toast.makeText(this, "이미 성공한 미션입니다", Toast.LENGTH_SHORT).show();
                }else{
                    nodifyData(key);
                    ((missionItem)adapter.getItem(index)).setSuccess(1);
                    Toast.makeText(this, "미션 완료!", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        return true;
    };
    ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    String key = fileSnapshot.getKey();
                    myRef.child(key).getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) { }
        };
    }
