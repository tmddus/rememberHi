package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
