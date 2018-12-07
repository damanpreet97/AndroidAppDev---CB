package com.example.delllatitude.lec19workmanager;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;

public class MyWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {
        //This runs on a separate thread.
        // so run all long running operations here

        for(int i=0;i<1000;i++){
            Log.e("TAG", "doWork: "+ i);
        }

        return Result.SUCCESS;
    }
}
