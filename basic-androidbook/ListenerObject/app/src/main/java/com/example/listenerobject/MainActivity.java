package com.example.listenerobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이벤트 리스터 객체 생성
        MyListener mL = new MyListener();

        b1 = (Button) findViewById(R.id.b1);
        // 위젯에 이벤트 리스너 객체 등록하기
        b1.setOnClickListener(mL);
    }

    // OnClickListener 인터페이스 구현한 클래스
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "리스너 인터페이스 구현하여 이벤트 처리", Toast.LENGTH_SHORT).show();
        }
    }
}
