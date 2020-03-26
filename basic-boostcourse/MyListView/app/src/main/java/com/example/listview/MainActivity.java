package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        ListView listView = (ListView) findViewById(R.id.ListView);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("오반", "010-1234-5678", R.drawable.ovan));
        adapter.addItem(new SingerItem("볼빨간 사춘기", "010-1000-2000", R.drawable.bol4));
        adapter.addItem(new SingerItem("창모", "010-3000-4000", R.drawable.changmo));
        adapter.addItem(new SingerItem("가호", "010-5000-6000", R.drawable.gaho));
        adapter.addItem(new SingerItem("백예린", "010-7000-8000", R.drawable.beak));
        adapter.addItem(new SingerItem("김희동", "010-4242-5697", R.drawable.pic1));
        adapter.addItem(new SingerItem("빌리 아일리쉬", "010-0001-0002", R.drawable.bilie));
        adapter.addItem(new SingerItem("애쉬 아일랜드", "010-0003-0004", R.drawable.ash));
        adapter.addItem(new SingerItem("기리보이", "010-0005-0006", R.drawable.giri));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : "+ item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(name, mobile, R.mipmap.ic_launcher));
                adapter.notifyDataSetChanged();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = null;

            // Recycling
            if (convertView == null) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }
}
