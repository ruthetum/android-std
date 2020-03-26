package com.example.buttonxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonListener1(View v) {
        Toast.makeText(getApplicationContext(), "Seoul", Toast.LENGTH_LONG).show();
    }

    public void buttonListener2(View v) {
        Toast.makeText(getApplicationContext(), "London", Toast.LENGTH_LONG).show();
    }
}
