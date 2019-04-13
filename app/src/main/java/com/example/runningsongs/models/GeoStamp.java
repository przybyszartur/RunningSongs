package com.example.runningsongs.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class GeoStamp {
    private LatLng latLng;
    private Date date;

    GeoStamp(LatLng latLng, Date date) {
        this.latLng = latLng;
        this.date = date;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Date getDate() {
        return date;
    }
}
