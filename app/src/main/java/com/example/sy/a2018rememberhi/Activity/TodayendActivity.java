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
import android.widget.Toast;

import com.example.sy.a2018rememberhi.DiaryDTO;
import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static int Today_Num = 0;

    String key[] = new String[3];
    String getTime;
    int i;

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

        key1.setOnClickListener(mClickListener);
        key2.setOnClickListener(mClickListener);
        key3.setOnClickListener(mClickListener);
        key4.setOnClickListener(mClickListener);
        key5.setOnClickListener(mClickListener);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        getTime = sdf.format(date);
        //승연아 병합 충돌 일어나면 swith를 살려줘 코드 가독성 보기 좋게 고쳤ㄷ어 사실 뭐가 더 가독성 있는지는 모르겠지만,,,,,,,,,,,,,, 넘 힘들다.

        final ArrayList<String> weather = new ArrayList<>();
        final ArrayList<String> tension = new ArrayList<>();

        spinnerWeather = findViewById(R.id.spinner_weather);
        spinnerTension = findViewById(R.id.spinner_tension);

        spinnerWeatherAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, weather);
        spinnerWeatherAdap.add("화창");
        spinnerWeatherAdap.add("흐림");
        spinnerWeatherAdap.add("구름");
        spinnerWeatherAdap.add("비");
        spinnerWeatherAdap.add("눈");
        spinnerWeather.setAdapter(spinnerWeatherAdap);

        spinnerTensionAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tension);
        spinnerTensionAdap.add("행복");
        spinnerTensionAdap.add("기쁨");
        spinnerTensionAdap.add("화남");
        spinnerTensionAdap.add("슬픔");
        spinnerTensionAdap.add("우울");
        spinnerTension.setAdapter(spinnerTensionAdap);

        final Spinner spinner_w = (Spinner) findViewById(R.id.spinner_weather);
        final Spinner spinner_t = (Spinner) findViewById(R.id.spinner_tension);

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

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost();
                finish();
            }
        });
    }
    private void writeNewPost() {
       DiaryDTO diaryDTO = new DiaryDTO(todayEtc.getText().toString(),getTime,TodayFeeling,key[0],key[1],key[2],TodayWeather);
        myRef.push().setValue(diaryDTO);
    }
    TextView.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (i >= 3 ){
                Toast.makeText(getApplicationContext(), "키워드는 3개만 선택 하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
                switch (v.getId()){
                    case R.id.key1:
                        key[i] = "행복"; i++; break;
                    case R.id.key2:
                        key[i] = "기쁨"; i++; break;
                    case R.id.key3:
                        key[i] = "즐거움"; i++; break;
                    case R.id.key4:
                        key[i] = "우울함"; i++;  break;
                    case R.id.key5:
                        key[i] = "화남"; i++;  break;
                }

            }
    };
}
