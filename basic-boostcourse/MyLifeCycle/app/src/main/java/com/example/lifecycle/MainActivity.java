package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onCreate 시점 확인
        Toast.makeText(this, "onCreate() 호출됨", Toast.LENGTH_LONG).show();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // onStart 시점 확인
        Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // onStop 시점 확인
        Toast.makeText(this, "onStop() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // onDestroy 시점 확인
        Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // onPause 시점 확인
        Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onResume 시점 확인
        Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_LONG).show();
    }
}
