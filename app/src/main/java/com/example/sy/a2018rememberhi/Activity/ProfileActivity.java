package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.UserDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView textname,textage;
    Button missionBtn, todayBtn, preventBtn, homeCommuBtn;
    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    String loginId;
    ProgressBar missionProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User");
        int valueResult=0;
        float checkValue=0, ValueCnt=0;

        textname = findViewById(R.id.profile_name);
        textage = findViewById(R.id.profile_age);
        missionBtn = findViewById(R.id.mission_btn);
        todayBtn = findViewById(R.id.how_today);
        preventBtn = findViewById(R.id.prevent);
        homeCommuBtn = findViewById(R.id.homecommu);
        missionProgress = findViewById(R.id.missionBar);

        checkValue = 20; ValueCnt = 100;
        missionProgress = findViewById(R.id.missionBar);
        valueResult = (int)(checkValue/ValueCnt *100);
        missionProgress.setProgress(valueResult);

        myRef.child(loginId).child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDTO userDTO = dataSnapshot.getValue(UserDTO.class);
                Log.e("유저 정보",userDTO.getUserName() + userDTO.getUserBirth());
                textname.setText(userDTO.getUserName());
                textage.setText(String.valueOf(userDTO.getUserBirth()));
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        missionBtn.setOnClickListener(mClickListener);
        todayBtn.setOnClickListener(mClickListener);
        preventBtn.setOnClickListener(mClickListener);
        homeCommuBtn.setOnClickListener(mClickListener);
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.mission_btn:
                    intent = new Intent(ProfileActivity.this,MissionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.how_today:
                    intent = new Intent(ProfileActivity.this,TodayendList.class);
                    startActivity(intent);
                    break;
                case R.id.homecommu:
                    intent = new Intent(ProfileActivity.this,MessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.prevent:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nid.or.kr/info/diction_list1.aspx?gubun=0101")));
            }
        }
    };
}
