package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListAdapter;

import java.sql.Time;

public class Mission2Activity extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
    Button okBtn;
    TextView Mission;
    TimePicker alarmTime;
    int nHour, nminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission2);
        okBtn = findViewById(R.id.mission2_ok);
        Mission = findViewById(R.id.todayCom);

        alarmTime.setOnTimeChangedListener(this);



        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), nHour+"시 " + nminute+"분에 알림 설정 되었습니다", Toast.LENGTH_SHORT);

                Intent intent = new Intent(Mission2Activity.this, MissionActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay,  int minute){
        nHour = hourOfDay;
        nminute = minute;
    }
}
