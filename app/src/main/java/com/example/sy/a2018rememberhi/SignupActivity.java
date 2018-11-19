package com.example.sy.a2018rememberhi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference muserRef = mRootRef.child("user");

    EditText idtxt, pwdtxt, pwdtxt2, name, phone2, phone3;
    ArrayAdapter spinnerAdapter, phoneSpinnerAdapter;
    Spinner spinner_age , spinner_phonenum;

    int age_result, phonenum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database = FirebaseDatabase.getInstance();
        idtxt = findViewById(R.id.idtxt);
        pwdtxt = findViewById(R.id.pwdtxt);
        pwdtxt2 = findViewById(R.id.pwdtxtConfirm);
        name = findViewById(R.id.name);
        spinner_age = findViewById(R.id.age);
        spinner_phonenum = findViewById(R.id.phonenum_spinner);
        phone2 = findViewById(R.id.phone2);
        phone3 = findViewById(R.id.phone3);
        final ArrayList<Integer> age = new ArrayList<>();
        final ArrayList<String> phone = new ArrayList<>();

        for(int i = 1; i < 100; i++){
            age.add(i);
        }

        phone.add("010");
        phone.add("011");
        phone.add("017");


        phoneSpinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, phone);
        spinner_phonenum.setAdapter(phoneSpinnerAdapter);

        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, age);
        spinner_age.setAdapter(spinnerAdapter);

        final Spinner spinner = (Spinner)findViewById(R.id.age);
        String age_str = spinner.getSelectedItem().toString();

       spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               age_result = (int)spinner_age.getItemAtPosition(position);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

       spinner_phonenum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               phonenum = (int)spinner_phonenum.getItemAtPosition(position);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


    }
    View.OnClickListener bntListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            UserDTO userDTO = new UserDTO(name.getText().toString(),idtxt.getText().toString(),pwdtxt2.getText().toString()," "," ",11,21, 2);
            database.getReference().child("user").setValue(userDTO);
        }
    };
    protected void onStart(){
        super.onStart();
        muserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
