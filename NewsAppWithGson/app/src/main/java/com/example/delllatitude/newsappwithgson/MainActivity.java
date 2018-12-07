package com.example.delllatitude.newsappwithgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(llm);

        String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=854bd0fae50c44f4a000ea26fb7d2c38";
        makeNetworkCall(url);

    }

    private void makeNetworkCall(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final APIResponse apiResponse = gson.fromJson(result, APIResponse.class);
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<NewsData> newsDataArrayList = apiResponse.getNewsDataArrayList();
                        rv.setAdapter(new NewsDataAdapter(newsDataArrayList, MainActivity.this));
                    }
                });
            }
        });
    }
}
