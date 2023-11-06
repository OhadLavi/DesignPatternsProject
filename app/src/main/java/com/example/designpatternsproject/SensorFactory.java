package com.example.designpatternsproject;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.widget.TextView;

public class SensorFactory {
    private Activity activity;
    private Context context;

    public SensorFactory(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public Command createSensorCommand(SensorType type, SensorManager sensorManager, TextView textView) {
        switch (type) {
            case Light:
                return new LightSensorCommand(sensorManager, textView);
            case Compass:
                return new CompassSensorCommand(sensorManager, textView);
            case Proximity:
                return new ProximitySensorCommand(sensorManager, textView);
            case Pressure:
                return new PressureSensorCommand(sensorManager, textView);
            case Position:
                return new PositionSensorCommand(textView, activity, context);
            default:
                return null;
        }
    }
}