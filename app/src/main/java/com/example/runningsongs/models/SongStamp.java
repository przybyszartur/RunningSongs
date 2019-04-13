package com.example.runningsongs.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class SongStamp implements Serializable {

    public Song song;
    public Date date;

    public SongStamp(Song song, Date date) {
        this.song = song;
        this.date = date;
    }

    public static SongStamp[] getFakeSongs() {
        Calendar cal = Calendar.getInstance();
        SongStamp[] arr = {
                new SongStamp(new Song("Someone like you", "Adele", "Sradele"), cal.getTime()),
                new SongStamp(new Song("Beat It", "Michael Jackson", "C"), cal.getTime()),
                new SongStamp(new Song("Smooth Criminal", "Michael Jackson", "C"), cal.getTime()),
                new SongStamp(new Song("My First Time", "Asa Akira", "C"), cal.getTime()),
                new SongStamp(new Song("Song 2", "Blur", "C"), cal.getTime())
        };
        return arr;
    }

}
