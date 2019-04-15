package com.example.runningsongs;



import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.runningsongs.models.Song;
import com.example.runningsongs.models.SongListener;
import com.example.runningsongs.models.SongListenerDelegate;
import com.example.runningsongs.models.SongStamp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.support.constraint.Constraints.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, SongListenerDelegate {

    private static final int REQ_PERMISSION = 1001;
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    SongListener listener;

    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;

    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gpsTracker = new GPSTracker(getActivity());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        listener = new SongListener(getActivity());
        listener.delegate = this;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_history, container, false);
        //Button b = (Button) mView.findViewById(R.id.button);
        // b.setOnClickListener(this);

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
        if(checkPermission()) {
            googleMap.setMyLocationEnabled(true);




            ///double lat = googleMap.getMyLocation().getLatitude();
            //double longt = googleMap.getMyLocation().getLongitude();
            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude ,longitude)).title("AKUKU"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude ,longitude), 15.5f), 4000, null);
            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng, 18),5000,null);
            //googleMap.animateCamera(CameraUpdateFactory.zoomBy(13));

        }
        else askPermission();
    }




    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }
    // Asks for permission
    private void askPermission() {
        Log.d(TAG, "askPermission()");
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                REQ_PERMISSION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch ( requestCode ) {
            case REQ_PERMISSION: {
                if ( grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    // Permission granted
                    if(checkPermission())
                        mGoogleMap.setMyLocationEnabled(true);

                } else {
                    // Permission denied

                }
                break;
            }
        }
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
