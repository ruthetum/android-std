package com.example.http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    Handler handler = new Handler();

    String urlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlStr = editText.getText().toString();

                RequestThread thread = new RequestThread();
                thread.start();
            }
        });
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data);
            }
        });
    }

    class RequestThread extends Thread {
        public void run(){
            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    // 10초 기다려서 응답없으면 끊음
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    // 이때 연결
                    int resCode = conn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line = null;

                        while(true){
                            line = reader.readLine();
                            if (line == null) {
                                break;
                            }
                            println(line);
                        }

                        reader.close();
                        conn.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
