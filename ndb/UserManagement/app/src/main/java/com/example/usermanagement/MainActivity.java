package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView pwText = (TextView) findViewById(R.id.pwText);
        TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPW = intent.getStringExtra("userPW");
        String msg = userID + "님 환영합니다!";

        welcomeMsg.setText(msg);
        idText.setText(userID);
        pwText.setText(userPW);
    }
}
