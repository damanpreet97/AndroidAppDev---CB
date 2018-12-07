package com.example.delllatitude.lec11broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction().toString();
        if (action .equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            Toast.makeText(context, "Hello! Airplane Mode changed", Toast.LENGTH_SHORT).show();
        }
        else if(action.equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Hello! Power Connected ", Toast.LENGTH_SHORT).show();
        }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Hello! Power Disconnected ", Toast.LENGTH_SHORT).show();
        }

    }
}
