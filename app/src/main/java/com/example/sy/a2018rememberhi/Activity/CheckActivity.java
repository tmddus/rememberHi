package com.example.sy.a2018rememberhi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.checkListViewAdapter;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    ListView checkListView;
    checkListViewAdapter adapter;
    ArrayList<String> checkListText;

    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getInstance().getReference();
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
        myRef.child("CheckList").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                        String str = fileSnapshot.getValue(String.class);
                        checkListText.add(str);
                    }
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        adapter.setArray(checkListText);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Checks = adapter.getChecks();
                Log.e("checks : " , Checks+"");

                Intent intent = new Intent(CheckActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}