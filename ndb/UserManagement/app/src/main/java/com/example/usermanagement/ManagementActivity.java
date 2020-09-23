package com.example.usermanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<>();
        adapter = new UserListAdapter(getApplicationContext(), userList, this);
        listView.setAdapter(adapter);

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, userPW, userName, userAge;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPW = object.getString("userPW");
                userName = object.getString("userName");
                userAge = object.getString("userAge");
                User user = new User(userID, userPW, userName, userAge);
                if (!userID.equals("admin"))
                    userList.add(user);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
