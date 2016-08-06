package br.com.dts.webservices;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import br.com.dts.webservices.app.App;
import br.com.dts.webservices.model.Place;
import br.com.dts.webservices.util.TraceRouteAsyncTask;

/**
 * Created by diegosouza on 8/3/16.
 */
public class TraceRouteActivity extends FragmentActivity {

    private GoogleMap mMap;

    private TraceRouteAsyncTask mTraceRouteAsyncTask;
    private final int DELAY_TIME = 10 * 1000; //20 seconds
    public static final int ERROR_MESSAGE = -1;

    private Place mPlace;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERROR_MESSAGE:
                    Toast.makeText(TraceRouteActivity.this,
                            "Erro ao traçar rota",
                            Toast.LENGTH_LONG).show();
                    finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_activity);

        mPlace = (Place) getIntent().getSerializableExtra(PlaceDetailActivity.EXTRA_PLACE_ROUTE);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();
        client = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.PLACE_DETECTION_API)
                //.addConnectionCallbacks(this)
              //  .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onResume() {
        setUpMapIfNeeded();
        traceRouteFromMyLocation();
        super.onResume();
    }


    private void notifyHandler() {
        if (mHandler != null) {
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (mTraceRouteAsyncTask != null && mTraceRouteAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
                        mTraceRouteAsyncTask.cancel(true);
                        Message message = new Message();
                        message.what = ERROR_MESSAGE;
                        mHandler.sendMessage(message);
                    }
                }
            }, DELAY_TIME);
        }
    }

    private void checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                setUpMapIfNeeded();
                traceRouteFromMyLocation();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PlaceDetailActivity.MY_PERMISSIONS_REQUEST_ACCESS_MAP);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PlaceDetailActivity.MY_PERMISSIONS_REQUEST_ACCESS_MAP: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    traceRouteFromMyLocation();
                }
                return;
            }
        }
    }

    private void traceRouteFromMyLocation() {

        try {
            Location location;

            location = LocationServices.FusedLocationApi.getLastLocation(
                    client);


            double destinationLocationLatitude = (Double.parseDouble(mPlace.getGeometry().getCoordinates()[1]));
            double destinationLocationLongitude = (Double.parseDouble(mPlace.getGeometry().getCoordinates()[0]));

            if (location != null) {
                mTraceRouteAsyncTask = new TraceRouteAsyncTask(this, mMap, mHandler);
                mTraceRouteAsyncTask.execute(location.getLatitude(),
                        location.getLongitude(),
                        destinationLocationLatitude,
                                destinationLocationLongitude);

                notifyHandler();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        destinationLocationLatitude,  destinationLocationLongitude), 15.0f));
            } else {
                //Localização não disponível. Usaremos uma ficticia

                mTraceRouteAsyncTask = new TraceRouteAsyncTask(this, mMap, mHandler);
                mTraceRouteAsyncTask.execute(-8.058299, //cesar lat
                        -34.871961, //cesar long
                        destinationLocationLatitude, // lat
                        destinationLocationLongitude); //long

                notifyHandler();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        destinationLocationLatitude,  destinationLocationLongitude), 15.0f));
            }
        } catch (SecurityException e) {
            checkPermission();
        }

    }



    private void setUpMapIfNeeded() {
        try{
            if (mMap == null) {
                mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map_route)).getMap();
                mMap.setMyLocationEnabled(true);
            }
        } catch (SecurityException e) {
            checkPermission();
            //TODO: Show message
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "TraceRoute Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://br.com.dts.webservices/http/host/path")
//        );
       /// AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "TraceRoute Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://br.com.dts.webservices/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }
}
