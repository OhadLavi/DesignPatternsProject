package com.example.designpatternsproject;

import android.content.Context;
import android.location.LocationManager;

public class LocationManagerSingleton {
    private static LocationManager locationManager;
    private LocationManagerSingleton() {}
    public static LocationManager getInstance(Context context) {
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        return locationManager;
    }
}