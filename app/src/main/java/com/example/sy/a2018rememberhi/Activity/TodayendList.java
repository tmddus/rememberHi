package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListAdapter;
import com.example.sy.a2018rememberhi.TodayListItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayendList extends AppCompatActivity {
    TextView today;
    Button writeBtn;
    ListView listview;
    TodayListAdapter adapter;
    TodayListItem item; //  item 변수에 DB에 있는 값 담아서 for 문으로 보내깅!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new TodayListAdapter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayend_list);
        today = findViewById(R.id.today);
        writeBtn = findViewById(R.id.todayend_write);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date);
        listview = findViewById(R.id.today_listview);

        item = new TodayListItem();

        today.setText(getTime);

        adapter.addItem("1", "엑소 보고싶다");
        adapter.addItem("2", "집에도 가고싶다");

        listview.setAdapter(adapter);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayendList.this, TodayendActivity.class);
                startActivity(intent);
            }
        });

    }
}
