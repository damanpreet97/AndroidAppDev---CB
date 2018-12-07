package com.example.delllatitude.lec6hwspaceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerDetailPot, FragmentDetail.newInstance((Data) getIntent().getParcelableExtra("Data"))).commit();
    }
}
