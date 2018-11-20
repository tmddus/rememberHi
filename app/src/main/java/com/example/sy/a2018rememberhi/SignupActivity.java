package com.example.sy.a2018rememberhi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    EditText idtxt, pwdtxt, pwdtxt2, name, Userphone2, Userphone3;
    ArrayAdapter spinnerAdapter, phoneSpinnerAdapter_user;
    Spinner spinner_age, spinner_phonenum_user;
    String gender_str;
    int age_result, gender;
    String UserPhoneNum = "";


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
        spinner_phonenum_user = findViewById(R.id.phonenum_spinner);
        Userphone2 = findViewById(R.id.phone2);
        Userphone3 = findViewById(R.id.phone3);

        final ArrayList<Integer> age = new ArrayList<>();
        final ArrayList<String> phone = new ArrayList<>();

        RadioGroup genderRadio = findViewById(R.id.rg_gender);

        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender_str = ((RadioButton) findViewById(checkedId)).getText().toString();
                if (gender_str.equals("여성")) {
                    gender = 1;
                }//임시
                else {
                    gender = 0;
                }
            }
        });
        for (int i = 1; i < 100; i++) {
            age.add(i);
        }
        phone.add("010");
        phone.add("011");
        phone.add("017");


        phoneSpinnerAdapter_user = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, phone);
        spinner_phonenum_user.setAdapter(phoneSpinnerAdapter_user);

        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, age);
        spinner_age.setAdapter(spinnerAdapter);


        final Spinner spinner = (Spinner) findViewById(R.id.age);

        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age_result = (int) spinner_age.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_phonenum_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               UserPhoneNum = spinner_phonenum_user.getItemAtPosition(position).toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


    }
    View.OnClickListener bntListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            UserPhoneNum += Userphone2.getText().toString();
            UserPhoneNum += Userphone3.getText().toString();

            //UserDTO userDTO = new UserDTO(name.getText().toString(),idtxt.getText().toString(),pwdtxt2.getText().toString()," ",UserPhoneNum,gender,age_result, 2);
            //database.getReference().child("user").setValue(userDTO);
        }
    };

}
