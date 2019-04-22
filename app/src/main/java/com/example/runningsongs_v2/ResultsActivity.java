package com.example.runningsongs_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        RunnerTracker tracker = getTracker();
        if (tracker == null) {
            Log.d("CO JEST GRANE", "Tracker jest nullem");
            return;
        }
        setActivityDetails(tracker);
        setLocations(tracker);
        setSongs(tracker);
    }

    private RunnerTracker getTracker() {
        Bundle bundle = getIntent().getExtras();
        RunnerTracker nTracker = (RunnerTracker)bundle.getSerializable("tracker");
        return nTracker;
    }

    private void setActivityDetails(RunnerTracker tracker) {
        Toast.makeText(this, tracker.getRunnerTrackerDate(), Toast.LENGTH_SHORT).show();
        // Ustawienie danych (czas, dystans itd)
    }

    private void setLocations(RunnerTracker tracker) {
        // Ustawienie i połączenie markerów na mapie
    }

    private void setSongs(RunnerTracker tracker) {
        // Ustawienie markerów z piosenkami na mapie kurwa
    }

}
