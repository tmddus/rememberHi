package com.example.sy.a2018rememberhi.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sy.a2018rememberhi.R;
import com.google.firebase.database.FirebaseDatabase;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage("아직 공사중입니다 ~! ^-^");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 아이콘
        // 다이얼로그 타이틀
        alert.setTitle("공사중!");
        // 다이얼로그 보기
        alert.show();

    }
}
