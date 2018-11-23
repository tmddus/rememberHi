package com.example.sy.a2018rememberhi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.DTO.UserDTO;
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
            if(inputId.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                return;
            }
               myRef.child(inputId.getText().toString()).child("info").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   if (dataSnapshot.exists()) {
                       myRef.child(inputId.getText().toString()).addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               UserDTO userDTO = dataSnapshot.getValue(UserDTO.class);
                               if(!inputPwd.getText().toString().equals(userDTO.getUserPWD())){
                                   Toast.makeText(getApplicationContext(), "로그인 실패 ", Toast.LENGTH_SHORT).show();
                                   finish();
                               }
                               Toast.makeText(getApplicationContext(), "로그인 성공 ", Toast.LENGTH_SHORT).show();
                               SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                               //아이디가 '부르곰'이고 비밀번호가 '네이버'일 경우 SharedPreferences.Editor를 통해
                               //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                               SharedPreferences.Editor autoLogin = auto.edit();
                               autoLogin.putString("inputId", inputId.getText().toString());
                               autoLogin.putString("inputPwd", inputPwd.getText().toString());
                               //꼭 commit()을 해줘야 값이 저장됩니다 ㅎㅎ
                               autoLogin.commit();

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
                   else{
                       Toast.makeText(getApplicationContext(), "아이디를 틀리셨습니다 ", Toast.LENGTH_SHORT).show();
                       inputId.setText("");
                       inputPwd.setText("");
                       return;
                   }
               }
               @Override
               public void onCancelled(DatabaseError firebaseError) {
               }
           });

       }
   };


}
