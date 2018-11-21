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
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListAdapter;
import com.example.sy.a2018rememberhi.TodayListItem;
import com.example.sy.a2018rememberhi.UserDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayendList extends AppCompatActivity {
    TextView today;
    Button writeBtn;
    ListView listview;
    TodayListAdapter adapter;
    TodayListItem item; //  item 변수에 DB에 있는 값 담아서 for 문으로 보내깅!!
    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String loginId;
    int Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/diary");

        adapter = new TodayListAdapter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayend_list);
        today = findViewById(R.id.today);
        writeBtn = findViewById(R.id.todayend_write);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date);

        listview = findViewById(R.id.today_listview);

        item = new TodayListItem();

        today.setText(getTime);

        myRef.child("1").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                DiaryDTO diaryDTO = fileSnapshot.getValue(DiaryDTO.class);
                                Num++;
                                adapter.addItem(String.valueOf(Num), diaryDTO.getDiaryDate()+"의 기록");
                            }
                            adapter.notifyDataSetChanged();
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

        listview.setAdapter(adapter);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayendList.this, TodayendActivity.class);
                startActivity(intent);
            }
        });

    }
}
