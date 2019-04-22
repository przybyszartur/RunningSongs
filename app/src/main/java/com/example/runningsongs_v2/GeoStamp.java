package com.example.runningsongs_v2;

import com.google.android.gms.maps.model.LatLng;

public final class GeoStamp {

    private Integer _id;
    private LatLng _latLng;

    public GeoStamp(int id, LatLng latLng) {
        _id = id;
        _latLng = latLng;
    }

    public GeoStamp(String expression) {
        // Example 1:lat###lng
        String[] parts = expression.split(":");
        _id = Integer.parseInt(parts[0]);

        String[] latLngParts = parts[1].split("###");
        Double latitude = Double.parseDouble(latLngParts[0]);
        Double longitude = Double.parseDouble(latLngParts[1]);
        _latLng = new LatLng(latitude, longitude);
    }

    public Integer getId() { return _id; }

    public LatLng getLatLng() { return _latLng; }

}
