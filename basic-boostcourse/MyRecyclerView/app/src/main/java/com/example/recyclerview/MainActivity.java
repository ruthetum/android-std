package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // 수평 방향
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SingerAdapter(getApplicationContext());

        adapter.addItem(new Singeritem("aaaa","01010100101"));
        adapter.addItem(new Singeritem("bbbb","01010343241"));
        adapter.addItem(new Singeritem("cccc","03432424101"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                Singeritem item = adapter.getItem(position);

                Toast.makeText(getApplicationContext(), "아이템 클릭됨 : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
