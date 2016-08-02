package br.com.dts.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.dts.webservices.model.Place;

public class PlaceDetailActivity extends AppCompatActivity {

    private TextView mTextName;
    private TextView mTextDetail;
    private Button mBtnRoute;

    private Place mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        mTextName = (TextView) findViewById(R.id.txt_place_name);
        mTextDetail = (TextView) findViewById(R.id.txt_place_details);

        mBtnRoute = (Button) findViewById(R.id.btn_route);

        mBtnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mPlace = (Place) getIntent().getSerializableExtra(ListPlacesActivity.EXTRA_PLACE);

        if (mPlace != null){
            mTextName.setText(mPlace.getProperties().getPTurist());
            mTextDetail.setText(mPlace.getProperties().getDescritv());
        }
    }
}
