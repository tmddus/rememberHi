package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TodayendActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    EditText todayEtc;
    Button postBtn;
    String TodayWeather;
    String TodayFeeling;
    Spinner spinnerWeather, spinnerTension;
    ArrayAdapter spinnerWeatherAdap, spinnerTensionAdap;
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

        final ArrayList<String> weather = new ArrayList<>();
        final ArrayList<String> tension = new ArrayList<>();

        spinnerWeather = findViewById(R.id.spinner_weather);
        spinnerTension = findViewById(R.id.spinner_tension);

        spinnerWeatherAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, weather);
        spinnerWeather.setAdapter(spinnerWeatherAdap);

        spinnerTensionAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tension);
        spinnerTension.setAdapter(spinnerTensionAdap);

        final Spinner spinner = (Spinner) findViewById(R.id.age);

        spinnerWeather.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TodayWeather = (String)spinnerWeather.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTension.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TodayFeeling = (String)spinnerTension.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
