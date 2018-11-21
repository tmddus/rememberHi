package com.example.sy.a2018rememberhi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sy.a2018rememberhi.R;

public class TodayendActivity extends AppCompatActivity {

    EditText todayEtc;
    Button postBtn;
    String TodayWeather;
    String TodayFeeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayend);
        postBtn = findViewById(R.id.postBtn);
        todayEtc = findViewById(R.id.todayEtc);




        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todayEndTexts="";

                todayEndTexts += TodayWeather;
                todayEndTexts += "\n";
                todayEndTexts += TodayFeeling;
                todayEndTexts += "\n";
                todayEndTexts += todayEtc.getText().toString();

                //쿼리문
            }
        });
    }
}
