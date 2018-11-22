package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        registerForContextMenu(missionList);


        missionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {



                return false;
            }
        });

/*
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
        */

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //res폴더의 menu플더안에 xml로 MenuItem추가하기.

        //mainmenu.xml 파일을 java 객체로 인플레이트(inflate)해서 menu객체에 추가
        getMenuInflater().inflate(R.menu.context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);

    }

    //Context 메뉴로 등록한 View(여기서는 ListView)가 클릭되었을 때 자동으로 호출되는 메소드

    public boolean onContextItemSelected(MenuItem item) {

        //AdapterContextMenuInfo

        //AdapterView가 onCreateContextMenu할때의 추가적인 menu 정보를 관리하는 클래스

        //ContextMenu로 등록된 AdapterView(여기서는 Listview)의 선택된 항목에 대한 정보를 관리하는 클래스

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();



        int index= info.position; //AdapterView안에서 ContextMenu를 보여즈는 항목의 위치

        switch( item.getItemId() ){

            case R.id.delete:

                Toast.makeText(this, " 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;



            case R.id.missionCh:

                Toast.makeText(this, ((missionItem)adapter.getItem(index)).getMissionText(), Toast.LENGTH_SHORT).show();

                break;



        }



        return true;

    };


}
