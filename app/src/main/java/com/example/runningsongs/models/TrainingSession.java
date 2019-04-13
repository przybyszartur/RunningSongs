package com.example.runningsongs.models;

import java.util.ArrayList;
import java.util.List;

public class TrainingSession {

    private int identifier;

    // Sekundy
    private int duration;

    // Kalorie
    private int calories;

    // List punktow w ktorych zapisujemy lokalizacje
    private List<GeoStamp> geoStampsList;

    // Lista piosenek w czasie
    private List<SongStamp> songStampList;

    public TrainingSession(int identifier) {
        this.identifier = identifier;
        geoStampsList = new ArrayList<>();
        songStampList = new ArrayList<>();
    }

    public void addGeoStamp(GeoStamp stamp) {
        geoStampsList.add(stamp);
    }

    public List<GeoStamp> getGeoStamps() {
        return geoStampsList;
    }

    public void addSongStamp(SongStamp stamp) {
        songStampList.add(stamp);
    }

    public List<SongStamp> getSongStampList() {
        return songStampList;
    }

}
