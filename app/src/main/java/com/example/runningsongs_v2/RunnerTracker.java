package com.example.runningsongs_v2;


import java.io.Serializable;
import java.util.List;

public class RunnerTracker implements Serializable {

    private int _id;
    private double _distance;
    private String _Date;
    private String _time;
    private List<SongStamp> _songStamps;

    public RunnerTracker() {

    }

    public RunnerTracker(int id, double distance, String date,String time) {
        this._id = id;
        this._distance = distance;
        this._Date = date;
        this._time=time;
    }

    public RunnerTracker(double distance, String date,String time) {
        this._distance = distance;
        this._Date = date;
        this._time=time;
    }

    public RunnerTracker(double distance, String date,String time, List<SongStamp> songStamps) {
        this._distance = distance;
        this._Date = date;
        this._time=time;
        _songStamps = songStamps;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setRunnerTrackerDistance(int distance) {
        this._distance = distance;
    }

    public double getRunnerTrackerDistance() {
        return this._distance;
    }
    public void setRunnerTrackerTime(String time) {
        this._time= time;
    }

    public String getRunnerTrackerTime() {
        return this._time;
    }

    public void setRunnerTrackerDate(String date ) {
        this._Date = date;
    }

    public String getRunnerTrackerDate() {
        return this._Date;
    }

    public List<SongStamp> getSongStamps() { return this._songStamps; }
}
