package com.example.designpatternsproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class ProximitySensorCommand implements Command {
    private final SensorManager sensorManager;
    private final Sensor ProximitySensor;
    private final TextView textView;
    public ProximitySensorCommand(SensorManager sensorManager, TextView textView) {
        this.sensorManager = sensorManager;
        this.ProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        this.textView = textView;
    }
    @Override
    public void registerSensorListener() {
        if (ProximitySensor == null)
            textView.setText(R.string.UnsupportedSensor);
        else
            sensorManager.registerListener(createSensorEventListener(), ProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        sensorManager.unregisterListener(createSensorEventListener(), ProximitySensor);
    }
}