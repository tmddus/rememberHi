package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.DTO.DiaryDTO;
import com.example.sy.a2018rememberhi.DTO.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.Adapter.TodayListAdapter;
import com.example.sy.a2018rememberhi.TodayListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
<<<<<<< HEAD
    // ArrayList<TodayListItem> array;
    ArrayList<TodayListItem> array = new ArrayList<TodayListItem>();
=======
    ArrayList<TodayListItem> array;

>>>>>>> 715e16abe638e8f651379ef4b03c7a60c1121add
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
<<<<<<< HEAD
                //array = new ArrayList<TodayListItem>();
                listview = findViewById(R.id.today_listview);
        adapter.notifyDataSetChanged();
=======
        array = new ArrayList<TodayListItem>();
>>>>>>> 715e16abe638e8f651379ef4b03c7a60c1121add

        listview.setAdapter(adapter);
        item = new TodayListItem();

        today.setText(getTime);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                DiaryDTO diaryDTO = fileSnapshot.getValue(DiaryDTO.class);
<<<<<<< HEAD
                                Log.e("log~~~~~~~~~",String.valueOf(Num));
                                item = new TodayListItem();
                                item.setListTitle(diaryDTO.getDiaryDate()+"의 기록");
                                item.setListNum(String.valueOf(Num));
                                Log.e("asd",item.getListNum());
                                Log.e("asd_1",item.getListTitle());
                                array.add(item);
=======
                                Num++;
                                adapter.addItem(diaryDTO.getDiaryDate()+"의 기록", Num);
                                adapter.notifyDataSetChanged();
>>>>>>> 715e16abe638e8f651379ef4b03c7a60c1121add
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

        adapter.notifyDataSetChanged();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TodayendList.this, ViewTodayendActivity.class);

                TodayListItem item = (TodayListItem) adapter.getItem(position); // 누른 게시글의 item 반환.
                //  item.getListNum(); item.setListTitle();
                //  item.getListNum();는 터치한 게시글의 숫자 item.setListTitle();는 터치한 게시글의 타이틀.

                String title="", today_content="";//여기 DB에서 받아와주세요~!
                intent.putExtra("title", title);
                intent.putExtra("content", today_content);
                startActivity(intent);

            }
        });

<<<<<<< HEAD
        listview.setAdapter(adapter);
        adapter.setArray(array);
=======
>>>>>>> 715e16abe638e8f651379ef4b03c7a60c1121add
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayendList.this, TodayendActivity.class);
                startActivity(intent);
            }
        });

    }
}