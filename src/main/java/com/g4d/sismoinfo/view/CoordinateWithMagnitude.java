package com.g4d.sismoinfo.view;

public class CoordinateWithMagnitude {
    private final double latitude;
    private final double longitude;
    private final double magnitude;

    public CoordinateWithMagnitude(double latitude, double longitude, double magnitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }
}
