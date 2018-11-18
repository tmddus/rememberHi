package com.example.sy.a2018rememberhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase database;
    EditText inputId, inputPwd;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = FirebaseDatabase.getInstance();
        inputId = findViewById(R.id.inputId);
        inputPwd = findViewById(R.id.inputPwd);
        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(bntListener);
    }
    View.OnClickListener bntListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            UserDTO userDTO = new UserDTO();
            //userDTO.getUserID()
            //database.getReference().child("user").setValue();
        }
    };
}
