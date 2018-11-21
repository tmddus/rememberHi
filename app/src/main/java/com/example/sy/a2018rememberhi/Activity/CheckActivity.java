package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.HashMap;
import java.util.Map;

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
                }
            });
        adapter.setArray(checkListText);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*SparseBooleanArray checkedItems = checkListView.getCheckedItemPositions();
                for (int i = adapter.getCount() - 1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        Checks++;
                    }
                }*/
                Checks = adapter.getChecks();
                Log.e("checks : " , Checks+"");

                Map<String, Object> taskMap = new HashMap<String, Object>();
                taskMap.put("userCheckList", Checks);

                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                String loginId = auto.getString("inputId",null);

                myRef.child("User").child(loginId).updateChildren(taskMap);

                Intent intent = new Intent(CheckActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}