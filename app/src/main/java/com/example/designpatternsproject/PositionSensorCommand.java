package com.example.designpatternsproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Locale;

public class PositionSensorCommand implements Command {
    private final TextView textView;
    boolean isGPSEnabled, isNetworkEnabled;
    private String longitude, latitude;
    private final Activity activity;
    private final Context context;

    public PositionSensorCommand(TextView textView, Activity activity, Context context) {
        this.textView = textView;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void registerSensorListener() {
        if (!checkPermission())
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        LocationManager locationManager = LocationManagerSingleton.getInstance(context);

        // Get the GPS and network status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Register the LocationListener to receive location updates
        if (isGPSEnabled && !checkPermission()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, createLocationListener());
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(locationGPS != null)
                textView.setText("Latitude: " + String.format("%.2f", locationGPS.getLatitude()) + "        Longitude: " + String.format("%.2f", locationGPS.getLongitude()));
        }
        else if(isNetworkEnabled)
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        1000, 1, createLocationListener());
            else
                textView.setText(R.string.UnsupportedSensor);
    }

    public LocationListener createLocationListener() {
        return new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // Update the latitude and longitude
                longitude = String.format(Locale.getDefault(), "%.2f", location.getLongitude());
                latitude = String.format(Locale.getDefault(), "%.2f", location.getLatitude());
                textView.setText("Latitude: " + latitude + "        Longitude: " + longitude);
            }
        };
    }

    @Override
    public void unregisterSensorListener() { }

    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

}