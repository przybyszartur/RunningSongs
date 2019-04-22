package com.example.runningsongs_v2;

import java.io.Serializable;

public final class Song implements Serializable {
    public String title;
    public String artist;
    public String album;

    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }
}
