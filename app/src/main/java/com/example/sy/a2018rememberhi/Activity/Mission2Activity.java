package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sy.a2018rememberhi.DTO.MissionDTO;
import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mission2Activity extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    Button okBtn;
    EditText Mission;
    TimePicker alarmTime;
    int nHour, nminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        String loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/mission");

        setContentView(R.layout.activity_mission2);
        okBtn = findViewById(R.id.mission2_ok);
        Mission = findViewById(R.id.todayCom);
        alarmTime = findViewById(R.id.timePicker);


        alarmTime.setOnTimeChangedListener(this);



        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost();
                Toast.makeText(getApplicationContext(), nHour+"시 " + nminute+"분에 알림 설정 되었습니다", Toast.LENGTH_SHORT);
                Log.e("nHour",String.valueOf(nHour));
                /*Intent intent = new Intent(Mission2Activity.this, MissionActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

    }
    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay,  int minute){
        nHour = hourOfDay;
        nminute = minute;
    }
    private void writeNewPost() {
        MissionDTO missionDTO = new MissionDTO(Mission.getText().toString(),String.valueOf(nHour)+ String.valueOf(nminute),0);
        myRef.push().setValue(missionDTO);
    }
}
