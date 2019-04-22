package com.example.runningsongs_v2;

import com.google.android.gms.maps.model.LatLng;

public final class SongStamp {

    public Integer id;
    public Song song;
    public LatLng latLng;

    public SongStamp(Integer id, Song song, LatLng latLng) {
        this.id = id;
        this.song = song;
        this.latLng = latLng;
    }

    public SongStamp(String expression) {
        // Example 1:title-artist:12.9219-92.009;
        String[] parts = expression.split(":");

        String identifier = parts[0];
        id = Integer.parseInt(identifier);

        String[] songParts = parts[1].split("###");
        String title = songParts[0];
        String artist = songParts[1];
        song = new Song(title, artist, "album");

        String[] latLngParts = parts[2].split("###");
        String lat = latLngParts[0];
        String lng = latLngParts[1];
        Double dLat = Double.parseDouble(lat);
        Double dLng = Double.parseDouble(lng);
        latLng = new LatLng(dLat, dLng);
    }

}
