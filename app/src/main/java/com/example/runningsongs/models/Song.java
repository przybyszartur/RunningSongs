package com.example.runningsongs.models;

import android.media.MediaMetadata;

import java.io.Serializable;

public class Song implements Serializable {

    public String title;
    public String artist;
    public String album;

    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public Song(MediaMetadata metadata) {
        title = metadata.containsKey(MediaMetadata.METADATA_KEY_TITLE) ? metadata.getString(MediaMetadata.METADATA_KEY_TITLE) : "Unknown Title";
        artist = metadata.containsKey(MediaMetadata.METADATA_KEY_ARTIST) ? metadata.getString(MediaMetadata.METADATA_KEY_ARTIST) : "Unknown Artist";
        title = metadata.containsKey(MediaMetadata.METADATA_KEY_ALBUM) ? metadata.getString(MediaMetadata.METADATA_KEY_ALBUM) : "Unknown Album";
    }

}
