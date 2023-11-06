package com.example.designpatternsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private ArrayList<Command> sensorCommands;
    private TextView textCompass, textLIGHT, textPressure, textProximity, textPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // Get reference to the sensor manager
        sensorCommands = new ArrayList<>();

        setContentView(R.layout.activity_main);
        setTextViews();
        setSensors();
        registerSensorsListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensorsListeners(); // Register the listeners to start receiving updates
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterSensorsListeners(); // Unregister the listeners to stop receiving updates
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit)
            finishAndRemoveTask();
        else
            return super.onOptionsItemSelected(item);
        return true;
    }

    // Get references to the TextViews
    private void setTextViews() {
        textCompass = (TextView) findViewById(R.id.textCompass);
        textLIGHT = (TextView) findViewById(R.id.textLIGHT);
        textPressure = (TextView) findViewById(R.id.textPressure);
        textProximity = (TextView) findViewById(R.id.textProximity);
        textPosition = (TextView) findViewById(R.id.textPosition);
    }

    public void setSensors() {
        SensorFactory sensorFactory = new SensorFactory(this, getApplicationContext());
        sensorCommands.add(sensorFactory.createSensorCommand(SensorType.Compass, sensorManager, textCompass));
        sensorCommands.add(sensorFactory.createSensorCommand(SensorType.Light, sensorManager, textLIGHT));
        sensorCommands.add(sensorFactory.createSensorCommand(SensorType.Pressure, sensorManager, textPressure));
        sensorCommands.add(sensorFactory.createSensorCommand(SensorType.Proximity, sensorManager, textProximity));
        sensorCommands.add(sensorFactory.createSensorCommand(SensorType.Position, sensorManager, textPosition));
    }
    public void registerSensorsListeners() {
        for (Command sensorCommand : sensorCommands)
            sensorCommand.registerSensorListener();
    }

    public void unregisterSensorsListeners() {
        for (Command sensorCommand : sensorCommands)
            sensorCommand.unregisterSensorListener();
    }
}