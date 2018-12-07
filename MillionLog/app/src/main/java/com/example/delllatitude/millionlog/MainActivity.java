package com.example.delllatitude.millionlog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.Btn);
        tv = findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute(100000);
            }
        });
        Log.e("TAG", "onCreate: ");
    }

    private void startCount(int i){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100000;i++){
                    Log.e("Tag", "onClick: btn, count = "+i);
                }
                tv.setText("Counting Done");
            }
        });
        t.start();

    }

    class MyTask extends AsyncTask<Integer, String, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... integers) {
            Integer vaue = integers[0];

            for(Integer i=0;i<vaue;i++){
                Log.e("LOG", "doInBackground: count "+ i);
                if(i%100==0){
                    publishProgress(i.toString());
                }
            }

            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            ProgressBar pb = findViewById(R.id.pb);
            pb.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            TextView tv = findViewById(R.id.tv);
            tv.setText(values[0]);
        }
    }

}
