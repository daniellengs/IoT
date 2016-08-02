package br.com.dts.webservices;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.dts.webservices.model.Place;

public class PlaceDetailActivity extends AppCompatActivity {

    private TextView mTextName;
    private TextView mTextDetail;
    private MapFragment mMapView;

    private GoogleMap mMap;

    private Place mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        mPlace = (Place) getIntent().getSerializableExtra(ListPlacesActivity.EXTRA_PLACE);

        mTextName = (TextView) findViewById(R.id.txt_place_name);
        mTextDetail = (TextView) findViewById(R.id.txt_place_details);

        setupMap();

        if (mPlace != null){
            mTextName.setText(mPlace.getProperties().getPTurist());
            mTextDetail.setText(mPlace.getProperties().getDescritv());
        }
    }

    private void setupMap(){
        //mMapView = (MapView) findViewById(R.id.mapview);

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

}
