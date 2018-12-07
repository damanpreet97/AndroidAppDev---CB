package com.example.delllatitude.lec11custombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyBroadReciever extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        final PendingResult pendingResult = goAsync();
        User user = intent.getParcelableExtra("KEY");
        String postUrl = "http://ptsv2.com/t/1exrz-1530788670/post";
        OkHttpClient okHttpClient =new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String jsonData = gson.toJson(user);
        RequestBody requestBody = RequestBody.create(mediaType, jsonData);
        Request request = new Request.Builder().url(postUrl).post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                Toast.makeText(context, "Done Successfully", Toast.LENGTH_SHORT).show();
//               String result = response.body().string();
                pendingResult.finish();
            }
        });
    }
}
