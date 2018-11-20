package com.example.sy.a2018rememberhi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    EditText idtxt, pwdtxt, pwdtxt2, name, Userphone2, Userphone3, Childphone2, Childphone3;
    ArrayAdapter spinnerAdapter, phoneSpinnerAdapter_user;
    Spinner spinner_age, spinner_phonenum_user, spinner_phonenum_child;
    String gender_str;
    int age_result, gender;
<<<<<<< HEAD
    String UserPhoneNum = "";
    Button SignupOk;
=======
    String UserPhoneNum = "", ChildPhoneNum = "";
    Button signupBtnOK;
>>>>>>> 062b48c9f2bf0a3bad333aae7dff927c765579df

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        idtxt = findViewById(R.id.idtxt);
        pwdtxt = findViewById(R.id.pwdtxt);
        pwdtxt2 = findViewById(R.id.pwdtxtConfirm);
        name = findViewById(R.id.name);
        spinner_age = findViewById(R.id.age);
        spinner_phonenum_user = findViewById(R.id.phonenum_spinner);
        spinner_phonenum_child = findViewById(R.id.childPhonenum_spinner);
        Userphone2 = findViewById(R.id.phone2);
        Userphone3 = findViewById(R.id.phone3);
<<<<<<< HEAD
        SignupOk = findViewById(R.id.signupOK);
=======
        Childphone2 = findViewById(R.id.childphone2);
        Childphone3 = findViewById(R.id.childphone3);
        signupBtnOK = findViewById(R.id.signup_ok);

>>>>>>> 062b48c9f2bf0a3bad333aae7dff927c765579df
        final ArrayList<Integer> age = new ArrayList<>();
        final ArrayList<String> phone = new ArrayList<>();

        RadioGroup genderRadio = findViewById(R.id.rg_gender);

        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender_str = ((RadioButton) findViewById(checkedId)).getText().toString();
                if (gender_str.equals("여성")) {gender = 1;}
                else {gender = 0;}
            }
        });
        for (int i = 1; i < 100; i++) { age.add(i); }
        phone.add("010");
        phone.add("011");
        phone.add("016");
        phone.add("017");
        phone.add("019");


        phoneSpinnerAdapter_user = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, phone);
        spinner_phonenum_user.setAdapter(phoneSpinnerAdapter_user);
        spinner_phonenum_child.setAdapter(phoneSpinnerAdapter_user);

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
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinner_phonenum_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChildPhoneNum = spinner_phonenum_child.getItemAtPosition(position).toString();
            }

<<<<<<< HEAD
           }
       });
        SignupOk.setOnClickListener(bntListener);
=======
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});


        signupBtnOK.setOnClickListener(bntListener);

>>>>>>> 062b48c9f2bf0a3bad333aae7dff927c765579df
    }
    View.OnClickListener bntListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            UserPhoneNum += Userphone2.getText().toString();
            UserPhoneNum += Userphone3.getText().toString();
<<<<<<< HEAD
            Toast.makeText(getApplicationContext(), "sfsfsfsfsfsf", Toast.LENGTH_LONG).show();
            myRef = FirebaseDatabase.getInstance().getReference("User");
            myRef.push().setValue("ddd");
            postFirebaseDatabase(true);
            //database.getReference().child("user").setValue(userDTO);
            //myRef.push().setValue(userDTO);

=======

            ChildPhoneNum += Childphone2.getText().toString();
            ChildPhoneNum += Childphone3.getText().toString();
            Log.e("User : ",UserPhoneNum);
            Log.e("Child : " , ChildPhoneNum);

            UserDTO userDTO = new UserDTO(name.getText().toString(),idtxt.getText().toString(),pwdtxt2.getText().toString(),ChildPhoneNum,UserPhoneNum,gender,age_result, 2);
            database.getReference().child("user").setValue(userDTO);
            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다! 환영합니다 " + name.getText().toString() + "님.", Toast.LENGTH_SHORT).show();
            finish();
>>>>>>> 062b48c9f2bf0a3bad333aae7dff927c765579df
        }
    };
    public void postFirebaseDatabase(boolean add){
        try{
            myRef = FirebaseDatabase.getInstance().getReference();
            Map<String, Object> childUpdates = new HashMap<>();
            Map<String, Object> postValues = null;
            if(add){
                //UserDTO userDTO = new UserDTO(name.getText().toString(),idtxt.getText().toString(),pwdtxt2.getText().toString()," ",UserPhoneNum,gender,age_result, 2);
                UserDTO userDTO = new UserDTO("a","a","a","1","1",1,1,1);
                postValues = userDTO.toMap();
            }
            childUpdates.put("/User/" + idtxt.getText().toString(), postValues);
            myRef.updateChildren(childUpdates);
        }catch (Exception e){
            e.fillInStackTrace();
        }

    }

}