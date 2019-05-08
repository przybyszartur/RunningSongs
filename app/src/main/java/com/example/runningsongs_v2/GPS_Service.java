package com.example.runningsongs_v2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GPS_Service extends Service {

    private LocationListener locationListener;
    private LocationManager locationManager;
    public GPS_Service_Delegate delegate = null;
    private static String Tag="GPS_Service";
    static Double lat1 = null;
    static Double lon1 = null;
    static Double lat2 = null;
    static Double lon2 = null;
    static Double distance = 0.0;
    static int status = 0;
    private long timeOfLastUpdate;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(Tag,"Service Created");


        getCurrentLocation();


    }
    public void getCurrentLocation() {

        locationManager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                Log.v("Debug", "in onLocation changed..");
                long timeElapsed = System.currentTimeMillis() - timeOfLastUpdate;
                if (status == 0) {
                    lat1 = location.getLatitude();
                    lon1 = location.getLongitude();
                } else if ((status % 2) != 0) {
                    lat2 = location.getLatitude();
                    lon2 = location.getLongitude();
                    distance += distanceBetweenTwoPoint(lat1, lon1, lat2, lon2);
                    Double speed = distance/timeElapsed;
                    Toast.makeText(getApplicationContext(), "Distance is: "
                            +distance +"\n "+ "Speed is: " + speed + "km/h", Toast.LENGTH_SHORT).show();

                } else if ((status % 2) == 0) {
                    lat1 = location.getLatitude();
                    lon1 = location.getLongitude();
                    distance += distanceBetweenTwoPoint(lat2, lon2, lat1, lon1);
                    Double speed = distance/timeElapsed;
                    Toast.makeText(getApplicationContext(), "Distance is: "
                            +distance +"\n "+ "Speed is: " + speed + "km/h", Toast.LENGTH_SHORT).show();
                    if (delegate != null) {
                        LatLng nLocation = new LatLng(lat1, lon1);
                        delegate.newLocationReceived(nLocation);
                    }

                }
                status++;
                Intent i = new Intent("location_update");
                i.putExtra("coordinates", distance);
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date(location.getTime());
                String formatted = format.format(date);
                i.putExtra("coordinates1",timeElapsed);
                timeOfLastUpdate = System.currentTimeMillis();

                sendBroadcast(i);

            }


            double distanceBetweenTwoPoint(double srcLat, double srcLng, double desLat, double desLng) {
                double earthRadius = 3958.75;
                double dLat = Math.toRadians(desLat - srcLat);
                double dLng = Math.toRadians(desLng - srcLng);
                double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(srcLat))
                        * Math.cos(Math.toRadians(desLat)) * Math.sin(dLng / 2)
                        * Math.sin(dLng / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double dist = earthRadius * c;

                double meterConversion = 1609;

                return (int) (dist * meterConversion);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d(Tag, "onProviderEnabled: " + s);
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d(Tag, "onProviderDisabled: " + s);
            }
        };
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    2, // minimum time interval between updates
                    5, // minimum distance between updates, in metres
                    locationListener);
            Log.d(Tag,"Successful");
        } catch (SecurityException e) {
            Log.d(Tag, e.toString());
        }
    }
    //locationManager =
//                (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        LocationListener locationListener = new LocationListener() {
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null && locationListener != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(locationListener);
        }
        Log.d(Tag,"Destroyed Service");
    }
}
