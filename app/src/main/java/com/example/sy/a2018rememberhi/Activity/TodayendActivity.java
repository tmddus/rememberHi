package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodayendActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    EditText todayEtc;
    Button postBtn;
    String TodayWeather;
    String TodayFeeling;
    TextView key1, key2, key3, key4, key5;
    int Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayend);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        String loginId = auto.getString("inputId",null);
        myRef = database.getInstance().getReference("User/"+loginId+"/diary");

        postBtn = findViewById(R.id.postBtn);
        todayEtc = findViewById(R.id.todayEtc);
        key1 = findViewById(R.id.key1);
        key2 = findViewById(R.id.key2);
        key3 = findViewById(R.id.key3);
        key4 = findViewById(R.id.key4);
        key5 = findViewById(R.id.key5);

        //코드가 정말 너무 더럽지만 더이상 xml을 수정하고싶지 않앗어..

        key1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayEtc.setText(todayEtc.getText().toString() + "행복");
            }
        });
        key2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayEtc.setText(todayEtc.getText().toString() + "기쁨");
            }
        });
        key3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayEtc.setText(todayEtc.getText().toString() + "즐거움");
            }
        });
        key4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayEtc.setText(todayEtc.getText().toString() + "우울");
            }
        });

        key5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayEtc.setText(todayEtc.getText().toString() + "분노");
            }
        });



        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost();
            }
        });
    }
    private void writeNewPost() {
       DiaryDTO diaryDTO = new DiaryDTO("","","","","","","");
        Num++;
        myRef.child(String.valueOf(Num)).setValue(diaryDTO);

    }
}
