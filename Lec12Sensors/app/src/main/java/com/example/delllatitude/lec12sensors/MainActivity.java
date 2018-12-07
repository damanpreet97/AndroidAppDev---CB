package com.example.delllatitude.lec12sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tvx, tvY, tvZ;
    LinearLayout linearLayout;

    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvx = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);
        linearLayout = findViewById(R.id.lLayout);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_UI);

//        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//
//        Log.e(TAG, "onCreate: Total Count" + sensors.size());
//
//        for(Sensor s: sensors){
//            Log.e(TAG, "onCreate: --------------------");
//            Log.e(TAG, "onCreate: "+ s.toString());
//            Log.e(TAG, "onCreate: --------------------");
        }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        tvx.setText(""+values[0]);
        tvY.setText(""+values[1]);
        tvZ.setText(""+values[1]);

//        Color color = Color.RGBToHSV();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

