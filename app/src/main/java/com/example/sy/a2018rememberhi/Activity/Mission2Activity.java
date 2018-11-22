package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.R;

public class Mission2Activity extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
    Button okBtn;
    EditText Mission;
    TimePicker alarmTime;
    int nHour, nminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission2);
        okBtn = findViewById(R.id.mission2_ok);
        Mission = findViewById(R.id.todayCom);
        alarmTime = findViewById(R.id.timePicker);

        alarmTime.setOnTimeChangedListener(this);



        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MissionTxt = Mission.getText().toString();
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
