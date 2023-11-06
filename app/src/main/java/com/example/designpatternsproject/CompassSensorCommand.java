package com.example.designpatternsproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class CompassSensorCommand implements Command {
    private final SensorManager sensorManager;
    private final Sensor compassSensor;
    private final TextView textView;

    public CompassSensorCommand(SensorManager sensorManager, TextView textView) {
        this.sensorManager = sensorManager;
        this.compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        this.textView = textView;
    }
    @Override
    public void registerSensorListener() {
        if (compassSensor == null)
            textView.setText(R.string.UnsupportedSensor);
        else
            sensorManager.registerListener(createSensorEventListener(), compassSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public SensorEventListener createSensorEventListener() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                textView.setText(String.format("%.2f", event.values[0]).concat("\u00B0"));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };
    }

    @Override
    public void unregisterSensorListener() {
        sensorManager.unregisterListener(createSensorEventListener(), compassSensor);
    }
}