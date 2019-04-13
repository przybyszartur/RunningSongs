package com.example.runningsongs.models;

import java.io.Serializable;
import java.util.Date;

public class SongStamp implements Serializable {

    public Song song;
    public String timeStamp;

    public SongStamp(Song song, String timeStamp) {
        this.song = song;
        this.timeStamp = timeStamp;
    }

    public static SongStamp[] getFakeSongs() {
        SongStamp[] arr = {
                new SongStamp(new Song("Someone like you", "Adele", "Sradele"), "1990"),
                new SongStamp(new Song("Beat It", "Michael Jackson", "C"), "1991"),
                new SongStamp(new Song("Smooth Criminal", "Michael Jackson", "C"), "1992"),
                new SongStamp(new Song("My First Time", "Asa Akira", "C"), "1994"),
                new SongStamp(new Song("Song 2", "Blur", "C"), "1996")
        };
        return arr;
    }

}
