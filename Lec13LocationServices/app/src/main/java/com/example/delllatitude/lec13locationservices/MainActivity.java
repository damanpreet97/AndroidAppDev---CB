package com.example.delllatitude.lec13locationservices;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView tv1, tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    LocationManager locationManager;
    public static final String TAG = "Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1= findViewById(R.id.tvLat);
        tv2= findViewById(R.id.tvLong);
        tv3= findViewById(R.id.tvProvider);
        tv4= findViewById(R.id.tvTime);
        tv5= findViewById(R.id.tvSpeed);
        tv6= findViewById(R.id.tvAccuracy);
        tv7= findViewById(R.id.tvAltitude);
        tv8= findViewById(R.id.tvBearing);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE},1234 );}
            else {
            addLocationListener();
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Thanks for the Permission", Toast.LENGTH_SHORT).show();
            addLocationListener();
        } else {
            Toast.makeText(this, "I need the Permission to work", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
            tv1.setText("Latitude: "+ location.getLatitude());
            tv2.setText(location.getLongitude()+"");
            tv3.setText(location.getProvider()+"");
            tv4.setText(location.getTime()+"");
            tv5.setText(location.getSpeed()+"");
            tv6.setText(location.getAccuracy()+"");
            tv7.setText(location.getAltitude()+"");
            tv8.setText(location.getBearing()+"");
        Log.e(TAG, "onLocationChanged: Hiiiiiiiiiiii...............");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @SuppressLint("MissingPermission")
    private void addLocationListener() {
        @SuppressLint("MissingPermission") Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    }
}