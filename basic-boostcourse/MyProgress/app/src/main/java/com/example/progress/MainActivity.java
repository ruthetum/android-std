package com.example.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 쓰레드 일정 지연 시키고 실행하기
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        ProgressThread thread = new ProgressThread();
//                        thread.start();
//                    }
//                }, 5000);
                ProgressTask task = new ProgressTask();
                task.execute("시작");
            }
        });
    }

    class ProgressTask extends AsyncTask<String, Integer, Integer> {
        int value = 0;
        @Override
        protected Integer doInBackground(String... strings) {
            // 쓰레드 안에 넣을 코드 입력
            while(true){
                if (value>100){
                    break;
                }
                value++;

                // UI 업데이트를 위해 onprogressUpdate로 값 전달
                publishProgress(value);

                try{
                    Thread.sleep(200);
                } catch(Exception e) {}
            }
            return value;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(), "완료됨.", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0].intValue());
        }
    }

    class ProgressThread extends Thread{
        int value = 0;

        public void run(){
            while(true){
                if (value>100){
                    break;
                }
                value++;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                    }
                });

                try{
                    Thread.sleep(200);
                } catch(Exception e) {}
            }
        }
    }
}
