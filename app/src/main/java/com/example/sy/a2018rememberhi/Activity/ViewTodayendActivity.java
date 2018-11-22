package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
