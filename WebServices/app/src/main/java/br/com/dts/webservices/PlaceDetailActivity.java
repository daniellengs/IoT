package br.com.dts.webservices;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

public class PlaceDetailActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
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

        checkPermissions();


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

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MAPS_RECEIVE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.MAPS_RECEIVE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.MAPS_RECEIVE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
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
