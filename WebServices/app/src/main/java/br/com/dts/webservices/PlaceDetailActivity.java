package br.com.dts.webservices;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.dts.webservices.model.Place;

/**
 * Created by diegosouza on 8/3/16.
 */

public class PlaceDetailActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_MAP = 1;
    private TextView mTextName;
    private TextView mTextDetail;

    private GoogleMap mMap;

    private Place mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        mPlace = (Place) getIntent().getSerializableExtra(ListPlacesActivity.EXTRA_PLACE);

        mTextName = (TextView) findViewById(R.id.txt_place_name);
        mTextDetail = (TextView) findViewById(R.id.txt_place_details);

        checkPermission();


        if (mPlace != null){
            mTextName.setText(mPlace.getProperties().getPTurist());
            mTextDetail.setText(mPlace.getProperties().getDescritv());
        }
    }

    private void setupMap(){



        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapview)).getMap();

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);

        double latitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[0]);
        double longitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[1]);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(mPlace.getProperties().getPTurist())
                .snippet(mPlace.getProperties().getPTurist())
                );

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_route:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestPermissions(){

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.MAPS_RECEIVE},
                MY_PERMISSIONS_REQUEST_ACCESS_MAP);



    }

    private void checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.MAPS_RECEIVE)
                    == PackageManager.PERMISSION_GRANTED) {
                setupMap();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_ACCESS_MAP);
            }
        } else {
            setupMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_MAP: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupMap();
                } else {

                }
                return;
            }
        }
    }
}
