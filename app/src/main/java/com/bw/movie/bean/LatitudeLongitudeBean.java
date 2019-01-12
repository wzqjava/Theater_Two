package com.bw.movie.bean;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:
 */
public class LatitudeLongitudeBean {
    private double latitude;
    private double longitude;

    public LatitudeLongitudeBean(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
