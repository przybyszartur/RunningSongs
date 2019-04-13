package com.example.runningsongs.models;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class SongStamp implements Serializable {

    public Song song;
    public LatLng latLng;

    public SongStamp(Song song, LatLng latLng) {
        this.song = song;
        this.latLng = latLng;
    }

    public static SongStamp[] getFakeSongs() {
        Calendar cal = Calendar.getInstance();
        SongStamp[] arr = {
                new SongStamp(new Song("Someone like you", "Adele", "Sradele"), null),
                new SongStamp(new Song("Beat It", "Michael Jackson", "C"), null),
                new SongStamp(new Song("Smooth Criminal", "Michael Jackson", "C"), null),
                new SongStamp(new Song("My First Time", "Asa Akira", "C"), null),
                new SongStamp(new Song("Song 2", "Blur", "C"), null)
        };
        return arr;
    }

}
