package com.example.acclero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor accelerometer,gravity;
    private TextView textView;
    private float xAcceleration,yAcceleration,zAcceleration,Gravity;
    // constants
    private final float LEG_THRSHOLD_AMPLITUDE = (float) 5.0;
    private static final float NOISE = (float) 2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.sensorData);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAcceleration = event.values[0];
            yAcceleration = event.values[1];
            zAcceleration = event.values[2];
            textView.setText("x:"+xAcceleration+"\nY:"+yAcceleration+"\nZ:"+zAcceleration);
            Gravity = Math.abs((xAcceleration* xAcceleration + yAcceleration * yAcceleration + zAcceleration * zAcceleration) / (manager.GRAVITY_EARTH * manager.GRAVITY_EARTH));

//            if (Gravity > StepUtil.GRAVITY_THRESHOLD) {
//
//                stepCounter++;
//            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}