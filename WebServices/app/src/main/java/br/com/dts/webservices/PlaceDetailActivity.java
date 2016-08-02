package br.com.dts.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import br.com.dts.webservices.model.Place;

public class PlaceDetailActivity extends AppCompatActivity {

    private TextView mTextName;
    private TextView mTextDetail;
    private Button mBtnRoute;
    private MapView mMapView;

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

        mBtnRoute = (Button) findViewById(R.id.btn_route);

        mBtnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if (mPlace != null){
            mTextName.setText(mPlace.getProperties().getPTurist());
            mTextDetail.setText(mPlace.getProperties().getDescritv());
        }
    }

    private void setupMap(){
        mMapView = (MapView) findViewById(R.id.mapview);

        mMap = mMapView.getMap();

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);

        double latitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[0]);
        double longitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[1]);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10);
        mMap.animateCamera(cameraUpdate);
    }
}
