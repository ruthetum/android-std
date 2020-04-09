package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;
    TextView textView2;
    SQLiteDatabase database;

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
                String databaseName = editText.getText().toString();
                // DB 열기
                openDatabase(databaseName);
            }
        });

        editText2 = (EditText) findViewById(R.id.editText2);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });
    }

    public void openDatabase(String databaseName) {
        println("openDatabase() 호출됨");

        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if (database != null) {
            println("데이터베이스 호출됨");
        }
    }

    public void createTable(String tableName) {
        println("createTable() 호출됨");

        if (database != null) {
            String sql = "create table " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            database.execSQL(sql);

            println("테이블 생성됨");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }


}
