package com.example.delllatitude.lec10hwpokedesk;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnGo;
    TextView tv;
    ImageView iv, btnPrev, btnNext;
    int id;
    ProgressDialog progress;
    FloatingActionButton fab;
    AlertDialog alertDialog;
    ApiResponse ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(this);
        et = findViewById(R.id.et);
        btnGo = findViewById(R.id.btnGo);
        fab = findViewById(R.id.fab);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StringBuilder url = new StringBuilder();
                url.append("http://pokeapi.co/api/v2/pokemon/");
                id = Integer.parseInt(et.getText().toString());
                String Id = et.getText().toString();
                makeConnectionCall(url.append(Id).toString());
            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StringBuilder url = new StringBuilder();
                url.append("http://pokeapi.co/api/v2/pokemon/");
//                int Id = Integer.parseInt(et.getText().toString());
                id= id +1;
                makeConnectionCall(url.append((id)).toString());
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StringBuilder url = new StringBuilder();
                url.append("http://pokeapi.co/api/v2/pokemon/");
//                int Id = Integer.parseInt(et.getText().toString());
                if(id>1) {
                    id = id - 1;
                    makeConnectionCall(url.append((id)).toString());
                }else{
                    Toast.makeText(MainActivity.this, "Minimum Pokemon Id is 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ar != null) {
                    View dialogView = LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_dialog, null, true);
                    TextView tv = dialogView.findViewById(R.id.tvDetailDialog);
                    ImageView iv = dialogView.findViewById(R.id.ivDeatilDialog);
                    dialogView.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                    Picasso.get().load(ar.getSprites().getFrontDefault()).noFade().into(iv);
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (i < ar.getForms().size()) {
                        sb.append(ar.getForms().get(i).getName() + "\n");
                        i++;
                    }
                    String form = sb.toString();
                    i = 0;
//                sb.delete(0, sb.length()-1);
                    sb = new StringBuilder();
                    while (i < ar.getAbilities().size()) {
                        sb.append(ar.getAbilities().get(i).getAbility().getName() + "\n\t\t\t\t\t\t");
                        i++;
                    }
                    String abilities = sb.toString();
                    i = 0;
                    sb = new StringBuilder();
//                while(i<ar.getStats().size()/2){
                    sb.append("Stat Name - " + ar.getStats().get(i).getStat().getName() + "\n\t\t\t\t\t" +
                            "Efforts - " + ar.getStats().get(i).getEffort() + "\n\t\t\t\t\t" +
                            "Base Stat - " + ar.getStats().get(i).getBaseStat());
//                }
                    String stats = sb.toString();
                    tv.setText("Form :  " + form + "\n" + "Abilities :  " + abilities + "\n" + "Stats :  " + stats);

                    alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Details").setView(dialogView).setCancelable(true).create();
                    alertDialog.show();
                } else{
                    Toast.makeText(MainActivity.this, "First Search a Pokemon To get its details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void makeConnectionCall(String url) {
        progress.setTitle("Loading");
//        progress.
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(MainActivity.this, "Check Network Connection and Try Again", Toast.LENGTH_SHORT);
                progress.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                progress.dismiss();
                String result = response.body().string();
                Gson gson = new Gson();
                ar = gson.fromJson(result, ApiResponse.class);
//                String ImageUrl = gson.fromJson(ar.getSprites().toString(), ApiResponse.class);
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Picaso
                        Picasso.get().load(ar.getSprites().getFrontDefault()).into((ImageView) findViewById(R.id.iv));
//                        Log.e("TAG", "run: "+ar.getFrontDefault());
                        tv = findViewById(R.id.tv);
                        tv.setText("Name: "+ ar.getName()+"\n\n Rank : " + ar.getRank()+"\n\n Weight: "+ar.getWeight()
                                +"\n\n Height: "+ ar.getHeight()+ "\n\nBase Experience: " + ar.getBaseExperience());
                    }
                });
            }
        });

    }
}
