package com.example.delllatitude.lec6hwspaceapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    public void startFragmentDetail(FragmentDetail fragmentDetail) {
        if(findViewById(R.id.containerDetailLand) == null){
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("Data", fragmentDetail.getArguments().getParcelable("Data"));
            startActivity(intent);
        } else{
            getSupportFragmentManager().beginTransaction().replace(R.id.containerDetailLand, fragmentDetail).commit();
        }
    }

    @Override
    public void handleOnClick(FragmentDetail fragmentDetail) {

    }

//        getSupportFragmentManager().beginTransaction().add()
    }

//    @Override
//    public void handleOnClick(Student student) {
//        FrameLayout containerB = findViewById(R.id.containerB);
//        if (containerB == null){
//            //Portrait, start the intent
//        }
//        else{
//            //Landscape, start the fragment
//        }
//    }

