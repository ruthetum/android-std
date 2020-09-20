package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView pwText = (TextView) findViewById(R.id.pwText);
        TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        Button managementBtn = (Button) findViewById(R.id.managementBtn);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPW = intent.getStringExtra("userPW");
        String msg = userID + "님 환영합니다!";

        welcomeMsg.setText(msg);
        idText.setText(userID);
        pwText.setText(userPW);

        if (!userID.equals("admin")){
//            managementBtn.setEnabled(false);  // 버튼은 있는데 클릭이 안됨
            managementBtn.setVisibility(View.GONE); // 안 보이게 하고 싶으면 setVisibility로 설정
        }

        managementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://10.0.2.2:3000/userlist";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            MainActivity.this.startActivity(intent);
        }
    }
}
