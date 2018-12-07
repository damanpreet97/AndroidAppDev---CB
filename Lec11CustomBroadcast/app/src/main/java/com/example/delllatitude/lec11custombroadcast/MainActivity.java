package com.example.delllatitude.lec11custombroadcast;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText etId, etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =findViewById(R.id.btn);
        etId=findViewById(R.id.etId);
        etPass=findViewById(R.id.etPass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyBroadReciever.class);
                User user = new User(etId.getText().toString(), etPass.getText().toString());
                intent.putExtra("KEY", user);
                sendBroadcast(intent);
            }
        });
    }
}
