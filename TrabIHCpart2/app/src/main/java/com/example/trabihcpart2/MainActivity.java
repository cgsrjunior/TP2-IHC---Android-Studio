package com.example.trabihcpart2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor light, accel, gyro;
    TextView lightValue, xVal, yVal, zVal, xGyro, yGyro, zGyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accelerometer sensor values
        xVal = (TextView) findViewById(R.id.acel_x_axis);
        yVal = (TextView) findViewById(R.id.acel_y_axis);
        zVal = (TextView) findViewById(R.id.acel_z_axis);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_NORMAL);

        //Accelerometer sensor values
        xGyro = (TextView) findViewById(R.id.gyro_x_axis);
        yGyro = (TextView) findViewById(R.id.gyro_y_axis);
        zGyro = (TextView) findViewById(R.id.gyro_z_axis);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);

        //Light sensor values
        lightValue = (TextView)findViewById(R.id.light_text_view);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null)
        {
            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightValue.setText("Light sensor not supported");
        }

        //GPS Values
        Button getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "LONG: " +
                            longi, Toast.LENGTH_LONG).show();
                }
            }
        });

    };


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Light values
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            lightValue.setText("Light Intensity: " + sensorEvent.values[0]);
        }

        //Accelerometer values
        xVal.setText("X: " + sensorEvent.values[0]);
        yVal.setText("Y: " + sensorEvent.values[1]);
        zVal.setText("Z: " + sensorEvent.values[2]);

        //Gyroscope values
        xGyro.setText("X: " + sensorEvent.values[0]);
        yGyro.setText("Y: " + sensorEvent.values[1]);
        zGyro.setText("Z: " + sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}