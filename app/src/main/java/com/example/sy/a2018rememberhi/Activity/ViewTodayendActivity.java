package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.Adapter.TodayListAdapter;
import com.example.sy.a2018rememberhi.DTO.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewTodayendActivity extends AppCompatActivity {
    TextView script, title; // 게시글 내용 - script
    String inputScript, inputTitle; //DB에서 불러온 값 저장할 변수
    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String loginId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todayend);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/diary");

        inputTitle = getIntent().getExtras().getString("title");
        inputScript = getIntent().getExtras().getString("content");

        title = findViewById(R.id.title);
        script = findViewById(R.id.script);

        title.setText(inputTitle);
        script.setText(inputScript);

    }

    public static class TodayendList extends AppCompatActivity {
        TextView today;
        Button writeBtn;
        ListView listview;
        TodayListAdapter adapter;
        TodayListItem item; //  item 변수에 DB에 있는 값 담아서 for 문으로 보내깅!!
        FirebaseDatabase database  = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        String loginId;
        int Num;
        ArrayList<TodayListItem> array;
        String key;
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
            array = new ArrayList<TodayListItem>();

            listview = findViewById(R.id.today_listview);
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
                                    key = fileSnapshot.getKey();
                                    Num++;
                                    adapter.addItem(diaryDTO.getDiaryDate()+"의 기록", Num);
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

            adapter.notifyDataSetChanged();


            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(TodayendList.this, ViewTodayendActivity.class);

                    TodayListItem item = (TodayListItem) adapter.getItem(position); // 누른 게시글의 item 반환.
                    //  item.getListNum(); item.setListTitle();
                    //  item.getListNum();는 터치한 게시글의 숫자 item.setListTitle();는 터치한 게시글의 타이틀.

                    String title=item.getListTitle(), today_content="";//여기 DB에서 받아와주세요~!
                    Log.e("d",title);
                    intent.putExtra("title", title);
                    intent.putExtra("content", today_content);
                    startActivity(intent);

                }
            });

            writeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(TodayendList.this, TodayendActivity.class);
                    intent.putExtra("num", Num);
                    startActivity(intent);
                }
            });

        }
    }
}
