package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.checkListViewAdapter;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    ListView checkListView;
    checkListViewAdapter adapter;
    ArrayList<String> checkListText;
    Button okBtn;
    int Checks=0; // 체크된 개수 저장하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        checkListView = findViewById(R.id.checkListView);
        adapter = new checkListViewAdapter();
        checkListText = new ArrayList<String>();
        okBtn = findViewById(R.id.okbtn);

        checkListView.setAdapter(adapter);

        checkListText.add("자기 전에 아무것도 생각이 안난다");
        checkListText.add("집에 가고 싶다");
        checkListText.add("동네 쌈바 템포");
        checkListText.add("동백섬 마 템포");

        adapter.setArray(checkListText);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItems = checkListView.getCheckedItemPositions();
                for (int i = adapter.getCount() - 1; i >= 0; i--) {
                    if (checkedItems.get(i))
                        Checks++;
                }

                Intent intent = new Intent(CheckActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}