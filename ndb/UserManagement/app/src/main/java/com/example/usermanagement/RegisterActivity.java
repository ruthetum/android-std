package com.example.usermanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String URL = "http://10.0.2.2:3000/register";

    private EditText idText;
    private EditText pwText;
    private EditText pwCheckText;
    private EditText nameText;
    private EditText ageText;
    private Button registerBtn;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idText = (EditText) findViewById(R.id.idText);
        pwText = (EditText) findViewById(R.id.pwText);
        pwCheckText = (EditText) findViewById(R.id.pwCheckText);
        nameText = (EditText) findViewById(R.id.nameText);
        ageText = (EditText) findViewById(R.id.ageText);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        queue = Volley.newRequestQueue(RegisterActivity.this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPW = pwText.getText().toString();
                String userPWCheck = pwCheckText.getText().toString();
                String userName = nameText.getText().toString();
                String userAge = ageText.getText().toString();

                if (!userPW.equals(userPWCheck)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("비밀번호가 일치하지 않습니다.")
                            .setNegativeButton("다시 시도", null)
                            .create()
                            .show();
                } else {
                    requestRegister(userID, userPW, userName, userAge);
                }
            }
        });
    }

    public void requestRegister(String userID, String userPW, String userName, String userAge) {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("userID", userID);
            userJson.put("userPW", userPW);
            userJson.put("userName", userName);
            userJson.put("userAge", userAge);
            String jsonString = userJson.toString();

            final RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, userJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String success = response.getString("success");
                        System.out.println(success);
                        if(success.equals("OK")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("회원 등록에 성공했습니다.")
                                    .setPositiveButton("확인", null)
                                    .create()
                                    .show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("회원 등록에 실패했습니다.")
                                    .setNegativeButton("다시 시도", null)
                                    .create()
                                    .show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
