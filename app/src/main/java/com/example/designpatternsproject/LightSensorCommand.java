package com.example.designpatternsproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class LightSensorCommand implements Command {
    private final SensorManager sensorManager;
    private final Sensor lightSensor;
    private final TextView textView;
    public LightSensorCommand(SensorManager sensorManager, TextView textView) {
        this.sensorManager = sensorManager;
        this.lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.textView = textView;
    }
    @Override
    public void registerSensorListener() {
        if (lightSensor == null)
            textView.setText(R.string.UnsupportedSensor);
        else
            sensorManager.registerListener(createSensorEventListener(), lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        sensorManager.unregisterListener(createSensorEventListener(), lightSensor);
    }
}