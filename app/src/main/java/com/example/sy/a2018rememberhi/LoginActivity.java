package com.example.sy.a2018rememberhi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
   EditText inputId, inputPwd;
   Button loginbtn;

    FirebaseDatabase database  = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getInstance().getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       inputId = findViewById(R.id.inputId);
       inputPwd = findViewById(R.id.inputPwd);
       loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(btnlistener);
    }
   View.OnClickListener btnlistener = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           myRef.child(inputId.getText().toString()).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   UserDTO userDTO = dataSnapshot.getValue(UserDTO.class);
                   Log.e("aa" , userDTO.getUserPWD());
                   if(!inputPwd.getText().toString().equals(userDTO.getUserPWD())){
                       Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                       finish();
                   }
                   Toast.makeText(getApplicationContext(), "로그인 성공 ", Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(LoginActivity.this, CheckActivity.class);
                   startActivity(intent);
                   finish();
               }
               @Override
               public void onCancelled(DatabaseError error) {
                   Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                   finish();
               }
           });
       }
   };



}
