package com.example.ex3ihc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    Sensor accel;
    double x,y,z;
    float acelVal, acelLast = 0;
    float shake;

    EditText xVal, yVal, zVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xVal = (EditText) findViewById(R.id.x_axis);
        yVal = (EditText) findViewById(R.id.y_axis);
        zVal = (EditText) findViewById(R.id.z_axis);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Register accelerometer listener");

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged - X:  " + sensorEvent.values[0] + "Y:  " + sensorEvent.values[1] + "Z:  " + sensorEvent.values[2]);

        xVal.setText("X: " + sensorEvent.values[0]);
        yVal.setText("Y: " + sensorEvent.values[1]);
        zVal.setText("Z: " + sensorEvent.values[2]);

        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];

        acelLast=acelVal;
        acelVal=(float) Math.sqrt((double) (x*x)+(y*y)+(z*z));
        float delta= acelVal-acelLast;
        shake =shake*0.9f+delta;

        if(shake>8){
            // Create the Intent object of this class Context() to Second_activity class
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            // start the Intent
            startActivity(intent);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}