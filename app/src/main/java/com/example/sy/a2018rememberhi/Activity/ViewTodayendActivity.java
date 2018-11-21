package com.example.sy.a2018rememberhi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;

public class ViewTodayendActivity extends AppCompatActivity {
    TextView script, title; // 게시글 내용 - script
    String inputScript="", inputTitle=""; //DB에서 불러온 값 저장할 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todayend);
        title = findViewById(R.id.title);
        script = findViewById(R.id.script);

        title.setText(inputTitle);
        script.setText(inputScript);


    }
}
