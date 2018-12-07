package com.example.delllatitude.lec13maps;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener,
        LocationListener{

    private int count = 0;
    private GoogleMap mMap;
    private LatLng initialPos, finalPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        attachLocationListener();
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.maps_style));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true));
//        initialPos = sydney;
//        mMap.setOnMarkerDragListener(this);
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,10.0f));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        count++;
        mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title("Move "+count));
//        initialPos = marker.getPosition();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(""));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 10.0f));
        finalPos = marker.getPosition();

        mMap.addPolyline(new PolylineOptions().add(initialPos, finalPos));
        initialPos = marker.getPosition();
    }
    @SuppressLint("MissingPermission")
    private void attachLocationListener() {
        //Get access to the LocationManager object
        //Request location updates from the GPS provider for every 10 seconds
        // and 25 meters of distance travelled

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000,
                25,
                this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(initialPos == null){
            count++;
            initialPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(initialPos).title("Position "+ count));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialPos, 10.0f));
        }else{
            count++;
            finalPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(initialPos).title("Position "+ count));
            mMap.addPolyline(new PolylineOptions().add(initialPos, finalPos));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(finalPos, 10.0f));
            initialPos = finalPos;
        }

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
}
