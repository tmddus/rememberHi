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
import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.MissionDTO;
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
    DiaryDTO diaryDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todayend);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        Log.e("id",loginId);
        inputTitle = getIntent().getExtras().getString("title");

        myRef = database.getInstance().getReference("User/"+loginId+"/diary");

        title = findViewById(R.id.title);
        script = findViewById(R.id.script);

        myRef.child(inputTitle).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                diaryDTO = dataSnapshot.getValue(DiaryDTO.class);
                inputScript = "오늘의 날씨는 "+ diaryDTO.getDiaryWeather() + "이고, 오늘의 기분은 " + diaryDTO.getDiaryFeel()+ "입니다."
                                +" 또한 오늘의 단어는 "+ diaryDTO.getDiaryKey1() + ", "+ diaryDTO.getDiaryKey2() + ", "+ diaryDTO.getDiaryKey3() + "입니다.";
                if(diaryDTO.getDiaryContent() != null){
                    inputScript += " 오늘의 한마디는 "+ diaryDTO.getDiaryContent()+"입니다.";
                }
                script.setText(inputScript);
                Log.e("sfssfs",diaryDTO.getDiaryWeather());
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        title.setText(inputTitle);

    }
}
