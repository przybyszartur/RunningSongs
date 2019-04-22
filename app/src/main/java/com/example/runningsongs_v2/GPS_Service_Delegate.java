package com.example.runningsongs_v2;

import com.google.android.gms.maps.model.LatLng;

public interface GPS_Service_Delegate {
    void newLocationReceived(LatLng location);
}
