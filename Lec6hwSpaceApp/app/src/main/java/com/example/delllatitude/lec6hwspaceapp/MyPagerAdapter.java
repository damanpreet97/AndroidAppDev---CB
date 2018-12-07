package com.example.delllatitude.lec6hwspaceapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Planets";
            case 1:
                return "Stars";
            case 2:
                return "Galaxies";
        }
        return "";
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return FragmentList.newInstance(Data.getPlanetArrayList());
            case 1:
                return FragmentList.newInstance(Data.getStarArrayList());
//                return new FragmentB();
            case 2:
                return FragmentList.newInstance(Data.getGalaxyArrayList());
//                return new FragmentC();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
