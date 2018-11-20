package com.example.sy.a2018rememberhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getInstance().getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        checkListView = findViewById(R.id.checkListView);
        adapter = new checkListViewAdapter();
        checkListText = new ArrayList<String>();

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




    }
}