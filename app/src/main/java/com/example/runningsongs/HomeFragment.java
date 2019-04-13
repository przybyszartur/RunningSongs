package com.example.runningsongs;



import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.runningsongs.models.Song;
import com.example.runningsongs.models.SongListener;
import com.example.runningsongs.models.SongListenerDelegate;
import com.example.runningsongs.models.SongStamp;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;


public class HomeFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, SongListenerDelegate {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    SongListener listener;

    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = new SongListener(getActivity());
        listener.delegate = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_history, container, false);
        Button b = (Button) mView.findViewById(R.id.button);
        b.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        listener.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

    }

    private void addSongMarker(SongStamp songStamp) {
        Marker melbourne = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.7143528, -74.0059731))
                .title(songStamp.song.title)
                .snippet(songStamp.song.artist));
    }

    //////////////////////////////////////////////// Nsaluchiwa


    @Override
    public void onSongReceived(Song song) {
        // Zapisac w liscie aktywnosci
        Toast.makeText(getContext(), song.title, Toast.LENGTH_SHORT).show();

        SongStamp stamp = new SongStamp(song, new LatLng(40.7143528, -74.0059731));
        addSongMarker(stamp);
    }
}
