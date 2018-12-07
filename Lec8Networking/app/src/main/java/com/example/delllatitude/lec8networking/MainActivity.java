package com.example.delllatitude.lec8networking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute(et.getText().toString());
            }
        });
    }

    class MyTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return makeNetworkCall(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView tv = findViewById(R.id.tv);
            tv.setText(s);
        }
    }

    public String makeNetworkCall(String currentURL){
        try {
            URL url = new URL(currentURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String s = "";

            //Using this(//A) delimiter reads the contet of the file in one go
            scanner.useDelimiter("\\A");
            if(scanner.hasNext()){
                s = scanner.next();
            }
            return s;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something Went Wrong! Try Again";
    }


}


