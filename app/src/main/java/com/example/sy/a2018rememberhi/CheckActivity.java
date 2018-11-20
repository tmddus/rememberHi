package com.example.sy.a2018rememberhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    ListView checkListView;
    checkListViewAdapter adapter;
    ArrayList<String> checkListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        checkListView = findViewById(R.id.checkListView);
        adapter = new checkListViewAdapter();
        checkListText = new ArrayList<String>();

        checkListView.setAdapter(adapter);

        checkListText.add("자기 전에 아무것도 생각이 안난다");
        checkListText.add("집에 가고 싶다");
        checkListText.add("동네 쌈바 템포");
        checkListText.add("동백섬 마 템포");

        adapter.setArray(checkListText);


    }
}