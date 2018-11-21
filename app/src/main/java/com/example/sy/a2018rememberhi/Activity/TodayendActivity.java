package com.example.sy.a2018rememberhi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sy.a2018rememberhi.R;

import java.util.ArrayList;

public class TodayendActivity extends AppCompatActivity {

    EditText todayEtc;
    Button postBtn;
    String TodayWeather;
    String TodayFeeling;
    Spinner spinnerWeather, spinnerTension;
    ArrayAdapter spinnerWeatherAdap, spinnerTensionAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayend);
        postBtn = findViewById(R.id.postBtn);
        todayEtc = findViewById(R.id.todayEtc);

        final ArrayList<String> weather = new ArrayList<>();
        final ArrayList<String> tension = new ArrayList<>();

        spinnerWeather = findViewById(R.id.spinner_weather);
        spinnerTension = findViewById(R.id.spinner_tension);

        spinnerWeatherAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, weather);
        spinnerWeather.setAdapter(spinnerWeatherAdap);

        spinnerTensionAdap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tension);
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
