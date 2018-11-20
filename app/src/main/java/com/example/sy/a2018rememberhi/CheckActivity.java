package com.example.sy.a2018rememberhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
import android.view.View;
import android.widget.Button;
>>>>>>> 1a94a6b969b8b73f93a81cbe0e0e53464f2d3926
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    ListView checkListView;
    checkListViewAdapter adapter;
    ArrayList<String> checkListText;
<<<<<<< HEAD
    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getInstance().getReference("User");
=======
    Button okBtn;

>>>>>>> 1a94a6b969b8b73f93a81cbe0e0e53464f2d3926
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
       // myRef.child("vvvv980").getValue();
        adapter.setArray(checkListText);
        for(int i = 0 ; i < 20; i ++){
            String a = "List" + String.valueOf(i+1);
            myRef.child(a).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String check = dataSnapshot.getValue(String.class);
                    checkListText.add(check);
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }



        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}