package com.example.designpatternsproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class PressureSensorCommand implements Command {
    private final SensorManager sensorManager;
    private final Sensor pressureSensor;
    private final TextView textView;
    public PressureSensorCommand(SensorManager sensorManager, TextView textView) {
        this.sensorManager = sensorManager;
        this.pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        this.textView = textView;


    }
    @Override
    public void registerSensorListener() {
        if (pressureSensor == null)
            textView.setText(R.string.UnsupportedSensor);
        else
            sensorManager.registerListener(createSensorEventListener(), pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public SensorEventListener createSensorEventListener() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText(String.valueOf(event.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };
    }

    @Override
    public void unregisterSensorListener() {
        sensorManager.unregisterListener(createSensorEventListener(), pressureSensor);
    }
    // other methods
}